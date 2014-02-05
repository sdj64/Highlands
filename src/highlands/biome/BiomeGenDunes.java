package highlands.biome;

import java.awt.Color;
import java.util.Random;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenTreePalm;

public class BiomeGenDunes extends BiomeGenBaseHighlands
{
	
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenDunes(int par1){
			super(par1);
			
			int trees = -999;
		    int grass = -999;
		    int flowers = -999;
		    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
	        
	        this.topBlock = (byte)Block.sand.blockID;
	        this.fillerBlock = (byte)Block.sand.blockID;
	        
	        this.spawnableCreatureList.clear();
	        
	        this.minHeight = 0.5F;
	        this.maxHeight = 1.0F;
	        this.temperature = 2.0F;
	        this.rainfall = 0.0F;
	        
	        this.setDisableRain();
	    }

	    /**
	     * Gets a WorldGen appropriate for this biome.
	     */
	    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	    {
	        return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
	    }
	    
	    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	    {
	        return (WorldGenerator)new WorldGenTreePalm(8, 3, false);
	    }

	    public void decorate(World par1World, Random par2Random, int par3, int par4)
	    {
	        biomedec.decorate(par1World, par2Random, par3, par4);
	        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 0, 32);
	        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 10, biomedec.HLsand, 0, 64);
	    }
	    
	    
	    @SideOnly(Side.CLIENT)
	    public int getSkyColorByTemp(float par1)
	    {
	    	if(HighlandsMain.skyColorFlag)return 0xFFEC9B;
	    	else return super.getSkyColorByTemp(par1);
	    }
	    
	    
}
