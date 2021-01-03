package ru.somber.anomaly.common.item;

import net.minecraft.block.Block;
import ru.somber.anomaly.AnomalyMod;
import ru.somber.anomaly.common.block.AbstractBlockAnomaly;

public class ItemAnomalyAcidMist extends AbstractItemAnomaly {

    public ItemAnomalyAcidMist(Block block) {
        super((AbstractBlockAnomaly) block);
        setUnlocalizedName("anomaly_acidmist_item");
        setTextureName(AnomalyMod.MOD_ID + ":" + "acid_mist");
    }

}
