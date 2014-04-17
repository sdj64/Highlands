package highlands.biome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreeAcacia;

public class BiomeGenSahel extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.3F, 0.5F);
    private static final int trees = 1 ,grass = 5, flowers = 0, plants = 1;
    public BiomeGenSahel(int par1){
		super(par1, new BiomeDecoratorHighlands(trees, grass, flowers, plants));
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sand;
        this.spawnableCreatureList.clear();
        this.setHeight(biomeHeight);
        this.temperature = 1.6F;
        this.rainfall = 0.1F;
        this.treeGenCache = new WorldGenTreeAcacia(7, 3, false);
        this.genCache = new WorldGenHighlandsShrub(0, 0);
        this.smallPlantsGenCache = new WorldGenSmallPlants(HighlandsBlocks.whiteFlower);
    }

    @Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (rand.nextInt(2) == 0 ? this.smallPlantsGenCache.setPlant(HighlandsBlocks.whiteFlower)
				: this.smallPlantsGenCache.setPlant(HighlandsBlocks.thornbush));
	}

    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (par1Random.nextInt(3) == 0 ? treeGenCache : (WorldGenAbstractTree) genCache);
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
    	biomedec.genOreHighlandsNoCheck(par1World, par2Random, par3, par4, 2, biomedec.HLdirt, 62, 80);
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 0, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 10, biomedec.HLsand, 0, 64);
    }
}
