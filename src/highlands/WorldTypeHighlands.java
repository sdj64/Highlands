package highlands;

import java.util.List;
import java.util.Arrays;
import java.util.Set;
import java.util.ArrayList;

import com.google.common.collect.Sets;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;

public class WorldTypeHighlands extends WorldType {

	private String name;
	
	public WorldTypeHighlands(int par1, String par2Str) {
		super(par1, par2Str);
		name = par2Str;
		biomesForWorldType = new BiomeGenBase[] {};
	}

	@Override
	public String getTranslateName(){
		return name;
	}
	
	public BiomeGenBase[] getBiomes(){
		return this.biomesForWorldType;
	}
	
	public static void addBiomeList(WorldType w, BiomeGenBase[] b){
		for(BiomeGenBase i : b){
			w.addNewBiome(i);
		}
	}
	
	public static void addBiomeList(WorldType w, ArrayList<BiomeGenBase> b){
		for(BiomeGenBase i : b){
			w.addNewBiome(i);
		}
	}
	
	@Override
	public void addNewBiome(BiomeGenBase biome){
		BiomeGenBase[] newBiomes = new BiomeGenBase[this.biomesForWorldType.length + 1];
		int count = 0;
		for(BiomeGenBase i : this.biomesForWorldType){
			newBiomes[count] = i;
			count++;
		}
		newBiomes[newBiomes.length - 1] = biome;
		this.biomesForWorldType = newBiomes;
	}
	
	public static void addBiomeList(WorldTypeHighlands w, BiomeGenBase[] b){
		int num = b.length + w.biomesForWorldType.length;
		BiomeGenBase[] newBiomes = new BiomeGenBase[num];
		int count = 0;
		for(BiomeGenBase i : w.biomesForWorldType){
			newBiomes[count] = i;
			count++;
		}
		for(BiomeGenBase i : b){
			newBiomes[count] = i;
			count++;
		}
		w.biomesForWorldType = newBiomes;
	}
	
	/*
	public void addNewBiome(BiomeGenBase biome)
    {
        List<BiomeGenBase> newBiomesForWorld = Arrays.asList(biomesForWorldType);
        newBiomesForWorld.add(biome);
        biomesForWorldType = newBiomesForWorld.toArray(new BiomeGenBase[0]);
    }
    */
	
	/*
	@Override
	public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    { 
        return new ChunkProviderGenerateNoLakes(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }
    */
}
