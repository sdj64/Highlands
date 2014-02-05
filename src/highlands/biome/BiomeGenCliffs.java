package highlands.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsGroundcover;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenCliffs extends BiomeGenBaseHighlands
{
	 private BiomeDecoratorHighlands biomedec;

	public BiomeGenCliffs(int par1)
    {
    	super(par1);
    	
	 	int trees = 2;
	    int grass = 4;
	    int flowers = 0;
	    int plants = 1;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
        
        this.maxHeight = 2.5F;
        this.minHeight = 2.5F;
        this.temperature = 0.4F;
        this.rainfall = 0.4F;
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.thornbush.blockID)
				: new WorldGenSmallPlants(HighlandsBlocks.blueberryBush.blockID));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(12) == 0 ? new WorldGenTaiga2(false) : new WorldGenHighlandsShrub(1, 1));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenHighlandsGroundcover(Block.tallGrass.blockID, 1, 1);
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16);
            int var8 = par2Random.nextInt(28) + 4;
            int var9 = par4 + par2Random.nextInt(16);
            int var10 = par1World.getBlockId(var7, var8, var9);

            if (var10 == Block.stone.blockID)
            {
                par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
            }
        }
        
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.ironGen, 64, 128);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 8, biomedec.redstoneGen, 16, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.lapisGen, 32, 64);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 32, 64);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.diamondGen, 16, 32);
    }
}






