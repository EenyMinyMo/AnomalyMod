package ru.AmaZ1nG.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class MutableSound {

    private final ResourceLocation loc;
    private ISound.AttenuationType attenuationType;
    private float volume;
    private float pitch;
    private boolean repeatable;
    private int repeatDelay;
    private float posX;
    private float posY;
    private float posZ;
    private double trackingRange;
    private MutableSound.SoundNode node;


    public static SoundHandler getSoundHandler() {
        return Minecraft.getMinecraft().getSoundHandler();
    }

    public MutableSound(ResourceLocation loc) {
        this.attenuationType = ISound.AttenuationType.LINEAR;
        this.volume = 1.0F;
        this.pitch = 1.0F;
        this.repeatable = false;
        this.repeatDelay = 0;
        this.trackingRange = 20.0D;
        this.node = null;
        this.loc = loc;
    }

    public boolean isPlaying() {
        return this.node != null && !this.node.done && this.isInTrackingRange();
    }

    public void play() {
        this.playDelayed(-1);
    }

    public void playDelayed(int ticks) {
        if(this.node != null) {
            this.node.destroy();
        }

        if(this.isInTrackingRange()) {
            this.node = new MutableSound.SoundNode();
            if(ticks < 0) {
                getSoundHandler().playSound(this.node);
            } else {
                getSoundHandler().playDelayedSound(this.node, ticks);
            }
        }

    }

    public void stop() {
        if(this.node != null) {
            this.node.destroy();
        }

    }

    protected void update(MutableSound.SoundNode node) {
        if(this.node != node) {
            node.destroy();
        } else if(!this.isInTrackingRange()) {
            node.done = true;
        }

    }

    public void setAttenuationType(ISound.AttenuationType attenuationType) {
        this.attenuationType = attenuationType;
    }

    public void setPosition(double x, double y, double z) {
        this.posX = (float)x;
        this.posY = (float)y;
        this.posZ = (float)z;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }

    public void setRepeatDelay(int repeatDelay) {
        this.repeatDelay = repeatDelay;
    }

    public void setTrackingRange(double trackingRange) {
        this.trackingRange = trackingRange;
    }

    public double distanceToEntity(Entity entity) {
        return this.distanceTo(entity.posX, entity.posY, entity.posZ);
    }

    public double distanceTo(double x, double y, double z) {
        x -= (double)this.posX;
        y -= (double)this.posY;
        z -= (double)this.posZ;
        return (double) MathHelper.sqrt_double(x * x + y * y + z * z);
    }

    protected boolean isInTrackingRange() {
        return true;
    }


    protected class SoundNode implements ISound, ITickableSound {

        protected boolean done = false;


        public boolean isDonePlaying() {
            return this.done;
        }

        public void destroy() {
            this.done = true;
            MutableSound.getSoundHandler().stopSound(this);
        }

        public void update() {
            MutableSound.this.update(this);
        }

        public ResourceLocation getPositionedSoundLocation() {
            return MutableSound.this.loc;
        }

        public boolean canRepeat() {
            return MutableSound.this.repeatable;
        }

        public int getRepeatDelay() {
            return MutableSound.this.repeatDelay;
        }

        public float getVolume() {
            return MutableSound.this.volume;
        }

        public float getPitch() {
            return MutableSound.this.pitch;
        }

        public float getXPosF() {
            return MutableSound.this.posX;
        }

        public float getYPosF() {
            return MutableSound.this.posY;
        }

        public float getZPosF() {
            return MutableSound.this.posZ;
        }

        public AttenuationType getAttenuationType() {
            return MutableSound.this.attenuationType;
        }
    }
}
