package highlands.block;

import highlands.api.HighlandsBlocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHLPlankSlab extends BlockSlab
{
	private Block modelBlock;

	public BlockHLPlankSlab(boolean isdouble, Material mat)
    {
		//TODO-                  correct material?
        super(isdouble, Material.field_151567_E);
        //TODO- setCreativeTab
        this.func_149647_a(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    //TODO-      getIcon
    public IIcon func_149691_a(int par1, int par2)
    {
    	//TODO- modelBlock is suspicious...
        return modelBlock.func_149691_a(par1, par2 & 7);
    }

    /**
     * Returns the ID of the items to drop on destruction.  Unfortunately can't make this use modelblock.
     */
    public Block idDropped(int par1, Random par2Random, int par3)
    {
        return HighlandsBlocks.hlplankhalf;
    }
    
    /*
     * attempt to get it to make double slabs like vanilla slabs do
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	ItemStack current = player.getCurrentEquippedItem();
    	int meta = par1World.getBlockMetadata(par2, par3, par4);
    	if(current != null && current == new ItemStack(this) && !this.isDoubleSlab
    			&& current.getItemDamage() == meta){
    		par1World.setBlock(par2, par3, par4, HighlandsBlocks.hlplankhalfdouble.blockID, meta, 3);
    		return true;
    	}
    	return false;
    }
    */

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     * 
     * Unfortunately can't make this use modelblock.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(HighlandsBlocks.hlplankhalf, 2, par1 & 7);
    }

    /**
     * Returns the slab block name with step type.
     */
	@Override
	//TODO-       getFullSlabName
	public String func_150002_b(int var1) {
		//TODO-      getUnlocalizedName
		return super.func_149641_N() + "." + var1;
	}
	
    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(Block par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	//TODO-   isDoubleSlab
        if (!this.field_150004_a)
        {
            for (int j = 0; j < 4; ++j)
            {
                par3List.add(new ItemStack(par1, 1, j));
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IIconRegister par1IconRegister) {}


}
