/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.ultitechbench;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ShapedOreRecipe implements IRecipe {
    //Added in for future ease of change, but hard coded for now.
    public static final int MAX_CRAFT_GRID_WIDTH = 5;
    public static final int MAX_CRAFT_GRID_HEIGHT = 5;

    protected ItemStack output = ItemStack.EMPTY;
    protected Object[] input = null;
    protected int width = 0;
    protected int height = 0;
    protected boolean mirrored = true;

    public ShapedOreRecipe(Block result, Object... recipe) {
        this(new ItemStack(result), recipe);
    }

    public ShapedOreRecipe(Item result, Object... recipe) {
        this(new ItemStack(result), recipe);
    }

    public ShapedOreRecipe(ItemStack result, Object... recipe) {
        output = result.copy();

        String shape = "";
        int idx = 0;

        if (recipe[idx] instanceof Boolean) {
            mirrored = (Boolean) recipe[idx];
            if (recipe[idx + 1] instanceof Object[]) recipe = (Object[]) recipe[idx + 1];
            else idx = 1;
        }

        if (recipe[idx] instanceof String[]) {
            String[] parts = ((String[]) recipe[idx++]);

            for (String s : parts) {
                width = s.length();
                shape += s;
            }

            height = parts.length;
        } else while (recipe[idx] instanceof String) {
            String s = (String) recipe[idx++];
            shape += s;
            width = s.length();
            height++;
        }

        if (width * height != shape.length()) {
            String ret = "Invalid shaped ore recipe: ";
            for (Object tmp : recipe) ret += tmp + ", ";
            ret += output;
            throw new RuntimeException(ret);
        }

        HashMap<Character, Object> itemMap = new HashMap<>();

        for (; idx < recipe.length; idx += 2) {
            Character chr = (Character) recipe[idx];
            Object in = recipe[idx + 1];

            if (in instanceof ItemStack) itemMap.put(chr, ((ItemStack) in).copy());
            else if (in instanceof Item) itemMap.put(chr, new ItemStack((Item) in));
            else if (in instanceof Block) itemMap.put(chr, new ItemStack((Block) in, 1, OreDictionary.WILDCARD_VALUE));
            else if (in instanceof String) itemMap.put(chr, OreDictionary.getOres((String) in));
            else {
                String ret = "Invalid shaped ore recipe: ";
                for (Object tmp : recipe) ret += tmp + ", ";
                ret += output;
                throw new RuntimeException(ret);
            }
        }

        input = new Object[width * height];
        int x = 0;
        for (char chr : shape.toCharArray()) input[x++] = itemMap.get(chr);
    }

    ShapedOreRecipe(ShapedRecipes recipe, Map<ItemStack, String> replacements) {
        output = recipe.getRecipeOutput();
        width = recipe.recipeWidth;
        height = recipe.recipeHeight;

        input = new Object[recipe.input.length];

        for (int i = 0; i < input.length; i++) {
            ItemStack ingredient = recipe.input[i];

            if (ingredient.isEmpty()) continue;

            input[i] = recipe.input[i];

            for (Map.Entry<ItemStack, String> replace : replacements.entrySet())
                if (OreDictionary.itemMatches(replace.getKey(), ingredient, true)) {
                    input[i] = OreDictionary.getOres(replace.getValue());
                    break;
                }
        }
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Override
    @Nonnull
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting var1) {
        return output.copy();
    }

    /**
     * Returns the size of the recipe area
     */
    @Override
    public int getRecipeSize() {
        return input.length;
    }

    @Override
    @Nonnull
    public ItemStack getRecipeOutput() {
        return output;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World world) {
        for (int x = 0; x <= MAX_CRAFT_GRID_WIDTH - width; x++) {
            for (int y = 0; y <= MAX_CRAFT_GRID_HEIGHT - height; ++y) {
                if (checkMatch(inv, x, y, false) || mirrored && checkMatch(inv, x, y, true)) {
                    return true;
                }

            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    protected boolean checkMatch(InventoryCrafting inv, int startX, int startY, boolean mirror) {
        for (int x = 0; x < MAX_CRAFT_GRID_WIDTH; x++) {
            for (int y = 0; y < MAX_CRAFT_GRID_HEIGHT; y++) {
                int subX = x - startX;
                int subY = y - startY;
                Object target = null;

                if (subX >= 0 && subY >= 0 && subX < width && subY < height)
                    target = mirror ? input[width - subX - 1 + subY * width] : input[subX + subY * width];

                ItemStack slot = inv.getStackInRowAndColumn(x, y);

                if (target instanceof ItemStack) {
                    if (!OreDictionary.itemMatches((ItemStack) target, slot, false)) return false;
                } else if (target instanceof List) {
                    boolean matched = false;

                    Iterator<ItemStack> itr = ((List<ItemStack>) target).iterator();
                    while (itr.hasNext() && !matched) matched = OreDictionary.itemMatches(itr.next(), slot, false);

                    if (!matched) return false;
                } else if (target == null && !slot.isEmpty()) return false;
            }
        }

        return true;
    }

    public ShapedOreRecipe setMirrored(boolean mirror) {
        mirrored = mirror;
        return this;
    }

    /**
     * Returns the input for this recipe, any mod accessing this value should never
     * manipulate the values in this array as it will effect the recipe itself.
     *
     * @return The recipes input vales.
     */
    public Object[] getInput() {
        return this.input;
    }

    //getRecipeLeftovers
    @Override
    @Nonnull
    public NonNullList<ItemStack> getRemainingItems(@Nonnull InventoryCrafting inv) {
        return ForgeHooks.defaultRecipeGetRemainingItems(inv);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}