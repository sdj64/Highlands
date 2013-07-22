package highlands.biome;

import java.util.Random;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.HighlandsMain;
import highlands.worldgen.BiomeDecoratorHighlands;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenTreeMangrove;

public class BiomeGenEstuary extends BiomeGenBaseHighlands
{
	
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenEstuary(int par1){
			super(par1);
			
			int trees = 10;
		    int grass = 0;
		    int flowers = 0;
		    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
	        
	        this.topBlock = (byte)Block.sand.blockID;
	        this.fillerBlock = (byte)Block.dirt.blockID;
	        
	        this.minHeight = -0.28F;
	        this.maxHeight = 0.1F;
	        this.temperature = 0.8F;
	        this.rainfall = 0.9F;
	        
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
	        return (WorldGenerator)new WorldGenTreeMangrove(4, 2, false);
	    }

	    public void decorate(World par1World, Random par2Random, int par3, int par4)
	    {
	    	if(par2Random.nextBoolean())biomedec.treesPerChunk = 0;
	    	else biomedec.treesPerChunk = 10;
	    	
	        biomedec.decorate(par1World, par2Random, par3, par4);
	        HighlandsMain.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.diamondGen, 0, 16);
	        HighlandsMain.genOreHighlands(par1World, par2Random, par3, par4, 15, HighlandsMain.HLsand, 0, 64);
	    }
	    
}











