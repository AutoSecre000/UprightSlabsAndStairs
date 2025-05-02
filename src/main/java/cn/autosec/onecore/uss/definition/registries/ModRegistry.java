package cn.autosec.onecore.uss.definition.registries;

import net.minecraft.registry.tag.TagKey;

public abstract class ModRegistry<T> {
    protected final T registryObject;
    protected final TagKey<T> tagKey;

    public ModRegistry(T registryObject, TagKey<T> tagKey) {
        this.registryObject = registryObject;
        this.tagKey = tagKey;
    }

    public T self() {
        return this.registryObject;
    }

    public T get() {
        return self();
    }

    public TagKey<T> tag() {
        return this.tagKey;
    }
}
