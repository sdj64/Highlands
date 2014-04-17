package highlands.biome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreeAcacia;

public class BiomeGenMesa extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(1.5F, 1.5F);
    private static final int trees = -100, grass = 18, flowers = 0, plants = 2;
    public BiomeGenMesa(int par1){
		super(par1 ,new BiomeDecoratorHighlands(trees, grass, flowers, plants));
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;
        this.setHeight(biomeHeight);
        this.temperature = 1.4F;
        this.rainfall = 0.1F;
        this.treeGenCache = new WorldGenTreeAcacia(7, 3, false);
        this.genCache = new WorldGenSmallPlants(HighlandsBlocks.thornbush);
    }

    @Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return this.genCache;
	}

    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return this.treeGenCache;
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 0, 32);
    }
}
