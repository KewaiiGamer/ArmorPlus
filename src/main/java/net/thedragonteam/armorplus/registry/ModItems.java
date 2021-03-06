/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.armors.base.ItemArmorBase;
import net.thedragonteam.armorplus.armors.base.ItemUltimateArmor;
import net.thedragonteam.armorplus.items.ItemUltimateParts;
import net.thedragonteam.armorplus.items.arrows.*;
import net.thedragonteam.armorplus.items.base.*;
import net.thedragonteam.armorplus.items.baubles.ItemBaubleDragon;
import net.thedragonteam.armorplus.items.books.ItemAPBook;
import net.thedragonteam.armorplus.items.consumables.RedstoneApple;
import net.thedragonteam.armorplus.items.consumables.TheGiftOfTheGods;
import net.thedragonteam.armorplus.items.dev.DevTool;
import net.thedragonteam.armorplus.items.dev.NBTItem;
import net.thedragonteam.armorplus.items.energy.tesla.*;
import net.thedragonteam.armorplus.items.enums.*;
import net.thedragonteam.armorplus.items.materials.ItemMaterial;
import net.thedragonteam.armorplus.items.materials.LavaCrystal;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.isBaublesLoaded;
import static net.thedragonteam.armorplus.ArmorPlus.isTeslaLoaded;
import static net.thedragonteam.armorplus.armors.APArmorMaterial.*;
import static net.thedragonteam.armorplus.registry.RegistryUtils.*;

public class ModItems {

    public static ItemAPBook bookInfo;
    public static ItemMaterial materials;
    public static BaseItem steelIngot, electricalIngot;
    public static RedstoneApple redstoneApple;
    public static LavaCrystal lavaCrystal;
    public static TheGiftOfTheGods theGiftOfTheGods;
    public static NBTItem nbtItem;
    public static boolean[] isEnabled = new boolean[]{
            enableCoalArmor, enableEmeraldArmor, enableLapisArmor, enableLavaArmor, enableObsidianArmor, enableRedstoneArmor,
            enableChickenArmor, enableSlimeArmor, enableEnderDragonArmor, enableGuardianArmor, enableSuperStarArmor,
    };
    public static Swords[] swordType = new Swords[]{
            Swords.COAL, Swords.LAPIS, Swords.REDSTONE, Swords.EMERALD, Swords.OBSIDIAN, Swords.LAVA, Swords.GUARDIAN, Swords.SUPER_STAR, Swords.ENDER_DRAGON
    };
    public static BattleAxes[] battleAxeType = new BattleAxes[]{
            BattleAxes.COAL, BattleAxes.LAPIS, BattleAxes.REDSTONE, BattleAxes.EMERALD, BattleAxes.OBSIDIAN, BattleAxes.LAVA, BattleAxes.GUARDIAN, BattleAxes.SUPER_STAR, BattleAxes.ENDER_DRAGON
    };
    public static Bows[] bowType = new Bows[]{
            Bows.COAL, Bows.LAPIS, Bows.REDSTONE, Bows.EMERALD, Bows.OBSIDIAN, Bows.LAVA, Bows.GUARDIAN, Bows.SUPER_STAR, Bows.ENDER_DRAGON
    };
    public static boolean[] isSwordEnabled = new boolean[]{
            enableCoalSword, enableLapisSword, enableRedstoneSword, enableEmeraldSword, enableObsidianSword, enableLavaSword, enableGuardianSword, enableSuperStarSword, enableEnderDragonSword
    };
    public static boolean[] isBowEnabled = new boolean[]{
            enableCoalBow, enableLapisBow, enableRedstoneBow, enableEmeraldBow, enableObsidianBow, enableLavaBow, enableGuardianBow, enableSuperStarBow, enableEnderDragonBow
    };
    public static boolean[] isBattleAxeEnabled = new boolean[]{
            enableCoalBattleAxe, enableLapisBattleAxe, enableRedstoneBattleAxe, enableEmeraldBattleAxe, enableObsidianBattleAxe, enableLavaBattleAxe, enableGuardianBattleAxe, enableSuperStarBattleAxe, enableEnderDragonBattleAxe
    };
    private static ItemArmorBase coalHelmet, coalChestplate, coalLeggings, coalBoots,
            emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots,
            obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots,
            redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots,
            lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots,
            lavaBoots, lavaHelmet, lavaChestplate, lavaLeggings,
            chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots,
            slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots,
            enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots,
            guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots,
            superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots,
            arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots,
            cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots,
            manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots,
            pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots,
            knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots;
    public static ItemArmorBase[] coal = new ItemArmorBase[]{coalHelmet, coalChestplate, coalLeggings, coalBoots};
    public static ItemArmorBase[] emerald = new ItemArmorBase[]{emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots};
    public static ItemArmorBase[] obsidian = new ItemArmorBase[]{obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots};
    public static ItemArmorBase[] redstone = new ItemArmorBase[]{redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots};
    public static ItemArmorBase[] lapis = new ItemArmorBase[]{lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots};
    public static ItemArmorBase[] lava = new ItemArmorBase[]{lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots};
    public static ItemArmorBase[] chicken = new ItemArmorBase[]{chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots};
    public static ItemArmorBase[] slime = new ItemArmorBase[]{slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots};
    public static ItemArmorBase[] enderDragon = new ItemArmorBase[]{enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots};
    public static ItemArmorBase[] guardian = new ItemArmorBase[]{guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots};
    public static ItemArmorBase[] superStar = new ItemArmorBase[]{superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots};
    public static ItemArmorBase[] ardite = new ItemArmorBase[]{arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots};
    public static ItemArmorBase[] cobalt = new ItemArmorBase[]{cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots};
    public static ItemArmorBase[] manyullyn = new ItemArmorBase[]{manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots};
    public static ItemArmorBase[] pigIron = new ItemArmorBase[]{pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots};
    public static ItemArmorBase[] knightSlime = new ItemArmorBase[]{knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots};
    //  private static APArmorMaterial[] armorMaterials = new APArmorMaterial[]{COAL, EMERALD, OBSIDIAN, REDSTONE, LAPIS, LAVA, CHICKEN, SLIME, ENDER_DRAGON, GUARDIAN, SUPER_STAR, ARDITE, COBALT, MANYULLYN, PIG_IRON, KNIGHT_SLIME};

