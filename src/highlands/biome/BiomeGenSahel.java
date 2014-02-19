package highlands.biome;

import java.util.Random;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreeAcacia;

public class BiomeGenSahel extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.3F, 0.5F);
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenSahel(int par1){
		super(par1);
		
		int trees = 1;
	    int grass = 5;
	    int flowers = 0;
	    int plants = 1;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
        
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sand;
        
        this.spawnableCreatureList.clear();
        this.setHeight(biomeHeight);
        this.temperature = 1.6F;
        this.rainfall = 0.1F;
        
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.whiteFlower)
				: new WorldGenSmallPlants(HighlandsBlocks.thornbush));
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
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? 
        		new WorldGenTreeAcacia(7, 3, false) : new WorldGenHighlandsShrub(0, 0));
    }

    public void decorate(World par1World, Random par2Random, BiomeGenBaseHighlands biome, int par3, int par4)
    {
    	biomedec.genOreHighlandsNoCheck(par1World, par2Random, par3, par4, 2, biomedec.HLdirt, 62, 80);
        biomedec.decorate(par1World, par2Random, biome, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 0, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 10, biomedec.HLsand, 0, 64);
    }
	    
}
