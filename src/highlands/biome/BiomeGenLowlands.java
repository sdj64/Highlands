package highlands.biome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreeFir;

public class BiomeGenLowlands extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(-0.2F, 0.5F);
    private static final int trees = 2, grass = 4, flowers = 0, plants = 4;
    public BiomeGenLowlands(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers, plants));
        this.setHeight(biomeHeight);
        this.temperature = 0.5F;
        this.rainfall = 1.2F;
    }

    @Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (rand.nextInt(3) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.cattail)
				: (rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.cotton)
				: new WorldGenSmallPlants(HighlandsBlocks.blueFlower)));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (par1Random.nextInt(8) == 0 ?
        		new WorldGenHighlandsShrub(0, 0) : par1Random.nextInt(4) != 0 ?
        		new WorldGenTrees(false, 3 + par1Random.nextInt(3), 0, 0, false) : new WorldGenTreeFir(10, 5, false, false));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.tallgrass, 1);
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.coalGen, 0, 128);
    }
}