    private static ItemUltimateArmor theUltimateHelmet, theUltimateChestplate, theUltimateLeggings, theUltimateBoots;
    public static ItemUltimateArmor[] theUltimate = new ItemUltimateArmor[]{
            theUltimateHelmet, theUltimateChestplate, theUltimateLeggings, theUltimateBoots
    };
    private static ItemSpecialSword coalSword, lapisSword, redstoneSword,
            emeraldSword, obsidianSword, lavaSword,
            guardianSword, superStarSword, enderDragonSword;
    public static ItemSpecialSword[] sword = new ItemSpecialSword[]{
            coalSword, lapisSword, redstoneSword, emeraldSword, obsidianSword, lavaSword, guardianSword, superStarSword, enderDragonSword
    };
    private static ItemSpecialBattleAxe coalBattleAxe, lapisBattleAxe, redstoneBattleAxe,
            emeraldBattleAxe, obsidianBattleAxe, lavaBattleAxe,
            guardianBattleAxe, superStarBattleAxe, enderDragonBattleAxe;
    public static ItemSpecialBattleAxe[] battleAxe = new ItemSpecialBattleAxe[]{
            coalBattleAxe, lapisBattleAxe, redstoneBattleAxe, emeraldBattleAxe, obsidianBattleAxe, lavaBattleAxe, guardianBattleAxe, superStarBattleAxe, enderDragonBattleAxe
    };
    private static ItemSpecialBow coalBow, lapisBow, redstoneBow,
            emeraldBow, obsidianBow, lavaBow,
            guardianBow, superStarBow, enderDragonBow;
    public static ItemSpecialBow[] bow = new ItemSpecialBow[]{
            coalBow, lapisBow, redstoneBow, emeraldBow, obsidianBow, lavaBow, guardianBow, superStarBow, enderDragonBow
    };
    public static ItemUltimateParts theUltimateParts;
    public static DevTool devTool;
    public static BaseDevItem theDragonTeamItem, moddedCityItem, jonBamsItem, twitchItem, beamItem;
    public static ItemTeslaPickaxe itemTeslaPickaxe;
    public static ItemTeslaSword itemTeslaSword;
    public static ItemTeslaAxe itemTeslaAxe;
    public static ItemTeslaRod itemTeslaRod;
    public static ItemTeslaHoe itemTeslaHoe;
    public static ItemTeslaShovel itemTeslaShovel;
    public static ItemCoalArrow itemCoalArrow;
    public static ItemLapisArrow itemLapisArrow;
    public static ItemRedstoneArrow itemRedstoneArrow;
    public static ItemLavaArrow itemLavaArrow;
    public static ItemEnderDragonArrow itemEnderDragonArrow;
    public static ItemBaubleDragon itemBaubleDragon;
    public static EntityEquipmentSlot[] equipmentSlots = new EntityEquipmentSlot[]{HEAD, CHEST, LEGS, FEET};

