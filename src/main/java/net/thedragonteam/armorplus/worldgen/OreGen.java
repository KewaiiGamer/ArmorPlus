/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.worldgen.structures.StructureCastle;

import java.util.Random;

import static net.thedragonteam.armorplus.ARPConfig.*;

/**
 * net.thedragonteam.armorplus.worldgen
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:30 PM.
 * - TheDragonTeam
 */
public class OreGen implements IWorldGenerator {

    private WorldGenerator lavaCrystalOverworldGenerator;
    private WorldGenerator lavaCrystalTheEndGenerator;
    private WorldGenerator lavaCrystalTheNetherGenerator;

    public OreGen() {
        lavaCrystalOverworldGenerator = new WorldGenMinable(ModBlocks.BLOCK_LAVA_CRYSTAL.getDefaultState(), lavaCrystalOverworldVeinAmount);
        lavaCrystalTheEndGenerator = new WorldGenMinable(ModBlocks.BLOCK_LAVA_CRYSTAL.getDefaultState(), lavaCrystalTheEndVeinAmount, BlockMatcher.forBlock(Blocks.END_STONE));
        lavaCrystalTheNetherGenerator = new WorldGenMinable(ModBlocks.BLOCK_LAVA_CRYSTAL.getDefaultState(), lavaCrystalTheNetherVeinAmount, BlockMatcher.forBlock(Blocks.NETHERRACK));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int blockX = chunkX * 16;
        int blockZ = chunkZ * 16;

        switch (world.provider.getDimension()) {
            case 0: //Overworld Dimension
                generateOverworldStructures(world, random, blockX, blockZ);

                if (enableLavaCrystalOverworldGen) {
                    this.runGenerator(lavaCrystalOverworldGenerator, world, random, chunkX, chunkZ, lavaCrystalOverworldRarity, lavaCrystalOverworldMinYSpawn, lavaCrystalOverworldMaxYSpawn);
                }
                break;
            case 1: //The End
                if (enableLavaCrystalTheEndGen) {
                    this.runGenerator(lavaCrystalTheEndGenerator, world, random, chunkX, chunkZ, lavaCrystalTheEndRarity, lavaCrystalTheEndMinYSpawn, lavaCrystalTheEndMaxYSpawn);
                }
                break;
            case -1: //The Nether
                if (enableLavaCrystalTheNetherGen) {
                    this.runGenerator(lavaCrystalTheNetherGenerator, world, random, chunkX, chunkZ, lavaCrystalTheNetherRarity, lavaCrystalTheNetherMinYSpawn, lavaCrystalTheNetherMaxYSpawn);
                }
                break;
        }
    }

    private void generateOverworldStructures(World world, Random rand, int blockX, int blockZ) {

        WorldGenerator genCabin = new StructureCastle();
        // 4% of chunks can have a castle
        if (rand.nextInt(100) < castleGenSpawnChance) {
            // get a random position in the chunk
            int randX = blockX + rand.nextInt(16);
            int randZ = blockZ + rand.nextInt(16);
            // use our custom function to get the ground height
            int groundY = getGroundFromAbove(world, randX, randZ);
            genCabin.generate(world, rand, new BlockPos(randX, groundY + 1, randZ));
        }
    }

    /**
     * HELPER METHODS
     **/
    // find a grass or dirt block to place the bush on
    public static int getGroundFromAbove(World world, int x, int z) {
        int y = 255;
        boolean foundGround = false;
        while (!foundGround && y-- >= 0) {
            Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            // "ground" for our bush is grass or dirt
            foundGround = blockAt == Blocks.DIRT || blockAt == Blocks.GRASS || blockAt != Blocks.AIR;
        }

        return y;
    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i++) {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}