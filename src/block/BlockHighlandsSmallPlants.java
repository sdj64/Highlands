package highlands.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import highlands.worldgen.WorldGenSmallPlants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
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
    
    
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	//TODO- all this ItemStack stuff, again, worries me
    	if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItemDamage()==15 && player.inventory.getCurrentItem() == new ItemStack(Items.dye))
    	{
	    	Random rand = new Random();
	    	//adds random plants of same type to surrounding blocks
	    	new WorldGenSmallPlants(this, 10).generate(par1World, new Random(), par2, par3, par4);
	    	
	    	//reduce bonemeal stack size by one
	    	if(player.capabilities.isCreativeMode != true)player.inventory.getCurrentItem().stackSize--;
		    return true;
    	}
    	return false;
    }
    
    
    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
    	if(plantType == 7) par5Entity.attackEntityFrom(DamageSource.cactus, 1);
    }

    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
        return Plains;
    }
    
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
    	this.blockIcon = par1IconRegister.registerIcon("Highlands:plant"+plantNames[plantType]);
    }
    
	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		list.add(new ItemStack(block, 1, plantType));
	}
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.blockIcon;
	}
    
}
