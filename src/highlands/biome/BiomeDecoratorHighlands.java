package highlands.biome;

import java.util.Random;

import highlands.HighlandsMain;
import highlands.worldgen.WorldGenUnderground2;
import highlands.worldgen.WorldGenWatermelon;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorHighlands extends BiomeDecorator
{
    //Ore Generators
    public static WorldGenerator HLsand = new WorldGenMinable(Blocks.sand, 32);
    public static WorldGenerator HLice = new WorldGenMinable(Blocks.ice, 32);
    public static WorldGenerator HLwater = new WorldGenUnderground2(Blocks.water, 4);
    public static WorldGenerator HLlava = new WorldGenUnderground2(Blocks.lava, 8);
    public static WorldGenerator HLdirt = new WorldGenUnderground2(Blocks.dirt, 72, Blocks.sand);
    public static WorldGenerator HLrock = new WorldGenUnderground2(Blocks.stone, 72, Blocks.dirt);
    public static WorldGenerator HLobsidian = new WorldGenMinable(Blocks.obsidian, 8);
    public static WorldGenerator HLpumpkin = new WorldGenPumpkin();
    public static WorldGenerator HLWatermelon = new WorldGenWatermelon();
	private int highlandsPlantsPerChunk;
	
	public BiomeDecoratorHighlands(int trees, int grass, int flowers, int hlPlants) {
		super();
		this.treesPerChunk = trees;
		this.grassPerChunk = grass;
		this.flowersPerChunk = flowers;
		this.highlandsPlantsPerChunk = hlPlants;
		this.sandPerChunk2 = 0;
		this.sandPerChunk = 15;
		this.generateLakes = false;
	}
	
	public BiomeDecoratorHighlands(int trees, int grass, int flowers) {
		this(trees, grass, flowers, 0);
	}
	
    @Override
	public void decorateChunk(World par1World, Random par2Random, BiomeGenBase biome, int par3, int par4){
		super.decorateChunk(par1World, par2Random, biome, par3, par4);
		
		if (this.currentWorld != null)
        {
            throw new RuntimeException("Already decorating!!");
        }
        else
        {
            this.currentWorld = par1World;
            this.randomGenerator = par2Random;
            this.chunk_X = par3;
            this.chunk_Z = par4;
        }
		
		if (/*randomGenerator.nextInt(8) == 0 &&*/ (biome.biomeName.equals("Autumn Forest") || biome.biomeName.equals("Bog")))
        {
            int var2 = this.chunk_X + 8;
            int var3 = this.randomGenerator.nextInt(128);
            int var4 = this.chunk_Z + 8;
            HLpumpkin.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }
        
        if (/*randomGenerator.nextInt(16) == 0 &&*/ (biome.biomeName.equals("Tropics") || biome.biomeName.equals("Tropcial Islands")))
        {
            int var2 = this.chunk_X + 8;
            int var3 = this.randomGenerator.nextInt(128);
            int var4 = this.chunk_Z + 8;
            HLWatermelon.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }
        
        // highlands plants generator
        if(HighlandsMain.plantsFlag){
	        for (int j = 0; j < this.highlandsPlantsPerChunk; ++j)
	        {
	            int k = this.chunk_X + 8;
	            int l = this.randomGenerator.nextInt(128);
	            int i1 = this.chunk_Z + 8;
	            WorldGenerator worldgenerator1 = ((BiomeGenBaseHighlands)biome).getRandomWorldGenForHighlandsPlants(this.randomGenerator);
                if(worldgenerator1!=null)
	                worldgenerator1.generate(this.currentWorld, this.randomGenerator, k, l, i1);
	        }
        }

        this.currentWorld = null;
        this.randomGenerator = null;
		
	}
	
	
	
	//Method for ore generators: inside the decorate method in biome classes, call this.
    //here is the decorate method:
    /*
    public void decorate(World par1World, Random par2Random, int par3, int par4){
        genOreHighlands(par1World, par2Random, par3, par4, timesPerChunk, HLWorldGenerator, minH, maxH)
    }
     */

    //WorldGenerators are in biomedec.goldGen, for example for gold.
    public void genOreHighlands(World par1World, Random par2Random, int locX, int locZ, int timesPerChunk, WorldGenerator HLWorldGenerator, int minH, int maxH)
    {
        if(HighlandsMain.useOreGens){
            for (int var5 = 0; var5 < timesPerChunk; ++var5)
            {
                int var6 = locX + par2Random.nextInt(16);
                int var7 = par2Random.nextInt(maxH - minH) + minH;
                int var8 = locZ + par2Random.nextInt(16);
                HLWorldGenerator.generate(par1World, par2Random, var6, var7, var8);
            }
        }
    }

    public void genOreHighlandsNoCheck(World par1World, Random par2Random, int locX, int locZ, int timesPerChunk, WorldGenerator HLWorldGenerator, int minH, int maxH)
    {
        for (int var5 = 0; var5 < timesPerChunk; ++var5)
        {
            int var6 = locX + par2Random.nextInt(16);
            int var7 = par2Random.nextInt(maxH - minH) + minH;
            int var8 = locZ + par2Random.nextInt(16);
            HLWorldGenerator.generate(par1World, par2Random, var6, var7, var8);
        }
    }
}