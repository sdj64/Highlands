package highlands.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDeadBush2 extends WorldGenerator
{
    public WorldGenDeadBush2(int par1)
    {
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var11;

        Block block = null;
        do 
        {
        	//TODO-           getBlock
            block = par1World.func_147439_a(par3,  par4, par5);
            if (block != null && !block.isLeaves(par1World, par3, par4, par5))
            {
                break;
            }
            par4--;
        } while (par4 > 0);

        for (int var7 = 0; var7 < 4; ++var7)
        {
            int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            //TODO-       isAirBlock                                          canBlockStay
            if (par1World.func_147437_c(var8, var9, var10) && Blocks.deadbush.func_149718_j(par1World, var8, var9, var10))
            {
            	//TODO-   setBlock
                par1World.func_147465_d(var8, var9, var10, Blocks.deadbush, 0, 2);
            }
        }

        return true;
    }
}
