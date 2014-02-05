package highlands.biome;

import highlands.HighlandsMain;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenAutumnBigTree;
import highlands.worldgen.WorldGenAutumnTree;
import highlands.worldgen.WorldGenSmallPlants;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenAutumnForest extends BiomeGenBaseHighlands
{
    private BiomeDecoratorHighlands biomedec;

	public BiomeGenAutumnForest(int par1)
    {
        super(par1);
        
	    int trees = 10;
	    int grass = 2;
	    int flowers = 8;
	    int plants = 2;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
	    
	    biomedec.generateLakes = true;
	    
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
        
        this.temperature = 0.6F;
        this.rainfall = 0.5F;
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.raspberryBush)
				: new WorldGenSmallPlants(HighlandsBlocks.blueberryBush));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
    	int a = par1Random.nextInt(6)+1;
    	switch(a){
    	case 1: return (WorldGenerator)this.worldGeneratorTrees; //birch tree
    	
    	case 2: if(HighlandsMain.vanillaBlocksFlag) return this.worldGeneratorTrees;//birch tree (vanillaBlocks only)
    	
    			else return (WorldGenerator)new WorldGenAutumnTree(false, 4, Blocks.log, HighlandsBlocks.autumnYellowLeaves);// autumn tree yellow
    	
    	case 3: if(HighlandsMain.vanillaBlocksFlag) return (WorldGenerator)(par1Random.nextInt(2) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false));//pine tree(vanillaBlocks only)
				
    			else return (par1Random.nextBoolean() ? (WorldGenerator)new WorldGenAutumnTree(false, 4, Blocks.log, HighlandsBlocks.autumnYellowLeaves) :
					(WorldGenerator)new WorldGenAutumnBigTree(false, Blocks.log, HighlandsBlocks.autumnYellowLeaves));// autumn tree yellow (chance of BigTree)
    	
    	case 4: if(HighlandsMain.vanillaBlocksFlag) return this.worldGeneratorTrees;//regular trees (vanillaBlocks only)
    	
				else return (WorldGenerator)new WorldGenAutumnTree(false, 4, Blocks.log, HighlandsBlocks.autumnOrangeLeaves);// autumn tree orange
    	
    	case 5: if(HighlandsMain.vanillaBlocksFlag) return this.worldGeneratorBigTree;//big trees (vanillaBlocks only)
    	
				else return (par1Random.nextBoolean() ? (WorldGenerator)new WorldGenAutumnTree(false, 4, Blocks.log, HighlandsBlocks.autumnOrangeLeaves) :
					(WorldGenerator)new WorldGenAutumnBigTree(false, Blocks.log, HighlandsBlocks.autumnOrangeLeaves));// autumn tree orange (chance of BigTree)
    	
    	case 6: return (WorldGenerator)(par1Random.nextInt(2) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false)); //pine tree
    	}
    	return this.worldGeneratorTrees;
    	
        //return (WorldGenerator)(par1Random.nextInt(5) == 0 ? this.worldGeneratorForest : (par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees));
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorate(par1World, par2Random, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 0, 32);
    }
    
    /*
    @SideOnly(Side.CLIENT)
    
    public int getBiomeGrassColor()
    {
    	if(!HighlandsMain.vanillaBlocksFlag)
        return 0xA7B539;
    	else return super.getBiomeGrassColor();
    }
    
    /*
    @Override
    public BiomeGenBase setColor(int par1)
    {
        this.color = 0xFFA811;
        return this;
    }
    */
    
}
