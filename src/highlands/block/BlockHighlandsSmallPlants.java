package highlands.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import highlands.worldgen.WorldGenSmallPlants;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.List;

import static net.minecraftforge.common.EnumPlantType.*;

public class BlockHighlandsSmallPlants extends BlockFlower implements IPlantable
{
	private int plantType;
	
	private String[] plantNames = 
		{
			"BlueFlower",
			"GreenLeaf",
			"WhiteFlower",
			"Cattail",
			"Lavender",
			"RaspberryBush",
			"BlueberryBush",
			"Thornbush",
			"Cotton",
		};
	
    public BlockHighlandsSmallPlants(int type)
    {
        super(type);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        plantType = type;
    }
    
    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItemDamage()==15 && player.inventory.getCurrentItem().getItem() == Items.dye)
    	{
	    	//adds random plants of same type to surrounding blocks
	    	new WorldGenSmallPlants(this, 10).generate(par1World, par1World.rand, par2, par3, par4);
	    	
	    	//reduce bonemeal stack size by one
	    	if(!player.capabilities.isCreativeMode)
                player.inventory.getCurrentItem().stackSize--;
		    return true;
    	}
    	return false;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
    	if(plantType == 7) par5Entity.attackEntityFrom(DamageSource.cactus, 1);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return Plains;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
    	this.blockIcon = par1IconRegister.registerIcon("Highlands:plant"+plantNames[plantType]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(item, 1, 0));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return blockIcon;
    }
}
