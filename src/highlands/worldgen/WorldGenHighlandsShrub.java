package highlands.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenHighlandsShrub extends WorldGenAbstractTree
{
    private final int leavesMeta;
    private final int logMeta;

    public WorldGenHighlandsShrub(int par1, int par2)
    {
        super(false);
        this.logMeta = par1;
        this.leavesMeta = par2;
    }
    
    public boolean generateReplaceSapling(World world, Random random, int locX, int locY, int locZ){
    	Block id = world.getBlock(locX, locY, locZ);
    	int meta = world.getBlockMetadata(locX, locY, locZ);
    	boolean flag = generate(world, random, locX, locY, locZ);
    	if(!flag) world.setBlock(locX, locY, locZ, id, meta, 3);
    	return flag;
    }

    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        Block var15 = par1World.getBlock(par3, par4, par5);

        while (par4 > 0 && (var15.isAir(par1World, par3, par4, par5) || var15.isLeaves(par1World, par3, par4, par5)))
        {
            par4--;
            var15 = par1World.getBlock(par3, par4, par5);
        }

        if (var15 == Blocks.dirt || var15 == Blocks.grass || var15 == Blocks.snow || var15 == Blocks.sand)
        {
            ++par4;
            this.setBlockAndNotifyAdequately(par1World, par3, par4, par5, Blocks.log, this.logMeta);

            for (int var8 = par4; var8 <= par4 + 2; ++var8)
            {
                int var9 = var8 - par4;
                int var10 = 2 - var9;

                for (int var11 = par3 - var10; var11 <= par3 + var10; ++var11)
                {
                    int var12 = var11 - par3;

                    for (int var13 = par5 - var10; var13 <= par5 + var10; ++var13)
                    {
                        int var14 = var13 - par5;

                        if ((Math.abs(var12) != var10 || Math.abs(var14) != var10 || par2Random.nextInt(2) != 0) && !par1World.getBlock(var11, var8, var13).isOpaqueCube())
                        {
                            this.setBlockAndNotifyAdequately(par1World, var11, var8, var13, Blocks.leaves, this.leavesMeta);
                        }
                    }
                }
            }
        }

        return true;
    }
}
