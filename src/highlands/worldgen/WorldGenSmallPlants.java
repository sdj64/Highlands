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
    
    private final int genAmount;
    private final int radius;

    public WorldGenSmallPlants(Block bush)
    {
        this.plantID = bush;
        this.genAmount = 36;
        this.radius = 8;
    }
    
    public WorldGenSmallPlants(Block id, int amt)
    {
        this.plantID = id;
        this.genAmount = amt;
        this.radius = 4;
    }

    public WorldGenerator setPlant(Block plant){
        this.plantID = plant;
        return this;
    }

    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        Block block = null;
        do 
        {
            block = par1World.getBlock(par3,par4, par5);
            if (!block.isAir(par1World, par3, par4, par5) && !block.isLeaves(par1World, par3, par4, par5))
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
            if (k1>0 && par1World.isAirBlock(j1, k1, l1) && Blocks.tallgrass.canBlockStay(par1World, j1, k1, l1))
            {
                par1World.setBlock(j1, k1, l1, this.plantID, 0, 2);
            }
        }
        return true;
    }
}
