package highlands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.layer.GenLayerHL;

import net.minecraft.block.Block;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;
import net.minecraftforge.event.terraingen.WorldTypeEvent.BiomeSize;
import net.minecraftforge.event.terraingen.WorldTypeEvent.InitBiomeGens;
import net.minecraftforge.event.world.WorldEvent.Load;

public class HighlandsEventManager {

	private Random rand = new Random();
	
	//allows get wood achievement for Highlands woods
	@ForgeSubscribe
	public void onItemPickupWood(EntityItemPickupEvent e){
		if (e.item.getEntityItem().itemID == HighlandsBlocks.firWood.blockID
				|| e.item.getEntityItem().itemID == HighlandsBlocks.acaciaWood.blockID
				|| e.item.getEntityItem().itemID == HighlandsBlocks.redwoodWood.blockID
				|| e.item.getEntityItem().itemID == HighlandsBlocks.poplarWood.blockID
				|| e.item.getEntityItem().itemID == HighlandsBlocks.canopyWood.blockID
				|| e.item.getEntityItem().itemID == HighlandsBlocks.ironWood.blockID
				|| e.item.getEntityItem().itemID == HighlandsBlocks.mangroveWood.blockID
				|| e.item.getEntityItem().itemID == HighlandsBlocks.ashWood.blockID){
			e.entityPlayer.triggerAchievement(AchievementList.mineWood);
		}
	}
	
	
	// Adds village spawning to Highlands worlds and default worlds if Highlands is enabled.
	@ForgeSubscribe
	public void onWorldStart(Load e){
		if(e.world.provider.terrainType == HighlandsMain.HL || e.world.provider.terrainType == HighlandsMain.HLLB || HighlandsMain.highlandsInDefaultFlag){
			ArrayList<BiomeGenBase> newTotalVillageBiomes = new ArrayList<BiomeGenBase>();
			newTotalVillageBiomes.addAll(MapGenVillage.villageSpawnBiomes);
			newTotalVillageBiomes.addAll(HighlandsMain.hlvillagebiomes);
			
			MapGenVillage.villageSpawnBiomes = newTotalVillageBiomes;
		}
		
		// loads compatibility mob lists for all biomes
		if(HighlandsMain.mocreaturescomp)HighlandsCompatibilityManager.mobload_biomes();
		
		//System.out.println(MapGenVillage.villageSpawnBiomes);
	}
	
	// Sets biome size for Highlands Large Biomes
	@ForgeSubscribe
	public void onLoadWorldType(BiomeSize e){
		if(e.worldType == HighlandsMain.HL){
			e.newSize = (byte)HighlandsMain.HighlandsBiomeSizeDefault;
		}
		if(e.worldType == HighlandsMain.HLLB){
			e.newSize = (byte)HighlandsMain.HighlandsBiomeSizeLB;
		}
	}
	
	// Initiates the new GenLayers
	@ForgeSubscribe
	public void onInitBiomeGenerators(InitBiomeGens e){
		if(e.worldType == HighlandsMain.HL || e.worldType == HighlandsMain.HLLB || HighlandsMain.highlandsInDefaultFlag){
			//this initiates the new gen layers (hills, shore, island).
			if(HighlandsMain.useGenLayers){
				e.newBiomeGens = GenLayerHL.initializeAllBiomeGenerators(e.seed, e.worldType);
			}
    		//System.out.println("Highlands initialized biome generators.");
		}
	}
	
	/*
	// Prevents lakes from generating in Highlands worlds
	@ForgeSubscribe
	public void onDecorateLakes(Decorate e){
		if(e.type == Decorate.EventType.LAKE && 
				(e.world.provider.terrainType == HighlandsMain.HL || e.world.provider.terrainType == HighlandsMain.HLLB || HighlandsMain.highlandsInDefaultFlag)){
			e.setResult(Event.Result.DENY);
			//System.out.println("Stopped a tiny pond from generating");
		}
	}
	*/
	
	// Prevents populate lakes from generating in Highlands worlds-
	// Biomes that don't decorate lakes actually won't have any.
	@ForgeSubscribe
	public void onDecorateLakes2(Populate e){
		if(e.type == Populate.EventType.LAKE && 
				(e.world.provider.terrainType == HighlandsMain.HL || e.world.provider.terrainType == HighlandsMain.HLLB || HighlandsMain.highlandsInDefaultFlag)){
			e.setResult(Event.Result.DENY);
			//System.out.println("Stopped a tiny pond from generating");
		}
	}
	
	
	// sets default village blocks
	@SideOnly(Side.CLIENT)
	
	@ForgeSubscribe
	public void onVillageSelectBlock(GetVillageBlockID e){
		if (e.biome.biomeName.equals(HighlandsBiomes.sahel.biomeName) || e.biome.biomeName.equals(HighlandsBiomes.outback.biomeName))
        {
			if (e.original == Block.wood.blockID)e.replacement = Block.wood.blockID;
            if (e.original == Block.cobblestone.blockID)e.replacement = Block.sandStone.blockID;
            if (e.original == Block.planks.blockID)e.replacement = Block.planks.blockID;
            if (e.original == Block.stairsWoodOak.blockID)e.replacement = Block.stairsWoodOak.blockID;
            if (e.original == Block.stairsCobblestone.blockID)e.replacement = Block.stairsSandStone.blockID;
            if (e.original == Block.gravel.blockID)e.replacement = Block.gravel.blockID;
        }
		if (e.biome.biomeName.equals(BiomeGenBase.icePlains.biomeName))
        {
            if (e.original == Block.wood.blockID)e.replacement = Block.wood.blockID;
            if (e.original == Block.cobblestone.blockID)e.replacement = Block.cobblestone.blockID;
            if (e.original == Block.planks.blockID)e.replacement = Block.blockSnow.blockID;
            if (e.original == Block.stairsWoodOak.blockID)e.replacement = Block.stairsWoodOak.blockID;
            if (e.original == Block.stairsCobblestone.blockID)e.replacement = Block.stairsCobblestone.blockID;
            if (e.original == Block.gravel.blockID)e.replacement = Block.gravel.blockID;
        }
	}
}




