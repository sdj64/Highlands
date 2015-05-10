package highlands.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMountain extends WorldGenerator
{
    private final int minHeight;
    private final int maxHeight;
    
    private final int snowrocksand;
    private final boolean notifyFlag;
    
    private World world;

    /** Constructor
     * @param minH minimum height of tree trunk
     * @param maxH max possible height above minH
     * @param notify whether or not to notify blocks of the change
     *  Generally false for world generation.
     */
    public WorldGenMountain(int minH, int maxH, boolean notify, int type)
    {
        this.minHeight = minH;
        this.maxHeight = maxH;
        this.notifyFlag = notify;
        this.snowrocksand = type;
    }

    @Override
    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	
    	int radius = minHeight + random.nextInt(maxHeight);
    	
    	//System.out.println("H:"+height+" X:"+locX+" Z:"+locZ);
    	
    	for(int x = (int)Math.ceil(locX - radius); x <= (int)Math.ceil(locX + radius); x++){
			for(int z = (int)Math.ceil(locZ - radius); z <= (int)Math.ceil(locZ + radius); z++){
				double xfr = z - locZ;
				double zfr = x - locX;
				
				int dist = (int)Math.sqrt(xfr * xfr + zfr * zfr);
				
				if(dist <= radius){
					//overwrites trees rather than placing the mountain on top of them.
                    locY = world.getTopSolidOrLiquidBlock(x, z);
					while(locY > 0){
						Block block = world.getBlock(x, locY, z);
						if(block.isOpaqueCube() && !block.isWood(world, x, locY, z) && !block.isLeaves(world, x, locY, z))
                            break;
                        locY--;
					}
                    if(locY == 0){
                        continue;
                    }
					
					int h = locY + radius - dist;
					for(int i = locY; i < h; i++){
						if(snowrocksand == 0 && h-i <4)setBlockInWorld(x, i, z, Blocks.snow, 0);
						else if(snowrocksand == 2 && h-i <4)setBlockInWorld(x, i, z, Blocks.sandstone, 0);
						else if(random.nextInt(3)== 0) setBlockInWorld(x, i, z, Blocks.cobblestone, 0);
						else setBlockInWorld(x, i, z, Blocks.stone, 0);
					}
					if(snowrocksand == 0 && h > 62)
						setBlockInWorld(x, h, z, Blocks.snow, 0);
				}
			}
		}
        this.world = null;
    	return true;
    }
    
    
    private void setBlockInWorld(int x, int y, int z, Block sandstone, int meta){
    	try{
			if(notifyFlag) world.setBlock(x, y, z, sandstone, meta, 3);
		    else world.setBlock(x, y, z, sandstone, meta, 2);
    	}
    	catch(RuntimeException e){
    		if(e.getMessage().equals("Already decorating!!")){
    			System.out.println("Error: Highlands Mountain block couldn't generate!");
    		}
    		//e.printStackTrace();
    	}
    }
    
}













