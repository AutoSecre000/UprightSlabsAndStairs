package cn.autosec.onecore.uss.definition.lib;

import cn.autosec.onecore.uss.Utils;
import cn.autosec.onecore.uss.definition.registries.ModRegistry;

public class ModRegistryLib<T> extends ModLib {
    public String name;
    public String localeEn = "";
    public String localeCn = "";
    public String descriptionLocaleEn = "";
    public String descriptionLocaleCn = "";
    public ModRegistry<T> modRegistry;
    public String creativeTab = Utils.ONECORE_CREATIVE_TAB_USS;

    public ModRegistryLib(ModRegistry<T> modRegistry, String name) {
        this.modRegistry = modRegistry;
        this.name = name;
    }

    public ModRegistryLib<T> locale(String localeEn, String localeCn) {
        this.localeEn = localeEn;
        this.localeCn = localeCn;
        if (descriptionLocaleEn.isEmpty()) {
            descriptionLocaleEn = localeEn;
        }
        if (descriptionLocaleCn.isEmpty()) {
            descriptionLocaleCn = localeCn;
        }
        return this;
    }

    public ModRegistryLib<T> creativeTab(String tab) {
        this.creativeTab = tab;
        return this;
    }
}
