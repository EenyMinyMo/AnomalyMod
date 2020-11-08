package ru.somber.anomaly.common.item;

import net.minecraft.block.Block;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.common.block.AbstractAnomalyBlock;

public class AnomalyKisselItem extends AbstractAnomalyItem {

    public AnomalyKisselItem(Block block) {
        super((AbstractAnomalyBlock) block);
        setTextureName(AnomalyMod.MOD_ID + ":" + "kissel");
    }

}
