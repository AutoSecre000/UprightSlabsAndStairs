package cn.autosec.onecore.uss.registry;

import net.minecraft.util.StringIdentifiable;

public enum ModSlabTypes implements StringIdentifiable {
    N("north"),
    E("east"),
    S("south"),
    W("west"),
    DOUBLE("double");

    private final String name;

    private ModSlabTypes(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
