package highlands;

import java.util.Random;

import org.apache.logging.log4j.Level;

import buildcraft.energy.worldgen.SimplexNoise;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public abstract class GenLayerBiomeReplacer
  extends GenLayer
{
  protected final double xOffset;
  protected final double zOffset;
  protected final double noiseScale;
  protected final double noiseThreshold;
  protected final int newBiomeId;
  
  // temp
  private Random rand;
  
  public GenLayerBiomeReplacer(long worldSeed, long seed, GenLayer parent, double noiseScale, double noiseThreshold, int newBiomeId)
  {
    super(seed);
    this.parent = parent;
    this.noiseScale = noiseScale;
    this.noiseThreshold = noiseThreshold;
    this.newBiomeId = newBiomeId;
    rand = new Random(worldSeed);
    this.xOffset = 0; //(rand.nextInt(500000) - 250000);
    this.zOffset = 0; //(rand.nextInt(500000) - 250000);
  }
  
  protected abstract boolean canReplaceBiome(int paramInt);
  
  public int[] getInts(int x, int z, int width, int length)
  {
	/**
    int[] inputBiomeIDs = this.parent.getInts(x - 1, z - 1, width + 2, length + 2);
    int[] outputBiomeIDs = IntCache.getIntCache(width * length);
    for (int xIter = 0; xIter < width; xIter++) {
      for (int zIter = 0; zIter < length; zIter++)
      {
    	initChunkSeed(xIter + x, zIter + z);
        int currentBiomeId = inputBiomeIDs[(xIter + 1 + (zIter + 1) * (width + 2))];
        if ((canReplaceBiome(currentBiomeId)) && (SimplexNoise.noise((xIter + x + this.xOffset) * this.noiseScale, (zIter + z + this.zOffset) * this.noiseScale) > this.noiseThreshold)) {
          outputBiomeIDs[(xIter + zIter * width)] = this.newBiomeId;
        } else {
          outputBiomeIDs[(xIter + zIter * width)] = currentBiomeId;
        }
      }
    }
    */
	  
	  int[] inputBiomeIDs = this.parent.getInts(x - 1, z - 1, width + 2, length + 2);
	    int[] outputBiomeIDs = IntCache.getIntCache(width * length);
	    for (int xIter = 0; xIter < width; xIter++) {
	      for (int zIter = 0; zIter < length; zIter++)
	      {
	    	initChunkSeed(xIter + x, zIter + z);
	        int currentBiomeId = inputBiomeIDs[(xIter + 1 + (zIter + 1) * (width + 2))];
	        if (canReplaceBiome(currentBiomeId) && (rand.nextInt(10) == 0)) {
	          outputBiomeIDs[(xIter + zIter * width)] = this.newBiomeId;
	          Logs.log(Level.INFO, "[Highlands] Volcano biome generated");
	        }
	        else {
	          outputBiomeIDs[(xIter + zIter * width)] = currentBiomeId;
	        }
	      }
	    }
    return outputBiomeIDs;
  }
}

