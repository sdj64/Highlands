package highlands.block;

import highlands.api.HighlandsBlocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockHLPlankSlab extends BlockSlab {

	private Block modelBlock;
	
	public BlockHLPlankSlab(boolean isDouble, Block model) {
		super(isDouble, model.getMaterial());
		modelBlock = model;
		if (!isDouble) this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		return modelBlock.getIcon(par1, par2 & 7);
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(this);
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int i)
    {
        return new ItemStack(Item.getItemFromBlock(HighlandsBlocks.hlplankhalf), 2, i & 7);
    }

    public String func_150002_b(int metadata)
    {
        if (metadata < 0 || metadata >= BlockHighlandsPlanks.woodType.length)
        {
            metadata = 0;
        }

        return super.getUnlocalizedName() + "." + BlockHighlandsPlanks.woodType[metadata];
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        if (item != Item.getItemFromBlock(Blocks.double_wooden_slab))
        {
            for (int i = 0; i < BlockHighlandsPlanks.woodType.length; ++i)
            {
                list.add(new ItemStack(item, 1, i));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {}
}
