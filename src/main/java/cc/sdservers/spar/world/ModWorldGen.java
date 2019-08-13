package cc.sdservers.spar.world;

import java.util.Random;

import com.google.common.base.Predicate;

import cc.sdservers.spar.block.ModBlocks;
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

			runGenerator(ModBlocks.MOISSANITE_ORE.getDefaultState(), world, rand, chunkX, chunkZ, 1, 0, 30, 4, Blocks.NETHERRACK);
			runGenerator(ModBlocks.TANTALUM_ORE.getDefaultState(), world, rand, chunkX, chunkZ, 8, 0, 100, 10, Blocks.NETHERRACK);
			break;
		case 0:
			//Overworld
			runGenerator(ModBlocks.KAOLINITE_BLOCK.getDefaultState(), world, rand, chunkX, chunkZ, 10, 50, 100, 20, Blocks.DIRT);
			runGenerator(ModBlocks.ZIRCON_ORE.getDefaultState(), world, rand, chunkX, chunkZ, 2, 0, 30, 8, Blocks.STONE);
			break;
		case 1:
			//End
			break;
		}

	}
	
	private void runGenerator(IBlockState block, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight, int size, Block blockToReplace) {
		
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
