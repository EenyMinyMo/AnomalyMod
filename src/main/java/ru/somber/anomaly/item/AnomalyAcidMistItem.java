package ru.somber.anomaly.item;

import net.minecraft.block.Block;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.block.AbstractAnomalyBlock;

public class AnomalyAcidMistItem extends AbstractAnomalyItem {

    public AnomalyAcidMistItem(Block block) {
        super((AbstractAnomalyBlock) block);
        setUnlocalizedName("anomaly_acidmist_item");
        setTextureName(AnomalyMod.MOD_ID + ":" + "acid_mist");
    }

}
