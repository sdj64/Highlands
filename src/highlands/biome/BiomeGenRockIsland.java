package highlands.biome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BiomeGenRockIsland extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.0F, 0.4F);
    private static final int trees = -100, grass = 0, flowers = 0;
    public BiomeGenRockIsland(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers));
        this.spawnableCreatureList.clear();
        this.topBlock = Blocks.stone;
        this.fillerBlock = Blocks.stone;
        this.setHeight(biomeHeight);
        this.temperature = 0.4F;
        this.rainfall = 0.4F;
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
    	biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
    }
}
