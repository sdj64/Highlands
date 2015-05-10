package highlands.biome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenBaldHill extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(1.0F, 1.0F);
    private static final int trees = 0, grass = 4, flowers = 1, plants = 2;
	public BiomeGenBaldHill(int par1){
		super(par1, new BiomeDecoratorHighlands(trees, grass, flowers, plants));
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;
        this.setHeight(biomeHeight);
        this.temperature = 0.5F;
        this.rainfall = 0.7F;
        this.smallPlantsGenCache = new WorldGenSmallPlants(HighlandsBlocks.whiteFlower);
    }

    @Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (rand.nextInt(3) == 0 ? this.smallPlantsGenCache.setPlant(HighlandsBlocks.whiteFlower)
				: (rand.nextInt(2) == 0 ? this.smallPlantsGenCache.setPlant(HighlandsBlocks.raspberryBush)
				: this.smallPlantsGenCache.setPlant(HighlandsBlocks.cotton)));
	}
    
    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlandsNoCheck(par1World, par2Random, par3, par4, 3 + par2Random.nextInt(6), biomedec.HLemerald, 4, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.ironGen, 0, 64);
    }
	    
}

