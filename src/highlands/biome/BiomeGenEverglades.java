package highlands.biome;

import java.util.Random;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsBigTree;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreeMangrove;
import highlands.worldgen.WorldGenTreePalm;

public class BiomeGenEverglades extends BiomeGenBaseHighlands
{
	private BiomeDecoratorHighlands biomedec;
	private static final Height biomeHeight = new Height(-0.2F, 0.1F);

	public BiomeGenEverglades(int par1){
		super(par1);
		int trees = -10;
	    int grass = 0;
	    int flowers = 0;
	    int plants = 0;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;
        this.setHeight(biomeHeight);
        this.temperature = 0.7F;
        this.rainfall = 1.2F;
	        
	}
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.cattail)
		: new WorldGenSmallPlants(HighlandsBlocks.leafyFern));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.tallgrass, 1);
    }
    
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)new WorldGenTreeMangrove(4, 2, false);
    }

    public void decorate(World world, Random rand, BiomeGenBaseHighlands biome, int par3, int par4)
    {
    	biomedec.decorate(world, rand, biome, par3, par4);
    	
    	boolean mangroves = rand.nextInt(10) == 0;
    	/*
    	for(int i = par3; i < par3+16; i++){
    		for(int j = par4; i < par4+16; j++){
    			
    			int k = 80;
    			for (boolean var6 = false; (world.getBlockId(i, k, j) == 0 || world.getBlockId(i, k, j) == Block.leaves.blockID) && k > 0; --k);
    			
    			if(k < 64){
    				if(rand.nextInt(10) == 0 && mangroves)new WorldGenTreeMangrove(4, 2, false).generate(world, rand, i, k, j);
    			}
    			if(k == 64){
    				if(world.getBlockId(i, k, j) == Block.grass.blockID)world.setBlock(i, k+1, j, Block.tallGrass.blockID, 1, 2);
    			}
    			else{
    				if(rand.nextInt(20) == 0)new WorldGenTreeMangrove(4, 2, false).generate(world, rand, i, k, j);
    				if(rand.nextInt(20) == 0)new WorldGenTreePalm(8, 3, false).generate(world, rand, i, k, j);
    				if(rand.nextInt(20) == 0)this.worldGeneratorSwamp.generate(world, rand, i, k, j);
    				if(rand.nextInt(8) == 0)this.getRandomWorldGenForHighlandsPlants(rand).generate(world, rand, i, k, j);
    			}
    			
    		}
    	}
    	*/
    	for(int i = 0; i < 16; i++){
    		for(int j = 0; j < 16; j++){
    			int topY = 128;
    			Block var11;
    	        for (boolean var6 = false; ((var11 = world.getBlock(par3+i, topY, par4+j)) == Blocks.air || var11 == Blocks.leaves) && topY > 0; --topY)
    	        {
    	            ;
    	        }
    			//System.out.println("the top block is id" + par1World.getBlockId(par3+i, topY, par4+j));
    			if(world.getBlock(par3+i, topY, par4+j) == Blocks.air)topY--;
    			/*
    			if(world.getBlockId(par3+i, topY, par4+j) == Block.grass.blockID && rand.nextInt(2) == 0){
    				world.setBlock(par3+i, topY, par4+j, Block.grass.blockID);
    				topY++;
    				if(rand.nextInt(3) != 0 && world.getBlockId(par3+i, topY, par4+j) == 0){
    					System.out.println("yes!");
    				}
    			}
    			*/
    			if(topY < 62){
    				if(rand.nextInt(10) == 0 && mangroves)new WorldGenTreeMangrove(4, 2, false).generate(world, rand, par3+i, topY, par4+j);
    			}
    			else if(topY == 62){
    				if(world.getBlock(par3+i, topY, par4+j) == Blocks.grass)world.setBlock(par3+i, topY+1, par4+j, Blocks.tallgrass, 1, 2);
    			}
    			else{
    				if(rand.nextInt(20) == 0)new WorldGenTreeMangrove(4, 2, false).generate(world, rand, par3+i, topY, par4+j);
    				if(rand.nextInt(20) == 0)new WorldGenTreePalm(8, 3, false).generate(world, rand, par3+i, topY, par4+j);
    				if(rand.nextInt(20) == 0)this.worldGeneratorSwamp.generate(world, rand, par3+i, topY, par4+j);
    				if(rand.nextInt(8) == 0)this.getRandomWorldGenForHighlandsPlants(rand).generate(world, rand, par3+i, topY, par4+j);
    			}
    		}
    	}
    	
    }
}











