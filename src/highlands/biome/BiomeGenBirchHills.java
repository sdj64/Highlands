package highlands.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreePoplar;

public class BiomeGenBirchHills extends BiomeGenBaseHighlands
{
    private static final Height biomeHeight = new Height(-0.1F, 0.8F);
    private static final int trees = 3, grass = 10, flowers = 4, plants = 1;
	public BiomeGenBirchHills(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers, plants));
        this.theBiomeDecorator.generateLakes = false;
        this.setHeight(biomeHeight);
        this.temperature = 0.6F;
        this.rainfall = 0.8F;
        this.treeGenCache = new WorldGenTreePoplar(10, 4, false);
        this.smallPlantsGenCache = new WorldGenSmallPlants(HighlandsBlocks.cotton);
    }

    @Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (rand.nextInt(2) == 0 ? this.smallPlantsGenCache.setPlant(HighlandsBlocks.cotton)
				: this.smallPlantsGenCache.setPlant(HighlandsBlocks.blueFlower));
	}

    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return treeGenCache;
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.lapisGen, 0, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 10, biomedec.HLwater, 0, 64);
    }
}
