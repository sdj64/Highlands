package highlands.worldgen.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.*;

public abstract class GenLayerHL extends GenLayer
{
    /**
     * the first array item is a linked list of the biomes, the second is the zoom function, the third is the same as the
     * first.
     */
    public static GenLayer[] initializeAllBiomeGenerators(long par0, WorldType par2WorldType)
    {
        GenLayerIslandHL islandHL = new GenLayerIslandHL(1L);
        GenLayerFuzzyZoom fuzzy = new GenLayerFuzzyZoom(2000L, islandHL);
        GenLayerAddIsland addIsland = new GenLayerAddIsland(1L, fuzzy);
        GenLayerZoom zoom = new GenLayerZoom(2001L, addIsland);
        addIsland = new GenLayerAddIsland(2L, zoom);
        GenLayerAddSnow var12 = new GenLayerAddSnow(2L, addIsland);
        zoom = new GenLayerZoom(2002L, var12);
        addIsland = new GenLayerAddIsland(3L, zoom);
        zoom = new GenLayerZoom(2003L, addIsland);
        addIsland = new GenLayerAddIsland(4L, zoom);
        GenLayerAddMushroomIsland mushroomIsland = new GenLayerAddMushroomIsland(5L, addIsland);
        GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, mushroomIsland);
        GenLayer genLayer = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
        byte size = 4;
        if (par2WorldType == WorldType.LARGE_BIOMES)
        {
            size = 6;
        }
        size = getModdedBiomeSize(par2WorldType, size);

        GenLayer var5 = GenLayerZoom.magnify(1000L, genLayer, 0);
        GenLayerRiverInit riverInit = new GenLayerRiverInit(100L, var5);
        var5 = GenLayerZoom.magnify(1000L, riverInit, size + 2);
        GenLayerRiver river = new GenLayerRiver(1L, var5);
        GenLayerSmooth var15 = new GenLayerSmooth(1000L, river);

        GenLayer var6 = GenLayerZoom.magnify(1000L, mushroomIsland, 0);
        GenLayerBiomeHL biomeHL = new GenLayerBiomeHL(200L, var6, par2WorldType);
        var6 = GenLayerZoom.magnify(1000L, biomeHL, 2);
        GenLayer var18 = new GenLayerHillsHL(1000L, var6);

        for (int var7 = 0; var7 < size; ++var7)
        {
            var18 = new GenLayerZoom((long)(1000 + var7), var18);

            if (var7 == 0)
            {
                var18 = new GenLayerAddIsland(3L, var18);
            }
            else if (var7 == 1)
            {
                var18 = new GenLayerShoreHL(1000L, var18);
            }
        }

        GenLayerSmooth var19 = new GenLayerSmooth(1000L, var18);
        GenLayerRiverMix var20 = new GenLayerRiverMix(100L, var19, var15);
        GenLayerVoronoiZoom var8 = new GenLayerVoronoiZoom(10L, var20);
        var20.initWorldGenSeed(par0);
        var8.initWorldGenSeed(par0);
        return new GenLayer[] {var20, var8, var20};
    }
    
    public GenLayerHL(long par1)
    {
        super(par1);
    }
}
