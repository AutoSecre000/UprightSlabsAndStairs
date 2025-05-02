package cn.autosec.onecore.uss.definition.custom.block.upright.copper;

import cn.autosec.onecore.uss.definition.custom.block.upright.UprightStairsBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;

public class CopperUprightStairsBlock extends UprightStairsBlock implements Oxidizable {
    private final Oxidizable.OxidationLevel oxidationLevel;

    public CopperUprightStairsBlock(Oxidizable.OxidationLevel oxidationLevel, Settings properties) {
        super(properties);
        this.oxidationLevel = oxidationLevel;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, net.minecraft.util.math.BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return Oxidizable.getIncreasedOxidationBlock(state.getBlock()).isPresent();
    }

    public Oxidizable.OxidationLevel getDegradationLevel() {
        return this.oxidationLevel;
    }
}
