package highlands.block;

import highlands.api.HighlandsBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import net.minecraftforge.event.entity.player.PlayerEvent.HarvestCheck;
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
    	//TODO- Material.leaves
        super(Material.field_151584_j, false);
        this.func_149675_a(true);
        this.treeType = type;
        this.func_149713_g(1);
        //TODO- setStepSound
        this.func_149672_a(field_149779_h);
        //TODO-setCreativeTab
        this.func_149647_a(CreativeTabs.tabDecorations);
        //TODO- setBurnProperties (fix this)
        
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
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    //TODO-     breakBlock
    public void func_149749_a(World par1World, int par2, int par3, int par4, int par5, int par6)
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
                    	//TODO- getBlock
                        Block j2 = par1World.func_147439_a(par2 + k1, par3 + l1, par4 + i2);

                        if (j2.isLeaves(par1World, par2 + k1, par3 + l1, par4 + i2))
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
    //TODO-     updateTick
    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random)
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
                            	//TODO-                 getBlock
                                Block block = par1World.func_147439_a(par2 + l1, par3 + i2, par4 + j2);

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
    
    @SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    //TODO-     randomDisplayTick
    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	super.func_149734_b(par1World, par2, par3, par4, par5Random);
    	//TODO-                                                                doesBlockHaveSolidTopSurface
        if (par1World.canLightningStrikeAt(par2, par3 + 1, par4) && !par1World.func_147466_a(par1World, par2, par3 - 1, par4) && par5Random.nextInt(15) == 1)
        {
            double d0 = (double)((float)par2 + par5Random.nextFloat());
            double d1 = (double)par3 - 0.05D;
            double d2 = (double)((float)par4 + par5Random.nextFloat());
            par1World.spawnParticle("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    private void removeLeaves(World par1World, int par2, int par3, int par4)
    {
    	//TODO- dropBlockAsItem
        this.func_149697_b(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
        //TODO-   setBlockToAir
        par1World.func_147468_f(par2, par3, par4);
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
    //TODO-      idDropped
    public Item func_149650_a(int par1, Random par2Random, int par3)
    {
    	//TODO-                      magicMumboJumboThatMakesThisWork
    	if(treeType == 0)return Item.func_150898_a(HighlandsBlocks.firSapling);
    	if(treeType == 1)return Item.func_150898_a(HighlandsBlocks.acaciaSapling);
    	if(treeType == 2)return Item.func_150898_a(HighlandsBlocks.poplarSapling);
    	if(treeType == 3)return Item.func_150898_a(HighlandsBlocks.redwoodSapling);
    	if(treeType == 4)return Item.func_150898_a(HighlandsBlocks.canopySapling);
    	if(treeType == 10)return Item.func_150898_a(HighlandsBlocks.palmSapling);
    	if(treeType == 11)return Item.func_150898_a(HighlandsBlocks.ironwoodSapling);
    	if(treeType == 12)return Item.func_150898_a(HighlandsBlocks.mangroveSapling);
    	if(treeType == 13)return Item.func_150898_a(HighlandsBlocks.ashSapling);
    	if(treeType == 14)return Item.func_150898_a(HighlandsBlocks.autumnOrangeSapling);
    	if(treeType == 15)return Item.func_150898_a(HighlandsBlocks.autumnYellowSapling);
        return Item.func_150898_a(Blocks.sapling);//ADD DIFFERENT SAPLINGS
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    //TODO-     dropBlockAsItemWithChance
    //I dunno if I did this right, we will see once it compiles
    public void func_149690_a(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
    	if (!par1World.isRemote)
        {
            int j1 = this.func_150123_b(par5);

            if (par7 > 0)
            {
                j1 -= 2 << par7;

                if (j1 < 10)
                {
                    j1 = 10;
                }
            }

            if (par1World.rand.nextInt(j1) == 0)
            {
                Item item = this.func_149650_a(par5, par1World.rand, par7);
                this.func_149642_a(par1World, par2, par3, par4, new ItemStack(item, 1, this.func_149692_a(par5)));
            }

            j1 = 200;

            if (par7 > 0)
            {
                j1 -= 10 << par7;

                if (j1 < 40)
                {
                    j1 = 40;
                }
            }

            this.func_150124_c(par1World, par2, par3, par4, par5, j1);
        }
    }
    
    //TODO-        leavesWeirdness
    protected void func_150124_c(World p_150124_1_, int p_150124_2_, int p_150124_3_, int p_150124_4_, int p_150124_5_, int p_150124_6_) {}
    
    //TODO-       getRenderType
    protected int func_150123_b(int p_150123_1_)
    {
        return 20;
    }
    
    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    //TODO-     harvestBlock
    public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        super.func_149636_a(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return 0;
    }
    
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    //TODO-        isOpaqueCube
    public boolean func_149662_c()
    {
        return !this.field_150121_P;
    }
    
    @SideOnly(Side.CLIENT)
    /**
     * Pass true to draw this block using fancy graphics, or false for fast graphics.
     */
    //TODO-     setGraphicsLevel
    public void func_150122_b(boolean par1)
    {
        this.field_150121_P = par1;
    }
    
    /**
     * Called when a user uses the creative pick block button on this block
     *
     * @param target The full target the player is looking at
     * @return A ItemStack to add to the player's inventory, Null if nothing should be added.
     */
    //TODO- do we even use this anymore?
//    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
//    {
//    	//TODO-       idPicked?
//    	int id = this.func_149643_k(world, x, y, z);
//
//        if (id == 0)
//        {
//            return null;
//        }
//
//        Item item = Item.itemsList[id];
//        if (item == null)
//        {
//            return null;
//        }
//
//        return new ItemStack(id, 1, 0);
//        //return new ItemStack(this.blockID, 1, 0);
//    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    //TODO-      getBlockTextureFromSideAndMetadata or getIcon
    public IIcon func_149691_a(int par1, int par2)
    {
         if (Minecraft.getMinecraft().gameSettings.fancyGraphics)
         {
        	 return textureFancy;
         }
         else
         {
        	 return textureFast;
         }
    }
    
    //TODO- Better Leaves is not for 1.7.x yet, will update when it is.
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
//
//    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    //TODO-     getSubBlocks
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    //TODO-   correct fix?
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this, 1, 0);
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IIconRegister par1IconRegister)
    {
        textureFancy = par1IconRegister.registerIcon("Highlands:leaves"+treeNames[treeType]);
        textureFast = par1IconRegister.registerIcon("Highlands:leaves"+treeNames[treeType]+"Fast");
    }
    
    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
    {
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

}
