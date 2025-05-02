package cn.autosec.onecore.uss.registry;

import net.minecraft.util.StringIdentifiable;

public enum ModStairTypes implements StringIdentifiable {
    NW("north_west"),
    SW("south_west"),
    NE("north_east"),
    SE("south_east");

    private final String name;

    private ModStairTypes(String name) {
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
