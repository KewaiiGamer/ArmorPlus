/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.proxy;

import net.minecraft.util.datafix.DataFixesManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.client.gui.APTab;
import net.thedragonteam.armorplus.commands.CommandArmorPlus;
import net.thedragonteam.armorplus.registry.*;
import net.thedragonteam.armorplus.resources.GlobalEventsArmorPlus;
import net.thedragonteam.armorplus.tileentity.*;
import net.thedragonteam.armorplus.worldgen.OreGen;
import net.thedragonteam.armorplus.worldgen.StructureGen;
import net.thedragonteam.armorplus.worldgen.nbt.StructureGenNBT;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.io.File;

import static net.thedragonteam.armorplus.compat.ICompatibility.InitializationPhase.*;

public class CommonProxy {

    public static File configDir;

    public void preInit(FMLPreInitializationEvent event) {
        configDir = new File(event.getModConfigurationDirectory() + "/" + ArmorPlus.MODID);
        configDir.mkdirs();
        ModEntities.init();
        ModBlocks.init();
        LogHelper.debug("Blocks Successfully Registered");
        ModItems.init(); // Initializes the items
        APItems.init(); // Initializes the helper item class
        APBlocks.init();
        LogHelper.debug("Items Successfully Registered");
        registerWorldGenerators();
        registerTileEntities();
        TileEntityLavaInfuser.registerFixesFurnace(DataFixesManager.createFixer());
        MinecraftForge.EVENT_BUS.register(new MobDrops());
        ModCompatibility.registerModCompat();
        ModCompatibility.loadCompat(PRE_INIT);
        LogHelper.info("Finished PreInitialization");
    }

    public void init(FMLInitializationEvent event) {
        registerEvents();
        ModOreDicts.registerOreDictEntries();
        APTab.initialize();
        ModEnchantments.registerEnchantments();
        ModCompatibility.loadCompat(INIT);
        LogHelper.info("Finished Initialization");
    }

    public void postInit(FMLPostInitializationEvent event) {
        ModCompatibility.loadCompat(POST_INIT);
        LogHelper.info("Finished PostInitialization");
    }

    public void modMapping(FMLModIdMappingEvent event) {
        ModCompatibility.loadCompat(MAPPING);
    }

    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandArmorPlus());
    }

    public void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new GlobalEventsArmorPlus());
        //Register to receive subscribed events
        MinecraftForge.EVENT_BUS.register(this);
        ModAchievements.init();
        ModRecipes.init();
    }

    public void registerModels() {
    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntityWithAlternatives(TileEntityWorkbench.class, "Workbench", "APWorkbench", "WorkbenchTier1", "WorkbenchTierOne");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityHighTechBench.class, "HighTechBench", "APHighTechBench", "WorkbenchTier2", "WorkbenchTierTwo");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityUltiTechBench.class, "UltiTechBench", "APUltiTechBench", "WorkbenchTier3", "WorkbenchTierThree");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityChampionBench.class, "ChampionBench", "APChampionBench", "WorkbenchTier4", "WorkbenchTierFour");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityLavaInfuser.class, "LavaInfuser", "APLavaInfuser");
    }

    public void registerWorldGenerators() {
        GameRegistry.registerWorldGenerator(new OreGen(), 1);
        GameRegistry.registerWorldGenerator(new StructureGen(), 2);
        GameRegistry.registerWorldGenerator(new StructureGenNBT(), 3);
    }
}