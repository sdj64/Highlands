package highlands.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemHighlandsBerries extends ItemFood{

	public ItemHighlandsBerries(int id) {
		super(id, 2, false);//id, 1 whole porkchop (hunger), wolves don't eat it.
		this.setCreativeTab(CreativeTabs.tabFood);
	}

	/**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 16;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("Highlands:itemBerries");
    }
}
