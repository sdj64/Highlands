package highlands.overworld;

import highlands.worldgen.layer.GenLayerHL;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.Level;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
public class WorldChunkManagerHL extends WorldChunkManager
{
	private GenLayer mbGenBiomes;
	private GenLayer mbBiomeIndexLayer;
	private BiomeCache mbBiomeCache;
	private List<BiomeGenBase> mbBiomesToSpawnIn;
	
	protected WorldChunkManagerHL()
    {
        this.mbBiomeCache = new BiomeCache(this);
        this.mbBiomesToSpawnIn = new ArrayList();
        this.mbBiomesToSpawnIn.addAll(allowedBiomes);
    }
	
	public WorldChunkManagerHL(long seed, WorldType worldType)
    {
        this();
        GenLayer[] agenlayer = GenLayerHL.initializeAllBiomeGenerators(seed, worldType);
        // agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
        this.mbGenBiomes = agenlayer[0];
        this.mbBiomeIndexLayer = agenlayer[1];
    }
	
	public WorldChunkManagerHL(World par1World)
    {
        this(par1World.getSeed(), par1World.getWorldInfo().getTerrainType());
    }
	
	@Override
	 public ChunkPosition findBiomePosition(int x, int z, int radius, List biomesToSpawnIn, Random random)
	 {
	 int spawnSearchRadius = 16;
	 //below pasted from parent function
	 IntCache.resetIntCache();
	 int l = x - spawnSearchRadius >> 2;
	 int i1 = z - spawnSearchRadius >> 2;
	 int j1 = x + spawnSearchRadius >> 2;
	 int k1 = z + spawnSearchRadius >> 2;
	 int l1 = j1 - l + 1;
	 int i2 = k1 - i1 + 1;
	 int[] aint = this.mbGenBiomes.getInts(l, i1, l1, i2);
	 ChunkPosition chunkposition = null;
	 int j2 = 0;
	 
	 //Logs.log(Level.ERROR, "WorldChunkMangerHL.findBiomePosition biomesToSpawnIn="+biomesToSpawnIn);

	 for (int k2 = 0; k2 < l1 * i2; ++k2)
	 {
	     int l2 = l + k2 % l1 << 2;
	     int i3 = i1 + k2 / l1 << 2;
	     BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[k2]);
	     //Logs.log(Level.ERROR, "WorldChunkMangerHL.findBiomePosition biomegenbase="+biomegenbase);
	     

	     if (biomesToSpawnIn.contains(biomegenbase) && (chunkposition == null || random.nextInt(j2 + 1) == 0))
	     {
	         chunkposition = new ChunkPosition(l2, 0, i3);
	         ++j2;
	     }
	 }

