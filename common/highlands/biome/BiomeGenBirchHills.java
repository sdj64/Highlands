package highlands.biome;

import java.util.Random;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.BiomeDecoratorHighlands;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreePoplar;

public class BiomeGenBirchHills extends BiomeGenBaseHighlands
{
    private BiomeDecoratorHighlands biomedec;

	public BiomeGenBirchHills(int par1)
    {
        super(par1);
	    int trees = 3;
	    int grass = 10;
	    int flowers = 4;
	    int plants = 1;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
        
        this.theBiomeDecorator.generateLakes = false;
        
        this.minHeight = -0.1F;
        this.maxHeight = 0.8F;
        
        this.temperature = 0.6F;
        this.rainfall = 0.8F;
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.cotton.blockID)
				: new WorldGenSmallPlants(HighlandsBlocks.blueFlower.blockID));
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
        return (WorldGenerator)new WorldGenTreePoplar(10, 4, false);
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorate(par1World, par2Random, par3, par4);
        HighlandsMain.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.lapisGen, 0, 32);
        HighlandsMain.genOreHighlands(par1World, par2Random, par3, par4, 10, HighlandsMain.HLwater, 0, 64);
    }

}
