package highlands.biome;

import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsBigTree;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenWoodsMountains extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.0F, 10.0F);
    private static final int trees = 10, grass = 5, flowers = 0, plants = 1;
    public BiomeGenWoodsMountains(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers, plants));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
        this.setHeight(biomeHeight);
        this.temperature = 0.6F;
        this.rainfall = 0.5F;
    }

    @Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.raspberryBush)
				: new WorldGenSmallPlants(HighlandsBlocks.thornbush));
	}

	/**
    * Gets a WorldGen appropriate for this biome.
     */
    @Override
	public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
    	int a = par1Random.nextInt(12);
    	switch(a){
    	case 1: return this.worldGeneratorTrees;
    	case 2: return this.worldGeneratorTrees;
    	case 3: return this.worldGeneratorBigTree;
    	case 4: return new WorldGenHighlandsBigTree(false, true, 2, 2, 1, 0);
    	case 5: return new WorldGenHighlandsBigTree(false, true, 0, 0, 2, 20);
    	default: return this.worldGeneratorTrees;
    	}
        //return (WorldGenerator)(par1Random.nextInt(5) == 0 ? this.worldGeneratorForest : (par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees));
    }
	/*
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false));
    }
    */
    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.diamondGen, 0, 16);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.HLwater, 0, 128);
    }
}
