package cn.autosec.onecore.uss.definition.custom.block.normal.glass;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class StainedGlassSlabBlock extends GlassSlabBlock implements BeaconBeamBlock {
    private final DyeColor dyeColor;
    public StainedGlassSlabBlock(DyeColor dyeColor, Block glassBlock) {
        super(Properties.ofFullCopy(glassBlock).mapColor(dyeColor));
        this.dyeColor = dyeColor;
    }

    @Override
    public @NotNull DyeColor getColor() {
        return this.dyeColor;
    }
}
