package highlands.biome;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenBadlands extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.8F, 0.9F);
    private static final int trees = 1, grass = 6, flowers = 0, plants = 1;
	public BiomeGenBadlands(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers, plants));
        this.setHeight(biomeHeight);
        this.temperature = 0.6F;
        this.rainfall = 0.1F;
    }

    @Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.whiteFlower)
				: new WorldGenSmallPlants(HighlandsBlocks.thornbush));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (par1Random.nextInt(3) != 0 ? new WorldGenHighlandsShrub(0, 0) : new WorldGenTrees(false, 2 + par1Random.nextInt(3), 0, 0, false));
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
        
        biomedec.genOreHighlandsNoCheck(par1World, par2Random, par3, par4, 6, biomedec.HLrock, 62, 120);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 0, 32);
    }
    
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int tempA, int tempB, int tempC)
    {
        return getModdedBiomeGrassColor(0xCCB978);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1)
    {
    	if(HighlandsMain.skyColorFlag)
            return 0xFFCC8E;
    	else
            return super.getSkyColorByTemp(par1);
    }
}




