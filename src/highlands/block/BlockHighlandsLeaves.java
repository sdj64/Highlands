package highlands.block;

import highlands.api.HighlandsBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHighlandsLeaves extends BlockLeaves
{
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
			"AutumnOrange",
			"AutumnYellow",
		};
	
	private int[] saplingRate = {
			30, 20, 20, 40, 20, 0, 0, 0, 0, 0, 12, 75, 15, 25, 20, 20
	};
    
    private IIcon textureFast;
    private IIcon textureFancy;
    
    private int treeType;

    public BlockHighlandsLeaves(int type)
    {
        super();
        this.treeType = type;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
    	return 16777215;
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1)
    {
    	return 16777215;
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    @Override
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
    	return 16777215;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random)
    {
        return par1Random.nextInt(saplingRate[treeType]) == 0 ? 1 : 0;//chance to drop sapling. Change for different trees
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
    	if(treeType == 0)return Item.getItemFromBlock(HighlandsBlocks.firSapling);
    	if(treeType == 1)return Item.getItemFromBlock(HighlandsBlocks.acaciaSapling);
    	if(treeType == 2)return Item.getItemFromBlock(HighlandsBlocks.poplarSapling);
    	if(treeType == 3)return Item.getItemFromBlock(HighlandsBlocks.redwoodSapling);
    	if(treeType == 4)return Item.getItemFromBlock(HighlandsBlocks.canopySapling);
    	if(treeType == 10)return Item.getItemFromBlock(HighlandsBlocks.palmSapling);
    	if(treeType == 11)return Item.getItemFromBlock(HighlandsBlocks.ironwoodSapling);
    	if(treeType == 12)return Item.getItemFromBlock(HighlandsBlocks.mangroveSapling);
    	if(treeType == 13)return Item.getItemFromBlock(HighlandsBlocks.ashSapling);
    	if(treeType == 14)return Item.getItemFromBlock(HighlandsBlocks.autumnOrangeSapling);
    	if(treeType == 15)return Item.getItemFromBlock(HighlandsBlocks.autumnYellowSapling);
        return Item.getItemFromBlock(Blocks.sapling);//ADD DIFFERENT SAPLINGS
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
    	//TODO- right fix?
    	if (!par1World.isRemote)
        {
            Item item = getItemDropped(par5, par1World.rand, par7);
            if (par1World.rand.nextFloat() <= par6)
            {
                this.dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(item, 1, this.damageDropped(par5)));
            }
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int par1)
    {
        return 0;
    }

    //TODO- Better Leaves is not 1.7.x
//    //BETTER LEAVES METHOD
//    public Icon getIconFallingLeaves(int metadata) {
//        return textureFast/* Your default opaque (fast) texture for the given metadata. */;
//    }
//    
//    //BETTER LEAVES METHOD
//    public float getSpawnChanceFallingLeaves(int metadata) {
//
//    	  // Return you spawn rate
//    	  // (0.0F = 0%; 1.0F = 100%; default is 0.008F)
//    	  return (this.treeType == 14 || this.treeType == 15) ? 0.06F : 0.008F;
//    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int par1, int par2)
    {
    	this.field_150121_P = Minecraft.isFancyGraphicsEnabled();
    	
    	if(this.field_150121_P)
            return textureFancy;
    	else
            return textureFast;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    @Override
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this, 1, 0);
    }

    @Override
    public String[] func_150125_e() {
        return treeNames;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        textureFancy = par1IconRegister.registerIcon("Highlands:leaves"+treeNames[treeType]);
        textureFast = par1IconRegister.registerIcon("Highlands:leaves"+treeNames[treeType]+"Fast");
    }

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, 0));
        return ret;
	}
    
    @Override
    public void beginLeavesDecay(World world, int x, int y, int z)
    {
        world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
    }

    //TODO- MFR isn't 1.7.x yet
//	//// MFR : IFactoryHarvestable
//	@Override
//	public int getPlantId() {
//		return blockID;
//	}
//
//	@Override
//	public HarvestType getHarvestType() {
//		return HarvestType.TreeLeaf;
//	}
//
//	@Override
//	public boolean breakBlock() {
//		return true;
//	}
//
//	@Override
//	public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
//		return true;
//	}
//
//	@Override
//	public List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
//		List<ItemStack> prod = new ArrayList<ItemStack>();
//
//		if (harvesterSettings.get("silkTouch") != null && harvesterSettings.get("silkTouch")) {
//			prod.add( new ItemStack(blockID, 1, 0) );
//			return prod;
//		}
//		return Block.blocksList[ world.getBlockId(x,y,z) ].getBlockDropped(world, x,y,z, world.getBlockMetadata(x,y,z), 0);
//	}
//
//	@Override
//	public void preHarvest(World world, int x, int y, int z) {
//	}
//
//	@Override
//	public void postHarvest(World world, int x, int y, int z) {
//	}


}
