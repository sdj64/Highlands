package highlands.worldgen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import highlands.HighlandsMain;
import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

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
    public WorldGenTreeMangrove(int lmd, int wmd, int wb, int lb, int minH, int maxH, boolean notify)
    {
        super(lmd, wmd, wb, lb, notify);
        this.minHeight = minH;
        this.maxHeight = maxH;
    }
    
    public WorldGenTreeMangrove(int minH, int maxH, boolean notify){
    	this(0, 0, HighlandsBlocks.mangroveWood.blockID, HighlandsBlocks.mangroveLeaves.blockID, minH, maxH, notify);
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
    	
    	if(locY < 62 && !notifyFlag) locY = 62;
        
        if(!(world.getBlockId(locX, locY-1, locZ) == Block.grass.blockID 
        		|| world.getBlockId(locX, locY-1, locZ) == Block.dirt.blockID
                || world.getBlockId(locX, locY-1, locZ) == Block.sand.blockID 
                || world.getBlockId(locX, locY-1, locZ) == Block.waterStill.blockID))return false;
        if(!isCubeClear(locX, locY+2, locZ, 0, 3))return false;
    	
        
        int waterH = 0;//height of water
        for (boolean var6 = false; (world.getBlockId(locX, locY-waterH, locZ) == 0 || world.getBlockId(locX, locY-waterH, locZ) == Block.leaves.blockID 
        		|| world.getBlockId(locX, locY-waterH, locZ) == Block.waterStill.blockID) && locY-waterH > 0; ++waterH);
        if(waterH > 4)return false;
        
        
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
		generateLeafLayerCircle(world, random, 3.5, locX, locZ, h);
		h++;
		generateLeafLayerCircle(world, random, 2.5, locX, locZ, h);
		h++;
		generateLeafLayerCircle(world, random, 1, locX, locZ, h);
		
    	return true;
    }
    
}













