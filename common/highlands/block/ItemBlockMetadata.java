package highlands.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemBlockMetadata extends ItemBlock
{

    public ItemBlockMetadata(int par1)
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
    
    public Icon getIconFromDamage(int par1){
    	if(this.itemID < 4096 && Block.blocksList[this.itemID].getRenderType() != 0)
    		return Block.blocksList[this.itemID].getIcon(0, par1);
    	else return super.getIconFromDamage(par1);
    }
}
