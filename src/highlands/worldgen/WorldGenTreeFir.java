package highlands.worldgen;

import highlands.HighlandsMain;
import highlands.api.HighlandsBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenTreeFir extends WorldGenHighlandsTreeBase
{
    private final boolean trunk2;

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
    public WorldGenTreeFir(int lmd, int wmd, Block wb, BlockLeavesBase lb, int minH, int maxH, boolean notify, boolean thickTrunk)
    {
        super(lmd, wmd, wb, lb, notify);
        this.minHeight = minH;
        this.maxHeight = maxH;
        this.trunk2 = thickTrunk;
    }
    
    public WorldGenTreeFir(int minH, int maxH, boolean notify, boolean thickTrunk){
    	this(0, 0, HighlandsBlocks.firWood, (BlockLeavesBase) HighlandsBlocks.firLeaves, minH, maxH, notify, thickTrunk);
    	if(HighlandsMain.vanillaBlocksFlag){
    		this.woodID = Blocks.log;
    		this.woodMeta = 1;
    		this.leavesID = Blocks.leaves;
    		this.leavesMeta = 1;
    	}
    }

    @Override
    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	this.random = random;
    	
    	boolean isWide = (random.nextInt(3) == 0);
    	int treeHeight = minHeight + random.nextInt(maxHeight);

        if(!isLegalTreePosition(world, locX, locY, locZ) 
        		&& world.getBlock(locX, locY-1, locZ) != Blocks.snow)
            return false;
        if(!isCubeClear(locX, locY+3, locZ, 2, 10))
            return false;
    	
		//generates the trunk
    	genTree(locX, locY, locZ, treeHeight, isWide);

    	if(this.trunk2){
    		genTree(locX+1, locY, locZ, treeHeight, isWide);
    		genTree(locX, locY, locZ+1, treeHeight, isWide);
    		genTree(locX+1, locY, locZ+1, treeHeight, isWide);
    	}
        this.world = null;
        this.random = null;

    	return true;
    }
    
    
    //TREE GENERATORS
    
	private boolean genTree(int locX, int locY, int locZ, int treeHeight, boolean isWide){
    	//generates the trunk
    	for(int i = 0; i < treeHeight; i++){
    		setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
    	}
    	
    	//generates the leaves
    	double leafRadius = 4.5;
    	double finalRadius = 2.5;
    	
    	int h0 = 6;
    	if(treeHeight < 16) h0 = 3;
    	int h;
    	for(h = locY + h0; h < treeHeight + locY; h++){
    		//level 1
    		if(isWide){
    			generateLeafLayerCircleNoise(leafRadius + 1, locX, locZ, h);
    			h++;
    		}
    		//level 2
    		generateLeafLayerCircleNoise(leafRadius, locX, locZ, h);
    		h++;
    		//level 3
    		generateLeafLayerCircleNoise(leafRadius - 1, locX, locZ, h);
    		//if not wide, gen a smaller layer
    		if(!isWide){
    			h++;
    			generateLeafLayerCircleNoise(leafRadius - 1.5, locX, locZ, h);
    		}
    		
    		leafRadius -= (leafRadius - finalRadius)/3.0;
    		
    	}
    	//generate top of tree
    	if(isWide){
    		generateLeafLayerCircleNoise(2, locX, locZ, h);
    		h++;
    	}
    	generateLeafLayerCircleNoise(1.5, locX, locZ, h);
    	h++;
    	generateLeafLayerCircleNoise(1, locX, locZ, h);
    	h++;
    	setBlockInWorld(locX, h, locZ, this.leavesID, this.leavesMeta);
    	h++;
    	setBlockInWorld(locX, h, locZ, this.leavesID, this.leavesMeta);
    	return true;
    }
    
}













