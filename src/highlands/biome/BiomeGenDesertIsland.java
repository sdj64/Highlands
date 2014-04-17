package highlands.biome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import highlands.worldgen.WorldGenTreePalm;

public class BiomeGenDesertIsland extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.0F, 0.2F);
    private static final int trees = 1, grass = 0, flowers = 0;
    public BiomeGenDesertIsland(int par1){
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers));
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sand;
        this.setHeight(biomeHeight);
        this.temperature = 0.8F;
        this.rainfall = 0.4F;
        this.treeGenCache = new WorldGenTreePalm(8, 3, false);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return treeGenCache;
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 0, 32);
    }
	    
}
