package highlands.biome;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenTreePalm;

public class BiomeGenDunes extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.5F, 1.0F);
    private static final int trees = -999, grass = -999, flowers = -999;
    public BiomeGenDunes(int par1){
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers));
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sand;
        this.spawnableCreatureList.clear();
        this.setHeight(biomeHeight);
        this.temperature = 2.0F;
        this.rainfall = 0.0F;

        this.setDisableRain();
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
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return new WorldGenTreePalm(8, 3, false);
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 0, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 10, biomedec.HLsand, 0, 64);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1)
    {
        if(HighlandsMain.skyColorFlag)
            return 0xFFEC9B;
        else
            return super.getSkyColorByTemp(par1);
    }
}
