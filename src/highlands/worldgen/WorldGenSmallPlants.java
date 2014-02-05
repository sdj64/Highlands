package highlands.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSmallPlants extends WorldGenerator
{
    /** Stores ID for WorldGenTallGrass */
    private Block plantID;
    
    private int genAmount;
    private int radius;

    public WorldGenSmallPlants(Block block)
    {
        this.plantID = block;
        this.genAmount = 36;
        this.radius = 8;
    }
    
    public WorldGenSmallPlants(Block blck, int amt)
    {
        this.plantID = blck;
        this.genAmount = amt;
        this.radius = 4;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int l;

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

        for (int i1 = 0; i1 < genAmount; ++i1)
        {
            int j1 = par3 + par2Random.nextInt(radius) - par2Random.nextInt(radius);
            int k1 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int l1 = par5 + par2Random.nextInt(radius) - par2Random.nextInt(radius);

            //TODO-                                                     canBlockStay?
            if (par1World.func_147437_c(j1, k1, l1) && Blocks.tallgrass.func_149718_j(par1World, j1, k1, l1))
            {
            	//TODO-   setBlock
                par1World.func_147465_d(j1, k1, l1, this.plantID, 0, 2);
            }
        }

        return true;
    }
}
