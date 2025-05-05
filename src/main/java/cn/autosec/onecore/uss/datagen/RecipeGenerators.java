package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.definition.lib.CraftingRecipeLib;
import cn.autosec.onecore.uss.definition.lib.StoneCutterRecipeLib;
import cn.autosec.onecore.uss.registry.ModRecipes;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecipeGenerators extends RecipeProvider {
    public RecipeGenerators(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        List<CraftingRecipeLib> craftingRecipeBuilders = ModRecipes.getCraftingRecipeBuilders();
        if (craftingRecipeBuilders != null) {
            for (CraftingRecipeLib lib : craftingRecipeBuilders) {
                if (lib.isConversionRecipe) {
                    lib.recipeBuilder.save(output, getConversionRecipeName(lib.conversionInput, lib.converter));
                } else {
                    lib.recipeBuilder.save(output);
                }
            }
        }

        List<StoneCutterRecipeLib> stoneCutterRecipeLibs = ModRecipes.getStoneCutterRecipes();
        if (stoneCutterRecipeLibs != null) {
            for (StoneCutterRecipeLib stoneCutterRecipeLib : stoneCutterRecipeLibs) {
                stonecutterResultFromBase(output, stoneCutterRecipeLib.category, stoneCutterRecipeLib.output,
                        stoneCutterRecipeLib.input, stoneCutterRecipeLib.outputNum);
            }
        }
    }
}
