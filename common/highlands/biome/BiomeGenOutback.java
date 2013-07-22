package highlands.biome;

import java.util.Random;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.HighlandsMain;
import highlands.worldgen.BiomeDecoratorHighlands;
import highlands.worldgen.WorldGenHighlandsGroundcover;
import highlands.worldgen.WorldGenHighlandsShrub;

public class BiomeGenOutback extends BiomeGenBaseHighlands
{
	
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenOutback(int par1){
			super(par1);
			
			int trees = 1;
		    int grass = 10;
		    int flowers = 0;
		    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
		    
		    biomedec.deadBushPerChunk = 40;
		    biomedec.cactiPerChunk = 4;
	        
	        this.minHeight = 0.1F;
	        this.maxHeight = 0.3F;
	        this.temperature = 1.6F;
	        this.rainfall = 0.1F;
	        
	        this.topBlock = (byte) Block.sand.blockID;
	        this.fillerBlock = (byte) Block.sand.blockID;
	        
	        this.spawnableCreatureList.clear();
	        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
	        
	    }
	
		

	    /**
	     * Gets a WorldGen appropriate for this biome.
	     */
	    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	    {
	        return new WorldGenHighlandsGroundcover(Block.tallGrass.blockID, 1, 2);
	    }
	    
	    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	    {
	        return (WorldGenerator)new WorldGenHighlandsShrub(0, 0);
	    }

	    public void decorate(World par1World, Random par2Random, int par3, int par4)
	    {
	        biomedec.decorate(par1World, par2Random, par3, par4);
	        HighlandsMain.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 0, 32);
	        HighlandsMain.genOreHighlands(par1World, par2Random, par3, par4, 10, HighlandsMain.HLsand, 0, 64);
	        
	        //the code for the random half grass
	        /*
	    	for(int i = 0; i < 16; i++){
	    		for(int j = 0; j < 16; j++){
	    			if(par1World.getBiomeGenForCoords(par3+i, par4+j) == HighlandsMain.outback){
		    			int topY = 128;
		    			int var11;
		    	        for (boolean var6 = false; ((var11 = par1World.getBlockId(par3+i, topY, par4+j)) == 0 || var11 == Block.leaves.blockID) && topY > 0; --topY)
		    	        {
		    	            ;
		    	        }
		    			//System.out.println("the top block is id" + par1World.getBlockId(par3+i, topY, par4+j));
		    			if(par1World.getBlockId(par3+i, topY, par4+j) == 0)topY--;
		    			if(par1World.getBlockId(par3+i, topY, par4+j) == Block.sand.blockID && par2Random.nextInt(2) == 0){
		    				par1World.setBlock(par3+i, topY, par4+j, Block.grass.blockID);
		    				topY++;
		    				if(par2Random.nextInt(3) != 0 && par1World.getBlockId(par3+i, topY, par4+j) == 0){
		    					par1World.setBlockAndMetadata(par3+i, topY, par4+j, Block.tallGrass.blockID, 1);
		    				}
		    			}
		    		}
	    		}
	    	}
	    	*/
	    	
	    	
	    }
	    
	    @Override
	    public int getBiomeFoliageColor(){
	    	return 0xA6C968;
	    }
	    
	    @Override
	    public int getBiomeGrassColor()
	    {
	        return 0xEEE980;
	    }
}







