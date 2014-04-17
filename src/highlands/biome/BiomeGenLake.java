package highlands.biome;

import java.util.Random;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import highlands.worldgen.WorldGenTreePoplar;

public class BiomeGenLake extends BiomeGenBaseHighlands
{
    private static final Height biomeHeight = new Height(-0.7F, 0.2F);
    private static final int trees = 3, grass = 12, flowers = 0;
    public BiomeGenLake(int par1)
    {
		super(par1, new BiomeDecoratorHighlands(trees, grass, flowers));
    	this.setHeight(biomeHeight);
        this.temperature = 0.8F;
        this.rainfall = 0.8F;
        this.spawnableCreatureList.clear();
        this.treeGenCache = new WorldGenTreePoplar(10, 4, false);
    }

    @Override
	public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
		return this.treeGenCache;
    }
}
