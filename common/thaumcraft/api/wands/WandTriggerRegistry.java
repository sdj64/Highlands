package thaumcraft.api.wands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * This class serves a similar function to IWandable in that it allows wands to interact
 * with object in the world. In this case it is most useful for adding interaction with non-mod
 * blocks where you can't control what happens in their code.
 * Example where it is used is in crafting the thaumonomicon from a bookshelf and the
 * crucible from a cauldron
 * 
 * @author azanor
 *
 */
public class WandTriggerRegistry {

	/**
	 * Registers an action to perform when a casting wand right clicks on a specific block. 
	 * A manager class needs to be created that implements IWandTriggerManager.
	 * @param manager
	 * @param event a logical number that you can use to differentiate different events or actions
	 * @param blockid
	 * @param meta send -1 as a wildcard value for all possible meta values
	 */
	public static void registerWandBlockTrigger(IWandTriggerManager manager, int event, int blockid, int meta) {
		triggers.put(Arrays.asList(blockid,meta),
				Arrays.asList(manager,event));
		
	}
	
	private static HashMap<List<Integer>,List> triggers = new  HashMap<List<Integer>,List>();
	
	public static boolean hasTrigger(int blockid, int meta) {
		if (triggers.containsKey(Arrays.asList(blockid,meta)) ||
			triggers.containsKey(Arrays.asList(blockid,-1))) return true;
		return false;
	}
	
	/**
	 * This is called by the onItemUseFirst function in wands. 
	 * Parameters and return value functions like you would expect for that function.
	 * @param world
	 * @param wand
	 * @param player
	 * @param x
	 * @param y
	 * @param z
	 * @param side
	 * @param blockid
	 * @param meta
	 * @return
	 */
	public static boolean performTrigger(World world, ItemStack wand, EntityPlayer player, 
			int x, int y, int z, int side, int blockid, int meta) {
				
		List l = triggers.get(Arrays.asList(blockid,meta));
		if (l==null) l = triggers.get(Arrays.asList(blockid,-1));
		if (l==null) return false;
		
		IWandTriggerManager manager = (IWandTriggerManager) l.get(0);
		int event = (Integer) l.get(1);
		return manager.performTrigger(world, wand, player, x, y, z, side, event);
	}
	
}
