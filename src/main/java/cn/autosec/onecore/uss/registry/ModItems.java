package cn.autosec.onecore.uss.registry;

import cn.autosec.onecore.uss.OneCore;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(OneCore.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
