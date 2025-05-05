package cn.autosec.onecore.uss.definition.custom.block.upright.copper;

import net.minecraft.world.level.block.WeatheringCopper;

public class WeatheredCutCopperUprightStairsBlock extends CopperUprightStairsBlock {

    public WeatheredCutCopperUprightStairsBlock(Properties properties) {
        super(WeatheringCopper.WeatherState.WEATHERED, properties);
    }
}
