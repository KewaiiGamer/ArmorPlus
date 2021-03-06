/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.workbench;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.BrokenCraftingRecipeException;
import mezz.jei.util.ErrorUtil;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapelessRecipes;

import javax.annotation.Nonnull;

class WBShapelessRecipeWrapper extends BlankRecipeWrapper implements IRecipeWrapper {

    private final ShapelessRecipes recipe;

    public WBShapelessRecipeWrapper(ShapelessRecipes recipe) {
        this.recipe = recipe;
        for (Object input : this.recipe.input) {
            if (input instanceof ItemStack) {
                ItemStack itemStack = (ItemStack) input;
                if (itemStack.getCount() != 1) {
                    itemStack.setCount(1);
                }
            }
        }
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ItemStack recipeOutput = recipe.getRecipeOutput();

        try {
            ingredients.setInputs(ItemStack.class, recipe.input);
            ingredients.setOutput(ItemStack.class, recipeOutput);
        } catch (RuntimeException e) {
            String info = ErrorUtil.getInfoFromBrokenCraftingRecipe(recipe, recipe.input, recipeOutput);
            throw new BrokenCraftingRecipeException(info, e);
        }
    }
}