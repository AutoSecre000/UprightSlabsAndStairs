package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.OneCore;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = OneCore.MODID,bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new BlockModelGenerators(output, helper));
        generator.addProvider(event.includeClient(), new LanguageGenerators(output,"en_us"));
        generator.addProvider(event.includeClient(), new LanguageGenerators(output,"zh_cn"));

        BlockTagsGenerators blockTagsGenerators = new BlockTagsGenerators(output, lookupProvider,
                event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTagsGenerators);
        generator.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(LootTableGenerators::new, LootContextParamSets.BLOCK)), lookupProvider));
        generator.addProvider(event.includeServer(), new RecipeGenerators(output, lookupProvider));
        generator.addProvider(event.includeServer(), new DataMapGenerators(output, lookupProvider));
    }
}
