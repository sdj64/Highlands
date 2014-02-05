package highlands.biome;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenTreeFir;
import highlands.worldgen.WorldGenUnderground2;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenAlps extends BiomeGenBaseHighlands{
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenAlps(int par1)
	{
	    super(par1);
	    
	    int trees = 1;
	    int grass = 0;
	    int flowers = 0;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
	    
	    this.spawnableCreatureList.clear();
	    this.topBlock = (Block)Blocks.snow;
	    this.fillerBlock = (Block)Blocks.snow;
	        
	    this.maxHeight = 1.6F;
	    this.minHeight = 0.8F;
	    this.temperature = 0.0F;
	    this.rainfall = 0.7F;
	        
	    this.setEnableSnow();
	}
	    
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
	    return (WorldGenerator)(par1Random.nextInt(5) == 0 ? new WorldGenTreeFir(10, 5, false, false) : new WorldGenHighlandsShrub(1, 1));
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
            //TODO-                 getBlock
            Block var10 = par1World.func_147439_a(var7, var8, var9);

            if (var10 == Blocks.stone)
            {
            	//TODO-   setBlock
                par1World.func_147465_d (var7, var8, var9, Blocks.emerald_ore, 0, 2);
            }
        }
        
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.ironGen, 0, 64);
    }
    
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1)
    {
    	if(HighlandsMain.skyColorFlag)return 0xC6E3FF;
    	else return super.getSkyColorByTemp(par1);
    }
    
    /*
    public void setSpawns(List hostile, List creature, List water){
    	this.spawnableMonsterList = hostile;
    	this.spawnableCreatureList = creature;
    	this.spawnableWaterCreatureList = water;
    }
    */
}
