package highlands.biome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenMountain;

public class BiomeGenRockMountains extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(2.0F, 2.0F);
    private static final int trees = -999 ,grass = 2 ,flowers = 0;
    public BiomeGenRockMountains(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers));
        this.spawnableCreatureList.clear();
        this.fillerBlock = Blocks.stone;
        this.setHeight(biomeHeight);
        this.temperature = 0.6F;
        this.rainfall = 0.5F;
        //this.setEnableSnow();
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (new WorldGenHighlandsShrub(1, 1));
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
    	if(par2Random.nextInt(2) == 0)
    		new WorldGenMountain(15, 15, false, 1).generate(par1World, par2Random, par3+par2Random.nextInt(16), 128, par4+par2Random.nextInt(16));
    	
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.ironGen, 64, 128);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 40, biomedec.ironGen, 0, 128);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 8, biomedec.redstoneGen, 16, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.lapisGen, 32, 64);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 32, 64);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.diamondGen, 16, 32);
    }
}
