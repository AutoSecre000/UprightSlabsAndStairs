package cn.autosec.onecore.uss.definition.custom.block.upright.copper;

import cn.autosec.onecore.uss.definition.custom.block.upright.UprightSlabBlock;
import cn.autosec.onecore.uss.registry.ModBlocks;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Supplier;

public class CopperUprightSlabBlock extends UprightSlabBlock implements WeatheringCopper {
    private final WeatherState weatherState;
    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK_MOD = Suppliers.memoize(
            () -> ImmutableBiMap.<Block, Block>builder().put(Blocks.COPPER_BLOCK, Blocks.EXPOSED_COPPER).
                    put(Blocks.EXPOSED_COPPER, Blocks.WEATHERED_COPPER).
                    put(Blocks.WEATHERED_COPPER, Blocks.OXIDIZED_COPPER).
                    put(Blocks.CUT_COPPER, Blocks.EXPOSED_CUT_COPPER).
                    put(Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER).
                    put(Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER).
                    put(Blocks.CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER_SLAB).
                    put(Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER_SLAB).
                    put(Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER_SLAB).
                    put(Blocks.CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_STAIRS).
                    put(Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_STAIRS).
                    put(Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_STAIRS).
                    put(ModBlocks.CUT_COPPER_UPRIGHT_SLAB.get(), ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_SLAB.get()).
                    put(ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_SLAB.get(), ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_SLAB.get()).
                    put(ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_SLAB.get(), ModBlocks.OXIDIZED_CUT_COPPER_UPRIGHT_SLAB.get()).
                    put(ModBlocks.CUT_COPPER_UPRIGHT_STAIRS.get(), ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_STAIRS.get()).
                    put(ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_STAIRS.get(), ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_STAIRS.get()).
                    put(ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_STAIRS.get(), ModBlocks.OXIDIZED_CUT_COPPER_UPRIGHT_STAIRS.get())
                    .build());
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK_MOD = Suppliers.memoize(() -> NEXT_BY_BLOCK_MOD.get().inverse());

    public CopperUprightSlabBlock(WeatheringCopper.WeatherState weatherState, Properties properties) {
        super(properties);
        this.weatherState = weatherState;
    }

    private Optional<Block> getPrevious(Block block) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK_MOD.get().get(block));
    }

    private Block getFirst(Block curBlock) {
        Block block = curBlock;
        for(Block block1 = PREVIOUS_BY_BLOCK_MOD.get().get(curBlock); block1 != null; block1 = PREVIOUS_BY_BLOCK_MOD.get().get(block1)) {
            block = block1;
        }
        return block;
    }

    private Optional<Block> getNext(Block block) {
        return Optional.ofNullable(NEXT_BY_BLOCK_MOD.get().get(block));
    }

    public Optional<BlockState> getPrevious(BlockState blockState) {
        return getPrevious(blockState.getBlock()).map((p_154903_) -> p_154903_.withPropertiesOf(blockState));
    }

    public BlockState getFirst(BlockState blockState) {
        return getFirst(blockState.getBlock()).withPropertiesOf(blockState);
    }

    public Optional<BlockState> getNext(BlockState blockState) {
        return getNext(blockState.getBlock()).map((input) -> input.withPropertiesOf(blockState));
    }

    public void randomTick(BlockState blockState, ServerLevel serverLevel,
                           BlockPos blockPos, RandomSource randomSource) {
        super.randomTick(blockState, serverLevel, blockPos, randomSource);
    }

    public boolean isRandomlyTicking(BlockState blockState) {
        return WeatheringCopper.getNext(blockState.getBlock()).isPresent();
    }

    public WeatherState getAge() {
        return this.weatherState;
    }
}
