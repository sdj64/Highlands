package highlands.block;

import java.util.Random;

import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenAutumnTree;
import highlands.worldgen.WorldGenHighlandsBigTree;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenShrubbery;
import highlands.worldgen.WorldGenTreeAcacia;
import highlands.worldgen.WorldGenTreeAsh;
import highlands.worldgen.WorldGenTreeCanopy;
import highlands.worldgen.WorldGenTreeDiamondheart;
import highlands.worldgen.WorldGenTreeFir;
import highlands.worldgen.WorldGenTreeIronwood;
import highlands.worldgen.WorldGenTreeMangrove;
import highlands.worldgen.WorldGenTreePalm;
import highlands.worldgen.WorldGenTreePoplar;
import highlands.worldgen.WorldGenTreeRedwood;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockHighlandsSapling extends BlockFlower implements IPlantable{

	private int treeType;
	
	private String[] treeNames = 
		{
			"Fir",
			"Acacia",
			"Poplar",
			"Redwood",
			"Eucalyptus",
			"GreatOak",
			"Beech",
			"DeadTree",
			"EvgBush",
			"DecBush",
			"Palm",
			"Ironwood",
			"Mangrove",
			"Ash",
			"AutumnYellow",
			"AutumnOrange",
			"Hedge",
		};
	
	private int[] growTimes = {
			5, 3, 3, 10, 6, 5, 2, 1, 1, 1, 2, 15, 2, 8, 2, 2, 0
	};
	
	
	/** constructs a Highlands sapling
	 * 
	 * @param par1 block id
	 * @param type type of tree, determines sapling texture, name, and tree- 
	 * 			Tree types:
     * 			0 = tall pine
     * 			1 = acacia (savannah tree)
     * 			2 = poplar / thin birch
     * 			3 = sequoia
     * 			4 = canopy tree
     * 			5 = great oak
     * 			6 = beech (big tree birch)
     * 			7 = dead big tree
     * 			8 = evergreen bush
     * 			9 = deciduous bush
     * 			10 = palm tree
     * 			11 = ironwood tree
     * 			12 = mangrove
     * 			13 = ash tree
     * 			14 = autumn tree yellow
     * 			15 = autumn tree orange
     * 			16 = hedge
	 */
    public BlockHighlandsSapling(int type)
    {	
    	//TODO-        correct material?
        super(type);
        float var3 = 0.4F;
        //TODO- setBlockBounds
        this.func_149676_a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        //TODO- setCreativeTab
        this.func_149647_a(CreativeTabs.tabDecorations);
        treeType = type;
        
        //System.out.println("Highlands Saplings texture file: " + this.currentTexture);
    }
    
    @Override
    //TODO-     registerIcons
    public void func_149651_a(IIconRegister par1IconRegister)
    {
    	//TODO- blockIcon
    	this.field_149761_L = par1IconRegister.registerIcon("Highlands:sapling"+treeNames[treeType]);
    }
    
    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
    	//TODO-                getBlock
        Block soil = par1World.func_147439_a(par2, par3 - 1, par4);
        return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) && 
                (soil != null && isSoilGoodForSaplingType(soil, par1World, par2, par3, par4));
    }
    
    private boolean isSoilGoodForSaplingType(Block soil, World par1World, int par2, int par3, int par4) {
    	if(soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this))return true;
    	if(treeType == 0 && soil == Blocks.snow)return true;
    	if(treeType == 10 && soil == Blocks.sand)return true;
    	if(treeType == 12 && soil == Blocks.water)return true;
		return false;
	}

	/**
     * Ticks the block if it's been scheduled
     */
    //TODO-     updateTick
    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            super.func_149674_a(par1World, par2, par3, par4, par5Random);

            if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0)
            {
            	if(par1World.getBlockMetadata(par2, par3, par4) > growTimes[treeType])
            		growTree(par1World,par2,par3,par4,par5Random);
            	else if(par1World.getBlockMetadata(par2, par3, par4) < 15)
            		//TODO-   setBlock
            		par1World.func_147465_d(par2, par3, par4, this, par1World.getBlockMetadata(par2, par3, par4)+1, 2);
            }
        }
    }
    
    /*
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	//System.out.println("Sapling activated!  " + (player.inventory.getCurrentItem().getItemDamage()==15) + "  " + (player.inventory.getCurrentItem().itemID==Item.dyePowder.itemID));
    	
    	
	    if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItemDamage()==15 && player.inventory.getCurrentItem().itemID==Item.dyePowder.itemID)
	    {
	    	if(player.capabilities.isCreativeMode == true)
	    		growTree(par1World,par2,par3,par4,new Random());
	    	else{
	    	//increase metadata by one if it is not ready to grow yet
	    	if(par1World.getBlockMetadata(par2, par3, par4) < growTimes[treeType]){
	    		par1World.setBlock(par2, par3, par4, this.blockID, par1World.getBlockMetadata(par2, par3, par4)+1, 2);
                if (!par1World.isRemote)
                {
                    par1World.playAuxSFX(2005, par2, par3, par4, 0);
                }
	    	}
	    	else growTree(par1World,par2,par3,par4,new Random());
	    	
	    	//reduce bonemeal stack size by one
	    	player.inventory.getCurrentItem().stackSize--;
	    	}
	    	return true;
	    }
	    return false;
    }
    */
    
    public boolean growTree(World par1World, int i, int j, int k, Random r){
    	int meta = par1World.getBlockMetadata(i, j, k);
    	boolean isTreeGrowSuccess = true;
    	int[] treeWideSaplings = new int[4];
    	
    	
    	//find if nearby saplings are the same in a 2x2 square
    	boolean growWideTree = false;
    	int[] xysw = growTreeWide(par1World, i, j, k);
    	if(xysw != null){
    		growWideTree = true;
    		treeWideSaplings[0] = par1World.getBlockMetadata(xysw[0], j, xysw[1]);
    		treeWideSaplings[1] = par1World.getBlockMetadata(xysw[0]+1, j, xysw[1]);
    		treeWideSaplings[2] = par1World.getBlockMetadata(xysw[0], j, xysw[1]+1);
    		treeWideSaplings[3] = par1World.getBlockMetadata(xysw[0]+1, j, xysw[1]+1);
    		
    		//TODO-   setBlock
    		//Setting down air blocks instead of null blocks for experimentation;
    		//in case of crash change to null.
    		par1World.func_147465_d(xysw[0], j, xysw[1], Blocks.air, 0, 2);
    		par1World.func_147465_d(xysw[0]+1, j, xysw[1], Blocks.air, 0, 2);
    		par1World.func_147465_d(xysw[0], j, xysw[1]+1, Blocks.air, 0, 2);
    		par1World.func_147465_d(xysw[0]+1, j, xysw[1]+1, Blocks.air, 0, 2);
    	}
    	
    	boolean replaceSapling = ((treeType >= 5 && treeType <= 9) || treeType == 11 || treeType == 14 || treeType == 15);
    	if(replaceSapling) par1World.func_147465_d(i, j, k, Blocks.air, 0, 2);
    	
    	
    	
    	//grow new tree in location based on type
    	if(treeType == 0){
    		if(growWideTree)isTreeGrowSuccess = new WorldGenTreeFir(10, 15, true, true).generate(par1World, r, xysw[0], j, xysw[1]);
    		else isTreeGrowSuccess = new WorldGenTreeFir(10, 15, true, false).generate(par1World, r, i, j, k);
    	}
    	if(treeType == 1)isTreeGrowSuccess = new WorldGenTreeAcacia(7, 3, true).generate(par1World, r, i, j, k);
    	//if(treeType == 1)isTreeGrowSuccess = new WorldGenTreeDiamondheart(70, 20, true).generate(par1World, r, i, j, k);
    	
    	if(treeType == 2)isTreeGrowSuccess = new WorldGenTreePoplar(10, 4, true).generate(par1World, r, i, j, k);
    	if(treeType == 3)isTreeGrowSuccess = new WorldGenTreeRedwood(35, 10, true).generate(par1World, r, i, j, k);
    	if(treeType == 4){
    		if(growWideTree)isTreeGrowSuccess = new WorldGenTreeCanopy(13, 6, true, true).generate(par1World, r, xysw[0], j, xysw[1]);
    		else isTreeGrowSuccess = new WorldGenTreeCanopy(13, 6, true, false).generate(par1World, r, i, j, k);
    	}
    	if(treeType == 5)isTreeGrowSuccess = new WorldGenHighlandsBigTree(false, true, 0, 0, 2, 20).generate(par1World, r, i, j, k);
    	if(treeType == 6)isTreeGrowSuccess = new WorldGenHighlandsBigTree(false, true, 2, 2, 1, 0).generate(par1World, r, i, j, k);
    	if(treeType == 7)isTreeGrowSuccess = new WorldGenHighlandsBigTree(false, false, 0, 0, 1, 0).generate(par1World, r, i, j, k);
    	if(treeType == 8)isTreeGrowSuccess = new WorldGenHighlandsShrub(1, 1).generate(par1World, r, i, j, k);
    	if(treeType == 9)isTreeGrowSuccess = new WorldGenHighlandsShrub(0, 0).generate(par1World, r, i, j, k);
    	if(treeType == 10)isTreeGrowSuccess = new WorldGenTreePalm(8, 3, true).generate(par1World, r, i, j, k);
    	if(treeType == 11)isTreeGrowSuccess = new WorldGenTreeIronwood(25, 50, true).generate(par1World, r, i, j, k);
    	if(treeType == 12)isTreeGrowSuccess = new WorldGenTreeMangrove(4, 2, false).generate(par1World, r, i, j, k);
    	if(treeType == 13)isTreeGrowSuccess = new WorldGenTreeAsh(16, 8, false).generate(par1World, r, i, j, k);
    	if(treeType == 14)isTreeGrowSuccess = new WorldGenAutumnTree(true, 4, Blocks.log, HighlandsBlocks.autumnOrangeLeaves).generate(par1World, r, i, j, k);
    	if(treeType == 15)isTreeGrowSuccess = new WorldGenAutumnTree(true, 4, Blocks.log, HighlandsBlocks.autumnYellowLeaves).generate(par1World, r, i, j, k);
    	if(treeType == 16)isTreeGrowSuccess = new WorldGenShrubbery(true).generate(par1World, r, i, j, k);
    	
    	/*
    	if(growWideTree && !isTreeGrowSuccess){
    		if(par1World.getBlockId(xysw[0], j, xysw[1]) == 0)par1World.setBlock(xysw[0], j, xysw[1], this.blockID, treeWideSaplings[0], 2);
    		if(par1World.getBlockId(xysw[0]+1, j, xysw[1]) == 0)par1World.setBlock(xysw[0]+1, j, xysw[1], this.blockID, treeWideSaplings[1], 2);
    		if(par1World.getBlockId(xysw[0], j, xysw[1]+1) == 0)par1World.setBlock(xysw[0], j, xysw[1]+1, this.blockID, treeWideSaplings[2], 2);
    		if(par1World.getBlockId(xysw[0]+1, j, xysw[1]+1) == 0)par1World.setBlock(xysw[0]+1, j, xysw[1]+1, this.blockID, treeWideSaplings[3], 2);
    	}
    	else if(!growWideTree && !isTreeGrowSuccess){
    		if(par1World.getBlockId(i, j, k) == 0)par1World.setBlock(i, j, k, this.blockID, meta, 2);
    	}
    	*/
    	
    	else if(!isTreeGrowSuccess && replaceSapling){
    		//TODO-      getBlock
    		if(par1World.func_147439_a(i, j, k) == null)
    			//TODO-   setBlock
    			par1World.func_147465_d(i, j, k, this, meta, 2);
    	}

	    return isTreeGrowSuccess;
    }
    
    //returns the x,y of the southwest sapling if there are four saplings of the same type
    
    //TODO- welp, this method is full of func_147439_a = getBlock
    
    public int[] growTreeWide(World world, int i, int j, int k){
    	if(world.func_147439_a(i+1, j, k) == this &&
    			world.func_147439_a(i, j, k+1) == this &&
    			world.func_147439_a(i+1, j, k+1) == this
    			){
    		return new int[] {i, k};
    	}
    	i--;
    	if(world.func_147439_a(i+1, j, k) == this &&
    			world.func_147439_a(i, j, k+1) == this &&
    			world.func_147439_a(i+1, j, k+1) == this
    			){
    		return new int[] {i, k};
    	}
    	k--;
    	if(world.func_147439_a(i+1, j, k) == this &&
    			world.func_147439_a(i, j, k+1) == this &&
    			world.func_147439_a(i+1, j, k+1) == this
    			){
    		return new int[] {i, k};
    	}
    	i++;
    	if(world.func_147439_a(i+1, j, k) == this &&
    			world.func_147439_a(i, j, k+1) == this &&
    			world.func_147439_a(i+1, j, k+1) == this
    			){
    		return new int[] {i, k};
    	}
    	return null;
    }
    
    
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 1;
    }
    
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
    	//if(treeType == 12) return EnumPlantType.Water;
        return EnumPlantType.Plains;
    }
    
    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }

	@Override
	//TODO-        canBlockStay
	public boolean func_149718_j(World world, int x, int y, int z) {
		// this crashes MC:
		// if (!isSoilGoodForSaplingType(block, world, x, y, z))

		//TODO-   isAirBlock
		if (world.func_147437_c(x,y,z)){
			//TODO-                  getBlock
			Block currentBlock = world.func_147439_a(x,y-1,z);
			if(treeType == 0 && currentBlock == Blocks.snow)return true;
			if(treeType == 10 && currentBlock == Blocks.sand)return true;
			if(treeType == 12 && currentBlock == Blocks.water)return true;
			if(currentBlock == Blocks.grass || currentBlock == Blocks.dirt)return true;
		}
		return false;
	}
}












