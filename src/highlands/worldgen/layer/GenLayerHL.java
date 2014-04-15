package highlands.worldgen.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerHL extends GenLayer
{
    /**
     * the first array item is a linked list of the biomes, the second is the zoom function, the third is the same as the
     * first.
     */
    public static GenLayer[] initializeAllBiomeGenerators(long par0, WorldType par2WorldType)
    {
        GenLayerIslandHL var3 = new GenLayerIslandHL(1L);
        GenLayerFuzzyZoom var9 = new GenLayerFuzzyZoom(2000L, var3);
        GenLayerAddIsland var10 = new GenLayerAddIsland(1L, var9);
        GenLayerZoom var11 = new GenLayerZoom(2001L, var10);
        var10 = new GenLayerAddIsland(2L, var11);
        GenLayerAddSnow var12 = new GenLayerAddSnow(2L, var10);
        var11 = new GenLayerZoom(2002L, var12);
        var10 = new GenLayerAddIsland(3L, var11);
        var11 = new GenLayerZoom(2003L, var10);
        var10 = new GenLayerAddIsland(4L, var11);
        GenLayerAddMushroomIsland var16 = new GenLayerAddMushroomIsland(5L, var10);
        byte var4 = 4;

        if (par2WorldType == WorldType.LARGE_BIOMES)
        {
            var4 = 6;
        }
        var4 = getModdedBiomeSize(par2WorldType, var4);

        GenLayer var5 = GenLayerZoom.magnify(1000L, var16, 0);
        GenLayerRiverInit var13 = new GenLayerRiverInit(100L, var5);
        var5 = GenLayerZoom.magnify(1000L, var13, var4 + 2);
        GenLayerRiver var14 = new GenLayerRiver(1L, var5);
        GenLayerSmooth var15 = new GenLayerSmooth(1000L, var14);
        GenLayer var6 = GenLayerZoom.magnify(1000L, var16, 0);
        GenLayerBiomeHL var17 = new GenLayerBiomeHL(200L, var6, par2WorldType);
        var6 = GenLayerZoom.magnify(1000L, var17, 2);
        Object var18 = new GenLayerHillsHL(1000L, var6);

        for (int var7 = 0; var7 < var4; ++var7)
        {
            var18 = new GenLayerZoom((long)(1000 + var7), (GenLayer)var18);

            if (var7 == 0)
            {
                var18 = new GenLayerAddIsland(3L, (GenLayer)var18);
            }

            if (var7 == 1)
            {
                var18 = new GenLayerShoreHL(1000L, (GenLayer)var18);
            }

            if (var7 == 1)
            {
                var18 = new GenLayerRiver(1000L, (GenLayer)var18);
            }
        }

        GenLayerSmooth var19 = new GenLayerSmooth(1000L, (GenLayer)var18);
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
