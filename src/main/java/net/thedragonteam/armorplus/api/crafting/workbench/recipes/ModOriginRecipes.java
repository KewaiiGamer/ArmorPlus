/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;

import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EASY;
import static net.thedragonteam.armorplus.APConfig.RecipesDifficulty.EXPERT;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModOriginRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        /* Coal Armor */
        if (enableCoalArmor) {
            if (getRD() == EASY && enableCoalArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "   ", "CCC", "C C", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "CCC", "C C", "   ", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalChestplate), "C C", "CCC", "CCC", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalLeggings), "CCC", "C C", "C C", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "   ", "C C", "C C", 'C', "itemCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "C C", "C C", "   ", 'C', "itemCoal"));
            }
            if (getRD() == EASY && enableCharcoalCoalArmorRecipe) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "   ", "CCC", "C C", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "CCC", "C C", "   ", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalChestplate), "C C", "CCC", "CCC", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalLeggings), "CCC", "C C", "C C", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "   ", "C C", "C C", 'C', "itemCharcoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "C C", "C C", "   ", 'C', "itemCharcoal"));
            }
            if (getRD() == EXPERT && enableCoalArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "   ", "CCC", "C C", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalHelmet), "CCC", "C C", "   ", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalChestplate), "C C", "CCC", "CCC", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalLeggings), "CCC", "C C", "C C", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "C C", "C C", 'C', "blockCoal"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(coalBoots), "C C", "C C", "   ", 'C', "blockCoal"));
            }
        }
        /* Lapis Armor */
        if (enableLapisArmor) {
            if (getRD() == EASY && enableLapisArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisHelmet), "   ", "LLL", "L L", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisHelmet), "LLL", "L L", "   ", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisChestplate), "L L", "LLL", "LLL", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisLeggings), "LLL", "L L", "L L", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBoots), "   ", "L L", "L L", 'L', "gemLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBoots), "L L", "L L", "   ", 'L', "gemLapis"));
            }
            if (getRD() == EXPERT && enableLapisArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisHelmet), "   ", "LLL", "L L", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisHelmet), "LLL", "L L", "   ", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisChestplate), "L L", "LLL", "LLL", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisLeggings), "LLL", "L L", "L L", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBoots), "   ", "L L", "L L", 'L', "blockLapis"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(lapisBoots), "L L", "L L", "   ", 'L', "blockLapis"));
            }
        }
        /* Redstone Armor */
        if (enableRedstoneArmor) {
            if (getRD() == EASY && enableRedstoneArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneHelmet), "   ", "RRR", "R R", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneHelmet), "RRR", "R R", "   ", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneChestplate), "R R", "RRR", "RRR", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneLeggings), "RRR", "R R", "R R", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBoots), "   ", "R R", "R R", 'R', "dustRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBoots), "R R", "R R", "   ", 'R', "dustRedstone"));
            }
            if (getRD() == EXPERT && enableRedstoneArmorRecipes) {
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneHelmet), "   ", "RRR", "R R", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneHelmet), "RRR", "R R", "   ", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneChestplate), "R R", "RRR", "RRR", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneLeggings), "RRR", "R R", "R R", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBoots), "   ", "R R", "R R", 'R', "blockRedstone"));
                manager.addRecipe(new ShapedOreRecipe(getItemStack(redstoneBoots), "R R", "R R", "   ", 'R', "blockRedstone"));
            }
        }
    }
}