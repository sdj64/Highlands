package highlands.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.HighlandsMain;
import highlands.worldgen.BiomeDecoratorHighlands;
import highlands.worldgen.WorldGenHighlandsShrub;

public class BiomeGenRockIsland extends BiomeGenBaseHighlands
{
	
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenRockIsland(int par1)
    {
        super(par1);
        
        int trees = -100;
	    int grass = 0;
	    int flowers = 0;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
        this.spawnableCreatureList.clear();
        
        this.topBlock = (byte) Block.stone.blockID;
        this.fillerBlock = (byte) Block.stone.blockID;
        
        this.maxHeight = 0.4F;
        this.minHeight = 0.0F;
        this.temperature = 0.4F;
        this.rainfall = 0.4F;
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
    	biomedec.decorate(par1World, par2Random, par3, par4);
    }
}
