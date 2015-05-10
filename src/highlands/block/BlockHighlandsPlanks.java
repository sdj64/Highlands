package highlands.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockHighlandsPlanks extends Block
{
    /** The type of tree this block came from. */
    public static final String[] woodType = new String[] {"Yellow", "White", "Red", "Grey"};
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

    public BlockHighlandsPlanks()
    {
        super(Material.wood);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 >= this.iconArray.length)
        {
            par2 = 0;
        }

        return this.iconArray[par2];
    }
    
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int par1)
    {
        return par1;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for(int i=0; i<woodType.length; i++){
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.iconArray = new IIcon[woodType.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon("Highlands:plank" + woodType[i]);
        }
    }
}
