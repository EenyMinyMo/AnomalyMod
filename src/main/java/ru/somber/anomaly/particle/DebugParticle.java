package ru.somber.anomaly.particle;

import org.lwjgl.util.vector.Vector3f;
import ru.somber.anomaly.ParticleIcons;
import ru.somber.clientutil.textureatlas.icon.AtlasIcon;
import ru.somber.clientutil.textureatlas.icon.MultiFrameAtlasIcon;
import ru.somber.commonutil.SomberCommonUtil;
import ru.somber.particlesystem.particle.AbstractParticleSimpleData;

import java.util.Random;

public class DebugParticle extends AbstractParticleSimpleData {

    public DebugParticle(float x, float y, float z, int maxLifeTime) {
        super(x, y, z, maxLifeTime, ParticleIcons.trashAnim1Icon);

        Random randomizer = SomberCommonUtil.RANDOMIZER;

        setHalfSizes(2, 1);
    }

    public DebugParticle(Vector3f newPosition, int maxLifeTime) {
        this(newPosition.getX(), newPosition.getY(), newPosition.getZ(), maxLifeTime);
    }

    @Override
    public void computeNormalVector(Vector3f destination, float lookAtX, float lookAtY, float lookAtZ, float interpolateFactor) {
        super.computeNormalVectorStaticParticle(destination);
    }

    @Override
    public void update() {
        super.update();

        Random randomizer = SomberCommonUtil.RANDOMIZER;
        float lifeFactor = getLifeFactor();

    }

    @Override
    public AtlasIcon getParticleIcon() {
        MultiFrameAtlasIcon multiIcon = (MultiFrameAtlasIcon) super.getParticleIcon();
        return multiIcon.getFrameIcon(0);


//        return super.getParticleIcon();


//        AtlasIcon icon = new AtlasIcon("", false, false) {
//            @Override
//            public void initIcon(int widthAtlas, int heightAtlas, int originXInAtlas, int originYInAtlas, boolean isRotated) {
//                setUseAnisotropicFiltering(true);
//                super.initIcon(widthAtlas, heightAtlas, originXInAtlas, originYInAtlas, isRotated);
//            }
//        };
//        icon.setIconWidth(8192);
//        icon.setIconHeight(4096);
//        icon.initIcon(8192, 4096, 0, 0, false);
//        return icon;
    }

}
