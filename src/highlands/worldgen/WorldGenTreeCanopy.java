package highlands.worldgen;

import highlands.HighlandsMain;
import highlands.api.HighlandsBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenTreeCanopy extends WorldGenHighlandsTreeBase
{
	
	private boolean trunk2;

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
    public WorldGenTreeCanopy(int lmd, int wmd, Block wb, BlockLeavesBase lb, int minH, int maxH, boolean notify, boolean thickTrunk)
    {
    	super(lmd, wmd, wb, lb, notify);
        
        this.minHeight = minH;
        this.maxHeight = maxH;
        this.trunk2 = thickTrunk;
    }
    
    public WorldGenTreeCanopy(int minH, int maxH, boolean notify, boolean thickTrunk){
    	this(0, 0, HighlandsBlocks.canopyWood, (BlockLeavesBase) HighlandsBlocks.canopyLeaves, minH, maxH, notify, thickTrunk);
    	if(HighlandsMain.vanillaBlocksFlag){
    		this.woodID = Blocks.log;
    		this.woodMeta = 3;
    		this.leavesID = Blocks.leaves;
    		this.leavesMeta = 3;
    	}
    }

    @Override
    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	this.random = random;
    	
    	boolean isWide = (random.nextInt(3) == 0);
    	int treeHeight = minHeight + random.nextInt(maxHeight);
        
        if(!isLegalTreePosition(world, locX, locY, locZ))return false;
        if(!isCubeClear(locX, locY+3, locZ, 2, 15))return false;
    	
		//generates the trunk
    	genTree(random, locX, locY, locZ, treeHeight, isWide);

    	if(this.trunk2){
    		treeHeight+= 3;
    		genTree(random, locX+1, locY, locZ, treeHeight, isWide);
    		treeHeight+= 3;
    		genTree(random, locX, locY, locZ+1, treeHeight, isWide);
    		treeHeight+= 3;
    		genTree(random, locX+1, locY, locZ+1, treeHeight, isWide);
    	}
        this.world = null;
        this.random = null;
    	
    	return true;
    }
    
    
    //TREE GENERATORS
    
	private boolean genTree(Random random, int locX, int locY, int locZ, int treeHeight, boolean isWide){
    	for(int i = 0; i < treeHeight; i++){
    		setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
    	}
    	
    	int h = locY + treeHeight - 1;
    	//generate leaves above trunk
    	generateLeafLayerCircle(5.5, locX, locZ, h);
    	h++;
    	generateLeafLayerCircle(4.5, locX, locZ, h);

		h = locY + treeHeight - 6;
		//generates branch
		int[] xyz = generateStraightBranch(4, locX, h, locZ, random.nextInt(4));
		generateLeafLayerCircle(4.5, xyz[0], xyz[2], xyz[1]);
		generateLeafLayerCircle(3.5, xyz[0], xyz[2], xyz[1]+1);
		return true;
    }
    	
}













