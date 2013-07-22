package highlands.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import highlands.HighlandsMain;
import highlands.api.HighlandsBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.common.IShearable;

public class BlockHighlandsLeaves extends BlockLeaves implements IShearable
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
    
    private Icon textureFast;
    private Icon textureFancy;
    
    private int treeType;
    
    int[] adjacentTreeBlocks;

    public BlockHighlandsLeaves(int id, int type)
    {
        super(id);
        this.treeType = type;
        setLightOpacity(1);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setBurnProperties(this.blockID, 30, 60);
    }

    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
    	return 16777215;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int par1)
    {
    	return 16777215;
    }
    
    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
    	return 16777215;
    }


    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return par1Random.nextInt(saplingRate[treeType]) == 0 ? 1 : 0;//chance to drop sapling. Change for different trees
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
    	if(treeType == 0)return HighlandsBlocks.firSapling.blockID;
    	if(treeType == 1)return HighlandsBlocks.acaciaSapling.blockID;
    	if(treeType == 2)return HighlandsBlocks.poplarSapling.blockID;
    	if(treeType == 3)return HighlandsBlocks.redwoodSapling.blockID;
    	if(treeType == 4)return HighlandsBlocks.canopySapling.blockID;
    	if(treeType == 10)return HighlandsBlocks.palmSapling.blockID;
    	if(treeType == 11)return HighlandsBlocks.ironwoodSapling.blockID;
    	if(treeType == 12)return HighlandsBlocks.mangroveSapling.blockID;
    	if(treeType == 13)return HighlandsBlocks.ashSapling.blockID;
    	if(treeType == 14)return HighlandsBlocks.autumnOrangeSapling.blockID;
    	if(treeType == 15)return HighlandsBlocks.autumnYellowSapling.blockID;
        return Block.sapling.blockID;//ADD DIFFERENT SAPLINGS
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
    	if (!par1World.isRemote)
        {
            ArrayList<ItemStack> items = getBlockDropped(par1World, par2, par3, par4, par5, par7);

            for (ItemStack item : items)
            {
                if (par1World.rand.nextFloat() <= par6)
                {
                    this.dropBlockAsItem_do(par1World, par2, par3, par4, item);
                }
            }
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return 0;
    }
    
    /**
     * Called when a user uses the creative pick block button on this block
     *
     * @param target The full target the player is looking at
     * @return A ItemStack to add to the player's inventory, Null if nothing should be added.
     */
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
    	int id = idPicked(world, x, y, z);

        if (id == 0)
        {
            return null;
        }

        Item item = Item.itemsList[id];
        if (item == null)
        {
            return null;
        }

        return new ItemStack(id, 1, 0);
        //return new ItemStack(this.blockID, 1, 0);
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
    	this.graphicsLevel = Block.leaves.graphicsLevel;
    	
    	if(this.graphicsLevel)return textureFancy;
    	else return textureFast;
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
    	this.graphicsLevel = Block.leaves.graphicsLevel;
    	
    	if(this.graphicsLevel)return textureFancy;
    	else return textureFast;
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 1, 0);
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        textureFancy = par1IconRegister.registerIcon("Highlands:leaves"+treeNames[treeType]);
        textureFast = par1IconRegister.registerIcon("Highlands:leaves"+treeNames[treeType]+"Fast");
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, 0));
        return ret;
    }
}
