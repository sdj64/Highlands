package highlands;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import highlands.block.BlockHighlandsSapling;
import highlands.worldgen.layer.GenLayerHL;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;
import net.minecraftforge.event.terraingen.WorldTypeEvent.BiomeSize;
import net.minecraftforge.event.terraingen.WorldTypeEvent.InitBiomeGens;
import net.minecraftforge.event.world.WorldEvent.Load;

import java.util.Arrays;
import java.util.List;

public class HighlandsEventManager {

	//allows get wood achievement for Highlands woods
	@SubscribeEvent
	public void onItemPickupWood(EntityItemPickupEvent e){
        Item picked = e.item.getEntityItem().getItem();
        for(Block block:HighlandsBlocks.logs){
            if(block != null && Item.getItemFromBlock(block) == picked){
                e.entityPlayer.triggerAchievement(AchievementList.mineWood);
                break;
            }
        }
	}

	// Adds village spawning to Highlands worlds and default worlds if Highlands is enabled.
    @SubscribeEvent
	public void onWorldStart(Load e){
		if(e.world.provider.terrainType == HighlandsMain.HL || e.world.provider.terrainType == HighlandsMain.HLLB || HighlandsMain.highlandsInDefaultFlag){
			for(BiomeGenBase biome:HighlandsMain.hlvillagebiomes){
                BiomeManager.addVillageBiome(biome, true);
            }
        }
		
		// loads compatibility mob lists for all biomes
		//if(HighlandsMain.mocreaturescomp)HighlandsCompatibilityManager.mobload_biomes();
		
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
		}
	}
	
    @SubscribeEvent
    public void bonemealEvent(BonemealEvent e)
    {
        if (!e.world.isRemote)
        {
        	for(Block b: HighlandsBlocks.saplings)
                if(b != null && e.block == b) {
                    BlockHighlandsSapling sapling = (BlockHighlandsSapling)e.block;
                    e.setResult(Event.Result.ALLOW);
                    if(e.entityPlayer.capabilities.isCreativeMode)
                        sapling.growTree(e.world, e.x, e.y, e.z, e.world.rand);
                    else
                        sapling.updateTick(e.world, e.x, e.y, e.z, e.world.rand);
                    break;
                }
        }
    }

	// sets default village blocks
	@SubscribeEvent
	public void onVillageSelectBlock(GetVillageBlockID e){
		if(e.biome != null){
			if ((HighlandsBiomes.sahel != null && e.biome.biomeName.equals(HighlandsBiomes.sahel.biomeName)) ||(HighlandsBiomes.outback != null && e.biome.biomeName.equals(HighlandsBiomes.outback.biomeName)))
	        {
                int i = replaced.indexOf(e.original);
                if(i!=-1){
                    e.replacement = replacement0.get(i);
                    e.setResult(Event.Result.DENY);
                }
	        }
			else if (BiomeGenBase.icePlains != null && e.biome.biomeName.equals(BiomeGenBase.icePlains.biomeName))
	        {
                int i = replaced.indexOf(e.original);
                if(i!=-1){
                    e.replacement = replacement1.get(i);
                    e.setResult(Event.Result.DENY);
                }
	        }
		}
	}

    private static List<Block> replaced = Arrays.asList(Blocks.log, Blocks.cobblestone, Blocks.planks, Blocks.oak_stairs, Blocks.stone_stairs, Blocks.gravel);
    private static List<Block> replacement0 = Arrays.asList(Blocks.log, Blocks.sandstone, Blocks.planks, Blocks.oak_stairs, Blocks.sandstone_stairs, Blocks.gravel);
    private static List<Block> replacement1 = Arrays.asList(Blocks.log, Blocks.cobblestone, Blocks.snow, Blocks.oak_stairs, Blocks.stone_stairs, Blocks.gravel);
}




