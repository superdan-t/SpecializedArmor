package cc.sdspar.spar.world;

import java.util.Random;

import cc.sdspar.spar.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ModWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case -1:
			//Nether
			runGenerator(ModBlocks.MOISSANITE_ORE.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.NETHERRACK, 0, 47, 4, 2);
			runGenerator(ModBlocks.NETHER_CRYSTAL_ORE.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.NETHERRACK, 0, 100, 2, 1);
			runGenerator(ModBlocks.TANTALUM_ORE.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.NETHERRACK, 0, 100, 10, 8);
			break;
		case 0:
			//Overworld
			runGenerator(ModBlocks.ALUMINUM_ORE.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.STONE, 20, 48, 9, 8);
			runGenerator(ModBlocks.COPPER_ORE.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.STONE, 10, 45, 8, 12);
			runGenerator(ModBlocks.KAOLINITE_BLOCK.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.DIRT, 50, 100, 20, 10);
			runGenerator(ModBlocks.LEAD_ORE.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.STONE, 10, 45, 8, 10);
			runGenerator(ModBlocks.TIN_ORE.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.STONE, 20, 48, 8, 9);
			runGenerator(ModBlocks.TITANIUM_ORE.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.STONE, 12, 40, 6, 8);
			runGenerator(ModBlocks.ZIRCON_ORE.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.STONE, 0, 16, 8, 3);
			break;
		case 1:
			//End
			//Order is rarity
			runGenerator(ModBlocks.ENDERINE_GOLD_ORE.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.OBSIDIAN, 65, 85, 4, 60);
			runGenerator(ModBlocks.ENDERINE_DIAMOND_ORE.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.OBSIDIAN, 75, 95, 4, 55);
			runGenerator(ModBlocks.ENDERINE_QUARTZ_ORE.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.OBSIDIAN, 0, 60, 4, 130);
			runGenerator(ModBlocks.ENDERINE_DUST_ORE.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.OBSIDIAN, 70, 120, 3, 170);
			runGenerator(ModBlocks.ENDERINE_CRYSTAL_ORE.getDefaultState(), world, rand, chunkX, chunkZ, Blocks.OBSIDIAN, 85, 120, 3, 150);
			break;
		}

	}
	
	private void runGenerator(IBlockState block, World world, Random rand, int chunk_X, int chunk_Z, Block blockToReplace, int minHeight, int maxHeight, int size, int chancesToSpawn) {
		
		
		if (minHeight > maxHeight) {	
			int swap = minHeight;
			minHeight = maxHeight;
			maxHeight = swap;
		}
		if (minHeight < 0) minHeight = 0;
		if (maxHeight > 255) maxHeight = 255;
		
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunk_Z * 16 + rand.nextInt(16);
			BlockPos pos = new BlockPos(x, y, z);
			WorldGenMinable generator = new WorldGenMinable(block, size, BlockMatcher.forBlock(blockToReplace));
			generator.generate(world, rand, pos);
		}
	}

}
