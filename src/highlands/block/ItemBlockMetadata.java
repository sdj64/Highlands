package highlands.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBlockMetadata extends ItemBlock
{

    public ItemBlockMetadata(Block par1)
    {
        super(par1);
        setHasSubtypes(true);
    }
    
//    public String getUnlocalizedName(ItemStack stack){
//    	//return getUnlocalizedName() + "_" + stack.getItemDamage();
//    }
    
    public int getMetadata(int par1){
    	return par1;
    }
    
    //TODO- broke all the things
//    public IIcon getIconFromDamage(int par1){
//    	if(this.getIdFromItem(getContainerItem()) < 4096 && this != 0)
//    		return this.getIcon(new ItemStack(Blocks.air), par1);
//    	else return super.getIconFromDamage(par1);
//    }
}
