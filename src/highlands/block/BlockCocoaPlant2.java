package highlands.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.Level;

import highlands.Highlands;
import highlands.Logs;
import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCocoaPlant2 extends BlockCocoa
{
    public BlockCocoaPlant2()
    {
        super();
        this.setBlockTextureName("cocoa");
    }
    
    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        int l = getDirection(par1World.getBlockMetadata(par2, par3, par4));
        par2 += Direction.offsetX[l];
        par4 += Direction.offsetZ[l];
        Block log = par1World.getBlock(par2, par3, par4);
        Logs.log(Level.INFO, "BlockCocoaPlant2 attached to "+log);
        //TODO-                              limitToValidMetadata
        return (log == Blocks.log && BlockLog.func_150165_c(par1World.getBlockMetadata(par2, par3, par4)) == 3)
        		|| (log == HighlandsBlocks.palmWood);
    }
}
