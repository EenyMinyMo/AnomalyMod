package ru.somber.anomaly.common.item;

import net.minecraft.block.Block;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.common.block.AbstractAnomalyBlock;

public class AnomalyTrampolineItem extends AbstractAnomalyItem {

    public AnomalyTrampolineItem(Block block) {
        super((AbstractAnomalyBlock) block);
        setTextureName(AnomalyMod.MOD_ID + ":" + "trampoline");
    }

}
