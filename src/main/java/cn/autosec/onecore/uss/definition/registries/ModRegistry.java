package cn.autosec.onecore.uss.definition.registries;

import net.minecraft.tags.TagKey;
import net.minecraftforge.registries.RegistryObject;

public class ModRegistry<T> {
    protected final RegistryObject<T> registryObject;

    protected final TagKey<T> tagKey;

    public ModRegistry(RegistryObject<T> registryObject, TagKey<T> tagKey) {
        this.registryObject = registryObject;
        this.tagKey = tagKey;
    }

    public RegistryObject<T> self() {
        return registryObject;
    }

    public T get() {
        return registryObject.get();
    }

    public TagKey<T> tag() {
        return tagKey;
    }
}
