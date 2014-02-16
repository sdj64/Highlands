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
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import powercrystals.minefactoryreloaded.api.FertilizerType;
import powercrystals.minefactoryreloaded.api.IFactoryFertilizable;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;

public class BlockHighlandsSapling extends BlockFlower implements IPlantable,IFactoryFertilizable,IFactoryPlantable{

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
    public BlockHighlandsSapling(int par1, int type)
    {	
        super(par1, Material.plants);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        treeType = type;
        
        //System.out.println("Highlands Saplings texture file: " + this.currentTexture);
    }
    
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
    	this.blockIcon = par1IconRegister.registerIcon("Highlands:sapling"+treeNames[treeType]);
    }
    
    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        Block soil = blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
        return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) && 
                (soil != null && isSoilGoodForSaplingType(soil, par1World, par2, par3, par4));
    }
    
    private boolean isSoilGoodForSaplingType(Block soil, World par1World, int par2, int par3, int par4) {
    	if(soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this))return true;
    	if(treeType == 0 && soil.blockID == Block.blockSnow.blockID)return true;
    	if(treeType == 10 && soil.blockID == Block.sand.blockID)return true;
    	if(treeType == 12 && soil.blockID == Block.waterStill.blockID)return true;
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
            		par1World.setBlock(par2, par3, par4, this.blockID, par1World.getBlockMetadata(par2, par3, par4)+1, 2);
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
    		par1World.setBlock(xysw[0], j, xysw[1], 0, 0, 2);
    		par1World.setBlock(xysw[0]+1, j, xysw[1], 0, 0, 2);
    		par1World.setBlock(xysw[0], j, xysw[1]+1, 0, 0, 2);
    		par1World.setBlock(xysw[0]+1, j, xysw[1]+1, 0, 0, 2);
    	}
    	
    	boolean replaceSapling = ((treeType >= 5 && treeType <= 9) || treeType == 11 || treeType == 14 || treeType == 15);
    	if(replaceSapling) par1World.setBlock(i, j, k, 0, 0, 2);
    	
    	
    	
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
    	if(treeType == 14)isTreeGrowSuccess = new WorldGenAutumnTree(true, 4, Block.wood.blockID, HighlandsBlocks.autumnOrangeLeaves.blockID).generate(par1World, r, i, j, k);
    	if(treeType == 15)isTreeGrowSuccess = new WorldGenAutumnTree(true, 4, Block.wood.blockID, HighlandsBlocks.autumnYellowLeaves.blockID).generate(par1World, r, i, j, k);
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
    		if(par1World.getBlockId(i, j, k) == 0)par1World.setBlock(i, j, k, this.blockID, meta, 2);
    	}

	    return isTreeGrowSuccess;
    }
    
    //returns the x,y of the southwest sapling if there are four saplings of the same type
    public int[] growTreeWide(World world, int i, int j, int k){
    	if(world.getBlockId(i+1, j, k) == this.blockID &&
    			world.getBlockId(i, j, k+1) == this.blockID &&
    			world.getBlockId(i+1, j, k+1) == this.blockID
    			){
    		return new int[] {i, k};
    	}
    	i--;
    	if(world.getBlockId(i+1, j, k) == this.blockID &&
    			world.getBlockId(i, j, k+1) == this.blockID &&
    			world.getBlockId(i+1, j, k+1) == this.blockID
    			){
    		return new int[] {i, k};
    	}
    	k--;
    	if(world.getBlockId(i+1, j, k) == this.blockID &&
    			world.getBlockId(i, j, k+1) == this.blockID &&
    			world.getBlockId(i+1, j, k+1) == this.blockID
    			){
    		return new int[] {i, k};
    	}
    	i++;
    	if(world.getBlockId(i+1, j, k) == this.blockID &&
    			world.getBlockId(i, j, k+1) == this.blockID &&
    			world.getBlockId(i+1, j, k+1) == this.blockID
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
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
    	//if(treeType == 12) return EnumPlantType.Water;
        return EnumPlantType.Plains;
    }

    @Override
    public int getPlantID(World world, int x, int y, int z)
    {
        return blockID;
    }
    @Override
    public int getPlantMetadata(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
    
    public int damageDropped(int par1)
    {
        return 0;
    }

	//// MFR : IFactoryFertilizable
	@Override
	public int getFertilizableBlockId() {
		return blockID;
	}

	@Override
	public boolean canFertilizeBlock(World world, int x, int y, int z, FertilizerType fertilizerType) {
		return world.getBlockId(x,y,z) == blockID;
	}

	@Override
	public boolean fertilize(World world, Random rand, int x, int y, int z, FertilizerType fertilizerType) {
		return this.growTree(world, x,y,z, rand);
	}

	//// MFR : IFactoryPlantable
	@Override
	public int getSeedId() {
		return blockID;
	}

	@Override
	public int getPlantedBlockId(World world, int x, int y, int z, ItemStack stack) {
		return blockID;
	}

	@Override
	public int getPlantedBlockMetadata(World world, int x, int y, int z, ItemStack stack) {
		return 0;
	}

	@Override
	public boolean canBePlantedHere(World world, int x, int y, int z, ItemStack stack) {
		// this crashes MC:
		// if (!isSoilGoodForSaplingType(block, world, x, y, z))

		if (world.isAirBlock(x,y,z)){
			int currentBlock = world.getBlockId(x,y-1,z);
			if(treeType == 0 && currentBlock == Block.blockSnow.blockID)return true;
			if(treeType == 10 && currentBlock == Block.sand.blockID)return true;
			if(treeType == 12 && currentBlock == Block.waterStill.blockID)return true;
			if(currentBlock == Block.grass.blockID || currentBlock == Block.dirt.blockID)return true;
		}
		return false;
	}

	@Override
	public void prePlant(World world, int x, int y, int z, ItemStack stack) {
	}

	@Override
	public void postPlant(World world, int x, int y, int z, ItemStack stack) {
	}
}












