package highlands.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreePalm;

public class BiomeGenTropicalIslands extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(-0.3F, 0.5F);
    private BiomeDecoratorHighlands biomedec;

	public BiomeGenTropicalIslands(int par1)
    {
        super(par1);
	    int trees = 5;
	    int grass = 8;
	    int flowers = 4;
	    int plants = 4;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
	    this.setHeight(biomeHeight);
        
        this.temperature = 1.0F;
        this.rainfall = 1.2F;
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
	    return new WorldGenTallGrass(Blocks.tallgrass, 2);
	}
    
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)new WorldGenTreePalm(8, 3, false);
    }
    
    public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.leafyFern)
				: new WorldGenSmallPlants(HighlandsBlocks.whiteFlower));
	}
    
    public void decorate(World par1World, Random par2Random, BiomeGenBaseHighlands biome, int par3, int par4)
    {
        biomedec.decorate(par1World, par2Random, biome, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.lapisGen, 0, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 10, biomedec.HLwater, 0, 64);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 10, biomedec.HLsand, 0, 64);
        
        WorldGenVines var5 = new WorldGenVines();
        for (int var6 = 0; var6 < 50; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16) + 8;
            byte var8 = 36;
            int var9 = par4 + par2Random.nextInt(16) + 8;
            var5.generate(par1World, par2Random, var7, var8, var9);
        }
    }

}
