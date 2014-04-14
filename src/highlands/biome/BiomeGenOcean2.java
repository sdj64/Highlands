package highlands.biome;

import java.util.Random;

import net.minecraft.world.World;

public class BiomeGenOcean2 extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(-1.5F, 0.4F);
    private static final int trees = 0 ,grass = 3, flowers = 1;
    public BiomeGenOcean2(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers));
        this.spawnableCreatureList.clear();
        this.setHeight(biomeHeight);
    }

    @Override
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

        biomedec.decorateChunk(world, par2Random, this, par3, par4);
        biomedec.genOreHighlands(world, par2Random, par3, par4, 10, biomedec.HLwater, 0, 64);
	    biomedec.genOreHighlands(world, par2Random, par3, par4, 10, biomedec.HLsand, 0, 64);
    }
}
