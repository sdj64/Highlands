package highlands.worldgen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import highlands.HighlandsMain;
import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeFir extends WorldGenHighlandsTreeBase
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
    public WorldGenTreeFir(int lmd, int wmd, int wb, int lb, int minH, int maxH, boolean notify, boolean thickTrunk)
    {
        super(lmd, wmd, wb, lb, notify);
        this.minHeight = minH;
        this.maxHeight = maxH;
        this.trunk2 = thickTrunk;
    }
    
    public WorldGenTreeFir(int minH, int maxH, boolean notify, boolean thickTrunk){
    	this(0, 0, HighlandsBlocks.firWood.blockID, HighlandsBlocks.firLeaves.blockID, minH, maxH, notify, thickTrunk);
    	if(HighlandsMain.vanillaBlocksFlag){
    		this.woodID = Block.wood.blockID;
    		this.woodMeta = 1;
    		this.leavesID = Block.leaves.blockID;
    		this.leavesMeta = 1;
    	}
    }

    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	this.random = random;
    	
    	boolean isWide = (random.nextInt(3) == 0);
    	int treeHeight = minHeight + random.nextInt(maxHeight);
    	
        
        if(!isLegalTreePosition(world, locX, locY, locZ) 
        		&& world.getBlockId(locX, locY-1, locZ) != Block.blockSnow.blockID)return false;
        if(!isCubeClear(locX, locY+3, locZ, 2, 10))return false;
    	
		//generates the trunk
    	genTree(world, random, locX, locY, locZ, treeHeight, isWide);
    	
    	
    	
    	if(this.trunk2){
    		genTree(world, random, locX+1, locY, locZ, treeHeight, isWide);
    		genTree(world, random, locX, locY, locZ+1, treeHeight, isWide);
    		genTree(world, random, locX+1, locY, locZ+1, treeHeight, isWide);
    	}
    	
    	return true;
    }
    
    
    //TREE GENERATORS
    
	private boolean genTree(World world, Random random, int locX, int locY, int locZ, int treeHeight, boolean isWide){
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
    			generateLeafLayerCircleNoise(world, random, leafRadius + 1, locX, locZ, h);
    			h++;
    		}
    		//level 2
    		generateLeafLayerCircleNoise(world, random, leafRadius, locX, locZ, h);
    		h++;
    		//level 3
    		generateLeafLayerCircleNoise(world, random, leafRadius - 1, locX, locZ, h);
    		//if not wide, gen a smaller layer
    		if(!isWide){
    			h++;
    			generateLeafLayerCircleNoise(world, random, leafRadius - 1.5, locX, locZ, h);
    		}
    		
    		leafRadius -= (leafRadius - finalRadius)/3.0;
    		
    	}
    	//generate top of tree
    	if(isWide){
    		generateLeafLayerCircleNoise(world, random, 2, locX, locZ, h);
    		h++;
    	}
    	generateLeafLayerCircleNoise(world, random, 1.5, locX, locZ, h);
    	h++;
    	generateLeafLayerCircleNoise(world, random, 1, locX, locZ, h);
    	h++;
    	setBlockInWorld(locX, h, locZ, this.leavesID, this.leavesMeta);
    	h++;
    	setBlockInWorld(locX, h, locZ, this.leavesID, this.leavesMeta);
    	return true;
    }
    
}













