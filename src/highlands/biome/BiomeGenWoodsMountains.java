package highlands.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsBigTree;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenWoodsMountains extends BiomeGenBaseHighlands
{
	private BiomeDecoratorHighlands biomedec;
	private static final Height biomeHeight = new Height(0.0F, 10.0F);

	public BiomeGenWoodsMountains(int par1)
    {
        super(par1);
	    int trees = 10;
	    int grass = 5;
	    int flowers = 0;
	    int plants = 1;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
        this.setHeight(biomeHeight);
        this.temperature = 0.6F;
        this.rainfall = 0.5F;
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.raspberryBush)
				: new WorldGenSmallPlants(HighlandsBlocks.thornbush));
	}

	/**
    * Gets a WorldGen appropriate for this biome.
     */
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
    	int a = par1Random.nextInt(12);
    	switch(a){
    	case 1: return (WorldGenerator)this.worldGeneratorTrees;
    	case 2: return (WorldGenerator)this.worldGeneratorTrees;
    	case 3: return (WorldGenerator)this.worldGeneratorBigTree;
    	case 4: return (WorldGenerator)new WorldGenHighlandsBigTree(false, true, 2, 2, 1, 0);
    	case 5: return (WorldGenerator)new WorldGenHighlandsBigTree(false, true, 0, 0, 2, 20);
    	default: return (WorldGenerator)this.worldGeneratorTrees;
    	}
        //return (WorldGenerator)(par1Random.nextInt(5) == 0 ? this.worldGeneratorForest : (par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees));
    }
	/*
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false));
    }
    */
    
    public void decorate(World par1World, Random par2Random, BiomeGenBaseHighlands biome, int par3, int par4)
    {
        biomedec.decorate(par1World, par2Random, biome, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.diamondGen, 0, 16);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.HLwater, 0, 128);
    }
}
