package highlands.biome;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenMountain;

public class BiomeGenRockMountains extends BiomeGenBaseHighlands
{
    private BiomeDecoratorHighlands biomedec;

	public BiomeGenRockMountains(int par1)
    {
        super(par1);
        
        int trees = -999;
	    int grass = 2;
	    int flowers = 0;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
        
        this.spawnableCreatureList.clear();
        
        this.fillerBlock = (byte) Block.stone.blockID;
        this.maxHeight = 2.0F;
        this.minHeight = 2.0F;
        this.temperature = 0.6F;
        this.rainfall = 0.5F;
        
        //this.setEnableSnow();
    }
    
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(new WorldGenHighlandsShrub(1, 1));
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
    	if(par2Random.nextInt(2) == 0 && !par1World.provider.terrainType.getTranslateName().equals("ATG - Alternate"))
    		new WorldGenMountain(15, 15, false, 1).generate(par1World, par2Random, par3+par2Random.nextInt(16), 128, par4+par2Random.nextInt(16));
    	
        biomedec.decorate(par1World, par2Random, par3, par4);
        
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.ironGen, 64, 128);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 40, biomedec.ironGen, 0, 128);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 8, biomedec.redstoneGen, 16, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.lapisGen, 32, 64);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 32, 64);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.diamondGen, 16, 32);
    }
}
