package cn.autosec.onecore.uss;

import cn.autosec.onecore.uss.definition.lib.BlockLib;
import cn.autosec.onecore.uss.registry.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import java.util.List;

public class OneCoreClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		List<BlockLib> blockLibs = ModBlocks.getBlockLib();
		for (BlockLib lib : blockLibs) {
			if (lib.isGlass) {
				BlockRenderLayerMap.INSTANCE.putBlock(lib.modRegistry.get(), lib.cutout ? RenderLayer.getCutout() : RenderLayer.getTranslucent());
			}
		}
	}
}