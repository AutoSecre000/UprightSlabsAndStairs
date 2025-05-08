package cn.autosec.onecore.uss.definition.custom.block.normal.concrete;

import cn.autosec.onecore.uss.OneCore;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

public class ConcretePowderSlabBlock extends SlabBlock implements CustomConcretePowderBlock {
    public static final MapCodec<ConcretePowderSlabBlock> CODEC = RecordCodecBuilder.mapCodec(
            p_341829_ -> p_341829_.group(BuiltInRegistries.BLOCK.byNameCodec().fieldOf("concrete").forGetter(p_313163_ -> p_313163_.concrete), propertiesCodec())
                    .apply(p_341829_, ConcretePowderSlabBlock::new)
    );
    private final Block concrete;

    @Override
    public @NotNull MapCodec<ConcretePowderSlabBlock> codec() {
        return CODEC;
    }

    public ConcretePowderSlabBlock(Block concrete, Properties properties) {
        super(properties);
        this.concrete = concrete;
    }

    private BlockState waterlogged(Level level, BlockPos pos, BlockState state) {
        FluidState fluidstate = level.getFluidState(pos);
        return state.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    private boolean isFree(BlockState selfState, BlockState state) {
        boolean isFree;
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:isFree]\nselfState:{}\nstate:{}", selfState, state);
        if (selfState.getValue(TYPE) == SlabType.TOP) {
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:isFree]is top slab");
            isFree = true;
        } else if (state.is(this) && state.getValue(TYPE) == SlabType.BOTTOM) {
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:isFree]over bottom slab");
            isFree = true;
        } else {
            isFree = CustomConcretePowderBlock.isFree(state);
        }
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:isFree]isFree:{}", isFree);
        return isFree;
    }

    @Override
    public void onLand(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state,
                       @NotNull BlockState otherState, @NotNull FallingBlockEntity entity) {
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]\nstate:{} pos:{} \notherState:{}", state, pos, otherState);
        if (CustomConcretePowderBlock.shouldSolidify(level, pos, state, otherState.getFluidState())) {
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]shouldSolidify");
            if (otherState.is(this.concrete) && otherState.getValue(TYPE) == SlabType.BOTTOM) {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]over bottom concrete slab");
                level.setBlock(pos, waterlogged(level, pos, this.concrete.withPropertiesOf(state).setValue(TYPE, SlabType.DOUBLE)), 3);
            } else if (!otherState.is(this.concrete)) {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]change to bottom concrete slab");
                level.setBlock(pos, waterlogged(level, pos, this.concrete.withPropertiesOf(state).setValue(TYPE, SlabType.BOTTOM)), 3);
            } else {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]change to concrete slab");
                level.setBlock(pos, waterlogged(level, pos, this.concrete.withPropertiesOf(state)), 3);
            }
        } else {
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]should not Solidify");
            if (otherState.is(state.getBlock()) && otherState.getValue(TYPE) == SlabType.BOTTOM) {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]over bottom slab");
                level.setBlock(pos, waterlogged(level, pos, state.setValue(TYPE, SlabType.DOUBLE)), 3);
            } else if (state.getValue(TYPE) == SlabType.DOUBLE) {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]is double slab");
                level.setBlock(pos, waterlogged(level, pos, state.setValue(TYPE, SlabType.DOUBLE)), 3);
            } else if (isFree(state, otherState)) {
                OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]change to bottom slab");
                level.setBlock(pos, waterlogged(level, pos, state.setValue(TYPE, SlabType.BOTTOM)), 3);
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter blockgetter = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = blockgetter.getBlockState(blockpos);
        BlockState nextState = super.getStateForPlacement(context);
        if (nextState == null) {
            nextState = blockstate;
        }
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:getStateForPlacement]\nblockstate:{} pos:{}\nnextState:{}", blockstate, blockpos, nextState);
        if (CustomConcretePowderBlock.shouldSolidify(blockgetter, blockpos, blockstate)) {
            nextState = waterlogged(context.getLevel(), blockpos, this.concrete.withPropertiesOf(nextState));
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:getStateForPlacement]shouldSolidify->>\nnextState:{}", nextState);
        }
        return nextState;
    }

    @Override
    protected @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull Direction dir,
                                              @NotNull BlockState newState, @NotNull LevelAccessor levelAccessor,
                                              @NotNull BlockPos pos1, @NotNull BlockPos pos2) {
        BlockState nextState = super.updateShape(state, dir, newState, levelAccessor, pos1, pos2);
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:updateShape]\nstate:{} pos1:{}\nnextState:{} pos2:{}",
                state, pos1, nextState, pos2);
        if (CustomConcretePowderBlock.touchesLiquid(levelAccessor, pos1, nextState)) {
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:updateShape]\ntouchesLiquid->>\nnextState:{} pos2:{}",
                    nextState, pos2);
            return this.concrete.withPropertiesOf(nextState);
        }
        levelAccessor.scheduleTick(pos1, this, this.getDelayAfterPlace());
        return nextState;
    }

    @Override
    protected void onPlace(@NotNull BlockState state, Level level, @NotNull BlockPos pos,
                           @NotNull BlockState otherState, boolean flag) {
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:onPlace]\nstate:{} pos:{}\notherState:{}", state, pos, otherState);
        level.scheduleTick(pos, this, this.getDelayAfterPlace());
    }

    @Override
    public void onBrokenAfterFall(@NotNull Level level, @NotNull BlockPos pos, FallingBlockEntity fallingBlockEntity) {
        BlockState blockstate = fallingBlockEntity.getBlockState();
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:onBrokenAfterFall]\nblockstate:{} pos:{}", blockstate, pos);
        if (blockstate.getValue(TYPE) == SlabType.DOUBLE) {
            fallingBlockEntity.spawnAtLocation(this);
        }
    }

    @Override
    protected void tick(@NotNull BlockState state, ServerLevel serverLevel,
                        @NotNull BlockPos pos, @NotNull RandomSource randomSource) {
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:tick]\nstate:{} pos:{}", state, pos);
        if (isFree(state, serverLevel.getBlockState(pos.below())) && pos.getY() >= serverLevel.getMinBuildHeight()) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(serverLevel, pos, state);
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:tick]falling");
            this.falling(fallingblockentity);
        }
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level,
                            @NotNull BlockPos pos, RandomSource randomSource) {
        if (randomSource.nextInt(16) == 0) {
            BlockPos blockpos = pos.below();
            if (isFree(state, level.getBlockState(blockpos))) {
                ParticleUtils.spawnParticleBelow(level, pos, randomSource,
                        new BlockParticleOption(ParticleTypes.FALLING_DUST, state));
            }
        }
    }
}
