package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.Utils;
import cn.autosec.onecore.uss.definition.lib.BlockLib;
import cn.autosec.onecore.uss.registry.ModBlocks;
import cn.autosec.onecore.uss.registry.ModCreativeTabs;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LanguageDefaultGenerators extends FabricLanguageProvider {
    public LanguageDefaultGenerators(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        addLocale(translationBuilder, Utils.ONECORE_DESCRIPTION_BRIEF_TAG, Utils.ONECORE_DESCRIPTION_BRIEF_EN);
        addLocale(translationBuilder, Utils.ONECORE_DESCRIPTION_DETAIL_TAG, Utils.ONECORE_DESCRIPTION_DETAIL_EN);
        List<BlockLib> blockLocales = ModBlocks.getBlockLib();
        if (blockLocales != null) {
            for (BlockLib blockLocale : blockLocales) {
                addLocale(translationBuilder, blockLocale.itemLib.modRegistry.get(), blockLocale.localeEn);
                String descriptionLocaleEn = blockLocale.itemLib.descriptionLocaleCn.isEmpty() ?
                        Utils.ONECORE_DESCRIPTION_DETAIL_EN : blockLocale.itemLib.descriptionLocaleEn;
                addLocale(translationBuilder,
                        Utils.ONECORE_DESCRIPTION_DETAIL_HEAD_TAG +
                                blockLocale.itemLib.modRegistry.get().getTranslationKey(), descriptionLocaleEn);
            }
        }
        List<ModCreativeTabs.CreativeTabLocale> creativeTabLocales = ModCreativeTabs.GetCreativeTabLocales();
        if (creativeTabLocales != null) {
            for (ModCreativeTabs.CreativeTabLocale creativeTabLocale : creativeTabLocales) {
                addLocale(translationBuilder, creativeTabLocale.tab, creativeTabLocale.localeEn);
            }
        }
    }

    private void addLocale(TranslationBuilder translationBuilder, Item key, String translation) {
        translationBuilder.add(key, translation);
    }

    private void addLocale(TranslationBuilder translationBuilder, String key, String translation) {
        translationBuilder.add(key, translation);
    }
}
