package highlands.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreeAsh;

public class BiomeGenRainforest extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.2F, 0.4F);
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenRainforest(int par1)
    {
        super(par1);
	    int trees = 12;
	    int grass = 10;
	    int flowers = 0;
	    int plants = 2;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
        
	    biomedec.generateLakes = true;
	    this.setHeight(biomeHeight);
        this.temperature = 0.8F;
        this.rainfall = 1.0F;
        
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.leafyFern)
				: new WorldGenSmallPlants(HighlandsBlocks.lavender));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
    	if(par1Random.nextInt(3) == 0)
        return (WorldGenerator)new WorldGenTreeAsh(16, 8, false);
    	else if(par1Random.nextInt(2) == 0)
    	return (WorldGenerator)new WorldGenTrees(false, 5 + par1Random.nextInt(4), 0, 0, false);
    	else return new WorldGenHighlandsShrub(0, 0);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Blocks.tallgrass, 1) : new WorldGenTallGrass(Blocks.tallgrass, 2);
    }

    public void decorate(World par1World, Random par2Random, BiomeGenBaseHighlands biome, int par3, int par4)
    {
        biomedec.decorate(par1World, par2Random, biome, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.diamondGen, 0, 16);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 10, biomedec.HLsand, 0, 64);
    }
}
