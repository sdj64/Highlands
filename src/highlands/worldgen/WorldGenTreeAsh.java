package highlands.worldgen;

import java.util.Random;

import highlands.HighlandsMain;
import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenTreeAsh extends WorldGenHighlandsTreeBase
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
    public WorldGenTreeAsh(int lmd, int wmd, Block wb, BlockLeavesBase lb, int minH, int maxH, boolean notify)
    {
    	super(lmd, wmd, wb, lb, notify);
        
        this.minHeight = minH;
        this.maxHeight = maxH;
    }
    
    public WorldGenTreeAsh(int minH, int maxH, boolean notify){
    	this(0, 0, HighlandsBlocks.ashWood, (BlockLeavesBase) HighlandsBlocks.ashLeaves, minH, maxH, notify);
    	if(HighlandsMain.vanillaBlocksFlag){
    		this.woodID = Blocks.log;
    		this.leavesID = Blocks.leaves;
    	}
    }

    @Override
    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	this.random = random;
        if(!isLegalTreePosition(world, locX, locY, locZ))return false;
        if(!isCubeClear(locX, locY+3, locZ, 3, 15))return false;
        
      //generates trunk 2*2
    	int treeHeight = minHeight + random.nextInt(maxHeight);
    	for(int i = 0; i < treeHeight; i++){
    		setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
    		setBlockInWorld(locX+1, locY + i, locZ, this.woodID, this.woodMeta);
    		setBlockInWorld(locX+1, locY + i, locZ+1, this.woodID, this.woodMeta);
    		setBlockInWorld(locX, locY + i, locZ+1, this.woodID, this.woodMeta);
    	}
    	//generates leaves at top
    	int h;
    	for(h = locY+treeHeight - 3; h < locY + treeHeight; h++){
    		generateLeafLayerCircleNoise(3.5, locX+random.nextInt(2), locZ+random.nextInt(2), h);
    	}
    	generateLeafLayerCircleNoise(3.8, locX+random.nextInt(2), locZ+random.nextInt(2), h);
    	h++;
    	generateLeafLayerCircleNoise(2.5, locX+random.nextInt(2), locZ+random.nextInt(2), h);
    	h++;
    	generateLeafLayerCircleNoise(2, locX+random.nextInt(2), locZ+random.nextInt(2), h);
    	h++;
    	generateLeafLayerCircleNoise(1, locX+random.nextInt(2), locZ+random.nextInt(2), h);
    	h -= 16;
    	//generates branches / leaves
    	int firstDir = random.nextInt(4);
    	for(int i = 0; i < 8; i++){
    		int[] xyz = generateStraightBranch(5, locX+random.nextInt(2), h+i, locZ+random.nextInt(2), (firstDir + i)%4);
    		generateLeafLayerCircleNoise(1.8, xyz[0], xyz[2], xyz[1]-1);
    		generateLeafLayerCircleNoise(2.5, xyz[0], xyz[2], xyz[1]);
    		generateLeafLayerCircleNoise(1.8, xyz[0], xyz[2], xyz[1]+1);
    	}
        this.world = null;
        this.random = null;
    	return true;
    }
}













