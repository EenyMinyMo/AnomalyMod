package ru.somber.anomaly.common.item;

import net.minecraft.block.Block;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.common.block.AbstractAnomalyBlock;

public class AnomalyAcidMistItem extends AbstractAnomalyItem {

    public AnomalyAcidMistItem(Block block) {
        super((AbstractAnomalyBlock) block);
        setUnlocalizedName("anomaly_acidmist_item");
        setTextureName(AnomalyMod.MOD_ID + ":" + "acid_mist");
    }

}
