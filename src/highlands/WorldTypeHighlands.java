package highlands;

import java.util.List;
import java.util.ArrayList;

import com.google.common.collect.ImmutableList;

import highlands.worldgen.layer.GenLayerBiomeHL;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class WorldTypeHighlands extends WorldType {

    private List<BiomeGenBase> biomesForWorldType;

    public WorldTypeHighlands(String par2Str) {
		super(par2Str);
		biomesForWorldType = new ArrayList<BiomeGenBase>();
	}

	public void addBiomeList(BiomeGenBase... b){
		for(BiomeGenBase i : b){
            biomesForWorldType.add(i);
		}
	}

	public void addBiomeList(List<BiomeGenBase> b){
		for(BiomeGenBase i : b){
            biomesForWorldType.add(i);
		}
	}

    public int getBiomeListSize(){
        return biomesForWorldType.size();
    }

    public BiomeGenBase[] getBiomeList(){
        return ImmutableList.copyOf(biomesForWorldType).toArray(new BiomeGenBase[getBiomeListSize()]);
    }
	
	/*
	@Override
	public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    { 
        return new ChunkProviderGenerateNoLakes(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }
    */

    @Override
    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer)
    {
        GenLayer ret = new GenLayerBiomeHL(200L, parentLayer, this);
        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdge(1000L, ret);
        return ret;
    }
}
