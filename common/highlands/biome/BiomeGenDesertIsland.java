package highlands.biome;

import java.util.Random;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenTreePalm;

public class BiomeGenDesertIsland extends BiomeGenBaseHighlands
{
	
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenDesertIsland(int par1){
			super(par1);
			
			int trees = 1;
		    int grass = 0;
		    int flowers = 0;
		    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
	        
	        this.topBlock = (byte)Block.sand.blockID;
	        this.fillerBlock = (byte)Block.sand.blockID;
	        
	        this.minHeight = 0.0F;
	        this.maxHeight = 0.2F;
	        this.temperature = 0.8F;
	        this.rainfall = 0.4F;
	        
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
	    }
	    
}
