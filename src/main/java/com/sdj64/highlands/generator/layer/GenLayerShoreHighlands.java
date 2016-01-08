package com.sdj64.highlands.generator.layer;

import com.sdj64.highlands.biome.BiomeGenBaseHighlands;
import com.sdj64.highlands.biome.HighlandsBiomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenMesa;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerShoreHighlands extends GenLayer
{
    private static final String __OBFID = "CL_00000568";

    public GenLayerShoreHighlands(long p_i2130_1_, GenLayer p_i2130_3_)
    {
        super(p_i2130_1_);
        this.parent = p_i2130_3_;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i1 = 0; i1 < areaHeight; ++i1)
        {
            for (int j1 = 0; j1 < areaWidth; ++j1)
            {
                this.initChunkSeed((long)(j1 + areaX), (long)(i1 + areaY));
                int k1 = aint[j1 + 1 + (i1 + 1) * (areaWidth + 2)];
                BiomeGenBase biomegenbase = BiomeGenBase.getBiome(k1);
                int l1;
                int i2;
                int j2;
                int k2;

                if (k1 == BiomeGenBase.mushroomIsland.biomeID)
                {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (areaWidth + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (areaWidth + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (areaWidth + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (areaWidth + 2)];

                    if (l1 != BiomeGenBase.ocean.biomeID && i2 != BiomeGenBase.ocean.biomeID && j2 != BiomeGenBase.ocean.biomeID && k2 != BiomeGenBase.ocean.biomeID)
                    {
                        aint1[j1 + i1 * areaWidth] = k1;
                    }
                    else
                    {
                        aint1[j1 + i1 * areaWidth] = BiomeGenBase.mushroomIslandShore.biomeID;
                    }
                }
                else if (biomegenbase != null && biomegenbase.getBiomeClass() == BiomeGenJungle.class)
                {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (areaWidth + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (areaWidth + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (areaWidth + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (areaWidth + 2)];

                    if (this.isJungleOrOcean(l1) && this.isJungleOrOcean(i2) && this.isJungleOrOcean(j2) && this.isJungleOrOcean(k2))
                    {
                        if (!isBiomeOceanic(l1) && !isBiomeOceanic(i2) && !isBiomeOceanic(j2) && !isBiomeOceanic(k2))
                        {
                            aint1[j1 + i1 * areaWidth] = k1;
                        }
                        else
                        {
                            aint1[j1 + i1 * areaWidth] = BiomeGenBase.beach.biomeID;
                        }
                    }
                    else
                    {
                        aint1[j1 + i1 * areaWidth] = BiomeGenBase.jungleEdge.biomeID;
                    }
                }
                else if (biomegenbase != null && HighlandsBiomes.foothillsBiomes.contains(biomegenbase))
                {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (areaWidth + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (areaWidth + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (areaWidth + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (areaWidth + 2)];

                    if (this.isEdgeNotApplied(l1, k1) && this.isEdgeNotApplied(i2, k1) && this.isEdgeNotApplied(j2, k1) && this.isEdgeNotApplied(k2, k1))
                    {
                        if (!isBiomeOceanic(l1) && !isBiomeOceanic(i2) && !isBiomeOceanic(j2) && !isBiomeOceanic(k2))
                        {
                            aint1[j1 + i1 * areaWidth] = k1;
                        }
                        else
                        {
                            aint1[j1 + i1 * areaWidth] = BiomeGenBase.beach.biomeID;
                        }
                    }
                    else
                    {
                        aint1[j1 + i1 * areaWidth] = biomegenbase.biomeID + 128;
                    }
                }
                else if (BiomeGenBase.getBiomeGenArray()[k1].minHeight + BiomeGenBase.getBiomeGenArray()[k1].maxHeight < 1.3)
                {
                    if (biomegenbase != null && biomegenbase.isSnowyBiome())
                    {
                        this.func_151632_a(aint, aint1, j1, i1, areaWidth, k1, BiomeGenBase.coldBeach.biomeID);
                    }
                    else if (k1 != BiomeGenBase.mesa.biomeID && k1 != BiomeGenBase.mesaPlateau_F.biomeID)
                    {
                        if (k1 != BiomeGenBase.ocean.biomeID && k1 != BiomeGenBase.deepOcean.biomeID && k1 != BiomeGenBase.river.biomeID && k1 != BiomeGenBase.swampland.biomeID)
                        {
                            l1 = aint[j1 + 1 + (i1 + 1 - 1) * (areaWidth + 2)];
                            i2 = aint[j1 + 1 + 1 + (i1 + 1) * (areaWidth + 2)];
                            j2 = aint[j1 + 1 - 1 + (i1 + 1) * (areaWidth + 2)];
                            k2 = aint[j1 + 1 + (i1 + 1 + 1) * (areaWidth + 2)];

                            if (!isBiomeOceanic(l1) && !isBiomeOceanic(i2) && !isBiomeOceanic(j2) && !isBiomeOceanic(k2))
                            {
                            	aint1[j1 + i1 * areaWidth] = k1;
                            }
                            else
                            {
                                aint1[j1 + i1 * areaWidth] = BiomeGenBase.beach.biomeID;
                            }
                        }
                        else
                        {
                        	aint1[j1 + i1 * areaWidth] = k1;
                        }
                    }
                    else
                    {
                        l1 = aint[j1 + 1 + (i1 + 1 - 1) * (areaWidth + 2)];
                        i2 = aint[j1 + 1 + 1 + (i1 + 1) * (areaWidth + 2)];
                        j2 = aint[j1 + 1 - 1 + (i1 + 1) * (areaWidth + 2)];
                        k2 = aint[j1 + 1 + (i1 + 1 + 1) * (areaWidth + 2)];

                        if (!isBiomeOceanic(l1) && !isBiomeOceanic(i2) && !isBiomeOceanic(j2) && !isBiomeOceanic(k2))
                        {
                            if (this.isMesa(l1) && this.isMesa(i2) && this.isMesa(j2) && this.isMesa(k2))
                            {
                                aint1[j1 + i1 * areaWidth] = k1;
                            }
                            else
                            {
                                aint1[j1 + i1 * areaWidth] = BiomeGenBase.desert.biomeID;
                            }
                        }
                        else
                        {
                            aint1[j1 + i1 * areaWidth] = k1;
                        }
                    }
                }
                else
                {
                    this.func_151632_a(aint, aint1, j1, i1, areaWidth, k1, BiomeGenBase.stoneBeach.biomeID);
                }
            }
        }

        return aint1;
    }

    private void func_151632_a(int[] p_151632_1_, int[] p_151632_2_, int p_151632_3_, int p_151632_4_, int p_151632_5_, int p_151632_6_, int p_151632_7_)
    {
        if (isBiomeOceanic(p_151632_6_))
        {
            p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
        }
        else
        {
            int j1 = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 - 1) * (p_151632_5_ + 2)];
            int k1 = p_151632_1_[p_151632_3_ + 1 + 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int l1 = p_151632_1_[p_151632_3_ + 1 - 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int i2 = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 + 1) * (p_151632_5_ + 2)];

            if (!isBiomeOceanic(j1) && !isBiomeOceanic(k1) && !isBiomeOceanic(l1) && !isBiomeOceanic(i2))
            {
                p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
            }
            else
            {
                p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_7_;
            }
        }
    }

    private boolean isJungleOrOcean(int id1)
    {
        return BiomeGenBase.getBiome(id1) != null && BiomeGenBase.getBiome(id1).getBiomeClass() == BiomeGenJungle.class ? true : id1 == BiomeGenBase.jungleEdge.biomeID || id1 == BiomeGenBase.jungle.biomeID || id1 == BiomeGenBase.jungleHills.biomeID || id1 == BiomeGenBase.forest.biomeID || id1 == BiomeGenBase.taiga.biomeID || isBiomeOceanic(id1);
    }
    
    private boolean isEdgeNotApplied(int id1, int target)
    {
        return id1 == target;
    }

    private boolean isMesa(int p_151633_1_)
    {
        return BiomeGenBase.getBiome(p_151633_1_) instanceof BiomeGenMesa;
    }
}