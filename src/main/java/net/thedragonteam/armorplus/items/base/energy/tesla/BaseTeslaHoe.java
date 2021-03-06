/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base.energy.tesla;

import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Optional.Method;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.base.BaseAPTeslaContainerProvider;
import net.thedragonteam.armorplus.items.base.BaseHoe;
import net.thedragonteam.armorplus.util.APTeslaUtils;

import javax.annotation.Nonnull;
import java.util.Set;

import static net.thedragonteam.armorplus.APConfig.teslaWeaponItemNameColor;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;

public class BaseTeslaHoe extends BaseHoe {

    public EnumRarity formattingName;
    private int cost = 10;
    private int maxCapacity;
    private int output;
    private int input;

    public BaseTeslaHoe(ToolMaterial material, String name, Set<Block> effectiveOn, int maxCapacity, int input, int output) {
        super(material, name);
        this.setCreativeTab(ArmorPlus.tabArmorplusTesla);
        this.setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    public BaseTeslaHoe(ToolMaterial material, String name, int maxCapacity, int input, int output) {
        this(material, name, null, maxCapacity, input, output);
        this.setCreativeTab(ArmorPlus.tabArmorplusTesla);
        this.setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
        this.formattingName = addRarity("TESLA", teslaWeaponItemNameColor, "Tesla");
    }

    @Override
    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return formattingName;
    }

    @Override
    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, @Nonnull World worldIn, BlockPos posIn, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ) {
        APTeslaUtils.usePower(player.getHeldItem(hand), cost);
        return super.onItemUse(player, worldIn, posIn, hand, facing, hitX, hitY, hitZ);
    }

    @Method(modid = "tesla")
    @Override
    public void getSubItems(@Nonnull Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        ItemStack powered = APTeslaUtils.createChargedStack(new ItemStack(itemIn));
        ItemStack unpowered = new ItemStack(itemIn);
        subItems.add(powered);
        subItems.add(unpowered);
    }

    @Override
    public boolean isRepairable() {
        return true;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return 30;
    }

    @Method(modid = "tesla")
    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return (1 - (double) APTeslaUtils.getStoredPower(stack) / (double) APTeslaUtils.getMaxCapacity(stack));
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Method(modid = "tesla")
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new BaseAPTeslaContainerProvider(new BaseTeslaContainer(), maxCapacity, output, input);
    }
}
