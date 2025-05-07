package cn.autosec.onecore.uss.definition.custom.block.normal.concrete;

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
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class ConcretePowderStairsBlock extends StairBlock implements CustomConcretePowderBlock {
    public static final MapCodec<ConcretePowderStairsBlock> CODEC = RecordCodecBuilder.mapCodec(
            p_341829_ -> p_341829_.group(BuiltInRegistries.BLOCK.byNameCodec().fieldOf("concrete").forGetter(p_313163_ -> p_313163_.concrete), BlockState.CODEC.fieldOf("base_state").forGetter(p_309296_ -> p_309296_.baseState), propertiesCodec())
                    .apply(p_341829_, ConcretePowderStairsBlock::new)
    );
    private final Block concrete;

    @Override
    public MapCodec<ConcretePowderStairsBlock> codec() {
        return CODEC;
    }

    public ConcretePowderStairsBlock(Block concrete, BlockState blockState, Properties properties) {
        super(blockState, properties);
        this.concrete = concrete;
    }

    @Override
    public void onLand(Level level, BlockPos pos, BlockState state, BlockState otherState, FallingBlockEntity entity) {
        if (CustomConcretePowderBlock.shouldSolidify(level, pos, state, otherState.getFluidState())) {
            FluidState fluidstate = level.getFluidState(pos);
            BlockState newBlockState = state.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
            level.setBlock(pos, newBlockState, 3);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter blockgetter = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = blockgetter.getBlockState(blockpos);
        return CustomConcretePowderBlock.shouldSolidify(blockgetter, blockpos, blockstate) ? this.concrete.defaultBlockState() : super.getStateForPlacement(context);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction dir, BlockState newState, LevelAccessor levelAccessor, BlockPos pos1, BlockPos pos2) {
        return CustomConcretePowderBlock.touchesLiquid(levelAccessor, pos1, state) ? this.concrete.defaultBlockState() : super.updateShape(state, dir, newState, levelAccessor, pos1, pos2);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState otherState, boolean flag) {
        level.scheduleTick(pos, this, this.getDelayAfterPlace());
    }

    @Override
    protected void tick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource randomSource) {
        if (CustomConcretePowderBlock.isFree(serverLevel.getBlockState(pos.below())) && pos.getY() >= serverLevel.getMinBuildHeight()) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(serverLevel, pos, state);
            this.falling(fallingblockentity);
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        if (randomSource.nextInt(16) == 0) {
            BlockPos blockpos = pos.below();
            if (CustomConcretePowderBlock.isFree(level.getBlockState(blockpos))) {
                ParticleUtils.spawnParticleBelow(level, pos, randomSource, new BlockParticleOption(ParticleTypes.FALLING_DUST, state));
            }
        }
    }
}
