package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.definition.lib.StoneCutterRecipeLib;
import cn.autosec.onecore.uss.registry.ModRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeGenerators extends RecipeProvider {
    public RecipeGenerators(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        List<RecipeBuilder> craftingRecipeBuilders = ModRecipes.getCraftingRecipeBuilders();
        if (craftingRecipeBuilders != null) {
            for (RecipeBuilder builder : craftingRecipeBuilders) {
                builder.save(output);
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
