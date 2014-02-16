package highlands;

import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class HighlandsFuelHandler implements IFuelHandler
{
	public int getBurnTime(ItemStack fuel) {
		
		int id = fuel.getItem().itemID;
		
		if(id == HighlandsBlocks.firWood.blockID)return 300;
		if(id == HighlandsBlocks.acaciaWood.blockID)return 300;
		if(id == HighlandsBlocks.redwoodWood.blockID)return 300;
		if(id == HighlandsBlocks.canopyWood.blockID)return 300;
		if(id == HighlandsBlocks.poplarWood.blockID)return 300;
		if(id == HighlandsBlocks.mangroveWood.blockID)return 300;
		if(id == HighlandsBlocks.ashWood.blockID)return 300;
		if(id == HighlandsBlocks.palmWood.blockID)return 300;
		if(id == HighlandsBlocks.ironWood.blockID)return 300;
		
		if(id == HighlandsBlocks.hlplanks.blockID)return 300;
		if(id == HighlandsBlocks.hlplankstairs0.blockID)return 300;
		if(id == HighlandsBlocks.hlplankstairs1.blockID)return 300;
		if(id == HighlandsBlocks.hlplankstairs2.blockID)return 300;
		if(id == HighlandsBlocks.hlplankstairs3.blockID)return 300;
		if(id == HighlandsBlocks.hlplankhalf.blockID)return 150;
		
		if(id == HighlandsBlocks.firSapling.blockID)return 100;
	    if(id == HighlandsBlocks.acaciaSapling.blockID)return 100;
	    if(id == HighlandsBlocks.redwoodSapling.blockID)return 100;
	    if(id == HighlandsBlocks.poplarSapling.blockID)return 100;
	    if(id == HighlandsBlocks.canopySapling.blockID)return 100;
	    if(id == HighlandsBlocks.greatOakSapling.blockID)return 100;
	    if(id == HighlandsBlocks.beechSapling.blockID)return 100;
	    if(id == HighlandsBlocks.deadSapling.blockID)return 100;
	    if(id == HighlandsBlocks.evergreenBushSapling.blockID)return 100;
	    if(id == HighlandsBlocks.deciduousBushSapling.blockID)return 100;
	    if(id == HighlandsBlocks.palmSapling.blockID)return 100;
	    if(id == HighlandsBlocks.ironwoodSapling.blockID)return 100;
	    if(id == HighlandsBlocks.mangroveSapling.blockID)return 100;
	    if(id == HighlandsBlocks.ashSapling.blockID)return 100;
	    if(id == HighlandsBlocks.autumnYellowSapling.blockID)return 100;
	    if(id == HighlandsBlocks.autumnOrangeSapling.blockID)return 100;
		
		
		return 0;
	}
}