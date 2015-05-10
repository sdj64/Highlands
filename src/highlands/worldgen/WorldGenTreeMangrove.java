package highlands.worldgen;

import highlands.HighlandsMain;
import highlands.api.HighlandsBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenTreeMangrove extends WorldGenHighlandsTreeBase
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
    public WorldGenTreeMangrove(int lmd, int wmd, Block wb, BlockLeavesBase lb, int minH, int maxH, boolean notify)
    {
        super(lmd, wmd, wb, lb, notify);
        this.minHeight = minH;
        this.maxHeight = maxH;
    }
    
    public WorldGenTreeMangrove(int minH, int maxH, boolean notify){
    	this(0, 0, HighlandsBlocks.mangroveWood, (BlockLeavesBase) HighlandsBlocks.mangroveLeaves, minH, maxH, notify);
    	if(HighlandsMain.vanillaBlocksFlag){
    		this.woodID = Blocks.log;
    		this.woodMeta = 2;
    		this.leavesID = Blocks.leaves;
    		this.leavesMeta = 2;
    	}
    }

    @Override
    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	this.random = random;
    	
    	if(locY < 62 && !notifyFlag) locY = 62;
        
        if(!(world.getBlock(locX, locY-1, locZ) == Blocks.grass 
        		|| world.getBlock(locX, locY-1, locZ) == Blocks.dirt
                || world.getBlock(locX, locY-1, locZ) == Blocks.sand 
                || world.getBlock(locX, locY-1, locZ) == Blocks.water))return false;
        if(!isCubeClear(locX, locY+2, locZ, 0, 3))return false;
    	
        
        int waterH = 0;//height of water
        for (; (world.getBlock(locX, locY-waterH, locZ) == Blocks.air || world.getBlock(locX, locY-waterH, locZ) == Blocks.leaves
        		|| world.getBlock(locX, locY-waterH, locZ) == Blocks.water) && locY-waterH > 0; ++waterH);
        if(waterH > 4)
            return false;
        
        
    	//generates trunk
    	int treeHeight = minHeight + random.nextInt(maxHeight) - 2;
    	locY++;
    	for(int i = 0; i < treeHeight; i++){
    		setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
    	}
    	//generates roots
    	for(int i = -1; i > -7; i--){
    		setBlockInWorld(locX + i, locY + i, locZ, this.woodID, this.woodMeta);
    		setBlockInWorld(locX - i, locY + i, locZ, this.woodID, this.woodMeta);
    		setBlockInWorld(locX, locY + i, locZ + i, this.woodID, this.woodMeta);
    		setBlockInWorld(locX, locY + i, locZ - i, this.woodID, this.woodMeta);
    	}
		//generates leaves
		int h = locY + treeHeight - 1;
		generateLeafLayerCircle(3.5, locX, locZ, h);
		h++;
		generateLeafLayerCircle(2.5, locX, locZ, h);
		h++;
		generateLeafLayerCircle(1, locX, locZ, h);

        this.world = null;
        this.random = null;
    	return true;
    }
    
}













