package highlands;

import highlands.block.BlockHighlandsSapling;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class HighlandsFuelHandler implements IFuelHandler
{
    //Give burning time for saplings (all wooden blocks already have a default burning time)
    @Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem() instanceof ItemBlock && Block.getBlockFromItem(fuel.getItem()) instanceof BlockHighlandsSapling)
            return 100;
		return 0;
	}
}