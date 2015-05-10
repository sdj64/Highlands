package highlands.biome;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import highlands.worldgen.WorldGenTreeMangrove;

public class BiomeGenEstuary extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(-0.28F, 0.1F);
    private static final int trees = 10, grass = 0, flowers = 0;
    public BiomeGenEstuary(int par1){
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers));
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.dirt;
        this.setHeight(biomeHeight);
        this.temperature = 0.8F;
        this.rainfall = 0.9F;
        this.treeGenCache = new WorldGenTreeMangrove(4, 2, false);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return this.treeGenCache;
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        if(par2Random.nextBoolean())
            biomedec.treesPerChunk = 0;
        else
            biomedec.treesPerChunk = 10;

        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.diamondGen, 0, 16);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 15, biomedec.HLsand, 0, 64);
    }
}











