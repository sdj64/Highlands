package highlands.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;

import highlands.HighlandsMain;
import highlands.api.HighlandsBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCocoaPlant2 extends BlockCocoa
{
    public BlockCocoaPlant2(int par1)
    {
        super(par1);
    }
    
    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        int l = getDirection(par1World.getBlockMetadata(par2, par3, par4));
        par2 += Direction.offsetX[l];
        par4 += Direction.offsetZ[l];
        int i1 = par1World.getBlockId(par2, par3, par4);
        return (i1 == Block.wood.blockID && BlockLog.limitToValidMetadata(par1World.getBlockMetadata(par2, par3, par4)) == 3)
        		|| (i1 == HighlandsBlocks.palmWood.blockID);
    }
}
