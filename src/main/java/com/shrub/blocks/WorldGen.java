package com.shrub.blocks;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
	    switch (world.provider.dimensionId) {
	    case 0: //Overworld
	    	
	    	this.runGenerator(this.genKaoliniteBlocks, world, random, chunkX, chunkZ, 10, 50, 100);
	    	this.runGenerator(this.genPentlanditeOre, world, random, chunkX, chunkZ, 4, 15, 50);
	    	this.runGenerator(this.genZirconOre, world, random, chunkX, chunkZ, 2, 0, 30); //Chance of 1 is ~1 vein per chunk, min height, max height

	        break;
	    case -1: //Nether
	    	
	    	this.runGenerator(genMoissaniteOre, world, random, chunkX, chunkZ, 1, 0, 30);
	    	this.runGenerator(genTantalumOre, world, random, chunkX, chunkZ, 8, 0, 100);

	        break;
	    case 1: //End

	        break;
	    }
	}
	
	private WorldGenerator genKaoliniteBlocks;
	private WorldGenerator genMoissaniteOre;
	private WorldGenerator genPentlanditeOre;
	private WorldGenerator genTantalumOre;
	private WorldGenerator genZirconOre;
		
	public WorldGen() {
		this.genKaoliniteBlocks = new WorldGenMinable(ModBlocks.kaoliniteClayBlock, 20, Blocks.dirt);
		this.genMoissaniteOre = new WorldGenMinable(ModBlocks.moissaniteOre, 4, Blocks.netherrack);
		this.genPentlanditeOre = new WorldGenMinable(ModBlocks.pentlanditeOre, 10);
		this.genTantalumOre = new WorldGenMinable(ModBlocks.tantalumOre, 10, Blocks.netherrack);
		this.genZirconOre = new WorldGenMinable(ModBlocks.zirconOre, 8); //Calls the world generator on zircon ore, max vein size is 8.
	}
	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
		
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
		
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunk_Z * 16 + rand.nextInt(16);
			generator.generate(world, rand, x, y, z);
		}
	}

}
