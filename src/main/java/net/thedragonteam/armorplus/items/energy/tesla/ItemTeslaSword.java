/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.energy.tesla;

import com.google.common.collect.Multimap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional.Method;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.energy.tesla.BaseTeslaSword;
import net.thedragonteam.armorplus.util.APTeslaUtils;

import javax.annotation.Nonnull;
import java.util.List;

import static net.thedragonteam.armorplus.APConfig.*;

public class ItemTeslaSword extends BaseTeslaSword {

    private float attackDamage;
    private ItemStack stack;

    public ItemTeslaSword() {
        super(ToolMaterial.DIAMOND, "tesla_sword", maxCapacitySword, inputSword, outputSword);
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return ArmorPlus.tabArmorplusTesla;
    }

    @Method(modid = "tesla")
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
        APTeslaUtils.usePower(stack, outputSword);
        return true;
    }

    @Override
    @Nonnull
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        switch (equipmentSlot) {
            case MAINHAND:
                multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.attackDamage, 0));
                multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
                break;
        }

        return multimap;
    }

    @Method(modid = "tesla")
    @Override
    public boolean onBlockDestroyed(@Nonnull ItemStack stack, @Nonnull World worldIn, IBlockState state, @Nonnull BlockPos pos, @Nonnull EntityLivingBase entityLiving) {
        APTeslaUtils.usePower(stack, outputSword);
        return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state) {
        return Items.DIAMOND_SWORD.canHarvestBlock(state);
    }

    @Method(modid = "tesla")
    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        return APTeslaUtils.getStoredPower(stack) < outputSword ? 0.5F : Items.WOODEN_SWORD.getStrVsBlock(stack, state) > 1.0F ? 5.5F : super.getStrVsBlock(stack, state);
    }

    @Method(modid = "tesla")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        createTooltip(stack, tooltip);
    }

    @Method(modid = "tesla")
    private void createTooltip(ItemStack stack, List<String> tooltip) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add(TextFormatting.DARK_AQUA + I18n.format("tooltip.tesla.powerinfo", Long.toString(APTeslaUtils.getStoredPower(stack)), Long.toString(APTeslaUtils.getMaxCapacity(stack))));
            tooltip.add(TextFormatting.DARK_AQUA + I18n.format("tooltip.tesla.cost.hit", Long.toString(outputSword)));
        } else
            tooltip.add(I18n.format("tooltip.tesla.showinfo", TextFormatting.DARK_AQUA, keyBindSneak.getDisplayName(), TextFormatting.GRAY));
    }
}