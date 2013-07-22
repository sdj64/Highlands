package highlands.worldgen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import highlands.HighlandsMain;
import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeAcacia extends WorldGenHighlandsTreeBase
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
    public WorldGenTreeAcacia(int lmd, int wmd, int wb, int lb, int minH, int maxH, boolean notify)
    {
    	super(lmd, wmd, wb, lb, notify);
        
        this.minHeight = minH;
        this.maxHeight = maxH;
    }
    
    public WorldGenTreeAcacia(int minH, int maxH, boolean notify){
    	this(0, 0, HighlandsBlocks.acaciaWood.blockID, HighlandsBlocks.acaciaLeaves.blockID, minH, maxH, notify);
    	if(HighlandsMain.vanillaBlocksFlag){
    		this.woodID = Block.wood.blockID;
    		this.leavesID = Block.leaves.blockID;
    	}
    }

    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	this.random = random;
    	

    	locY = findTopBlock(locX, locZ);
        
        if(!(world.getBlockId(locX, locY, locZ) == Block.grass.blockID || world.getBlockId(locX, locY, locZ) == Block.dirt.blockID))return false;
        if(!isCubeClear(locX, locY+3, locZ, 2, 6))return false;
        
    	//generates the trunk
    	locY++;
    	int treeHeight = minHeight + random.nextInt(maxHeight);
    	for(int i = 0; i < treeHeight; i++){
    		setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
    	}
    	
    	int h = locY + treeHeight - 1;
    	//generate leaves above trunk
    	generateLeafLayerCircle(world, random, 5.5, locX, locZ, h);
    	h++;
    	generateLeafLayerCircle(world, random, 4.5, locX, locZ, h);

		h = locY + treeHeight - 6;
		//generates branch
		int[] xyz = generateStraightBranch(world, random, 4, locX, h, locZ, random.nextInt(4));
		generateLeafLayerCircle(world, random, 4.5, xyz[0], xyz[2], xyz[1]);
		generateLeafLayerCircle(world, random, 3.5, xyz[0], xyz[2], xyz[1]+1);
		return true;
    }
}













