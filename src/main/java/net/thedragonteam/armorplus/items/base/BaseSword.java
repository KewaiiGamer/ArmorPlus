/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.util.Utils;

import javax.annotation.Nonnull;

import static net.thedragonteam.armorplus.util.ArmorPlusItemUtils.isItemRepairable;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;

public class BaseSword extends ItemSword implements IItemHelper {

    public Item itemEasy;
    public Item itemExpert;
    public TextFormatting formatting;
    public String effect;
    public EnumRarity formattingName;
    public String itemName;

    public BaseSword(ToolMaterial material, String name, Item repairEasy, Item repairExpert, String textFormatting, String effectName) {
        super(material);
        this.itemName = name;
        this.itemEasy = repairEasy;
        this.itemExpert = repairExpert;
        this.formatting = TextFormatting.getValueByName(textFormatting);
        this.effect = effectName;
        this.setRegistryName(name);
        this.setUnlocalizedName(Utils.setName(name));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
        formattingName = addRarity("SWORD", formatting, "Sword");
    }

    @Override
    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return formattingName;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, @Nonnull ItemStack repair) {
        return isItemRepairable(repair, itemEasy, itemExpert);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public ItemStack getItemStack(ItemStack stack) {
        return stack;
    }

    @Override
    public Item getItem(Item item) {
        return item;
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(this);
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public String getName(String name) {
        return name;
    }

    @Override
    public String getName() {
        return this.itemName;
    }
}
