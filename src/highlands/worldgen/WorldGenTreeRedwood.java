package highlands.worldgen;

import highlands.HighlandsMain;
import highlands.api.HighlandsBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenTreeRedwood extends WorldGenHighlandsTreeBase
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
    public WorldGenTreeRedwood(int lmd, int wmd, Block wb, BlockLeavesBase lb, int minH, int maxH, boolean notify)
    {
    	super(lmd, wmd, wb, lb, notify);
        this.minHeight = minH;
        this.maxHeight = maxH;
    }
    
    public WorldGenTreeRedwood(int minH, int maxH, boolean notify){
    	this(0, 0, HighlandsBlocks.redwoodWood, (BlockLeavesBase) HighlandsBlocks.redwoodLeaves, minH, maxH, notify);
    	if(HighlandsMain.vanillaBlocksFlag){
    		this.woodID = Blocks.log;
    		this.leavesID = Blocks.leaves;
    		this.leavesMeta = 1;
    	}
    }

    @Override
    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	this.random = random;

        if(!isLegalTreePosition(world, locX, locY, locZ))
            return false;
        if(!isCubeClear(locX, locY+3, locZ, 4, 20))
            return false;
        
        int treeHeight = minHeight + random.nextInt(maxHeight);
		//generates the trunk - different than other tree types, uses layer circles.
		double theta = Math.atan(2.8/treeHeight);
		
		for(int i = -3; i <= treeHeight; i++){
			double r = (treeHeight - i) * Math.tan(theta);
			generateWoodLayerCircle(r, locX, locZ, locY+i);
		}
		
		//generate the branches. They start halfway up the tree and are generated every 5 blocks up.
		int h = locY + treeHeight - 5;
		double r = 2;
		for(int i = 0; i < 6; i++){
			generateSequoiaBranch(r, locX, locZ, h);
			h-=3;
			if(r < 6)r++;
		}
		
		h = locY + treeHeight - 2;
		generateLeafLayerCircleNoise(3.5, locX, locZ, h);
		h++;
		generateLeafLayerCircleNoise(2.5, locX, locZ, h);
		h++;
		generateLeafLayerCircleNoise(2, locX, locZ, h);
		h++;
		generateLeafLayerCircleNoise(1.5, locX, locZ, h);
		h++;
		generateLeafLayerCircleNoise(1, locX, locZ, h);
		h++;
		generateLeafLayerCircleNoise(0, locX, locZ, h);
		h++;
		generateLeafLayerCircleNoise(0, locX, locZ, h);
        this.world = null;
        this.random = null;
		return true;
    }
}













