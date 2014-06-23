package highlands.block;

import highlands.api.HighlandsBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemSlabPlanks extends ItemSlab
{
    public ItemSlabPlanks(Block block)
    {
        super(block, (BlockSlab)HighlandsBlocks.hlplankhalf, (BlockSlab)HighlandsBlocks.hlplankhalfdouble, false);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
}
