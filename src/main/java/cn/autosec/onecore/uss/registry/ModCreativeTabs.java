package cn.autosec.onecore.uss.registry;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.Utils;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModCreativeTabs {
    public static final RegistryKey<ItemGroup> CREATIVE_MODE_TABS_BUILDINGS =
            RegistryKey.of(Registries.ITEM_GROUP.getKey(),
            Identifier.of(OneCore.MOD_ID, "item_group"));
    public static final ItemGroup ONECORE_TAB_BUILDINGS = FabricItemGroup.builder()
            .icon(() -> ModBlocks.getFirstBlockItemLib(CREATIVE_MODE_TABS_BUILDINGS).modRegistry.get().getDefaultStack())
            .displayName(Text.translatable(Utils.ONECORE_CREATIVE_TAB_USS))
            .build();

    public static void initialize() {
        // Register the group.
        Registry.register(Registries.ITEM_GROUP, CREATIVE_MODE_TABS_BUILDINGS, ONECORE_TAB_BUILDINGS);
    }

    public static final class CreativeTabLocale {
        public String tab;
        public String localeEn;
        public String localeCn;

        public CreativeTabLocale(String tab, String localeEn, String localeCn) {
            this.tab = tab;
            this.localeEn = localeEn;
            this.localeCn = localeCn;
        }
    }
    private static final List<CreativeTabLocale> creativeTabLocales = new ArrayList<>();

    public static List<CreativeTabLocale> GetCreativeTabLocales() {
        if (creativeTabLocales.isEmpty()) {
            creativeTabLocales.add(new CreativeTabLocale(Utils.ONECORE_CREATIVE_TAB_USS,
                    Utils.ONECORE_CREATIVE_TAB_USS_EN, Utils.ONECORE_CREATIVE_TAB_USS_CN));
        }

        return creativeTabLocales;
    }
}
