package cn.autosec.onecore.uss.definition.custom.block.upright.glass;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.NotNull;

public class StainedGlassUprightSlabBlock extends GlassUprightSlabBlock implements Stainable {
    private final DyeColor dyeColor;
    public StainedGlassUprightSlabBlock(DyeColor dyeColor, Block glassBlock) {
        super(AbstractBlock.Settings.copy(glassBlock).mapColor(dyeColor));
        this.dyeColor = dyeColor;
    }

    @Override
    public @NotNull DyeColor getColor() {
        return this.dyeColor;
    }
}
