package cn.autosec.onecore.uss.definition;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.Item;

public class FurnaceRecipeLib extends ModLib {
    public Item input;
    public Item output;
    public RecipeCategory category;
    public float exp = 0.7f;
    public int time;
    public boolean isOre;

    public FurnaceRecipeLib(Item input, RecipeCategory recipeCategory, Item output, int time) {
        this.input = input;
        this.category = recipeCategory;
        this.output = output;
        this.time = time;
        this.isOre = true;
    }

    public FurnaceRecipeLib exp(float exp) {
        this.exp = exp;
        return this;
    }
}
