package com.sdj64.highlands;

import com.sdj64.highlands.block.HighlandsBlocks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class HighlandsRecipes {

	
	public static void init(){
		
		for(int i = 0; i < HighlandsBlocks.NUM_TREE_TYPES; i++){
			if(HighlandsBlocks.planks[i] != null && HighlandsBlocks.woods[i] != null)
				//Bamboo stem only crafts into one plank
				if(i == HighlandsBlocks.EnumTypeTree.BAMBOO.getMetadata())
					GameRegistry.addShapelessRecipe(new ItemStack(HighlandsBlocks.planks[i], 1), new ItemStack(HighlandsBlocks.woods[i]));
				else
					GameRegistry.addShapelessRecipe(new ItemStack(HighlandsBlocks.planks[i], 4), new ItemStack(HighlandsBlocks.woods[i]));
		}
	}
}
