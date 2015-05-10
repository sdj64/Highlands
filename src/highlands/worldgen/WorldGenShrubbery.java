package highlands.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenShrubbery extends WorldGenHighlandsTreeBase
{

    /** Constructor
     * @param notify whether or not to notify blocks of the tree being grown.
     *  Generally false for world generation, true for saplings.
     */
    public WorldGenShrubbery(boolean notify)
    {
    	super(0, 0, Blocks.fence, Blocks.leaves, notify);
    }

    //a Shrubbery is a small tree with a 1 block high fence trunk and 2-4 blocks of leaves.
    @Override
    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	this.random = random;

    	//finds top block for the given x,z position (excluding leaves and grass)
        Block var11 = world.getBlock(locX, locY, locZ);

        while (locY > 0 && (var11.isAir(world, locX, locY, locZ) || var11.isLeaves(world, locX, locY, locZ)))
        {
            locY--;
            var11 = world.getBlock(locX, locY, locZ);
        }
        //locY is now the highest solid terrain block
        
        if(!(var11 == Blocks.grass || var11 == Blocks.dirt))
        	return false;
        
    	//generates the trunk
    	locY++;
    	setBlockInWorld(locX, locY, locZ, this.woodID, this.woodMeta);
    	
    	//generate leaves above trunk
    	int leafHeight = random.nextInt(3)+ 2;
    	for(int i = 1; i < leafHeight; i++){
    		setBlockInWorld(locX, locY + 1, locZ, this.leavesID, this.leavesMeta);
    	}

        this.world = null;
        this.random = null;
    	
		return true;
    }
}