    public static void init() {
        if (isBaublesLoaded()) {
            itemBaubleDragon = new ItemBaubleDragon();
        }
        if (isTeslaLoaded()) {
            itemTeslaShovel = new ItemTeslaShovel();
            itemTeslaHoe = new ItemTeslaHoe();
            itemTeslaAxe = new ItemTeslaAxe();
            itemTeslaPickaxe = new ItemTeslaPickaxe();
            itemTeslaSword = new ItemTeslaSword();
        }
        itemTeslaRod = new ItemTeslaRod();
        twitchItem = new BaseDevItem(DevItems.TWITCH);
        beamItem = new BaseDevItem(DevItems.BEAM);
        theDragonTeamItem = new BaseDevItem(DevItems.THE_DRAGON_TEAM);
        moddedCityItem = new BaseDevItem(DevItems.MODDED_CITY);
        jonBamsItem = new BaseDevItem(DevItems.JON_BAMS);
        materials = new ItemMaterial();
        lavaCrystal = new LavaCrystal();
        theGiftOfTheGods = new TheGiftOfTheGods();
        bookInfo = new ItemAPBook();
        steelIngot = new BaseItem(Items.STEEL_INGOT);
        electricalIngot = new BaseItem(Items.ELECTRICAL_INGOT);
        redstoneApple = new RedstoneApple();
        nbtItem = new NBTItem();
        //Armors
        registerArmor(enableCoalArmor, coal, COAL);
        registerArmor(enableEmeraldArmor, emerald, EMERALD);
        registerArmor(enableLapisArmor, lapis, LAPIS);
        registerArmor(enableLavaArmor, lava, LAVA);
        registerArmor(enableObsidianArmor, obsidian, OBSIDIAN);
        registerArmor(enableRedstoneArmor, redstone, REDSTONE);
        registerArmor(enableChickenArmor, chicken, CHICKEN);
        registerArmor(enableSlimeArmor, slime, SLIME);
        registerArmor(enableEnderDragonArmor, enderDragon, ENDER_DRAGON);
        registerArmor(enableGuardianArmor, guardian, GUARDIAN);
        registerArmor(enableSuperStarArmor, superStar, SUPER_STAR);
        registerArmor(enableArditeArmor, ardite, ARDITE);
        registerArmor(enableCobaltArmor, cobalt, COBALT);
        registerArmor(enableManyullynArmor, manyullyn, MANYULLYN);
        registerArmor(enablePigIronArmor, pigIron, PIG_IRON);
        registerArmor(enableKnightSlimeArmor, knightSlime, KNIGHT_SLIME);
        registerArmor(enableTheUltimateArmor, theUltimate);
        //Swords
        registerSword(isSwordEnabled, sword, swordType);
        //BattleAxes
        registerBattleAxe(isBattleAxeEnabled, battleAxe, battleAxeType);
        //Bows
        registerBow(isBowEnabled, bow, bowType);
        devTool = new DevTool();
        itemCoalArrow = new ItemCoalArrow();
        itemLapisArrow = new ItemLapisArrow();
        itemRedstoneArrow = new ItemRedstoneArrow();
        itemLavaArrow = new ItemLavaArrow();
        itemEnderDragonArrow = new ItemEnderDragonArrow();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        itemEnderDragonArrow.initModel();
        if (isBaublesLoaded()) {
            itemBaubleDragon.initModel();
        }
        if (isTeslaLoaded()) {
            itemTeslaShovel.initModel();
            itemTeslaHoe.initModel();
            itemTeslaAxe.initModel();
            itemTeslaPickaxe.initModel();
            itemTeslaSword.initModel();
        }
        itemTeslaRod.initModel();
        twitchItem.initModel();
        beamItem.initModel();
        theDragonTeamItem.initModel();
        moddedCityItem.initModel();
        jonBamsItem.initModel();
        materials.initModel();
        lavaCrystal.initModel();
        theGiftOfTheGods.initModel();
        bookInfo.initModel();
        steelIngot.initModel();
        electricalIngot.initModel();
        redstoneApple.initModel();
        registerArmorModel(enableCoalArmor, coal);
        registerArmorModel(enableEmeraldArmor, emerald);
        registerArmorModel(enableLapisArmor, lapis);
        registerArmorModel(enableLavaArmor, lava);
        registerArmorModel(enableObsidianArmor, obsidian);
        registerArmorModel(enableRedstoneArmor, redstone);
        registerArmorModel(enableChickenArmor, chicken);
        registerArmorModel(enableSlimeArmor, slime);
        registerArmorModel(enableEnderDragonArmor, enderDragon);
        registerArmorModel(enableGuardianArmor, guardian);
        registerArmorModel(enableSuperStarArmor, superStar);
        registerArmorModel(enableArditeArmor, ardite);
        registerArmorModel(enableCobaltArmor, cobalt);
        registerArmorModel(enableManyullynArmor, manyullyn);
        registerArmorModel(enablePigIronArmor, pigIron);
        registerArmorModel(enableKnightSlimeArmor, knightSlime);
        registerArmorModel(enableTheUltimateArmor, theUltimate);
        registerSwordModel(isSwordEnabled, sword);
        registerBattleAxeModel(isBattleAxeEnabled, battleAxe);
        registerBowModel(isBowEnabled, bow);
        devTool.initModel();
        nbtItem.initModel();
        itemCoalArrow.initModel();
        itemLapisArrow.initModel();
        itemRedstoneArrow.initModel();
        itemLavaArrow.initModel();
    }
}
