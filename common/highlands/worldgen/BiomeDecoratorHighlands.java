package highlands.worldgen;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

import java.util.Random;

import highlands.HighlandsMain;
import highlands.biome.BiomeGenBaseHighlands;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeDecoratorHighlands extends BiomeDecorator
{
	private int highlandsPlantsPerChunk;
	
	
	public BiomeDecoratorHighlands(BiomeGenBase par1BiomeGenBase, int trees, int grass, int flowers, int hlPlants) {
		super(par1BiomeGenBase);
		this.treesPerChunk = trees;
		this.grassPerChunk = grass;
		this.flowersPerChunk = flowers;
		this.highlandsPlantsPerChunk = hlPlants;
		this.sandPerChunk2 = 0;
		this.sandPerChunk = 15;
		this.generateLakes = false;
	}
	
	public BiomeDecoratorHighlands(BiomeGenBase par1BiomeGenBase, int trees, int grass, int flowers) {
		this(par1BiomeGenBase, trees, grass, flowers, 0);
	}
	
	
	public void decorate(World par1World, Random par2Random, int par3, int par4){
		super.decorate(par1World, par2Random, par3, par4);
		
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
            int var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            int var3 = this.randomGenerator.nextInt(128);
            int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }
        
        if (/*randomGenerator.nextInt(16) == 0 &&*/ (biome.biomeName.equals("Tropics") || biome.biomeName.equals("Tropcial Islands")))
        {
            int var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            int var3 = this.randomGenerator.nextInt(128);
            int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            (new WorldGenWatermelon()).generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }
        
        // highlands plants generator
        if(HighlandsMain.plantsFlag){
	        for (int j = 0; j < this.highlandsPlantsPerChunk; ++j)
	        {
	            int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
	            int l = this.randomGenerator.nextInt(128);
	            int i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
	            WorldGenerator worldgenerator1 = ((BiomeGenBaseHighlands)this.biome).getRandomWorldGenForHighlandsPlants(this.randomGenerator);
	            worldgenerator1.generate(this.currentWorld, this.randomGenerator, k, l, i1);
	        }
        }

        this.currentWorld = null;
        this.randomGenerator = null;
		
	}
}