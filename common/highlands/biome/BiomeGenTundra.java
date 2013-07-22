package highlands.biome;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.HighlandsMain;
import highlands.worldgen.BiomeDecoratorHighlands;
import highlands.worldgen.WorldGenHighlandsShrub;

public class BiomeGenTundra extends BiomeGenBaseHighlands
{
    private BiomeDecoratorHighlands biomedec;

	public BiomeGenTundra(int par1)
    {
        super(par1);
        
        int trees = 0;
	    int grass = 0;
	    int flowers = 0;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
        
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.blockSnow.blockID;
        this.fillerBlock = (byte)Block.blockSnow.blockID;
        
        this.maxHeight = 0.1F;
        this.minHeight = 0.3F;
        this.temperature = 0.0F;
        this.rainfall = 0.5F;
        
        this.setEnableSnow();
    }
    
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(new WorldGenHighlandsShrub(1, 1));
        
        //par1Random.nextInt(2) == 0 ? new WorldGenHighlandsShrub(1, 1) : new WorldGenTallGrass(Block.tallGrass.blockID, 1)
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorate(par1World, par2Random, par3, par4);
        HighlandsMain.genOreHighlands(par1World, par2Random, par3, par4, 20, HighlandsMain.HLice, 0, 128);
    }
    
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1)
    {
    	if(HighlandsMain.skyColorFlag)return 0xC6E3FF;
    	else return super.getSkyColorByTemp(par1);
    }
}
