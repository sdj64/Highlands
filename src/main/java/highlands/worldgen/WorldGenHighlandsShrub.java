package highlands.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenHighlandsShrub extends WorldGenerator
{
    private int field_76527_a;
    private int field_76526_b;

    public WorldGenHighlandsShrub(int par1, int par2)
    {
        this.field_76526_b = par1;
        this.field_76527_a = par2;
    }
    
    public boolean generateReplaceSapling(World world, Random random, int locX, int locY, int locZ){
    	//TODO-          getBlock
    	Block id = world.func_147439_a(locX, locY, locZ);
    	int meta = world.getBlockMetadata(locX, locY, locZ);
    	boolean flag = generate(world, random, locX, locY, locZ);
    	//TODO-         setBlock
    	if(!flag) world.func_147465_d(locX, locY, locZ, id, meta, 3);
    	return flag;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        Block var15;

        for (boolean var6 = false; ((var15 = par1World.func_147439_a(par3, par4, par5)) == null || var15 == Blocks.leaves) && par4 > 0; --par4)
        {
            ;
        }

        Block var7 = par1World.func_147439_a(par3, par4, par5);

        if (var7 == Blocks.dirt || var7 == Blocks.grass || var7 == Blocks.snow || var7 == Blocks.sand)
        {
            ++par4;
            //TODO- setBlockAndMetadata
            this.func_150516_a(par1World, par3, par4, par5, Blocks.log, this.field_76526_b);

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

                        //TODO- questionable fix
                        if ((Math.abs(var12) != var10 || Math.abs(var14) != var10 || par2Random.nextInt(2) != 0) && par1World.func_147439_a(var11, var8, var13).isNormalCube(par1World, var11, var8, var13))
                        {
                        	//TODO- setBlockAndMetadata
                            this.func_150516_a(par1World, var11, var8, var13, Blocks.leaves, this.field_76527_a);
                        }
                    }
                }
            }
        }

        return true;
    }
}
