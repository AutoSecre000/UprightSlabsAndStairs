package cn.autosec.onecore.uss.definition.registries;

import net.minecraft.tags.TagKey;

public abstract class ModRegistry<T, U> {
    protected final T registryObject;

    protected final TagKey<U> tagKey;

    public ModRegistry(T registryObject, TagKey<U> tagKey) {
        this.registryObject = registryObject;
        this.tagKey = tagKey;
    }

    public T self() {
        return registryObject;
    }

    public abstract U get();

    public TagKey<U> tag() {
        return tagKey;
    }
}
