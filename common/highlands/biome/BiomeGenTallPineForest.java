package highlands.biome;

import java.util.Random;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.HighlandsMain;
import highlands.worldgen.BiomeDecoratorHighlands;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenTreeFir;

public class BiomeGenTallPineForest extends BiomeGenBaseHighlands
{
	
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenTallPineForest(int par1)
	    {
	        super(par1);
	        
	        int trees = 4;
		    int grass = 2;
		    int flowers = 0;
		    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);

	        
	        this.minHeight = 0.2F;
	        this.maxHeight = 0.7F;
	        this.temperature = 0.1F;
	        this.rainfall = 0.8F;
	        
	    }

	    /**
	     * Gets a WorldGen appropriate for this biome.
	     */
	    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	    {
	        return (WorldGenerator)(par1Random.nextInt(8) == 0 ?
	        		new WorldGenTaiga1() : par1Random.nextInt(3) == 0 ?
	        		new WorldGenHighlandsShrub(0, 0) : 
	        			par1Random.nextInt(4) == 0? new WorldGenTreeFir(15, 10, false, true) : new WorldGenTreeFir(15, 10, false, false));
	    }

	    /**
	     * Gets a WorldGen appropriate for this biome.
	     */
	    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	    {
	        return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
	    }

	    public void decorate(World par1World, Random par2Random, int par3, int par4)
	    {
	        biomedec.decorate(par1World, par2Random, par3, par4);
	        HighlandsMain.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.coalGen, 0, 128);
	    }
}
