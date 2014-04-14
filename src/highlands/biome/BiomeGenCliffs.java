package highlands.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsGroundcover;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenCliffs extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(2.5F, 2.5F);
    private static final int trees = 2, grass = 4, flowers = 0,plants = 1;
	public BiomeGenCliffs(int par1)
    {
    	super(par1, new BiomeDecoratorHighlands(trees, grass, flowers, plants));
        this.setHeight(biomeHeight);
        this.temperature = 0.4F;
        this.rainfall = 0.4F;
    }

    @Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.thornbush)
				: new WorldGenSmallPlants(HighlandsBlocks.blueberryBush));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (par1Random.nextInt(12) == 0 ? new WorldGenTaiga2(false) : new WorldGenHighlandsShrub(1, 1));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenHighlandsGroundcover(Blocks.tallgrass, 1, 1);
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16);
            int var8 = par2Random.nextInt(28) + 4;
            int var9 = par4 + par2Random.nextInt(16);
            Block var10 = par1World.getBlock(var7, var8, var9);

            if (var10 == Blocks.stone)
            {
                par1World.setBlock(var7, var8, var9, Blocks.emerald_ore, 0, 2);
            }
        }
        
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.ironGen, 64, 128);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 8, biomedec.redstoneGen, 16, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.lapisGen, 32, 64);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 32, 64);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.diamondGen, 16, 32);
    }
}






