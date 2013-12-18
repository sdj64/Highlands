package highlands.worldgen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import highlands.HighlandsMain;
import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

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
    public WorldGenTreeCanopy(int lmd, int wmd, int wb, int lb, int minH, int maxH, boolean notify, boolean thickTrunk)
    {
    	super(lmd, wmd, wb, lb, notify);
        
        this.minHeight = minH;
        this.maxHeight = maxH;
        this.trunk2 = thickTrunk;
    }
    
    public WorldGenTreeCanopy(int minH, int maxH, boolean notify, boolean thickTrunk){
    	this(0, 0, HighlandsBlocks.canopyWood.blockID, HighlandsBlocks.canopyLeaves.blockID, minH, maxH, notify, thickTrunk);
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
    	
    	boolean isWide = (random.nextInt(3) == 0);
    	int treeHeight = minHeight + random.nextInt(maxHeight);
        
        if(!isLegalTreePosition(world, locX, locY, locZ))return false;
        if(!isCubeClear(locX, locY+3, locZ, 2, 15))return false;
    	
		//generates the trunk
    	genTree(world, random, locX, locY, locZ, treeHeight, isWide);
    	
    	
    	
    	if(this.trunk2){
    		treeHeight+= 3;
    		genTree(world, random, locX+1, locY, locZ, treeHeight, isWide);
    		treeHeight+= 3;
    		genTree(world, random, locX, locY, locZ+1, treeHeight, isWide);
    		treeHeight+= 3;
    		genTree(world, random, locX+1, locY, locZ+1, treeHeight, isWide);
    	}
    	
    	return true;
    }
    
    
    //TREE GENERATORS
    
	private boolean genTree(World world, Random random, int locX, int locY, int locZ, int treeHeight, boolean isWide){
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













