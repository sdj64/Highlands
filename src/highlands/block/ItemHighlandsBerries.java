package highlands.block;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemHighlandsBerries extends ItemFood{

	public ItemHighlandsBerries() {
		super(2, false);//1 whole porkchop (hunger), wolves don't eat it, default saturation (0.6F).
		this.setCreativeTab(CreativeTabs.tabFood);
        this.setTextureName("Highlands:itemBerries");
	}

	/**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 16;
    }
}
