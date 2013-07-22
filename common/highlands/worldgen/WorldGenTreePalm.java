package highlands.worldgen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import highlands.HighlandsMain;
import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreePalm extends WorldGenHighlandsTreeBase
{

    /** Constructor - gets the generator for the correct highlands tree
     * @param lmd leaf meta data
     * @param wmd wood meta data
     * @param wb wood block id
     * @param lb leaf block id
     * @param minH minimum height of tree trunk
     * @param maxH max possible height above minH the tree trunk could grow
     * @param notify whether or not to notify blocks of the tree being grown.
     *  Generally false for world generation, true for saplings.
     */
    public WorldGenTreePalm(int lmd, int wmd, int wb, int lb, int minH, int maxH, boolean notify)
    {
    	super(lmd, wmd, wb, lb, notify);
        
        this.minHeight = minH;
        this.maxHeight = maxH;
    }
    
    public WorldGenTreePalm(int minH, int maxH, boolean notify){
    	this(0, 0, HighlandsBlocks.palmWood.blockID, HighlandsBlocks.palmLeaves.blockID, minH, maxH, notify);
    	if(HighlandsMain.vanillaBlocksFlag){
    		this.woodID = Block.wood.blockID;
    		this.woodMeta = 3;
    		this.leavesID = Block.leaves.blockID;
    		this.leavesMeta = 3;
    	}
    }

    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	this.random = random;
    	

    	locY = findTopBlock(locX, locZ);
        
        if(!(world.getBlockId(locX, locY, locZ) == Block.grass.blockID || world.getBlockId(locX, locY, locZ) == Block.dirt.blockID
        		|| (world.getBlockId(locX, locY, locZ) == Block.sand.blockID && world.getBiomeGenForCoords(locX, locZ) != HighlandsBiomes.dunes)))return false;
        if(!isCubeClear(locX, locY+3, locZ, 1, 4))return false;
        
        //generates trunk
    	int treeHeight = minHeight + random.nextInt(maxHeight);
    	for(int i = 0; i < treeHeight; i++){
    		setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
    	}
    	//generates leaves
    	int h = locY + treeHeight;
    	setBlockInWorld(locX, h, locZ, this.leavesID, this.leavesMeta);
    	int r = 1;
    	setBlockInWorld(locX+r, h, locZ, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX-r, h, locZ, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX, h, locZ+r, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX, h, locZ-r, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX+r, h, locZ+r, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX-r, h, locZ-r, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX-r, h, locZ+r, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX+r, h, locZ-r, this.leavesID, this.leavesMeta);
    	r++;
    	setBlockInWorld(locX+r, h, locZ, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX-r, h, locZ, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX, h, locZ+r, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX, h, locZ-r, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX+r, h, locZ+r, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX-r, h, locZ-r, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX-r, h, locZ+r, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX+r, h, locZ-r, this.leavesID, this.leavesMeta);
    	r++;
    	setBlockInWorld(locX+r, h, locZ, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX-r, h, locZ, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX, h, locZ+r, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX, h, locZ-r, this.leavesID, this.leavesMeta);
    	h--;
    	setBlockInWorld(locX+r, h, locZ, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX-r, h, locZ, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX, h, locZ+r, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX, h, locZ-r, this.leavesID, this.leavesMeta);
    	r++;
    	setBlockInWorld(locX+r, h, locZ, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX-r, h, locZ, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX, h, locZ+r, this.leavesID, this.leavesMeta);
    	setBlockInWorld(locX, h, locZ-r, this.leavesID, this.leavesMeta);
    	
    	if(random.nextInt(3) == 0){
    		for (int k1 = 0; k1 < 4; ++k1)
            {
                if (random.nextInt(2) == 0)
                {
                    int i2 = random.nextInt(3);
                    this.setBlockAndMetadata(world, locX +
                    		Direction.offsetX[Direction.rotateOpposite[k1]], h,
                    		locZ + Direction.offsetZ[Direction.rotateOpposite[k1]],
                    		Block.cocoaPlant.blockID, i2 << 2 | k1);
                }
            }
    	}
		return true;
    }
}













