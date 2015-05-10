package highlands.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenPinelands extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.6F, 1.4F);
    private static final int trees = 3, grass = 6, flowers = 0, plants = 3;
    public BiomeGenPinelands(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers, plants));
	    biomedec.generateLakes = true;
	    this.setHeight(biomeHeight);
        this.temperature = 0.5F;
        this.rainfall = 0.6F;
        this.treeGenCache = new WorldGenHighlandsShrub(1, 1);
        this.genCache = new WorldGenTaiga2(false);
        this.smallPlantsGenCache = new WorldGenSmallPlants(HighlandsBlocks.thornbush);
    }

    @Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (rand.nextInt(2) == 0 ? this.smallPlantsGenCache.setPlant(HighlandsBlocks.thornbush)
				: this.smallPlantsGenCache.setPlant(HighlandsBlocks.blueberryBush));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (par1Random.nextInt(2) == 0 ? this.treeGenCache : (WorldGenAbstractTree)this.genCache);
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlandsNoCheck(par1World, par2Random, par3, par4, 3 + par2Random.nextInt(6), biomedec.HLemerald, 4, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.ironGen, 0, 64);
    }
}
