package highlands.worldgen;

import java.util.Random;

import highlands.HighlandsMain;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;

public class WorldGenAutumnTree extends WorldGenerator
{
    /** The minimum height of a generated tree. */
    private final int minTreeHeight;

    /** True if this tree should grow Vines. */
    private final boolean vinesGrow;

    /** The blockID of the wood to use in tree generation. */
    private int blockWood;

    /** The blockID of the leaves to use in tree generation. */
    private int blockLeaves;

    public WorldGenAutumnTree(boolean par1, int par2, Block log, Block autumnYellowLeaves)
    {
        super(par1);
        this.minTreeHeight = par2;
        this.blockWood = log;
        this.blockLeaves = autumnYellowLeaves;
        this.vinesGrow = false;
        
        if(HighlandsMain.vanillaBlocksFlag){
        	blockWood = Block.wood.blockID;
        	blockLeaves = Block.leaves.blockID;
        }
    }
    
    public boolean generateReplaceSapling(World world, Random random, int locX, int locY, int locZ){
    	int id = world.getBlockId(locX, locY, locZ);
    	int meta = world.getBlockMetadata(locX, locY, locZ);
    	boolean flag = generate(world, random, locX, locY, locZ);
    	if(!flag) world.setBlock(locX, locY, locZ, id, meta, 3);
    	return flag;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int l = par2Random.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

        if (par4 >= 1 && par4 + l + 1 <= 256)
        {
            int i1;
            byte b0;
            int j1;
            int k1;

            for (i1 = par4; i1 <= par4 + 1 + l; ++i1)
            {
                b0 = 1;

                if (i1 == par4)
                {
                    b0 = 0;
                }

                if (i1 >= par4 + 1 + l - 2)
                {
                    b0 = 2;
                }

                for (int l1 = par3 - b0; l1 <= par3 + b0 && flag; ++l1)
                {
                    for (j1 = par5 - b0; j1 <= par5 + b0 && flag; ++j1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            k1 = par1World.getBlockId(l1, i1, j1);

                            Block block = Block.blocksList[k1];

                            if (k1 != 0 &&
                               !block.isLeaves(par1World, l1, i1, j1) &&
                                k1 != Block.grass.blockID &&
                                k1 != Block.dirt.blockID &&
                               !block.isWood(par1World, l1, i1, j1))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                i1 = par1World.getBlockId(par3, par4 - 1, par5);
                Block soil = Block.blocksList[i1];
                boolean isSoil = (soil != null && soil.canSustainPlant(par1World, par3, par4 - 1, par5, ForgeDirection.UP, (BlockSapling)Block.sapling));

                if (isSoil && par4 < 256 - l - 1)
                {
                    soil.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
                    b0 = 3;
                    byte b1 = 0;
                    int i2;
                    int j2;
                    int k2;

                    for (j1 = par4 - b0 + l; j1 <= par4 + l; ++j1)
                    {
                        k1 = j1 - (par4 + l);
                        i2 = b1 + 1 - k1 / 2;

                        for (j2 = par3 - i2; j2 <= par3 + i2; ++j2)
                        {
                            k2 = j2 - par3;

                            for (int l2 = par5 - i2; l2 <= par5 + i2; ++l2)
                            {
                                int i3 = l2 - par5;

                                if (Math.abs(k2) != i2 || Math.abs(i3) != i2 || par2Random.nextInt(2) != 0 && k1 != 0)
                                {
                                    int j3 = par1World.getBlockId(j2, j1, l2);
                                    Block block = Block.blocksList[j3];

                                    if (block == null || block.canBeReplacedByLeaves(par1World, j2, j1, l2))
                                    {
                                        this.setBlockAndMetadata(par1World, j2, j1, l2, this.blockLeaves, 0);
                                    }
                                }
                            }
                        }
                    }

                    for (j1 = 0; j1 < l; ++j1)
                    {
                        k1 = par1World.getBlockId(par3, par4 + j1, par5);

                        Block block = Block.blocksList[k1];

                        if (k1 == 0 || block == null || block.isLeaves(par1World, par3, par4 + j1, par5))
                        {
                            this.setBlockAndMetadata(par1World, par3, par4 + j1, par5, this.blockWood, 0);

                            if (this.vinesGrow && j1 > 0)
                            {
                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 - 1, par4 + j1, par5))
                                {
                                    this.setBlockAndMetadata(par1World, par3 - 1, par4 + j1, par5, Block.vine.blockID, 8);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 1, par4 + j1, par5))
                                {
                                    this.setBlockAndMetadata(par1World, par3 + 1, par4 + j1, par5, Block.vine.blockID, 2);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + j1, par5 - 1))
                                {
                                    this.setBlockAndMetadata(par1World, par3, par4 + j1, par5 - 1, Block.vine.blockID, 1);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + j1, par5 + 1))
                                {
                                    this.setBlockAndMetadata(par1World, par3, par4 + j1, par5 + 1, Block.vine.blockID, 4);
                                }
                            }
                        }
                    }

                    if (this.vinesGrow)
                    {
                        for (j1 = par4 - 3 + l; j1 <= par4 + l; ++j1)
                        {
                            k1 = j1 - (par4 + l);
                            i2 = 2 - k1 / 2;

                            for (j2 = par3 - i2; j2 <= par3 + i2; ++j2)
                            {
                                for (k2 = par5 - i2; k2 <= par5 + i2; ++k2)
                                {
                                    Block block = Block.blocksList[par1World.getBlockId(j2, j1, k2)];
                                    if (block != null && block.isLeaves(par1World, j2, j1, k2))
                                    {
                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(j2 - 1, j1, k2) == 0)
                                        {
                                            this.growVines(par1World, j2 - 1, j1, k2, 8);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(j2 + 1, j1, k2) == 0)
                                        {
                                            this.growVines(par1World, j2 + 1, j1, k2, 2);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(j2, j1, k2 - 1) == 0)
                                        {
                                            this.growVines(par1World, j2, j1, k2 - 1, 1);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(j2, j1, k2 + 1) == 0)
                                        {
                                            this.growVines(par1World, j2, j1, k2 + 1, 4);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Grows vines downward from the given block for a given length. Args: World, x, starty, z, vine-length
     */
    private void growVines(World par1World, int par2, int par3, int par4, int par5)
    {
        this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
        int i1 = 4;

        while (true)
        {
            --par3;

            if (par1World.getBlockId(par2, par3, par4) != 0 || i1 <= 0)
            {
                return;
            }

            this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
            --i1;
        }
    }
}
