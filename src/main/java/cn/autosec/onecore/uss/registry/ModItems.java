package cn.autosec.onecore.uss.registry;

import cn.autosec.onecore.uss.OneCore;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static Item register(Item item, String id) {
        // Create the identifier for the item.
        Identifier itemID = Identifier.of(OneCore.MOD_ID, id);

        // Register item and return!
        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static void initialize() {
        // Get the event for modifying entries in the ingredients group.
        // And register an event handler that adds our suspicious item to the ingredients group.
    }
}
