package highlands.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.SpawnListEntry;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.BiomeDecoratorHighlands;
import highlands.worldgen.WorldGenHighlandsBigTree;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenWoodlands extends BiomeGenBaseHighlands
{
    private BiomeDecoratorHighlands biomedec;

	public BiomeGenWoodlands(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));

        int trees = 11;
        int grass = 2;
        int flowers = 0;
        int plants = 1;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
	    
	    biomedec.generateLakes = true;
	    
	    this.temperature = 0.6F;
	    this.rainfall = 0.6F;
        
	    
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.raspberryBush.blockID)
				: new WorldGenSmallPlants(HighlandsBlocks.thornbush.blockID));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
    	int a = par1Random.nextInt(12);
    	switch(a){
    	case 1: return (WorldGenerator)this.worldGeneratorForest;
    	case 2: return (WorldGenerator)this.worldGeneratorForest;
    	case 3: return (WorldGenerator)this.worldGeneratorBigTree;
    	case 4: return (WorldGenerator)new WorldGenHighlandsBigTree(false, true, 2, 2, 1, 0);
    	case 5: return (WorldGenerator)new WorldGenHighlandsBigTree(false, true, 0, 0, 2, 20);
    	default: return (WorldGenerator)this.worldGeneratorTrees;
    	}
        //return (WorldGenerator)(par1Random.nextInt(5) == 0 ? this.worldGeneratorForest : (par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees));
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
    	biomedec.decorate(par1World, par2Random, par3, par4);
    }
}