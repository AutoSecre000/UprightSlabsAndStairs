package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.definition.BlockLib;
import cn.autosec.onecore.uss.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;

public class BlockModelGenerators extends BlockStateProvider {
    public BlockModelGenerators(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, OneCore.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        List<BlockLib> blockModels = ModBlocks.getBlockLib();
        if (blockModels != null) {
            for (BlockLib blockLib : blockModels) {
                if (blockLib.isSimpleCube) {
                    simpleBlockWithItem(blockLib.modRegistry.get(), cubeAll(blockLib.modRegistry.get()));
                }
            }
        }
    }
}
