package highlands.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreeRedwood;

public class BiomeGenRedwoodForest extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.5F, 0.5F);
    private static final int trees = 7 ,grass = 5, flowers = 0, plants = 1;
    public BiomeGenRedwoodForest(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers, plants));
	    biomedec.generateLakes = true;
	    this.setHeight(biomeHeight);
        this.temperature = 0.6F;
        this.rainfall = 0.2F;
        this.genCache = new WorldGenHighlandsShrub(0, 0);
        this.treeGenCache = new WorldGenTreeRedwood(35, 10, false);
        this.smallPlantsGenCache = new WorldGenSmallPlants(HighlandsBlocks.thornbush);
    }

    @Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (rand.nextInt(2) == 0 ? this.smallPlantsGenCache.setPlant(HighlandsBlocks.thornbush)
				: this.smallPlantsGenCache.setPlant(HighlandsBlocks.raspberryBush));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
    	if(par1Random.nextInt(3) == 0)
            return this.treeGenCache;
    	else
            return (WorldGenAbstractTree) this.genCache;
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlandsNoCheck(par1World, par2Random, par3, par4, 3 + par2Random.nextInt(6), biomedec.HLemerald, 4, 32);
    }
}
