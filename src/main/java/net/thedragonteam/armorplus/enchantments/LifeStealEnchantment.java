/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.enchantments;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.thedragonteam.thedragonlib.util.LogHelper;

import static net.thedragonteam.armorplus.APConfig.debugMode;
import static net.thedragonteam.armorplus.APConfig.debugModeEnchantments;

public class LifeStealEnchantment extends EnchantmentBase {

    public LifeStealEnchantment() {
        super("life_steal",Rarity.RARE, EnumEnchantmentType.ALL, new EntityEquipmentSlot[]{
                EntityEquipmentSlot.MAINHAND},
                1, 3, 10, 15, true, true);
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
        Levels levelIn = Levels.values()[level];
        float damageDealt;
        if (user.getHeldItemMainhand().getCount() > 0 && !(user.getHeldItemMainhand().getItem() instanceof ItemTool) || user.getHeldItemOffhand().getCount() > 0 && !(user.getHeldItemOffhand().getItem() instanceof ItemTool) || user.getHeldItemMainhand().getCount() > 0 && !(user.getHeldItemMainhand().getItem() instanceof ItemSword) || user.getHeldItemOffhand().getCount() > 0 && !(user.getHeldItemOffhand().getItem() instanceof ItemSword))
            switch (levelIn) {
                case ZERO:
                    break;
                case ONE:
                    user.heal(0.5F);
                    break;
                case TWO:
                    user.heal(1.5F);
                    break;
                case THREE:
                    user.heal(2.5F);
                    break;
            }
        else if (user.getHeldItemMainhand().getCount() > 0 && user.getHeldItemMainhand().getItem() instanceof ItemTool || user.getHeldItemOffhand().getCount() > 0 && user.getHeldItemOffhand().getItem() instanceof ItemTool) {
            damageDealt = ((ItemTool) user.getHeldItemMainhand().getItem()).getToolMaterial().getDamageVsEntity();
            float damageDealtTool = damageDealt / 0.5F;
            switch (levelIn) {
                case ZERO:
                    break;
                case ONE:
                    if (damageDealtTool >= 0.0F) {
                        float damageGained = damageDealtTool + 0.5F;
                        float healedDamage = 0.0F;
                        if (damageGained <= 4.0F) healedDamage = ((0.5F * damageGained) + 0.5F) - 0.312F;
                        else if (damageGained >= 4.5F && damageGained < 9.5F)
                            healedDamage = (((0.5F * damageGained + 1.0F) / 2F) + 0.272F);
                        else if (damageGained >= 9.5F && damageGained < 14.5F)
                            healedDamage = (((0.5F * damageGained - 5.0F) / 2F) + 0.411F);
                        else if (damageGained >= 14.5F && damageGained < 19.5F)
                            healedDamage = (((0.5F * damageGained - 10.0F) / 2F) + 0.914F);
                        else if (damageGained >= 19.5F) healedDamage = (((0.5F * damageGained - 15.0F) / 2F) + 1.21F);
                        user.heal(healedDamage);
                        if (debugMode && debugModeEnchantments)
                            LogHelper.info("Level 1 Dealt Damage: " + damageDealt + " Final Damage: " + damageDealtTool + " Gained Damage: " + damageGained + " Healed Damage: " + healedDamage);
                    }
                    break;
                case TWO:
                    if (damageDealtTool >= 0.0F) {
                        float damageGained = damageDealtTool + 1.5F;
                        float healedDamage = 0.0F;
                        if (damageGained <= 4.0F) healedDamage = ((1.5F * damageGained) + 0.312F) - 0.34F;
                        else if (damageGained >= 4.5F && damageGained < 9.5F)
                            healedDamage = (((1.5F * damageGained - 1.0F) / 2F) - 0.82F);
                        else if (damageGained >= 9.5F && damageGained < 14.5F)
                            healedDamage = (((1.5F * damageGained - 4.0F) / 2F) - 1.11F);
                        else if (damageGained >= 14.5F && damageGained < 19.5F)
                            healedDamage = (((1.5F * damageGained - 10.0F) / 2F) - 3.0F);
                        else if (damageGained >= 19.5F) healedDamage = (((1.5F * damageGained - 15.0F) / 2F) - 5.23F);
                        user.heal(healedDamage);
                        if (debugMode && debugModeEnchantments)
                            LogHelper.info("Level 2 Dealt Damage: " + damageDealt + " Final Damage: " + damageDealtTool + " Gained Damage: " + damageGained + " Healed Damage: " + healedDamage);
                    }
                    break;
                case THREE:
                    if (damageDealtTool >= 0.0F) {
                        float damageGained = damageDealtTool + 2.5F;
                        float healedDamage = 0.0F;
                        if (damageGained <= 4.0F) healedDamage = ((2.5F * damageGained) - 2.0F);
                        else if (damageGained >= 4.5F && damageGained < 9.5F)
                            healedDamage = (((2.5F * damageGained - 3.0F) / 2F) - 2.53F);
                        else if (damageGained >= 9.5F && damageGained < 14.5F)
                            healedDamage = (((2.5F * damageGained - 4.0F) / 2F) - 3.4F);
                        else if (damageGained >= 14.5F && damageGained < 19.5F)
                            healedDamage = (((2.5F * damageGained - 10.0F) / 2F) - 6.5F);
                        else if (damageGained >= 19.5F) healedDamage = (((2.5F * damageGained - 15.0F) / 2F) - 10.5F);
                        user.heal(healedDamage);
                        if (debugMode && debugModeEnchantments)
                            LogHelper.info("Level 3 Dealt Damage: " + damageDealt + " Final Damage: " + damageDealtTool + " Gained Damage: " + damageGained + " Healed Damage: " + healedDamage);
                    }
                    break;
            }
        } else if (user.getHeldItemMainhand().getCount() > 0 && user.getHeldItemMainhand().getItem() instanceof ItemSword || user.getHeldItemOffhand().getCount() > 0 && user.getHeldItemOffhand().getItem() instanceof ItemSword) {
            damageDealt = ((ItemSword) user.getHeldItemMainhand().getItem()).getDamageVsEntity();
            float damageDealtSword = damageDealt / 0.5F;
            switch (levelIn) {
                case ZERO:
                    break;
                case ONE:
                    if (damageDealtSword >= 0.0F) {
                        float damageGained = damageDealtSword + 0.5F;
                        float healedDamage = 0.0F;
                        if (damageGained <= 4.5F) healedDamage = ((0.5F * damageGained) + 0.534F) + 0.135F;
                        else if (damageGained >= 5.0F && damageGained < 10.0F)
                            healedDamage = (((0.5F * damageGained + 1.0F) / 2F) + 0.84F);
                        else if (damageGained >= 10.0F && damageGained < 15.0F)
                            healedDamage = (((0.5F * damageGained - 4.0F) / 2F) + 0.525F);
                        else if (damageGained >= 15.0F && damageGained < 20.0F)
                            healedDamage = (((0.5F * damageGained - 6.0F) / 2F) + 2.125F);
                        else if (damageGained >= 20.0F) healedDamage = (((0.5F * damageGained - 12.0F) / 2F) + 2.5F);
                        user.heal(healedDamage);
                        if (debugMode && debugModeEnchantments)
                            LogHelper.info("Level 1 Dealt Damage: " + damageDealt + " Final Damage: " + damageDealtSword + " Gained Damage: " + damageGained + " Healed Damage: " + healedDamage);
                    }
                    break;
                case TWO:
                    if (damageDealtSword >= 0.0F) {
                        float damageGained = damageDealtSword + 1.5F;
                        float healedDamage = 0.0F;
                        if (damageGained <= 4.5F) healedDamage = ((1.5F * damageGained) - 1.0F);
                        else if (damageGained >= 5.0F && damageGained < 10.0F)
                            healedDamage = (((1.5F * damageGained - 1.0F) / 2F) - 1.0F);
                        else if (damageGained >= 10.0F && damageGained < 15.0F)
                            healedDamage = (((1.5F * damageGained - 4.0F) / 2F) - 1.2F);
                        else if (damageGained >= 15.0F && damageGained < 20.0F)
                            healedDamage = (((1.5F * damageGained - 10.0F) / 2F) - 3.0F);
                        else if (damageGained >= 20.0F) healedDamage = (((1.5F * damageGained - 15.0F) / 2F) - 3.3F);
                        user.heal(healedDamage);
                        if (debugMode && debugModeEnchantments)
                            LogHelper.info("Level 2 Dealt Damage: " + damageDealt + " Final Damage: " + damageDealtSword + " Gained Damage: " + damageGained + " Healed Damage: " + healedDamage);
                    }
                    break;
                case THREE:
                    if (damageDealtSword >= 0.0F) {
                        float damageGained = damageDealtSword + 2.5F;
                        float healedDamage = 0.0F;
                        if (damageGained <= 4.5F) healedDamage = ((2.5F * damageGained) - 2.0F);
                        else if (damageGained >= 5.0F && damageGained < 10.0F)
                            healedDamage = (((2.5F * damageGained - 3.0F) / 2F) - 2.52F);
                        else if (damageGained >= 10.0F && damageGained < 15.0F)
                            healedDamage = (((2.5F * damageGained - 4.0F) / 2F) - 5.5F);
                        else if (damageGained >= 15.0F && damageGained < 20.0F)
                            healedDamage = (((2.5F * damageGained - 10.0F) / 2F) - 6.3F);
                        else if (damageGained >= 20.0F) healedDamage = (((2.5F * damageGained - 15.0F) / 2F) - 13.22F);
                        user.heal(healedDamage);
                        if (debugMode && debugModeEnchantments)
                            LogHelper.info("Level 3 Dealt Damage: " + damageDealt + " Final Damage: " + damageDealtSword + " Gained Damage: " + damageGained + " Healed Damage: " + healedDamage);
                    }
                    break;
            }
        }
    }
}
