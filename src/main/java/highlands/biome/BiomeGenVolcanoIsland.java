package highlands.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBiomes;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;

public class BiomeGenVolcanoIsland extends BiomeGenBaseHighlands
{
	
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenVolcanoIsland(int par1)
    {
        super(par1);
        
        int trees = -100;
	    int grass = 0;
	    int flowers = 0;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
        this.spawnableCreatureList.clear();
        
        this.topBlock = (byte) Block.gravel.blockID;
        this.fillerBlock = (byte) Block.gravel.blockID;
        
        this.maxHeight = 1.7F;
        this.minHeight = 1.7F;
        this.temperature = 0.8F;
        this.rainfall = 0.4F;
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
    	biomedec.decorate(par1World, par2Random, par3, par4);
    	
    	for(int i = 0; i < 16; i++){
    		for(int j = 0; j < 16; j++){
    			if(par1World.getBiomeGenForCoords(par3+i, par4+j) == HighlandsBiomes.volcanoIsland){
	    			int topY = 128;
	    			int var11;
	    	        for (boolean var6 = false; ((var11 = par1World.getBlockId(par3+i, topY, par4+j)) == 0 || var11 == Block.leaves.blockID) && topY > 0; --topY)
	    	        {
	    	            ;
	    	        }
	    	        if(topY > 65){
		    			if(par1World.getBlockId(par3+i, topY, par4+j) == 0)topY--;
		    			
		    			//chance to generate a lava lake on top of the volcano
		    			if(par1World.getBiomeGenForCoords(par3+i + 8, par4+j) == HighlandsBiomes.volcanoIsland &&
		    					par1World.getBiomeGenForCoords(par3+i - 8, par4+j) == HighlandsBiomes.volcanoIsland &&
		    					par1World.getBiomeGenForCoords(par3+i, par4+j + 8) == HighlandsBiomes.volcanoIsland &&
		    					par1World.getBiomeGenForCoords(par3+i, par4+j - 8) == HighlandsBiomes.volcanoIsland &&
		    					par2Random.nextInt(10) == 0){
		    				new WorldGenLakes(Block.lavaStill.blockID).generate(par1World, par2Random, par3+i, topY, par4+j);
		    			}
		    			
		    			int a = par2Random.nextInt(10);
		    			if(a == 9 && par2Random.nextInt(5) == 0){
		    				par1World.setBlock(par3+i, topY, par4+j, Block.lavaMoving.blockID, 0, 3);
		    				par1World.setBlock(par3+i, topY+1, par4+j, 0, 0, 3);
		    			}
		    			else if(a == 7 || a == 8) par1World.setBlock(par3+i, topY, par4+j, Block.cobblestone.blockID, 0, 2);
		    			else if(a == 6) par1World.setBlock(par3+i, topY, par4+j, Block.obsidian.blockID, 0, 2);
		    			else if(a == 5 || a == 4) par1World.setBlock(par3+i, topY, par4+j, Block.stone.blockID, 0, 2);
	    	        }
	    		}
    		}
    	}
    	
    	biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.ironGen, 0, 64);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 12, biomedec.redstoneGen, 0, 16);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.lapisGen, 0, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 4, biomedec.goldGen, 0, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.diamondGen, 0, 16);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 12, biomedec.HLlava, 0, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 25, biomedec.HLobsidian, 0, 64);
    }
}
