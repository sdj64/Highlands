package highlands.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenTreePoplar;

public class BiomeGenLake extends BiomeGenBaseHighlands
{
    private BiomeDecoratorHighlands biomedec;

	public BiomeGenLake(int par1)
    {
		super(par1);
		
    	int trees = 3;
	    int grass = 12;
	    int flowers = 0;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
    	
	    this.minHeight = -0.7F;
	    this.maxHeight = 0.2F;
        this.temperature = 0.8F;
        this.rainfall = 0.8F;
	    
        this.spawnableCreatureList.clear();
    }
	
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)new WorldGenTreePoplar(10, 4, false);
    }
}
