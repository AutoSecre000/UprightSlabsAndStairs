package cn.autosec.onecore.uss.definition.custom.block.upright.copper;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.definition.custom.block.upright.UprightSlabBlock;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class CopperUprightSlabBlock extends UprightSlabBlock implements CustomWeatheringCopper {
    private final WeatheringCopper.WeatherState weatherState;
    public static final MapCodec<CopperUprightSlabBlock> CODEC = RecordCodecBuilder.mapCodec(
            p_311462_ -> p_311462_.group(WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state").forGetter(ChangeOverTimeBlock::getAge), propertiesCodec())
                    .apply(p_311462_, CopperUprightSlabBlock::new)
    );

    @Override
    public MapCodec<CopperUprightSlabBlock> codec() {
        return CODEC;
    }

    public CopperUprightSlabBlock(WeatheringCopper.WeatherState weatherState, Properties properties) {
        super(properties);
        this.weatherState = weatherState;
    }

    @Override
    protected void randomTick(BlockState blockState, ServerLevel serverLevel,
                              BlockPos blockPos, RandomSource randomSource) {
        OneCore.LOGGER.info("[CopperUprightSlabBlock:randomTick] receive random tick");
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
