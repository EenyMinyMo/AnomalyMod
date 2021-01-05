package ru.somber.anomaly.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderEntityBolt extends Render {

    private static final ResourceLocation arrowTextures = new ResourceLocation("textures/entity/arrow.png");


    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return arrowTextures;
    }

    public void doRender(Entity entityForRender, double posX, double posY, double posZ, float p_76986_8_, float p_76986_9_) {
        this.bindEntityTexture(entityForRender);

//        GL11.glPushMatrix();
//        GL11.glTranslatef((float)posX, (float)posY, (float)posZ);
//        GL11.glRotatef(entityForRender.prevRotationYaw + (entityForRender.rotationYaw - entityForRender.prevRotationYaw) * p_76986_9_ - 90.0F, 0.0F, 1.0F, 0.0F);
//        GL11.glRotatef(entityForRender.prevRotationPitch + (entityForRender.rotationPitch - entityForRender.prevRotationPitch) * p_76986_9_, 0.0F, 0.0F, 1.0F);
//        Tessellator tessellator = Tessellator.instance;
//        byte b0 = 0;
//        float f2 = 0.0F;
//        float f3 = 0.5F;
//        float f4 = (float)(0 + b0 * 10) / 32.0F;
//        float f5 = (float)(5 + b0 * 10) / 32.0F;
//        float f6 = 0.0F;
//        float f7 = 0.15625F;
//        float f8 = (float)(5 + b0 * 10) / 32.0F;
//        float f9 = (float)(10 + b0 * 10) / 32.0F;
//        float f10 = 0.05625F;
//        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
//
//        GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
//        GL11.glScalef(f10, f10, f10);
//        GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
//        GL11.glNormal3f(f10, 0.0F, 0.0F);
//        tessellator.startDrawingQuads();
//        tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double)f6, (double)f8);
//        tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double)f7, (double)f8);
//        tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double)f7, (double)f9);
//        tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double)f6, (double)f9);
//        tessellator.draw();
//        GL11.glNormal3f(-f10, 0.0F, 0.0F);
//        tessellator.startDrawingQuads();
//        tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double)f6, (double)f8);
//        tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double)f7, (double)f8);
//        tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double)f7, (double)f9);
//        tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double)f6, (double)f9);
//        tessellator.draw();
//
//        for (int i = 0; i < 4; ++i) {
//            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
//            GL11.glNormal3f(0.0F, 0.0F, f10);
//            tessellator.startDrawingQuads();
//            tessellator.addVertexWithUV(-8.0D, -2.0D, 0.0D, (double)f2, (double)f4);
//            tessellator.addVertexWithUV(8.0D, -2.0D, 0.0D, (double)f3, (double)f4);
//            tessellator.addVertexWithUV(8.0D, 2.0D, 0.0D, (double)f3, (double)f5);
//            tessellator.addVertexWithUV(-8.0D, 2.0D, 0.0D, (double)f2, (double)f5);
//            tessellator.draw();
//        }
//
//        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
//        GL11.glPopMatrix();


        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glPushMatrix();
        GL11.glTranslated(posX, posY, posZ);
        GL11.glRotatef(entityForRender.prevRotationYaw + (entityForRender.rotationYaw - entityForRender.prevRotationYaw) * p_76986_9_ - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(entityForRender.prevRotationPitch + (entityForRender.rotationPitch - entityForRender.prevRotationPitch) * p_76986_9_, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(entityForRender.width, entityForRender.height, 1);


        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0, 0, 0, 0, 0);
        tessellator.addVertexWithUV(1, 0, 0, 0, 0.5);
        tessellator.addVertexWithUV(1, 1, 0, 0.5, 0.5);
        tessellator.addVertexWithUV(0, 1, 0, 0.5, 0);
        tessellator.draw();

        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_CULL_FACE);
    }

}
