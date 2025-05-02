package cn.autosec.onecore.uss.definition.lib;

import cn.autosec.onecore.uss.Utils;
import cn.autosec.onecore.uss.registry.ModCreativeTabs;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;

public abstract class ModRegistryLib<T> extends ModLib {
    public String name;
    public String localeEn = "";
    public String localeCn = "";
    public String descriptionLocaleEn = "";
    public String descriptionLocaleCn = "";
    public T modRegistry;
    public RegistryKey<ItemGroup> creativeTab = ModCreativeTabs.CREATIVE_MODE_TABS_BUILDINGS;

    public ModRegistryLib(T modRegistry, String name) {
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

    public ModRegistryLib<T> creativeTab(RegistryKey<ItemGroup> tab) {
        this.creativeTab = tab;
        return this;
    }
}
