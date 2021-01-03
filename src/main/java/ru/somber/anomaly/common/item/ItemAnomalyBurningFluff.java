package ru.somber.anomaly.common.item;

import net.minecraft.block.Block;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.common.block.AbstractBlockAnomaly;

public class ItemAnomalyBurningFluff extends AbstractItemAnomaly {

    public ItemAnomalyBurningFluff(Block block) {
        super((AbstractBlockAnomaly) block);
        setTextureName(AnomalyMod.MOD_ID + ":" + "burning_fluff");
    }

}
