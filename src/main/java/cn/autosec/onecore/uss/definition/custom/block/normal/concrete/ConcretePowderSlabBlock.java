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
import net.minecraft.tags.BlockTags;
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

public class ConcretePowderSlabBlock extends SlabBlock implements CustomConcretePowderBlock {
    public static final MapCodec<ConcretePowderSlabBlock> CODEC = RecordCodecBuilder.mapCodec(
            p_341829_ -> p_341829_.group(BuiltInRegistries.BLOCK.byNameCodec().fieldOf("concrete").forGetter(p_313163_ -> p_313163_.concrete), propertiesCodec())
                    .apply(p_341829_, ConcretePowderSlabBlock::new)
    );
    private final Block concrete;

    @Override
    public MapCodec<ConcretePowderSlabBlock> codec() {
        return CODEC;
    }

    public ConcretePowderSlabBlock(Block concrete, Properties properties) {
        super(properties);
        this.concrete = concrete;
    }

    private BlockState getNextBlockState(Level level, BlockPos pos, BlockState state) {
        FluidState fluidstate = level.getFluidState(pos);
        return state.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    private boolean isFree(BlockState selfState, BlockState state) {
        boolean isFree;
        if (state.is(selfState.getBlock()) && state.getValue(TYPE) == SlabType.BOTTOM) {
            isFree = true;
        } else if (selfState.getValue(TYPE) == SlabType.TOP) {
            isFree = true;
        } else {
            isFree = state.isAir() || state.is(BlockTags.FIRE) || state.liquid() || state.canBeReplaced();
        }
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:isFree]isFree:{}\nselfState:{}\nstate:{}", isFree, selfState, state);
        return isFree;
    }

    @Override
    public void onLand(Level level, BlockPos pos, BlockState state, BlockState otherState, FallingBlockEntity entity) {
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:onLand]\nstate:{}\notherState:{}", state, otherState);
        if (CustomConcretePowderBlock.shouldSolidify(level, pos, state, otherState.getFluidState())) {
            if (otherState.is(this.concrete) && otherState.getValue(TYPE) == SlabType.BOTTOM) {
                level.setBlock(pos, getNextBlockState(level, pos, this.concrete.withPropertiesOf(state).setValue(TYPE, SlabType.DOUBLE)), 3);
            } else if (!otherState.is(this.concrete)) {
                level.setBlock(pos, getNextBlockState(level, pos, this.concrete.withPropertiesOf(state).setValue(TYPE, SlabType.BOTTOM)), 3);
            } else {
                level.setBlock(pos, getNextBlockState(level, pos, this.concrete.withPropertiesOf(state)), 3);
            }
        } else {
            if (otherState.is(state.getBlock()) && otherState.getValue(TYPE) == SlabType.BOTTOM) {
                level.setBlock(pos, getNextBlockState(level, pos, state.setValue(TYPE, SlabType.DOUBLE)), 3);
            } else if (isFree(state, otherState)) {
                level.setBlock(pos, getNextBlockState(level, pos, state.setValue(TYPE, SlabType.BOTTOM)), 3);
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter blockgetter = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = blockgetter.getBlockState(blockpos);
        BlockState nextState = super.getStateForPlacement(context);
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:getStateForPlacement]\nblockstate:{}\nnextState:{}", blockstate, nextState);
        if (CustomConcretePowderBlock.shouldSolidify(blockgetter, blockpos, blockstate)) {
            nextState = getNextBlockState(context.getLevel(), blockpos, this.concrete.withPropertiesOf(nextState));
            OneCore.LOGGER.info("[ConcretePowderSlabBlock:getStateForPlacement]shouldSolidify->>\nnextState:{}", nextState);
        }
        return nextState;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction dir, BlockState newState, LevelAccessor levelAccessor, BlockPos pos1, BlockPos pos2) {
        BlockState nextState = super.updateShape(state, dir, newState, levelAccessor, pos1, pos2);
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:updateShape]\nstate:{}\nnextState:{}", state, nextState);
        return CustomConcretePowderBlock.touchesLiquid(levelAccessor, pos1, nextState) ? this.concrete.withPropertiesOf(nextState) : nextState;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState otherState, boolean flag) {
        OneCore.LOGGER.info("[ConcretePowderSlabBlock:onPlace]\nstate:{}\notherState:{}", state, otherState);
        level.scheduleTick(pos, this, this.getDelayAfterPlace());
    }

    @Override
    protected void tick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource randomSource) {
        if (isFree(state, serverLevel.getBlockState(pos.below())) && pos.getY() >= serverLevel.getMinBuildHeight()) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(serverLevel, pos, state);
            this.falling(fallingblockentity);
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        if (randomSource.nextInt(16) == 0) {
            BlockPos blockpos = pos.below();
            if (isFree(state, level.getBlockState(blockpos))) {
                ParticleUtils.spawnParticleBelow(level, pos, randomSource, new BlockParticleOption(ParticleTypes.FALLING_DUST, state));
            }
        }
    }
}
