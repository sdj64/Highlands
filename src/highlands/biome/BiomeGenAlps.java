package highlands.biome;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenTreeFir;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenAlps extends BiomeGenBaseHighlands{
	private static final Height biomeHeight = new Height(0.8F, 1.6F);
    private static final int trees = 1, grass = 0, flowers = 0;
	
	public BiomeGenAlps(int par1)
	{
	    super(par1, new BiomeDecoratorHighlands(trees, grass, flowers));
	    this.setHeight(biomeHeight);
	    this.spawnableCreatureList.clear();
	    this.topBlock = Blocks.snow;
	    this.fillerBlock = Blocks.snow;
	    this.temperature = 0.0F;
	    this.rainfall = 0.7F;
	        
	    this.setEnableSnow();
	}

    @Override
	public WorldGenAbstractTree func_150567_a(Random par1Random)
	{
	    return (par1Random.nextInt(5) == 0 ? new WorldGenTreeFir(10, 5, false, false) : new WorldGenHighlandsShrub(1, 1));
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
        
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.ironGen, 0, 64);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1)
    {
    	if(HighlandsMain.skyColorFlag)
            return 0xC6E3FF;
    	else
            return super.getSkyColorByTemp(par1);
    }
}
