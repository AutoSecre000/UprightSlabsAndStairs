package cn.autosec.onecore.uss.definition.custom.block.upright.copper;

import cn.autosec.onecore.uss.registry.ModBlocks;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Supplier;

public interface CustomWeatheringCopper extends ChangeOverTimeBlock<WeatheringCopper.WeatherState> {
    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(
            () -> ImmutableBiMap.<Block, Block>builder()
                    .put(ModBlocks.CUT_COPPER_UPRIGHT_SLAB.get(), ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_SLAB.get())
                    .put(ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_SLAB.get(), ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_SLAB.get())
                    .put(ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_SLAB.get(), ModBlocks.OXIDIZED_CUT_COPPER_UPRIGHT_SLAB.get())
                    .put(ModBlocks.CUT_COPPER_UPRIGHT_STAIRS.get(), ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_STAIRS.get())
                    .put(ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_STAIRS.get(), ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_STAIRS.get())
                    .put(ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_STAIRS.get(), ModBlocks.OXIDIZED_CUT_COPPER_UPRIGHT_STAIRS.get())
                    .build()
    );
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());
    Supplier<BiMap<Block, Block>> WAXED_ON = Suppliers.memoize(
            () -> ImmutableBiMap.<Block, Block>builder()
                    .put(ModBlocks.CUT_COPPER_UPRIGHT_SLAB.get(), ModBlocks.WAXED_CUT_COPPER_UPRIGHT_SLAB.get())
                    .put(ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_SLAB.get(), ModBlocks.WAXED_EXPOSED_CUT_COPPER_UPRIGHT_SLAB.get())
                    .put(ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_SLAB.get(), ModBlocks.WAXED_WEATHERED_CUT_COPPER_UPRIGHT_SLAB.get())
                    .put(ModBlocks.OXIDIZED_CUT_COPPER_UPRIGHT_SLAB.get(), ModBlocks.WAXED_OXIDIZED_CUT_COPPER_UPRIGHT_SLAB.get())
                    .put(ModBlocks.CUT_COPPER_UPRIGHT_STAIRS.get(), ModBlocks.WAXED_CUT_COPPER_UPRIGHT_STAIRS.get())
                    .put(ModBlocks.EXPOSED_CUT_COPPER_UPRIGHT_STAIRS.get(), ModBlocks.WAXED_EXPOSED_CUT_COPPER_UPRIGHT_STAIRS.get())
                    .put(ModBlocks.WEATHERED_CUT_COPPER_UPRIGHT_STAIRS.get(), ModBlocks.WAXED_WEATHERED_CUT_COPPER_UPRIGHT_STAIRS.get())
                    .put(ModBlocks.OXIDIZED_CUT_COPPER_UPRIGHT_STAIRS.get(), ModBlocks.WAXED_OXIDIZED_CUT_COPPER_UPRIGHT_STAIRS.get())
                    .build()
    );
    Supplier<BiMap<Block, Block>> WAXED_OFF = Suppliers.memoize(() -> WAXED_ON.get().inverse());

    static Optional<Block> getPrevious(Block block) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(block));
    }

    static Block getFirst(Block inputBlock) {
        Block block = inputBlock;

        for (Block block1 = PREVIOUS_BY_BLOCK.get().get(inputBlock); block1 != null; block1 = PREVIOUS_BY_BLOCK.get().get(block1)) {
            block = block1;
        }

        return block;
    }

    static Optional<BlockState> getPrevious(BlockState blockState) {
        return getPrevious(blockState.getBlock()).map(p_154903_ -> p_154903_.withPropertiesOf(blockState));
    }

    static Optional<Block> getNext(Block block) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(block));
    }

    static BlockState getFirst(BlockState blockState) {
        return getFirst(blockState.getBlock()).withPropertiesOf(blockState);
    }

    @Override
    default Optional<BlockState> getNext(BlockState blockState) {
        return getNext(blockState.getBlock()).map(p_154896_ -> p_154896_.withPropertiesOf(blockState));
    }

    @Override
    default float getChanceModifier() {
        return this.getAge() == WeatheringCopper.WeatherState.UNAFFECTED ? 0.75F : 1.0F;
    }

    static Optional<Block> getWaxedOff(Block block) {
        return Optional.ofNullable(WAXED_OFF.get().get(block));
    }

    static Optional<Block> getWaxedOn(Block block) {
        return Optional.ofNullable(WAXED_ON.get().get(block));
    }

    static Optional<BlockState> getWaxedOff(BlockState blockState) {
        return getWaxedOff(blockState.getBlock()).map(block -> block.withPropertiesOf(blockState));
    }

    static Optional<BlockState> getWaxedOn(BlockState blockState) {
        return getWaxedOn(blockState.getBlock()).map(block -> block.withPropertiesOf(blockState));
    }
}
