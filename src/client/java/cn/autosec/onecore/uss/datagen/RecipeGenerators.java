package cn.autosec.onecore.uss.datagen;

import cn.autosec.onecore.uss.OneCore;
import cn.autosec.onecore.uss.definition.lib.CraftingRecipeLib;
import cn.autosec.onecore.uss.definition.lib.FurnaceRecipeLib;
import cn.autosec.onecore.uss.definition.lib.StoneCutterRecipeLib;
import cn.autosec.onecore.uss.registry.ModRecipes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeGenerators extends FabricRecipeProvider {
    public RecipeGenerators(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        List<CraftingRecipeLib> craftingRecipeBuilders = ModRecipes.getCraftingRecipeBuilders();
        if (craftingRecipeBuilders != null) {
            for (CraftingRecipeLib lib : craftingRecipeBuilders) {
                if (lib.isConversionRecipe) {
                    lib.recipeBuilder.offerTo(recipeExporter, convertBetween(lib.conversionInput, lib.converter));
                } else {
                    lib.recipeBuilder.offerTo(recipeExporter);
                }
            }
        }

        List<FurnaceRecipeLib> furnaceRecipeLibs = ModRecipes.getFurnaceRecipes();
        if (furnaceRecipeLibs != null) {
            for (FurnaceRecipeLib furnaceRecipeLib : furnaceRecipeLibs) {
                FabricRecipeProvider.offerSmelting(recipeExporter, List.of(furnaceRecipeLib.input), furnaceRecipeLib.category,
                        furnaceRecipeLib.output, furnaceRecipeLib.exp, furnaceRecipeLib.time, OneCore.MOD_ID);
                if (furnaceRecipeLib.isOre) {
                    FabricRecipeProvider.offerBlasting(recipeExporter, List.of(furnaceRecipeLib.input), furnaceRecipeLib.category,
                            furnaceRecipeLib.output, furnaceRecipeLib.exp, furnaceRecipeLib.time / 2, OneCore.MOD_ID);
                }
            }
        }

        List<StoneCutterRecipeLib> stoneCutterRecipeLibs = ModRecipes.getStoneCutterRecipes();
        if (stoneCutterRecipeLibs != null) {
            for (StoneCutterRecipeLib stoneCutterRecipeLib : stoneCutterRecipeLibs) {
                FabricRecipeProvider.offerStonecuttingRecipe(recipeExporter, stoneCutterRecipeLib.category,
                        stoneCutterRecipeLib.output, stoneCutterRecipeLib.input, stoneCutterRecipeLib.outputNum);
            }
        }
    }
}
