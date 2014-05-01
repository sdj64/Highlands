package highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class GenLayerAddHLIsland extends GenLayerBiomeReplacer
{
  public static final double NOISE_SCALE = 0.002D;
  public static double NOISE_THRESHOLD = 0.7D;
  
  public GenLayerAddHLIsland(long worldSeed, long seed, GenLayer parent)
  {
    super(worldSeed, seed, parent, 0.002D, NOISE_THRESHOLD, HighlandsBiomes.volcanoIsland.biomeID);
  }
  
  protected boolean canReplaceBiome(int biomeId)
  {
	  return true;
    //return BiomeDictionary.isBiomeOfType(BiomeGenBase.getBiome(biomeId), BiomeDictionary.Type.WATER);
  }
}
