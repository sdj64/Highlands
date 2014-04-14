package highlands.biome;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;

public class BiomeGenTundra extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.1F, 0.3F);
    private static final int trees = 0 ,grass = 0, flowers = 0;
    public BiomeGenTundra(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers));
        this.spawnableCreatureList.clear();
        this.topBlock = Blocks.snow;
        this.fillerBlock = Blocks.snow;
        this.setHeight(biomeHeight);
        this.temperature = 0.0F;
        this.rainfall = 0.5F;
        this.setEnableSnow();
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (new WorldGenHighlandsShrub(1, 1));
        
        //par1Random.nextInt(2) == 0 ? new WorldGenHighlandsShrub(1, 1) : new WorldGenTallGrass(Block.tallGrass.blockID, 1)
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.HLice, 0, 128);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1)
    {
    	if(HighlandsMain.skyColorFlag)return 0xC6E3FF;
    	else return super.getSkyColorByTemp(par1);
    }
}
