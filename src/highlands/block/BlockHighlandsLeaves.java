package highlands.block;

import highlands.api.HighlandsBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHighlandsLeaves extends BlockLeavesBase implements IShearable
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
    
    int[] adjacentTreeBlocks;

    public BlockHighlandsLeaves(int type)
    {
        super(Material.leaves, false);
        this.setTickRandomly(true);
        this.treeType = type;
        setLightOpacity(1);
        this.setCreativeTab(CreativeTabs.tabDecorations);
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
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
    {
        byte b0 = 1;
        int j1 = b0 + 1;

        if (par1World.checkChunksExist(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1))
        {
            for (int k1 = -b0; k1 <= b0; ++k1)
            {
                for (int l1 = -b0; l1 <= b0; ++l1)
                {
                    for (int i2 = -b0; i2 <= b0; ++i2)
                    {
                        Block j2 = par1World.getBlock(par2 + k1, par3 + l1, par4 + i2);

                        if (j2 != null)
                        {
                            j2.beginLeavesDecay(par1World, par2 + k1, par3 + l1, par4 + i2);
                        }
                    }
                }
            }
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            int l = par1World.getBlockMetadata(par2, par3, par4);

            if ((l & 8) != 0 && (l & 4) == 0)
            {
                byte b0 = 4;
                int i1 = b0 + 1;
                byte b1 = 32;
                int j1 = b1 * b1;
                int k1 = b1 / 2;

                if (this.adjacentTreeBlocks == null)
                {
                    this.adjacentTreeBlocks = new int[b1 * b1 * b1];
                }

                int l1;

                if (par1World.checkChunksExist(par2 - i1, par3 - i1, par4 - i1, par2 + i1, par3 + i1, par4 + i1))
                {
                    int i2;
                    int j2;
                    int k2;

                    for (l1 = -b0; l1 <= b0; ++l1)
                    {
                        for (i2 = -b0; i2 <= b0; ++i2)
                        {
                            for (j2 = -b0; j2 <= b0; ++j2)
                            {
                                Block block = par1World.getBlock(par2 + l1, par3 + i2, par4 + j2);

                                if (block != null && block.canSustainLeaves(par1World, par2 + l1, par3 + i2, par4 + j2))
                                {
                                    this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
                                }
                                else if (block != null && block.isLeaves(par1World, par2 + l1, par3 + i2, par4 + j2))
                                {
                                    this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
                                }
                                else
                                {
                                    this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
                                }
                            }
                        }
                    }

                    for (l1 = 1; l1 <= 4; ++l1)
                    {
                        for (i2 = -b0; i2 <= b0; ++i2)
                        {
                            for (j2 = -b0; j2 <= b0; ++j2)
                            {
                                for (k2 = -b0; k2 <= b0; ++k2)
                                {
                                    if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1)
                                    {
                                        if (this.adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
                                        {
                                            this.adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
                                        {
                                            this.adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2)
                                        {
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2)
                                        {
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2)
                                        {
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
                                        }

                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2)
                                        {
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                l1 = this.adjacentTreeBlocks[k1 * j1 + k1 * b1 + k1];

                if (l1 >= 0)
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, l & -9, 4);
                }
                else
                {
                    this.removeLeaves(par1World, par2, par3, par4);
                }
            }
        }
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
        if (par1World.canLightningStrikeAt(par2, par3 + 1, par4) && !World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4) && par5Random.nextInt(15) == 1)
        {
            double d0 = (double)((float)par2 + par5Random.nextFloat());
            double d1 = (double)par3 - 0.05D;
            double d2 = (double)((float)par4 + par5Random.nextFloat());
            par1World.spawnParticle("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
    
    private void removeLeaves(World par1World, int par2, int par3, int par4)
    {
        this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
        par1World.setBlockToAir(par2, par3, par4);
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
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    @Override
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int par1)
    {
        return 0;
    }
    
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube()
    {
        return !this.field_150121_P;
    }
    
    @SideOnly(Side.CLIENT)
    /**
     * Pass true to draw this block using fancy graphics, or false for fast graphics.
     */
    public void setGraphicsLevel(boolean par1)
    {
        this.field_150121_P = par1;
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
	public boolean isShearable(ItemStack item, IBlockAccess world, int x,
			int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world,
			int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, 0));
        return ret;
	}
    
    @Override
    public void beginLeavesDecay(World world, int x, int y, int z)
    {
        world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
    }

    @Override
    public boolean isLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
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
