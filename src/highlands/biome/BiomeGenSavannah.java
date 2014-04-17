package highlands.biome;

import java.util.Random;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import highlands.worldgen.WorldGenTreeAcacia;

public class BiomeGenSavannah extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.2F, 0.2F);
    private static final int trees = 0,grass = 18,flowers = 0;
	public BiomeGenSavannah(int par1){
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;
        this.setHeight(biomeHeight);
        this.temperature = 1.2F;
        this.rainfall = 0.1F;
        this.treeGenCache = new WorldGenTreeAcacia(7, 3, false);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return this.treeGenCache;
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 0, 32);
    }
}
