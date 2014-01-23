package highlands.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockMetadata extends ItemBlock
{

    public ItemBlockMetadata(Block par1)
    {
        super(par1);
        setHasSubtypes(true);
    }
    
    public String getUnlocalizedName(ItemStack stack){
    	return getUnlocalizedName() + "_" + stack.getItemDamage();
    }
    
    public int getMetadata(int par1){
    	return par1;
    }
    
    //TODO- fix me! fix me!
//    public IIcon getIconFromDamage(int par1){
//    	if(this.itemID < 4096 && Block.blocksList[this.itemID].getRenderType() != 0)
//    		return Block.blocksList[this.itemID].getIcon(0, par1);
//    	else return super.getIconFromDamage(par1);
//    }
}
