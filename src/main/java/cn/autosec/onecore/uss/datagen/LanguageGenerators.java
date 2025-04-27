package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.Utils;
import cn.autosec.onecore.uss.definition.lib.BlockLib;
import cn.autosec.onecore.uss.registry.ModBlocks;
import cn.autosec.onecore.uss.registry.ModCreativeTabs;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.List;

public class LanguageGenerators extends LanguageProvider {
    private final String locale;

    public LanguageGenerators(PackOutput output, String locale) {
        super(output, OneCore.MODID, locale);
        this.locale = locale;
    }

    @Override
    protected void addTranslations() {
        addLocale(Utils.ONECORE_DESCRIPTION_BRIEF_TAG, Utils.ONECORE_DESCRIPTION_BRIEF_EN, Utils.ONECORE_DESCRIPTION_BRIEF_CN);
        addLocale(Utils.ONECORE_DESCRIPTION_DETAIL_TAG, Utils.ONECORE_DESCRIPTION_DETAIL_EN, Utils.ONECORE_DESCRIPTION_DETAIL_CN);
        List<BlockLib> blockLocales = ModBlocks.getBlockLib();
        if (blockLocales != null) {
            for (BlockLib blockLocale : blockLocales) {
                addLocale(blockLocale.itemLib.modRegistry.get(), blockLocale.localeEn, blockLocale.localeCn);
                String descriptionLocaleEn = blockLocale.itemLib.descriptionLocaleEn.isEmpty() ?
                        Utils.ONECORE_DESCRIPTION_DETAIL_EN : blockLocale.itemLib.descriptionLocaleEn;
                String descriptionLocaleCn = blockLocale.itemLib.descriptionLocaleCn.isEmpty() ?
                        Utils.ONECORE_DESCRIPTION_DETAIL_CN : blockLocale.itemLib.descriptionLocaleCn;
                addLocale(Utils.ONECORE_DESCRIPTION_DETAIL_HEAD_TAG + blockLocale.itemLib.modRegistry.get().getDescriptionId(),
                        descriptionLocaleEn, descriptionLocaleCn);
            }
        }
        List<ModCreativeTabs.CreativeTabLocale> creativeTabLocales = ModCreativeTabs.GetCreativeTabLocales();
        if (creativeTabLocales != null) {
            for (ModCreativeTabs.CreativeTabLocale creativeTabLocale : creativeTabLocales) {
                addLocale(creativeTabLocale.tab, creativeTabLocale.localeEn, creativeTabLocale.localeCn);
            }
        }
    }

    private void addLocale(Item key, String en, String cn) {
        if (this.locale.equals("zh_cn")) {
            add(key, cn);
        } else {
            add(key, en);
        }
    }

    private void addLocale(String key, String en, String cn) {
        if (this.locale.equals("zh_cn")) {
            add(key, cn);
        } else {
            add(key, en);
        }
    }
}
