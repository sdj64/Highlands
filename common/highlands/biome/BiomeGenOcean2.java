package highlands.biome;

import java.util.Random;

import highlands.HighlandsMain;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenOcean2 extends BiomeGenBaseHighlands
{
	
	private BiomeDecoratorHighlands biomedec;
	
    public BiomeGenOcean2(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        
        int trees = 0;
	    int grass = 3;
	    int flowers = 1;
        this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
        
        this.maxHeight = 0.4F;
        this.minHeight = -1.5F;
    }
    
    public void decorate(World world, Random par2Random, int par3, int par4)
    {
    	/*
    	// makes islands grass
    	for(int i = -16; i < 32; i++){
    		for(int j = -16; j < 32; j++){
    			if(world.getBiomeGenForCoords(par3+i, par4+j) == HighlandsMain.ocean2){
	    			int topY = world.getTopSolidOrLiquidBlock(par3+i, par4+j);//80;
	    			/*
	    			int var11;
	    	        for (boolean var6 = false; ((var11 = world.getBlockId(par3+i, topY, par4+j)) == 0 || var11 == Block.leaves.blockID) && topY > 0; --topY)
	    	        {
	    	            ;
	    	        }
	    	        * /
	    			//System.out.println("the top block is id" + world.getBlockId(par3+i, topY, par4+j));
	    			if(world.getBlockId(par3+i, topY, par4+j) == 0)topY--;
	    			if(topY > 62){
	    				world.setBlock(par3+i, topY, par4+j, Block.grass.blockID);
	    			}
	    		}
    		}
    	}
    	*/

        biomedec.decorate(world, par2Random, par3, par4);
        biomedec.genOreHighlands(world, par2Random, par3, par4, 10, biomedec.HLwater, 0, 64);
	    biomedec.genOreHighlands(world, par2Random, par3, par4, 10, biomedec.HLsand, 0, 64);
    }
}
