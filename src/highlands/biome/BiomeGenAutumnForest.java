package highlands.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenAutumnTree;
import highlands.worldgen.WorldGenAutumnBigTree;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenAutumnForest extends BiomeGenBaseHighlands
{
    private static final int trees = 10, grass = 2, flowers = 8, plants = 2;
	public BiomeGenAutumnForest(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers, plants));
	    
	    biomedec.generateLakes = true;
        biomedec.generatePumpkin = true;
	    
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
        
        this.temperature = 0.6F;
        this.rainfall = 0.5F;
        this.smallPlantsGenCache = new WorldGenSmallPlants(HighlandsBlocks.raspberryBush);
    }

    @Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (rand.nextInt(2) == 0 ? this.smallPlantsGenCache.setPlant(HighlandsBlocks.raspberryBush)
				: this.smallPlantsGenCache.setPlant(HighlandsBlocks.blueberryBush));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
    	int a = par1Random.nextInt(6)+1;
    	switch(a){
    	case 1: return this.worldGeneratorTrees; //birch tree
    	
    	case 2: if(HighlandsMain.vanillaBlocksFlag) return this.worldGeneratorTrees;//birch tree (vanillaBlocks only)
    	
    			else return new WorldGenAutumnTree(false, 4, Blocks.log, HighlandsBlocks.autumnYellowLeaves);// autumn tree yellow
    	
    	case 3: if(HighlandsMain.vanillaBlocksFlag) return (par1Random.nextInt(2) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false));//pine tree(vanillaBlocks only)
				
    			else return (par1Random.nextBoolean() ? new WorldGenAutumnTree(false, 4, Blocks.log, HighlandsBlocks.autumnYellowLeaves) :
					new WorldGenAutumnBigTree(false, Blocks.log, HighlandsBlocks.autumnYellowLeaves));// autumn tree yellow (chance of BigTree)
    	
    	case 4: if(HighlandsMain.vanillaBlocksFlag) return this.worldGeneratorTrees;//regular trees (vanillaBlocks only)
    	
				else return new WorldGenAutumnTree(false, 4, Blocks.log, HighlandsBlocks.autumnOrangeLeaves);// autumn tree orange
    	
    	case 5: if(HighlandsMain.vanillaBlocksFlag) return this.worldGeneratorBigTree;//big trees (vanillaBlocks only)
    	
				else return (par1Random.nextBoolean() ? new WorldGenAutumnTree(false, 4, Blocks.log, HighlandsBlocks.autumnOrangeLeaves) :
					new WorldGenAutumnBigTree(false, Blocks.log, HighlandsBlocks.autumnOrangeLeaves));// autumn tree orange (chance of BigTree)
    	
    	case 6: return (par1Random.nextInt(2) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false)); //pine tree
    	}
    	return this.worldGeneratorTrees;
    	
        //return (par1Random.nextInt(5) == 0 ? this.worldGeneratorForest : (par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees));
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
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
