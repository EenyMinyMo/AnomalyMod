package ru.somber.anomaly.common.item;

import net.minecraft.block.Block;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.common.block.AbstractBlockAnomaly;

public class ItemAnomalyCarousel extends AbstractItemAnomaly {

    public ItemAnomalyCarousel(Block block) {
        super((AbstractBlockAnomaly) block);
        setTextureName(AnomalyMod.MOD_ID + ":" + "carousel");
    }

}
