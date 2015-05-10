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
    public static WorldGenerator HLwatermelon = new WorldGenWatermelon();
    public static WorldGenerator HLemerald = new StoneToEmeraldReplacer();
	private int highlandsPlantsPerChunk;
    public boolean generatePumpkin, generateWatermelon;
	
	public BiomeDecoratorHighlands(int trees, int grass, int flowers, int hlPlants) {
		super();
		this.treesPerChunk = trees;
		this.grassPerChunk = grass;
		this.flowersPerChunk = flowers;
		this.highlandsPlantsPerChunk = hlPlants;
		this.sandPerChunk2 = 0;
		this.sandPerChunk = 15;
		this.generateLakes = false;
        this.generatePumpkin = false;
        this.generateWatermelon = false;
	}
	
	public BiomeDecoratorHighlands(int trees, int grass, int flowers) {
		this(trees, grass, flowers, 0);
	}
	
    @Override
	protected void genDecorations(BiomeGenBase biome){
		super.genDecorations(biome);
        int var2 = this.chunk_X + 8;
        int var4 = this.chunk_Z + 8;
		if(generatePumpkin)
        {
            int var3 = this.randomGenerator.nextInt(128);
            HLpumpkin.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }

        if(generateWatermelon)
        {
            int var3 = this.randomGenerator.nextInt(128);
            HLwatermelon.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }

        // highlands plants generator
        if(HighlandsMain.plantsFlag){
	        for (int j = 0; j < this.highlandsPlantsPerChunk; ++j)
	        {
	            int l = this.randomGenerator.nextInt(128);
	            WorldGenerator worldgenerator1 = ((BiomeGenBaseHighlands)biome).getRandomWorldGenForHighlandsPlants(this.randomGenerator);
                if(worldgenerator1!=null)
	                worldgenerator1.generate(this.currentWorld, this.randomGenerator, var2, l, var4);
	        }
        }
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

    public static class StoneToEmeraldReplacer extends WorldGenerator{

        @Override
        public boolean generate(World world, Random rand, int x, int y, int z) {
            if(world.getBlock(x, y, z) == Blocks.stone){
                world.setBlock(x, y, z, Blocks.emerald_ore, 0, 2);
                return true;
            }
            return false;
        }
    }
}