package cn.autosec.onecore.uss.definition.custom.block.upright.copper;

import net.minecraft.world.level.block.WeatheringCopper;

public class WeatheredCutCopperUprightSlabBlock extends CopperUprightSlabBlock {

    public WeatheredCutCopperUprightSlabBlock(Properties properties) {
        super(WeatheringCopper.WeatherState.WEATHERED, properties);
    }
}
