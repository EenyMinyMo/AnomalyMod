package ru.somber.anomaly.common.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class EntityBolt extends Entity implements IProjectile {
    private final float forceFactor;
    private boolean requiredReflect;


    public EntityBolt(World world, float forceFactor, EntityLivingBase shootingEntity) {
        super(world);

        this.forceFactor = forceFactor;
        this.renderDistanceWeight = 10;
        this.setSize(0.1F, 0.1F);

        this.setLocationAndAngles(shootingEntity.posX, shootingEntity.posY + shootingEntity.getEyeHeight(), shootingEntity.posZ,
                                  shootingEntity.rotationYaw, shootingEntity.rotationPitch);

        this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.2F;
        this.posY -= 0.2;
        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.2F;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;

        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
        this.motionY = -MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, forceFactor, 1.0F);
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void setPositionAndRotation2(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
        this.setPosition(p_70056_1_, p_70056_3_, p_70056_5_);
        this.setRotation(p_70056_7_, p_70056_8_);
    }

    @Override
    protected void entityInit() {

    }

    @Override
    public void setThrowableHeading(double xDirection, double yDirection, double zDirection, float force, float p_70186_8_) {
        float directionLength = MathHelper.sqrt_double(xDirection * xDirection + yDirection * yDirection + zDirection * zDirection);

        xDirection /= directionLength;
        yDirection /= directionLength;
        zDirection /= directionLength;

        xDirection *= force;
        yDirection *= force;
        zDirection *= force;

        this.motionX = xDirection;
        this.motionY = yDirection;
        this.motionZ = zDirection;

        float directionLengthXZ = MathHelper.sqrt_double(xDirection * xDirection + zDirection * zDirection);
        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(xDirection, zDirection) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(yDirection, directionLengthXZ) * 180.0D / Math.PI);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (ticksExisted > 100) {
            setDead();
        }

        this.rotationPitch++;
        this.rotationYaw++;

        //создаем вектора позиций и находим объект, который может колайднуться с болтом
        Vec3 currentPosition = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 nextPosition = Vec3.createVectorHelper(this.posX + this.motionX,
                                                    this.posY + this.motionY,
                                                    this.posZ + this.motionZ);
        MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(
                currentPosition, nextPosition, false, true, false);

//        if (movingobjectposition != null) {
//            nextPosition = Vec3.createVectorHelper(
//                    movingobjectposition.hitVec.xCoord,
//                    movingobjectposition.hitVec.yCoord,
//                    movingobjectposition.hitVec.zCoord);
//        }
//
//        Entity entity = null;
//        List<Entity> entityList = worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
//        double d0 = 0.0D;
//        for (Entity entity1 : entityList) {
//            if (entity1.canBeCollidedWith()) {
//                double expandFactor = 0.3F;
//                AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(expandFactor, expandFactor, expandFactor);
//                MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(currentPosition, nextPosition);
//
//                if (movingobjectposition1 != null) {
//                    double d1 = currentPosition.distanceTo(movingobjectposition1.hitVec);
//
//                    if (d1 < d0 || d0 == 0.0D) {
//                        entity = entity1;
//                        d0 = d1;
//                    }
//                }
//            }
//        }
//
//        if (entity != null) {
//            movingobjectposition = new MovingObjectPosition(entity);
//        }

        if (movingobjectposition != null) {
            switch (movingobjectposition.sideHit) {
                case 0 : {
                    this.motionY = -this.motionY;
                } break;

                case 1 : {
                    this.motionY = -this.motionY;
                } break;

                case 2 : {
                    this.motionZ = -this.motionZ;
                } break;

                case 3 : {
                    this.motionZ = -this.motionZ;
                } break;

                case 4 : {
                    this.motionX = -this.motionX;
                } break;

                case 5 : {
                    this.motionX = -this.motionX;
                } break;
            }

            this.motionX *= 0.5F;
            this.motionY *= 0.5F;
            this.motionZ *= 0.5F;
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;

        float motionFactor = 0.99F;

        if (this.isInWater()) {
            for (int l = 0; l < 4; ++l) {
                double f4 = 0.25F;
                this.worldObj.spawnParticle("bubble",
                                            this.posX - this.motionX * f4,
                                            this.posY - this.motionY * f4,
                                            this.posZ - this.motionZ * f4,
                                            this.motionX,
                                            this.motionY,
                                            this.motionZ);
            }

            motionFactor = 0.8F;
        }

        this.motionX *= motionFactor;
        this.motionY *= motionFactor;
        this.motionZ *= motionFactor;

        this.motionY -= 0.05F;

        this.setPosition(this.posX, this.posY, this.posZ);
        this.func_145775_I();
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        posX = tag.getFloat("x");
        posY = tag.getFloat("y");
        posZ = tag.getFloat("z");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        tag.setFloat("x", (float) posX);
        tag.setFloat("y", (float) posY);
        tag.setFloat("z", (float) posZ);
    }
}
