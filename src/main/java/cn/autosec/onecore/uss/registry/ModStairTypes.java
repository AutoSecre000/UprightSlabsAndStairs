package cn.autosec.onecore.uss.registry;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum ModStairTypes implements StringRepresentable {
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

    public @NotNull String getSerializedName() {
        return this.name;
    }
}
