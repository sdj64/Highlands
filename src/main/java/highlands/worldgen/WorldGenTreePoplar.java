package highlands.worldgen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import highlands.HighlandsMain;
import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreePoplar extends WorldGenHighlandsTreeBase
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
    public WorldGenTreePoplar(int lmd, int wmd, int wb, int lb, int minH, int maxH, boolean notify)
    {
        super(lmd, wmd, wb, lb, notify);
        this.minHeight = minH;
        this.maxHeight = maxH;
    }
    
    public WorldGenTreePoplar(int minH, int maxH, boolean notify){
    	this(0, 0, HighlandsBlocks.poplarWood.blockID, HighlandsBlocks.poplarLeaves.blockID, minH, maxH, notify);
    	if(HighlandsMain.vanillaBlocksFlag){
    		this.woodID = Block.wood.blockID;
    		this.woodMeta = 2;
    		this.leavesID = Block.leaves.blockID;
    		this.leavesMeta = 2;
    	}
    }

    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	this.random = random;
    	
    	
        
        if(!isLegalTreePosition(world, locX, locY, locZ))return false;
        if(!isCubeClear(locX, locY+3, locZ, 1, 8))return false;
    	
        //generates the trunk
    	int treeHeight = minHeight + random.nextInt(maxHeight);
    	for(int i = 0; i < treeHeight; i++){
    		setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
    	}
    	
    	//generates the leaves.
    	int h = locY + 3;
    	generateLeafLayerCircle(world, random, 1, locX, locZ, h);
    	h++;
    	generateLeafLayerCircle(world, random, 1.5, locX, locZ, h);
    	h++;
    	generateLeafLayerCircle(world, random, 2, locX, locZ, h);
    	
    	for(h = h + 1; h < locY + treeHeight; h++){
    		generateLeafLayerCircleNoise(world, random, 2.8, locX, locZ, h);
    	}
    	generateLeafLayerCircleNoise(world, random, 2, locX, locZ, h);
    	h++;
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