	 return chunkposition;
	 }
 
 
 // multi biome stuff
 /**
  * checks given Chunk's Biomes against List of allowed ones
  */
 @Override
 public boolean areBiomesViable(int par1, int par2, int par3, List par4List)
 {
     IntCache.resetIntCache();
     int l = par1 - par3 >> 2;
     int i1 = par2 - par3 >> 2;
     int j1 = par1 + par3 >> 2;
     int k1 = par2 + par3 >> 2;
     int l1 = j1 - l + 1;
     int i2 = k1 - i1 + 1;
     int[] aint = this.mbGenBiomes.getInts(l, i1, l1, i2);

     try
     {
         for (int j2 = 0; j2 < l1 * i2; ++j2)
         {
             BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[j2]);

             if (!par4List.contains(biomegenbase))
             {
                 return false;
             }
         }

         return true;
     }
     catch (Throwable throwable)
     {
         CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
         CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
         crashreportcategory.addCrashSection("Layer", this.mbGenBiomes.toString());
         crashreportcategory.addCrashSection("x", Integer.valueOf(par1));
         crashreportcategory.addCrashSection("z", Integer.valueOf(par2));
         crashreportcategory.addCrashSection("radius", Integer.valueOf(par3));
         crashreportcategory.addCrashSection("allowed", par4List);
         throw new ReportedException(crashreport);
     }
 }
 /**
  * Returns an array of biomes for the location input.
  */
 @Override
 public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
 {
     IntCache.resetIntCache();

     if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
     {
         par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
     }

     int[] aint = this.mbGenBiomes.getInts(par2, par3, par4, par5);

     try
     {
         for (int i1 = 0; i1 < par4 * par5; ++i1)
         {
             par1ArrayOfBiomeGenBase[i1] = BiomeGenBase.getBiome(aint[i1]);
         }

         return par1ArrayOfBiomeGenBase;
     }
     catch (Throwable throwable)
     {
         CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
         CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
         crashreportcategory.addCrashSection("biomes[] size", Integer.valueOf(par1ArrayOfBiomeGenBase.length));
         crashreportcategory.addCrashSection("x", Integer.valueOf(par2));
         crashreportcategory.addCrashSection("z", Integer.valueOf(par3));
         crashreportcategory.addCrashSection("w", Integer.valueOf(par4));
         crashreportcategory.addCrashSection("h", Integer.valueOf(par5));
         throw new ReportedException(crashreport);
     }
 }
 /**
  * Return a list of biomes for the specified blocks. Args: listToReuse, x, y, width, length, cacheFlag (if false,
  * don't check biomeCache to avoid infinite loop in BiomeCacheBlock)
  */
 @Override
 public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
 {
     IntCache.resetIntCache();

     if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
     {
         par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
     }

     if (par6 && par4 == 16 && par5 == 16 && (par2 & 15) == 0 && (par3 & 15) == 0)
     {
         BiomeGenBase[] abiomegenbase1 = this.mbBiomeCache.getCachedBiomes(par2, par3);
         System.arraycopy(abiomegenbase1, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
         return par1ArrayOfBiomeGenBase;
     }
     else
     {
         int[] aint = this.mbBiomeIndexLayer.getInts(par2, par3, par4, par5);

         for (int i1 = 0; i1 < par4 * par5; ++i1)
         {
             par1ArrayOfBiomeGenBase[i1] = BiomeGenBase.getBiome(aint[i1]);
         }

         return par1ArrayOfBiomeGenBase;
     }
 }
 /**
  * Returns the BiomeGenBase related to the x, z position on the world.
  */
 @Override
 public BiomeGenBase getBiomeGenAt(int par1, int par2)
 {
     return this.mbBiomeCache.getBiomeGenAt(par1, par2);
 }
 /**
  * Calls the WorldChunkManager's biomeCache.cleanupCache()
  */
 @Override
 public void cleanupCache()
 {
     this.mbBiomeCache.cleanupCache();
 }
 /**
  * Gets the list of valid biomes for the player to spawn in.
  */
 @Override
 public List getBiomesToSpawnIn()
 {
     return this.mbBiomesToSpawnIn;
 }
 /**
  * Returns a list of rainfall values for the specified blocks. Args: listToReuse, x, z, width, length.
  */
 @Override
 public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
 {
     IntCache.resetIntCache();

     if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
     {
         par1ArrayOfFloat = new float[par4 * par5];
     }

     int[] aint = this.mbBiomeIndexLayer.getInts(par2, par3, par4, par5);

     for (int i1 = 0; i1 < par4 * par5; ++i1)
     {
         try
         {
             float f = (float)BiomeGenBase.getBiome(aint[i1]).getIntRainfall() / 65536.0F;

             if (f > 1.0F)
             {
                 f = 1.0F;
             }

             par1ArrayOfFloat[i1] = f;
         }
         catch (Throwable throwable)
         {
             //CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
             //CrashReportCategory crashreportcategory = crashreport.makeCategory("DownfallBlock");
             //crashreportcategory.addCrashSection("biome id", Integer.valueOf(i1));
             //crashreportcategory.addCrashSection("downfalls[] size", Integer.valueOf(par1ArrayOfFloat.length));
             //crashreportcategory.addCrashSection("x", Integer.valueOf(par2));
             //crashreportcategory.addCrashSection("z", Integer.valueOf(par3));
             //crashreportcategory.addCrashSection("w", Integer.valueOf(par4));
             //crashreportcategory.addCrashSection("h", Integer.valueOf(par5));
             //throw new ReportedException(crashreport);
         }
     }

     return par1ArrayOfFloat;
 }
}