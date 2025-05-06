package cn.autosec.onecore.uss.registry;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.Utils;
import cn.autosec.onecore.uss.definition.lib.BlockLib;
import cn.autosec.onecore.uss.definition.lib.ItemLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OneCore.MODID);
    // Creative Tab [begin]
    private static final Component titleCompBuildings = Component.translatable(Utils.ONECORE_CREATIVE_TAB_USS);
    private static final Supplier<ItemStack> getIconBuildings = () -> {
        ItemLib blockItemLib = ModBlocks.getFirstBlockItemLib(Utils.ONECORE_CREATIVE_TAB_USS);
        return Objects.requireNonNull(blockItemLib).modRegistry.get().getDefaultInstance();
    };
    private static final Supplier<CreativeModeTab> buildTabBuildings =
            () -> CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.INGREDIENTS)
                    .title(titleCompBuildings).icon(getIconBuildings)
                    .displayItems((parameters, output) -> {
                        List<BlockLib> blockTags = ModBlocks.getBlockLib();
                        if (blockTags != null) {
                            for (BlockLib blockLib: blockTags) {
                                if (blockLib.creativeTab.equals(Utils.ONECORE_CREATIVE_TAB_USS)) {
                                    output.accept(blockLib.itemLib.modRegistry.get());
                                }
                            }
                        }
                    }).build();
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ONECORE_TAB_BUILDINGS = CREATIVE_MODE_TABS.register(Utils.ONECORE_CREATIVE_TAB_USS, buildTabBuildings);
    // Creative Tab [end]
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

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
