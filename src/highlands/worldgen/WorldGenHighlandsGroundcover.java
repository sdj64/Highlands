package highlands.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenHighlandsGroundcover extends WorldGenerator
{
    /** Stores ID for WorldGenTallGrass */
    private BlockTallGrass tallGrassID;
    private int tallGrassMetadata;
    
    //Type 1 is Cliff (tall grass + cobble boulders), Type 2 is Outback (tall grass + grass blocks underneath on sand)
    private int type;

    public WorldGenHighlandsGroundcover(BlockTallGrass tallgrass, int par2, int type)
    {
        this.tallGrassID = tallgrass;
        this.tallGrassMetadata = par2;
        this.type = type;
    }

    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        Block var11;

        for (boolean var6 = false; ((var11 = par1World.getBlock(par3, par4, par5)) == Blocks.air || var11 == Blocks.leaves) && par4 > 0; --par4)
        {
            ;
        }

        for (int var7 = 0; var7 < 128; ++var7)
        {
            int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if(type == 1){
	            if (par1World.isAirBlock(var8, var9, var10) && this.tallGrassID.canBlockStay(par1World, var8, var9, var10))
	            {
	            	if(par2Random.nextInt(2) == 0){
	            		par1World.setBlock(var8, var9, var10, this.tallGrassID, this.tallGrassMetadata, 2);
	            	}
	            	else{
	            		par1World.setBlock(var8, var9, var10, (par2Random.nextInt(8) == 0 ? Blocks.mossy_cobblestone : Blocks.cobblestone), 0, 2);
	            	}
	            }
            }
            if(type == 2){
            	if (par1World.getBlock(var8, var9-1, var10) == Blocks.sand){
            		par1World.setBlock(var8, var9-1, var10, Blocks.grass, 0, 2);
            		if (par1World.isAirBlock(var8, var9, var10)){
                		par1World.setBlock(var8, var9, var10, this.tallGrassID, this.tallGrassMetadata, 2);
                	}
            	}
            }
        }

        return true;
    }
}
