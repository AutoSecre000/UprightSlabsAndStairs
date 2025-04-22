package cn.autosec.onecore.uss.registry;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum ModSlabTypes implements StringRepresentable {
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

    public @NotNull String getSerializedName() {
        return this.name;
    }
}
