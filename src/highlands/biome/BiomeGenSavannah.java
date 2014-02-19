package highlands.biome;

import java.util.Random;
import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenTreeAcacia;

public class BiomeGenSavannah extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.2F, 0.2F);
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenSavannah(int par1){
			super(par1);
			
			this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
			
			int trees = 0;
		    int grass = 18;
		    int flowers = 0;
		    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
	        
	        this.topBlock = Blocks.grass;
	        this.fillerBlock = Blocks.dirt;
	        this.setHeight(biomeHeight);
	        this.temperature = 1.2F;
	        this.rainfall = 0.1F;
	        
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
	        return (WorldGenerator)new WorldGenTreeAcacia(7, 3, false);
	    }

	    public void decorate(World par1World, Random par2Random, BiomeGenBaseHighlands biome, int par3, int par4)
	    {
	        biomedec.decorate(par1World, par2Random, biome, par3, par4);
	        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 0, 32);
	    }
}
