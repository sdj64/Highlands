package highlands.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import highlands.Highlands;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenAutumnTree;
import highlands.worldgen.WorldGenHighlandsBigTree;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenJapaneseMapleTree;
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
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockHighlandsSapling2 extends BlockHighlandsSapling implements IPlantable{

	private int treeType;
	
	private String[] treeNames = 
		{
			"Hedge",        // not used
			"JapaneseMaple" // new
		};
	
	private int[] growTimes = {
			0, 5
	};
	
	//private IIcon[] textures;
	
	
	/** constructs a Highlands sapling
	 * @param type 
	 * 
	 * @param par1 type id
	 */
    public BlockHighlandsSapling2(int type)
    {	
        super(type);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        this.setCreativeTab(Highlands.tabHighlands);
        treeType = type;
        
        //System.out.println("Highlands Saplings texture file: " + this.currentTexture);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
    	this.blockIcon = par1IconRegister.registerIcon("Highlands:sapling" + treeNames[treeType]);
    	
    	/**
    	textures = new IIcon[treeNames.length];

		for (int i = 0; i < treeNames.length; ++i) {
			textures[i] = par1IconRegister.registerIcon("Highlands:sapling" + treeNames[i]);
		}
		*/
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.blockIcon;
		/**
		if (meta < 0 || meta >= treeNames.length) {
			meta = 0;
		}

		return textures[meta];
		*/
	}
	
	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		list.add(new ItemStack(block, 1, treeType));
		/**
		for (int i = 0; i < treeNames.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
		*/
	}
    
    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        Block soil = par1World.getBlock(par2, par3 - 1, par4);
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
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            super.updateTick(par1World, par2, par3, par4, par5Random);

            if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0)
            {
            	if(par1World.getBlockMetadata(par2, par3, par4) > growTimes[treeType])
            		growTree(par1World,par2,par3,par4,par5Random);
            	else if(par1World.getBlockMetadata(par2, par3, par4) < 15)
            		par1World.setBlock(par2, par3, par4, this, par1World.getBlockMetadata(par2, par3, par4)+1, 2);
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
    		par1World.setBlock(xysw[0], j, xysw[1], Blocks.air, 0, 2);
    		par1World.setBlock(xysw[0]+1, j, xysw[1], Blocks.air, 0, 2);
    		par1World.setBlock(xysw[0], j, xysw[1]+1, Blocks.air, 0, 2);
    		par1World.setBlock(xysw[0]+1, j, xysw[1]+1, Blocks.air, 0, 2);
    	}
    	
    	boolean replaceSapling = true;
    	if(replaceSapling) 
    		par1World.setBlock(i, j, k, Blocks.air, 0, 2);
    	
    	//grow new tree in location based on type
    	if(treeType == 0)isTreeGrowSuccess = new WorldGenShrubbery(true).generate(par1World, r, i, j, k);
    	if(treeType == 1)isTreeGrowSuccess = new WorldGenJapaneseMapleTree(8, 4, true).generate(par1World, r, i, j, k);
    	//if(treeType == 1)isTreeGrowSuccess = new WorldGenTreeDiamondheart(70, 20, true).generate(par1World, r, i, j, k);
    	
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
    		if(par1World.isAirBlock(i, j, k)) par1World.setBlock(i, j, k, this, meta, 2);
    	}

	    return isTreeGrowSuccess;
    }
    
    //returns the x,y of the southwest sapling if there are four saplings of the same type
    public int[] growTreeWide(World world, int i, int j, int k){
    	if(world.getBlock(i+1, j, k) == this &&
    			world.getBlock(i, j, k+1) == this &&
    			world.getBlock(i+1, j, k+1) == this
    			){
    		return new int[] {i, k};
    	}
    	i--;
    	if(world.getBlock(i+1, j, k) == this &&
    			world.getBlock(i, j, k+1) == this &&
    			world.getBlock(i+1, j, k+1) == this
    			){
    		return new int[] {i, k};
    	}
    	k--;
    	if(world.getBlock(i+1, j, k) == this &&
    			world.getBlock(i, j, k+1) == this &&
    			world.getBlock(i+1, j, k+1) == this
    			){
    		return new int[] {i, k};
    	}
    	i++;
    	if(world.getBlock(i+1, j, k) == this &&
    			world.getBlock(i, j, k+1) == this &&
    			world.getBlock(i+1, j, k+1) == this
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
    //public int getRenderType()
    //{
    //    return 1;
    //}
    
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
    	//if(treeType == 12) return EnumPlantType.Water;
        return EnumPlantType.Plains;
    }
    
    public int getPlantMetadata(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
    
    public int damageDropped(int metadata)
    {
        return metadata;
    }

    //TODO- MFR is not 1.7.x yet
//	//// MFR : IFactoryFertilizable
//	@Override
//	public int getFertilizableBlockId() {
//		return blockID;
//	}
//
//	@Override
//	public boolean canFertilizeBlock(World world, int x, int y, int z, FertilizerType fertilizerType) {
//		return world.getBlockId(x,y,z) == blockID;
//	}
//
//	@Override
//	public boolean fertilize(World world, Random rand, int x, int y, int z, FertilizerType fertilizerType) {
//		return this.growTree(world, x,y,z, rand);
//	}
//
//	//// MFR : IFactoryPlantable
//	@Override
//	public int getSeedId() {
//		return blockID;
//	}
//
//	@Override
//	public int getPlantedBlockId(World world, int x, int y, int z, ItemStack stack) {
//		return blockID;
//	}
//
//	@Override
//	public int getPlantedBlockMetadata(World world, int x, int y, int z, ItemStack stack) {
//		return 0;
//	}

	public boolean isValidPosition(World world, int x, int y, int z, int meta) {
		// this crashes MC:
		// if (!isSoilGoodForSaplingType(block, world, x, y, z))

		if (world.isAirBlock(x,y,z)){
			Block currentBlock = world.getBlock(x,y-1,z);
			if(treeType == 0 && currentBlock == Blocks.snow)return true;
			if(treeType == 10 && currentBlock == Blocks.sand)return true;
			if(treeType == 12 && currentBlock == Blocks.water)return true;
			if(currentBlock == Blocks.grass || currentBlock == Blocks.dirt)return true;
		}
		return false;
	}
}












