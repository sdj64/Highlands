package highlands.worldgen;

import java.util.Random;

import highlands.HighlandsMain;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenAutumnTree extends WorldGenerator
{
    /** The minimum height of a generated tree. */
    private final int minTreeHeight;

    /** True if this tree should grow Vines. */
    private final boolean vinesGrow;

    /** The blockID of the wood to use in tree generation. */
    private Block blockWood;

    /** The blockID of the leaves to use in tree generation. */
    private BlockLeaves blockLeaves;

    public WorldGenAutumnTree(boolean par1, int par2, Block par3, Block autumnYellowLeaves)
    {
        super(par1);
        this.minTreeHeight = par2;
        this.blockWood = par3;
        this.blockLeaves = (BlockLeaves) autumnYellowLeaves;
        this.vinesGrow = false;
        
        if(HighlandsMain.vanillaBlocksFlag){
        	blockWood = Blocks.log;
        	blockLeaves = Blocks.leaves;
        }
    }
    
    public boolean generateReplaceSapling(World world, Random random, int locX, int locY, int locZ){
    	//TODO-            getBlock
    	Block blck = world.func_147439_a(locX, locY, locZ);
    	int meta = world.getBlockMetadata(locX, locY, locZ);
    	boolean flag = generate(world, random, locX, locY, locZ);
    	//TODO-         setBlock
    	if(!flag) world.func_147465_d(locX, locY, locZ, blck, meta, 3);
    	return flag;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int l = par2Random.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

        if (par4 >= 1 && par4 + l + 1 <= 256)
        {
            byte b0;
            int j1;
            Block k1;

            for (int i1 = par4; i1 <= par4 + 1 + l; ++i1)
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
                            k1 = par1World.func_147439_a(l1, i1, j1);

                            if (!k1.isAir(par1World, l1, i1, j1) &&
                               !k1.isLeaves(par1World, l1, i1, j1) &&
                                k1 != Blocks.grass &&
                                k1 != Blocks.dirt &&
                               !k1.isWood(par1World, l1, i1, j1))
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
                Block soil = par1World.func_147439_a(par3, par4 - 1, par5);
                boolean isSoil = (soil != null && soil.canSustainPlant(par1World, par3, par4 - 1, par5, ForgeDirection.UP, (IPlantable) Blocks.sapling));

                if (isSoil && par4 < 256 - l - 1)
                {
                    soil.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
                    b0 = 3;
                    byte b1 = 0;
                    int i2;
                    int j2;
                    int k2;
                    int k3;

                    for (j1 = par4 - b0 + l; j1 <= par4 + l; ++j1)
                    {
                        k3 = j1 - (par4 + l);
                        i2 = b1 + 1 - k3 / 2;

                        for (j2 = par3 - i2; j2 <= par3 + i2; ++j2)
                        {
                            k2 = j2 - par3;

                            for (int l2 = par5 - i2; l2 <= par5 + i2; ++l2)
                            {
                                int i3 = l2 - par5;

                                if (Math.abs(k2) != i2 || Math.abs(i3) != i2 || par2Random.nextInt(2) != 0 && k3 != 0)
                                {
                                	//TODO-              getBlock
                                    Block j3 = par1World.func_147439_a(j2, j1, l2);

                                    if (j3.isAir(par1World, j2, j1, l2) || j3.canBeReplacedByLeaves(par1World, j2, j1, l2))
                                    {
                                    	//TODO setBlockAndMetadata
                                        this.func_150516_a(par1World, j2, j1, l2, this.blockLeaves, 0);
                                    }
                                }
                            }
                        }
                    }

                    for (j1 = 0; j1 < l; ++j1)
                    {
                    	//TODO-        getBlock
                        Block block = par1World.func_147439_a(par3, par4 + j1, par5);

                        if (block.isAir(par1World, par3, par4 + j1, par5) || block.isLeaves(par1World, par3, par4 + j1, par5))
                        {
                        	//TODO- setBlockAndMetadata
                            this.func_150516_a(par1World, par3, par4 + j1, par5, this.blockWood, 0);

                            if (this.vinesGrow && j1 > 0)
                            {
                            	//TODO-                                    isAirBlock
                                if (par2Random.nextInt(3) > 0 && par1World.func_147437_c(par3 - 1, par4 + j1, par5))
                                {
                                	//TODO- setBlockAndMetadata
                                    this.func_150516_a(par1World, par3 - 1, par4 + j1, par5, Blocks.vine, 8);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.func_147437_c(par3 + 1, par4 + j1, par5))
                                {
                                    this.func_150516_a(par1World, par3 + 1, par4 + j1, par5, Blocks.vine, 2);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.func_147437_c(par3, par4 + j1, par5 - 1))
                                {
                                    this.func_150516_a(par1World, par3, par4 + j1, par5 - 1, Blocks.vine, 1);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.func_147437_c(par3, par4 + j1, par5 + 1))
                                {
                                    this.func_150516_a(par1World, par3, par4 + j1, par5 + 1, Blocks.vine, 4);
                                }
                            }
                        }
                    }

                    if (this.vinesGrow)
                    {
                        for (j1 = par4 - 3 + l; j1 <= par4 + l; ++j1)
                        {
                            k3 = j1 - (par4 + l);
                            i2 = 2 - k3 / 2;

                            for (j2 = par3 - i2; j2 <= par3 + i2; ++j2)
                            {
                                for (k2 = par5 - i2; k2 <= par5 + i2; ++k2)
                                {
                                	//TODO-       getBlock
                                    if (par1World.func_147439_a(j2, j1, k2).isLeaves(par1World, j2, j1, k2))
                                    {
                                        if (par2Random.nextInt(4) == 0 && par1World.func_147439_a(j2 - 1, j1, k2).isAir(par1World, j2 - 1, j1, k2))
                                        {
                                            this.growVines(par1World, j2 - 1, j1, k2, 8);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.func_147439_a(j2 + 1, j1, k2).isAir(par1World, j2 + 1, j1, k2))
                                        {
                                            this.growVines(par1World, j2 + 1, j1, k2, 2);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.func_147439_a(j2, j1, k2 - 1).isAir(par1World, j2, j1, k2 - 1))
                                        {
                                            this.growVines(par1World, j2, j1, k2 - 1, 1);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.func_147439_a(j2, j1, k2 + 1).isAir(par1World, j2, j1, k2 + 1))
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
    	//TODO-setBlockAndMetadata
        this.func_150516_a(par1World, par2, par3, par4, Blocks.vine, par5);
        int i1 = 4;

        while (true)
        {
            --par3;

            if (par1World.func_147439_a(par2, par3, par4).isAir(par1World, par2, par3, par4) || i1 <= 0)
            {
                return;
            }

            this.func_150516_a(par1World, par2, par3, par4, Blocks.vine, par5);
            --i1;
        }
    }
}
