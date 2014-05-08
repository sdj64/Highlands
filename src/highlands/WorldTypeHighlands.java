package highlands;

//import highlands.worldgen.layer.GenLayerBiomeHL;

import fabricator77.multiworld.api.IMWWorldType;
import highlands.overworld.ChunkProviderHLOverworld;
import highlands.worldgen.layer.GenLayerBiomeHL;

import java.util.List;
import java.util.Arrays;
import java.util.Set;
import java.util.ArrayList;

import com.google.common.collect.Sets;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;

public class WorldTypeHighlands extends WorldType implements IMWWorldType {

	private String name;
	
	public WorldTypeHighlands(String par2Str) {
		super(par2Str);
		name = par2Str;
	}
	 @Override
	 public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer)
	 {
	     GenLayer ret = new GenLayerBiomeHL(200L, parentLayer, this);
	     ret = GenLayerZoom.magnify(1000L, ret, 2);
	     ret = new GenLayerBiomeEdge(1000L, ret);
	     return ret;
	 }
	 
	 @Override
	 public WorldChunkManager getChunkManager(World world)
	 {
		 return new WorldChunkManagerHL(world);
	 }

	 @Override
	 public IChunkProvider getChunkGenerator(World world, String generatorOptions) {
		 return new ChunkProviderHLOverworld(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
	 }
}
