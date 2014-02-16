package highlands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import highlands.block.BlockHighlandsSapling;
import highlands.integration.HighlandsCompatibilityManager;
import highlands.worldgen.layer.GenLayerHL;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;
import net.minecraftforge.event.terraingen.WorldTypeEvent.BiomeSize;
import net.minecraftforge.event.terraingen.WorldTypeEvent.InitBiomeGens;
import net.minecraftforge.event.world.WorldEvent.Load;

public class HighlandsEventManager {

	private Random rand = new Random();
	
	//allows get wood achievement for Highlands woods
	@SubscribeEvent
	public void onItemPickupWood(EntityItemPickupEvent e){
		if (e.item.getEntityItem() == new ItemStack(HighlandsBlocks.firWood)
				|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.acaciaWood)
				|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.redwoodWood)
				|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.poplarWood)
				|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.canopyWood)
				|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.ironWood)
				|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.mangroveWood)
				|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.ashWood)){
			e.entityPlayer.triggerAchievement(AchievementList.mineWood);
		}
	}
	
	
	// Adds village spawning to Highlands worlds and default worlds if Highlands is enabled.
    @SubscribeEvent
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
    @SubscribeEvent
	public void onLoadWorldType(BiomeSize e){
		if(e.worldType == HighlandsMain.HL){
			e.newSize = (byte)HighlandsMain.HighlandsBiomeSizeDefault;
		}
		if(e.worldType == HighlandsMain.HLLB){
			e.newSize = (byte)HighlandsMain.HighlandsBiomeSizeLB;
		}
	}
	
	// Initiates the new GenLayers
    @SubscribeEvent
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
    @SubscribeEvent
	public void onDecorateLakes2(Populate e){
		if(e.type == Populate.EventType.LAKE && 
				(e.world.provider.terrainType == HighlandsMain.HL || e.world.provider.terrainType == HighlandsMain.HLLB || HighlandsMain.highlandsInDefaultFlag)){
			e.setResult(Event.Result.DENY);
			//System.out.println("Stopped a tiny pond from generating");
		}
	}
	
    @SubscribeEvent
    public void bonemealEvent(BonemealEvent e)
    {
        if (!e.world.isRemote)
        {
        	boolean isHLSapling = false;
        	for(Block b: HighlandsBlocks.saplings)if(b != null && e.block == b)isHLSapling = true;
            if (isHLSapling)
            {
            	//TODO- questionable fix
                BlockHighlandsSapling sapling = (BlockHighlandsSapling)Block.blockRegistry.getObject(e.block);
                e.setResult(Event.Result.ALLOW);
                if(e.entityPlayer.capabilities.isCreativeMode)
                	sapling.growTree(e.world, e.x, e.y, e.z, rand);
                else 
                	sapling.updateTick(e.world, e.x, e.y, e.z, rand);
            }
        }
    }
	
	
	// sets default village blocks
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onVillageSelectBlock(GetVillageBlockID e){
		if(e.biome != null && HighlandsBiomes.sahel != null && HighlandsBiomes.outback != null && BiomeGenBase.icePlains != null){
			if (e.biome.biomeName.equals(HighlandsBiomes.sahel.biomeName) || e.biome.biomeName.equals(HighlandsBiomes.outback.biomeName))
	        {
				if (e.original == Blocks.log)e.replacement = Blocks.log;
	            if (e.original == Blocks.cobblestone)e.replacement = Blocks.sandstone;
	            if (e.original == Blocks.planks)e.replacement = Blocks.planks;
	            if (e.original == Blocks.oak_stairs)e.replacement = Blocks.oak_stairs;
	            if (e.original == Blocks.stone_stairs)e.replacement = Blocks.sandstone_stairs;
	            if (e.original == Blocks.gravel)e.replacement = Blocks.gravel;
	        }
			if (e.biome.biomeName.equals(BiomeGenBase.icePlains.biomeName))
	        {
	            if (e.original == Blocks.log)e.replacement = Blocks.log;
	            if (e.original == Blocks.cobblestone)e.replacement = Blocks.cobblestone;
	            if (e.original == Blocks.planks)e.replacement = Blocks.snow;
	            if (e.original == Blocks.oak_stairs)e.replacement = Blocks.oak_stairs;
	            if (e.original == Blocks.stone_stairs)e.replacement = Blocks.stone_stairs;
	            if (e.original == Blocks.gravel)e.replacement = Blocks.gravel;
	        }
		}
	}
}




