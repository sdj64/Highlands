package highlands.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;

public class BiomeGenRockIsland extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.0F, 0.4F);
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenRockIsland(int par1)
    {
        super(par1);
        
        int trees = -100;
	    int grass = 0;
	    int flowers = 0;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
        this.spawnableCreatureList.clear();
        
        this.topBlock = Blocks.stone;
        this.fillerBlock = Blocks.stone;
        this.setHeight(biomeHeight);
        this.temperature = 0.4F;
        this.rainfall = 0.4F;
    }
    
    public void decorate(World par1World, Random par2Random, BiomeGenBaseHighlands biome, int par3, int par4)
    {
    	biomedec.decorate(par1World, par2Random, biome, par3, par4);
    }
}
