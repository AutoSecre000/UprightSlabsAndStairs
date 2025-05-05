package cn.autosec.onecore.uss.definition.custom.block.upright.copper;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.definition.custom.block.upright.UprightStairsBlock;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class CopperUprightStairsBlock extends UprightStairsBlock implements CustomWeatheringCopper {
    public static final MapCodec<CopperUprightStairsBlock> CODEC = RecordCodecBuilder.mapCodec(
            p_311618_ -> p_311618_.group(
                            WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state").forGetter(ChangeOverTimeBlock::getAge),
                            propertiesCodec()
                    )
                    .apply(p_311618_, CopperUprightStairsBlock::new)
    );
    private final WeatheringCopper.WeatherState weatherState;

    @Override
    public MapCodec<CopperUprightStairsBlock> codec() {
        return CODEC;
    }

    public CopperUprightStairsBlock(WeatheringCopper.WeatherState weatherState, Properties properties) {
        super(properties);
        this.weatherState = weatherState;
    }

    @Override
    protected void randomTick(BlockState blockState, ServerLevel serverLevel,
                              BlockPos blockPos, RandomSource randomSource) {
        OneCore.LOGGER.info("[CopperUprightStairsBlock:randomTick] receive random tick");
        this.changeOverTime(blockState, serverLevel, blockPos, randomSource);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState blockState) {
        return CustomWeatheringCopper.getNext(blockState.getBlock()).isPresent();
    }

    public WeatheringCopper.WeatherState getAge() {
        return this.weatherState;
    }
}
