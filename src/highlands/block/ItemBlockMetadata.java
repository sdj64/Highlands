package highlands.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockMetadata extends ItemBlockWithMetadata
{
    public ItemBlockMetadata(Block par1)
    {
        super(par1, par1);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
    	return getUnlocalizedName() + stack.getItemDamage();
    }
}
