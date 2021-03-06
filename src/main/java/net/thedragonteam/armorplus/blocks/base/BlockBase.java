/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

import javax.annotation.Nonnull;

import static net.thedragonteam.armorplus.util.Utils.setName;

public class BlockBase extends Block {

    public BlockBase(String name) {
        this(Material.GROUND, name);
    }

    public BlockBase(Material material, String name) {
        this(material, name, 0.0F);
    }

    public BlockBase(Material material, String name, boolean unbreakable) {
        this(material, name, 0.0F, unbreakable);
    }

    public BlockBase(Material material, String name, float resistance) {
        this(material, name, resistance, 0.0F);
    }

    public BlockBase(Material material, String name, float resistance, boolean unbreakable) {
        this(material, name, resistance, 0.0F, unbreakable);
    }

    public BlockBase(Material material, String name, float resistance, float hardness) {
        this(material, name, resistance, hardness, ToolType.PICKAXE);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, boolean unbreakable) {
        this(material, name, resistance, hardness, ToolType.PICKAXE, unbreakable);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool) {
        this(material, name, resistance, hardness, tool, 0);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool, boolean unbreakable) {
        this(material, name, resistance, hardness, tool, 0, unbreakable);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool, int harvestLevel) {
        this(material, name, resistance, hardness, tool, harvestLevel, 0.0F);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool, int harvestLevel, boolean unbreakable) {
        this(material, name, resistance, hardness, tool, harvestLevel, 0.0F, unbreakable);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool, int harvestLevel, float lightLevel) {
        this(material, name, resistance, hardness, tool, harvestLevel, lightLevel, 0, false);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool, int harvestLevel, float lightLevel, boolean unbreakable) {
        this(material, name, resistance, hardness, tool, harvestLevel, lightLevel, 0, unbreakable);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool, int harvestLevel, float lightLevel, int lightOpacity, boolean unbreakable) {
        super(material);
        this.setRegistryName(name);
        this.setUnlocalizedName(setName(name));
        this.setResistance(resistance);
        this.setHardness(hardness);
        this.setHarvestLevel(tool.getTool(), harvestLevel);
        this.setLightLevel(lightLevel);
        this.setLightOpacity(lightOpacity);
        if (unbreakable) {
            setBlockUnbreakable();
        }
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    public enum ToolType implements IStringSerializable {
        PICKAXE("pickaxe", "Pickaxe"),
        AXE("axe", "Axe"),
        SHOVEL("shovel", "Shovel");

        private final String tool;

        private final String name;

        ToolType(String toolIn, String nameIn) {
            tool = toolIn;
            name = nameIn;
        }

        public String getTool() {
            return tool;
        }

        @Override
        @Nonnull
        public String getName() {
            return name;
        }
    }
}