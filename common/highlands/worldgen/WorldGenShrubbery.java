package highlands.worldgen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import highlands.HighlandsMain;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenShrubbery extends WorldGenHighlandsTreeBase
{

    /** Constructor
     * @param notify whether or not to notify blocks of the tree being grown.
     *  Generally false for world generation, true for saplings.
     */
    public WorldGenShrubbery(boolean notify)
    {
    	super(0, 0, Block.fence.blockID, Block.leaves.blockID, notify);
    }

    //a Shrubbery is a small tree with a 1 block high fence trunk and 2-4 blocks of leaves.
    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	this.random = random;
    	

    	//finds top block for the given x,z position (excluding leaves and grass)
        for (boolean var6 = false; (world.getBlockId(locX, locY, locZ) == 0 || world.getBlockId(locX, locY, locZ) == Block.leaves.blockID) && locY > 0; --locY);
        //locY is now the highest solid terrain block
        
        if(!(world.getBlockId(locX, locY, locZ) == Block.grass.blockID || world.getBlockId(locX, locY, locZ) == Block.dirt.blockID))return false;
        
    	//generates the trunk
    	locY++;
    	int treeHeight = minHeight + random.nextInt(maxHeight);
    	setBlockInWorld(locX, locY, locZ, this.woodID, this.woodMeta);
    	
    	//generate leaves above trunk
    	int leafHeight = random.nextInt(3)+ 2;
    	for(int i = 1; i < leafHeight; i++){
    		setBlockInWorld(locX, locY + 1, locZ, this.leavesID, this.leavesMeta);
    	}
    	
    	
		return true;
    }
}













