package ru.somber.anomaly.item;

import net.minecraft.block.Block;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.block.AbstractAnomalyBlock;

public class AnomalyAerationItem extends AbstractAnomalyItem {

    public AnomalyAerationItem(Block block) {
        super((AbstractAnomalyBlock) block);
        setTextureName(AnomalyMod.MOD_ID + ":" + "aeration");
    }

}
