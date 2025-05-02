package cn.autosec.onecore.uss;

import cn.autosec.onecore.uss.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class OneCoreDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(BlockTagsGenerators::new);
		pack.addProvider(LanguageDefaultGenerators::new);
		pack.addProvider(LanguageChineseGenerators::new);
		pack.addProvider(RecipeGenerators::new);
		pack.addProvider(LootTableGenerators::new);
	}
}
