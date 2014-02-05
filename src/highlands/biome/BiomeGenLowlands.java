package highlands.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreeFir;

public class BiomeGenLowlands extends BiomeGenBaseHighlands
{
	
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenLowlands(int par1)
    {
        super(par1);
        
        int trees = 2;
	    int grass = 4;
	    int flowers = 0;
	    int plants = 4;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);

        this.minHeight = -0.2F;
        this.maxHeight = 0.5F;
        this.temperature = 0.5F;
        this.rainfall = 1.2F;
        
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(3) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.cattail.blockID)
				: (rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.cotton.blockID)
				: new WorldGenSmallPlants(HighlandsBlocks.blueFlower.blockID)));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(8) == 0 ?
        		new WorldGenHighlandsShrub(0, 0) : par1Random.nextInt(4) != 0 ?
        		new WorldGenTrees(false, 3 + par1Random.nextInt(3), 0, 0, false) : new WorldGenTreeFir(10, 5, false, false));
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
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.coalGen, 0, 128);
    }
}
