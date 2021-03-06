/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

import javax.annotation.Nonnull;

import static net.thedragonteam.armorplus.APConfig.theUltimateArmorItemNameColor;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;
import static net.thedragonteam.armorplus.util.Utils.setName;

public class ItemUltimateParts extends Item {

    private final String[] ULTIMATE_NAMES = new String[]{
            "_helmet_right", "_helmet_middle", "_helmet_left",
            "_chestplate_right", "_chestplate_middle", "_chestplate_left",
            "_leggings_right", "_leggings_middle", "_leggings_left",
            "_boots_right", "_boots_middle", "_boots_left"
    };
    public EnumRarity formattingName;

    public ItemUltimateParts() {
        setHasSubtypes(true);
        setRegistryName("the_ultimate_part");
        setUnlocalizedName(setName("the_ultimate_part"));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
        formattingName = addRarity("ULTIMATE", theUltimateArmorItemNameColor, "Ultimate");
    }

    @Override
    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return formattingName;
    }

    @Override
    @Nonnull
    public String getUnlocalizedName(ItemStack stack) {
        for (int i = 0; i < ULTIMATE_NAMES.length; i++)
            if (stack.getItemDamage() == i)
                return super.getUnlocalizedName(stack) + ULTIMATE_NAMES[i];
        return super.getUnlocalizedName();
    }

    @Override
    public void getSubItems(@Nonnull Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (int i = 0; i < ULTIMATE_NAMES.length; i++)
            subItems.add(new ItemStack(itemIn, 1, i));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        for (int i = 0; i < ULTIMATE_NAMES.length; i++)
            ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(getRegistryName() + ULTIMATE_NAMES[i], "inventory"));
    }
}
