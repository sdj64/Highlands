package highlands.biome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreePalm;

public class BiomeGenTropicalIslands extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(-0.3F, 0.5F);
    private static final int trees = 5 ,grass = 8, flowers = 4, plants = 4;
    public BiomeGenTropicalIslands(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers, plants));
	    this.setHeight(biomeHeight);
        this.temperature = 1.0F;
        this.rainfall = 1.2F;
        this.biomedec.generateWatermelon = true;
        this.genCache = new WorldGenTallGrass(Blocks.tallgrass, 2);
        this.treeGenCache = new WorldGenTreePalm(8, 3, false);
        this.smallPlantsGenCache = new WorldGenSmallPlants(HighlandsBlocks.leafyFern);
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
	    return this.genCache;
	}

    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return this.treeGenCache;
    }

    @Override
    public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (rand.nextInt(2) == 0 ? this.smallPlantsGenCache.setPlant(HighlandsBlocks.leafyFern)
				: this.smallPlantsGenCache.setPlant(HighlandsBlocks.whiteFlower));
	}

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.lapisGen, 0, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 10, biomedec.HLwater, 0, 64);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 10, biomedec.HLsand, 0, 64);
        
        WorldGenVines var5 = new WorldGenVines();
        for (int var6 = 0; var6 < 50; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16) + 8;
            byte var8 = 36;
            int var9 = par4 + par2Random.nextInt(16) + 8;
            var5.generate(par1World, par2Random, var7, var8, var9);
        }
    }

}
