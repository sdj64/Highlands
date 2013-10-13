package highlands;

import java.util.ArrayList;

import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import highlands.biome.*;
import highlands.block.BlockHighlandsLeaves;
import highlands.block.BlockHighlandsLog;
import highlands.block.BlockHighlandsPlanks;
import highlands.block.BlockHighlandsSapling;
import highlands.block.BlockHighlandsSmallPlants;
import highlands.block.BlockHighlandsStairs;
import highlands.block.BlockHLPlankSlab;
import highlands.block.ItemHighlandsBerries;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBeach;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;


public class Initializer
{
	

	private static String biomePrefix = "";
	
	public static void constructSettings()
	{
		
		HighlandsMain.HighlandsBiomeSizeDefault = Config.biomeSize.getInt();
		HighlandsMain.HighlandsBiomeSizeLB = Config.LBbiomeSize.getInt();
		
		HighlandsMain.islandRarity = Config.islandRarity.getInt();
		
		int a = Config.moreOceans.getInt();
		for(int i = 0; i < a; i++){
			if(HighlandsMain.improvedOceans)HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.ocean2);
			else HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.ocean);
		}
		
		HighlandsMain.mocreaturescomp = Config.mobModCompatibility.getBoolean(false);
		
		HighlandsMain.skyColorFlag = Config.skyColors.getBoolean(false);
		
		HighlandsMain.vanillaBlocksFlag = !Config.modWoodAndLeaves.getBoolean(true);
		HighlandsMain.plantsFlag = Config.smallPlants.getBoolean(true);
		
		HighlandsMain.highlandsInDefaultFlag = Config.genDefault.getBoolean(false);
		
		HighlandsMain.useOreGens = Config.genOre.getBoolean(true);
		HighlandsMain.useGenLayers = !Config.safeMode.getBoolean(false);
	}	
	
	
	public static void constructBlocks() {
		//Saplings
		HighlandsBlocks.acaciaSapling = new BlockHighlandsSapling(Config.acaciaSaplingID.getInt(), 1).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_acaciaSapling");
		HighlandsBlocks.beechSapling = new BlockHighlandsSapling(Config.beechSaplingID.getInt(), 6).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_beechSapling");
		HighlandsBlocks.canopySapling = new BlockHighlandsSapling(Config.canopySaplingID.getInt(), 4).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_canopySapling");
		HighlandsBlocks.deadSapling = new BlockHighlandsSapling(Config.deadSaplingID.getInt(), 7).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_deadSapling");
		HighlandsBlocks.greatOakSapling = new BlockHighlandsSapling(Config.greatOakSaplingID.getInt(), 5).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_greatOakSapling");
		HighlandsBlocks.firSapling = new BlockHighlandsSapling(Config.firSaplingID.getInt(), 0).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_firSapling");
		HighlandsBlocks.poplarSapling = new BlockHighlandsSapling(Config.poplarSaplingID.getInt(), 2).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_poplarSapling");
		HighlandsBlocks.redwoodSapling = new BlockHighlandsSapling(Config.redwoodSaplingID.getInt(), 3).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_redwoodSapling");
		HighlandsBlocks.evergreenBushSapling = new BlockHighlandsSapling(Config.evergreenBushSaplingID.getInt(), 8).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_evgBushSapling");
		HighlandsBlocks.deciduousBushSapling = new BlockHighlandsSapling(Config.deciduousBushSaplingID.getInt(), 9).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_decBushSapling");
		HighlandsBlocks.palmSapling = new BlockHighlandsSapling(Config.palmSaplingID.getInt(), 10).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_palmSapling");
		HighlandsBlocks.ironwoodSapling = new BlockHighlandsSapling(Config.ironwoodSaplingID.getInt(), 11).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_ironwoodSapling");
		HighlandsBlocks.mangroveSapling = new BlockHighlandsSapling(Config.mangroveSaplingID.getInt(), 12).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_mangroveSapling");
		HighlandsBlocks.ashSapling = new BlockHighlandsSapling(Config.ashSaplingID.getInt(), 13).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_ashSapling");
		HighlandsBlocks.autumnYellowSapling = new BlockHighlandsSapling(Config.autumnYellowSaplingID.getInt(), 14).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_autumnYellowSapling");
		HighlandsBlocks.autumnOrangeSapling = new BlockHighlandsSapling(Config.autumnOrangeSaplingID.getInt(), 15).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_autumnOrangeSapling");
		
		//Wood
		HighlandsBlocks.acaciaWood = new BlockHighlandsLog(Config.acaciaWoodID.getInt(), 1).setHardness(2.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_acaciaWood");
		HighlandsBlocks.canopyWood = new BlockHighlandsLog(Config.canopyWoodID.getInt(), 4).setHardness(2.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_canopyWood");
		HighlandsBlocks.firWood = new BlockHighlandsLog(Config.firWoodID.getInt(), 0).setHardness(2.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_firWood");
		HighlandsBlocks.poplarWood = new BlockHighlandsLog(Config.poplarWoodID.getInt(), 2).setHardness(2.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_poplarWood");
		HighlandsBlocks.redwoodWood = new BlockHighlandsLog(Config.redwoodWoodID.getInt(), 3).setHardness(2.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_redwoodWood");
		HighlandsBlocks.palmWood = new BlockHighlandsLog(Config.palmWoodID.getInt(), 10).setHardness(2.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_palmWood");
		HighlandsBlocks.ironWood = new BlockHighlandsLog(Config.ironWoodID.getInt(), 11).setHardness(2.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_ironwoodWood");
		HighlandsBlocks.mangroveWood = new BlockHighlandsLog(Config.mangroveWoodID.getInt(), 12).setHardness(2.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_mangroveWood");
		HighlandsBlocks.ashWood = new BlockHighlandsLog(Config.ashWoodID.getInt(), 13).setHardness(2.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_ashWood");
		
		//Leaves
		HighlandsBlocks.acaciaLeaves = new BlockHighlandsLeaves(Config.acaciaLeavesID.getInt(), 1).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_acaciaLeaves");
		HighlandsBlocks.canopyLeaves = new BlockHighlandsLeaves(Config.canopyLeavesID.getInt(), 4).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_canopyLeaves");
		HighlandsBlocks.firLeaves = new BlockHighlandsLeaves(Config.firLeavesID.getInt(), 0).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_firLeaves");
		HighlandsBlocks.poplarLeaves = new BlockHighlandsLeaves(Config.poplarLeavesID.getInt(), 2).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_poplarLeaves");
		HighlandsBlocks.redwoodLeaves = new BlockHighlandsLeaves(Config.redwoodLeavesID.getInt(), 3).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_redwoodLeaves");
		HighlandsBlocks.palmLeaves = new BlockHighlandsLeaves(Config.palmLeavesID.getInt(), 10).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_palmLeaves");
		HighlandsBlocks.ironwoodLeaves = new BlockHighlandsLeaves(Config.ironwoodLeavesID.getInt(), 11).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_ironwoodLeaves");
		HighlandsBlocks.mangroveLeaves = new BlockHighlandsLeaves(Config.mangroveLeavesID.getInt(), 12).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_mangroveLeaves");
		HighlandsBlocks.ashLeaves = new BlockHighlandsLeaves(Config.ashLeavesID.getInt(), 13).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_ashLeaves");
		HighlandsBlocks.autumnYellowLeaves = new BlockHighlandsLeaves(Config.autumnYellowLeavesID.getInt(), 14).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_yellowLeaves");
		HighlandsBlocks.autumnOrangeLeaves = new BlockHighlandsLeaves(Config.autumnOrangeLeavesID.getInt(), 15).setHardness(0.2F)
				.setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_orangeLeaves");
		
		//Plants
		HighlandsBlocks.blueFlower = new BlockHighlandsSmallPlants(Config.blueFlowerID.getInt(), 0).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_blueFlower");
		HighlandsBlocks.leafyFern = new BlockHighlandsSmallPlants(Config.leafyFernID.getInt(), 1).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_leafyFern");
		HighlandsBlocks.whiteFlower = new BlockHighlandsSmallPlants(Config.whiteFlowerID.getInt(), 2).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_whiteFlower");
		HighlandsBlocks.cattail = new BlockHighlandsSmallPlants(Config.cattailID.getInt(), 3).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_cattail");
		HighlandsBlocks.lavender = new BlockHighlandsSmallPlants(Config.lavenderID.getInt(), 4).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_lavender");
		HighlandsBlocks.raspberryBush = new BlockHighlandsSmallPlants(Config.raspberryBushID.getInt(), 5).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_raspberryBush");
		HighlandsBlocks.blueberryBush = new BlockHighlandsSmallPlants(Config.blueberryBushID.getInt(), 6).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_blueberryBush");
		HighlandsBlocks.thornbush = new BlockHighlandsSmallPlants(Config.thornbushID.getInt(), 7).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_thornbush");
		HighlandsBlocks.cotton = new BlockHighlandsSmallPlants(Config.cottonID.getInt(), 8).setHardness(0.0F)
				.setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hl_cotton");
		
		HighlandsBlocks.berries = new ItemHighlandsBerries(Config.berriesID.getInt());
		
		//Planks
		HighlandsBlocks.hlplanks = new BlockHighlandsPlanks(Config.hlplanksID.getInt()).setHardness(2.0F).setResistance(5.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_woodPlanks");
		HighlandsBlocks.hlplankstairs0 = new BlockHighlandsStairs(Config.hlPlankStairs0ID.getInt(), HighlandsBlocks.hlplanks, 0).setHardness(2.0F).setResistance(5.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_woodStairs0");
		HighlandsBlocks.hlplankstairs1 = new BlockHighlandsStairs(Config.hlPlankStairs1ID.getInt(), HighlandsBlocks.hlplanks, 1).setHardness(2.0F).setResistance(5.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_woodStairs1");
		HighlandsBlocks.hlplankstairs2 = new BlockHighlandsStairs(Config.hlPlankStairs2ID.getInt(), HighlandsBlocks.hlplanks, 2).setHardness(2.0F).setResistance(5.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_woodStairs2");
		HighlandsBlocks.hlplankstairs3 = new BlockHighlandsStairs(Config.hlPlankStairs3ID.getInt(), HighlandsBlocks.hlplanks, 3).setHardness(2.0F).setResistance(5.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_woodStairs3");
		HighlandsBlocks.hlplankhalf = new BlockHLPlankSlab(Config.hlPlankHalfID.getInt(), false, HighlandsBlocks.hlplanks).setHardness(2.0F).setResistance(5.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_woodSlab");
		HighlandsBlocks.hlplankhalfdouble = new BlockHLPlankSlab(Config.hlPlankHalfDoubleID.getInt(), true, HighlandsBlocks.hlplanks).setHardness(2.0F).setResistance(5.0F)
				.setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hl_woodSlabDouble");

		HighlandsBlocks.saplings = new Block[]{
				HighlandsBlocks.firSapling,
				HighlandsBlocks.acaciaSapling,
				HighlandsBlocks.redwoodSapling,
				HighlandsBlocks.poplarSapling,
				HighlandsBlocks.canopySapling,
				HighlandsBlocks.greatOakSapling,
				HighlandsBlocks.beechSapling,
				HighlandsBlocks.deadSapling,
				HighlandsBlocks.evergreenBushSapling,
				HighlandsBlocks.deciduousBushSapling,
				HighlandsBlocks.palmSapling,
				HighlandsBlocks.ironwoodSapling,
				HighlandsBlocks.mangroveSapling,
				HighlandsBlocks.ashSapling,
				HighlandsBlocks.autumnYellowSapling,
				HighlandsBlocks.autumnOrangeSapling,
		};

		HighlandsBlocks.leaves = new Block[]{
				HighlandsBlocks.firLeaves,
				HighlandsBlocks.acaciaLeaves,
				HighlandsBlocks.redwoodLeaves,
				HighlandsBlocks.poplarLeaves,
				HighlandsBlocks.canopyLeaves,
				HighlandsBlocks.palmLeaves,
				HighlandsBlocks.ironwoodLeaves,
				HighlandsBlocks.mangroveLeaves,
				HighlandsBlocks.ashLeaves,
				HighlandsBlocks.autumnYellowLeaves,
				HighlandsBlocks.autumnOrangeLeaves,
		};

		HighlandsBlocks.logs = new Block[]{
				HighlandsBlocks.firWood,
				HighlandsBlocks.acaciaWood,
				HighlandsBlocks.redwoodWood,
				HighlandsBlocks.poplarWood,
				HighlandsBlocks.canopyWood,
				HighlandsBlocks.palmWood,
				HighlandsBlocks.ironWood,
				HighlandsBlocks.mangroveWood,
				HighlandsBlocks.ashWood,
		};
	}

	
	
	
	
	
	public static void constructBiomes() {
		
		biomePrefix = Config.biomePrefix.getBoolean(false) ? "Highlands_" : "";
		
		//main biomes
		HighlandsBiomes.alps = new BiomeGenAlps(Config.alpsID.getInt()).setBiomeName(biomePrefix+"Alps");
		if(Config.alpsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.alps);
		HighlandsBiomes.autumnForest = new BiomeGenAutumnForest(Config.autumnForestID.getInt()).setBiomeName(biomePrefix+"Autumn Forest");
		if(Config.autumnForestGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.autumnForest);
		HighlandsBiomes.badlands = new BiomeGenBadlands(Config.badlandsID.getInt()).setBiomeName(biomePrefix+"Badlands");
		if(Config.badlandsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.badlands);
		HighlandsBiomes.birchHills = new BiomeGenBirchHills(Config.birchHillsID.getInt()).setBiomeName(biomePrefix+"Birch Hills");
		if(Config.birchHillsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.birchHills);
		HighlandsBiomes.bog = new BiomeGenBog(Config.bogID.getInt()).setBiomeName(biomePrefix+"Bog");
		if(Config.bogGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.bog);
		HighlandsBiomes.cliffs = new BiomeGenCliffs(Config.cliffsID.getInt()).setBiomeName(biomePrefix+"Cliffs");
		if(Config.cliffsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.cliffs);
		HighlandsBiomes.desertMountains = new BiomeGenDesertMountains(Config.desertMountainsID.getInt()).setBiomeName(biomePrefix+"Desert Mountains");
		if(Config.desertMountainsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.desertMountains);
		HighlandsBiomes.dunes = new BiomeGenDunes(Config.dunesID.getInt()).setBiomeName(biomePrefix+"Dunes");
		if(Config.dunesGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.dunes);
		HighlandsBiomes.estuary = new BiomeGenEstuary(Config.estuaryID.getInt()).setBiomeName(biomePrefix+"Estuary");
		if(Config.estuaryGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.estuary);
		//HighlandsBiomes.everglades = new BiomeGenEverglades(Config.evergladesID.getInt()).setBiomeName(biomePrefix+"Everglades");
		//if(Config.evergladesGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.everglades);
		HighlandsBiomes.flyingMountains = new BiomeGenFlyingMountains(Config.flyingMountainsID.getInt()).setBiomeName(biomePrefix+"Flying Mountains");
		if(Config.flyingMountainsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.flyingMountains);
		HighlandsBiomes.glacier = new BiomeGenGlacier(Config.glacierID.getInt()).setBiomeName(biomePrefix+"Glacier");
		if(Config.glacierGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.glacier);
		HighlandsBiomes.highlandsb = new BiomeGenHighlands(Config.highlandsbID.getInt()).setBiomeName(biomePrefix+"Highlands");
		if(Config.highlandsbGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.highlandsb);
		HighlandsBiomes.lowlands = new BiomeGenLowlands(Config.lowlandsID.getInt()).setBiomeName(biomePrefix+"Lowlands");
		if(Config.lowlandsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.lowlands);
		HighlandsBiomes.meadow = new BiomeGenMeadow(Config.meadowID.getInt()).setBiomeName(biomePrefix+"Meadow");
		if(Config.meadowGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.meadow);
		HighlandsBiomes.outback = new BiomeGenOutback(Config.outbackID.getInt()).setBiomeName(biomePrefix+"Outback");
		if(Config.outbackGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.outback);
		HighlandsBiomes.pinelands = new BiomeGenPinelands(Config.pinelandsID.getInt()).setBiomeName(biomePrefix+"Pinelands");
		if(Config.pinelandsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.pinelands);
		HighlandsBiomes.rainforest = new BiomeGenRainforest(Config.rainforestID.getInt()).setBiomeName(biomePrefix+"Rainforest");
		if(Config.rainforestGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.rainforest);
		HighlandsBiomes.redwoodForest = new BiomeGenRedwoodForest(Config.redwoodForestID.getInt()).setBiomeName(biomePrefix+"Redwood Forest");
		if(Config.redwoodForestGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.redwoodForest);
		HighlandsBiomes.rockMountains = new BiomeGenRockMountains(Config.rockMountainsID.getInt()).setBiomeName(biomePrefix+"Rock Mountains");
		if(Config.rockMountainsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.rockMountains);
		HighlandsBiomes.sahel = new BiomeGenSahel(Config.sahelID.getInt()).setBiomeName(biomePrefix+"Sahel");
		if(Config.sahelGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.sahel);
		HighlandsBiomes.savannah = new BiomeGenSavannah(Config.savannahID.getInt()).setBiomeName(biomePrefix+"Savannah");
		if(Config.savannahGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.savannah);
		HighlandsBiomes.steppe = new BiomeGenSteppe(Config.steppeID.getInt()).setBiomeName(biomePrefix+"Steppe");
		if(Config.steppeGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.steppe);
		HighlandsBiomes.snowMountains = new BiomeGenSnowMountains(Config.snowMountainsID.getInt()).setBiomeName(biomePrefix+"Snow Mountains");
		if(Config.snowMountainsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.snowMountains);
		HighlandsBiomes.tallPineForest = new BiomeGenTallPineForest(Config.tallPineForestID.getInt()).setBiomeName(biomePrefix+"Tall Pine Forest");
		if(Config.tallPineForestGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.tallPineForest);
		HighlandsBiomes.tropics = new BiomeGenTropics(Config.tropicsID.getInt()).setBiomeName(biomePrefix+"Tropics");
		if(Config.tropicsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.tropics);
		HighlandsBiomes.tropicalIslands = new BiomeGenTropicalIslands(Config.tropicalIslandsID.getInt()).setBiomeName(biomePrefix+"Tropical Islands");
		if(Config.tropicalIslandsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.tropicalIslands);
		HighlandsBiomes.tundra = new BiomeGenTundra(Config.tundraID.getInt()).setBiomeName(biomePrefix+"Tundra");
		if(Config.tundraGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.tundra);
		HighlandsBiomes.woodlands = new BiomeGenWoodlands(Config.woodlandsID.getInt()).setBiomeName(biomePrefix+"Woodlands");
		if(Config.woodlandsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.woodlands);
		HighlandsBiomes.woodsMountains = new BiomeGenWoodsMountains(Config.woodlandMountainsID.getInt()).setBiomeName(biomePrefix+"Woodland Mountains");
		if(Config.woodlandMountainsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(HighlandsBiomes.woodsMountains);
		
		//improved ocean biome
		HighlandsBiomes.ocean2 = new BiomeGenOcean2(Config.ocean2ID.getInt()).setBiomeName(biomePrefix+"Ocean");
		HighlandsMain.improvedOceans = Config.ocean2Generate.getBoolean(true);
		
		
		//sub-biomes
		HighlandsBiomes.desertIsland = new BiomeGenDesertIsland(Config.desertIslandID.getInt()).setBiomeName(biomePrefix+"Desert Island").setMinMaxHeight(0.1F, 0.1F);
		if(Config.desertIslandGenerate.getBoolean(true)) HighlandsBiomes.subBiomes.add(HighlandsBiomes.desertIsland);
		HighlandsBiomes.forestIsland = new BiomeGenWoodlands(Config.forestIslandID.getInt()).setBiomeName(biomePrefix+"Forest Island").setMinMaxHeight(0.2F, 0.4F);
		if(Config.forestIslandGenerate.getBoolean(true)) HighlandsBiomes.subBiomes.add(HighlandsBiomes.forestIsland);
		HighlandsBiomes.jungleIsland = new BiomeGenJungle(Config.jungleIslandID.getInt()).setBiomeName(biomePrefix+"Jungle Island").setMinMaxHeight(0.2F, 0.4F).setTemperatureRainfall(1.0F, 1.2F);
		if(Config.jungleIslandGenerate.getBoolean(true)) HighlandsBiomes.subBiomes.add(HighlandsBiomes.jungleIsland);
		HighlandsBiomes.volcanoIsland = new BiomeGenVolcanoIsland(Config.volcanoIslandID.getInt()).setBiomeName(biomePrefix+"Volcano Island");
		if(Config.volcanoIslandGenerate.getBoolean(true)) HighlandsBiomes.subBiomes.add(HighlandsBiomes.volcanoIsland);
		HighlandsBiomes.snowIsland = new BiomeGenTaiga(Config.snowIslandID.getInt()).setBiomeName(biomePrefix+"Snow Island").setMinMaxHeight(0.2F, 0.4F).setTemperatureRainfall(0.0F, 0.6F);
		if(Config.snowIslandGenerate.getBoolean(true)) HighlandsBiomes.subBiomes.add(HighlandsBiomes.snowIsland);
		HighlandsBiomes.rockIsland = new BiomeGenRockIsland(Config.rockIslandID.getInt()).setBiomeName(biomePrefix+"Rock Island");
		if(Config.rockIslandGenerate.getBoolean(true)) HighlandsBiomes.subBiomes.add(HighlandsBiomes.rockIsland);
		HighlandsBiomes.windyIsland = new BiomeGenOcean(Config.windyIslandID.getInt()).setBiomeName(biomePrefix+"Windy Island").setMinMaxHeight(0.1F, 0.2F);
		if(Config.windyIslandGenerate.getBoolean(true)) HighlandsBiomes.subBiomes.add(HighlandsBiomes.windyIsland);
		HighlandsBiomes.lake = new BiomeGenLake(Config.lakeID.getInt()).setBiomeName(biomePrefix+"Lake");
		if(Config.lakeGenerate.getBoolean(true)) HighlandsBiomes.subBiomes.add(HighlandsBiomes.lake);
		HighlandsBiomes.baldHill = new BiomeGenBaldHill(Config.baldHillID.getInt()).setBiomeName(biomePrefix+"Bald Hill");
		if(Config.baldHillGenerate.getBoolean(true)) HighlandsBiomes.subBiomes.add(HighlandsBiomes.baldHill);
		HighlandsBiomes.mesa = new BiomeGenMesa(Config.mesaID.getInt()).setBiomeName(biomePrefix+"Mesa");
		if(Config.mesaGenerate.getBoolean(true)) HighlandsBiomes.subBiomes.add(HighlandsBiomes.mesa);
		HighlandsBiomes.valley = new BiomeGenForest(Config.valleyID.getInt()).setBiomeName(biomePrefix+"Valley");
		if(Config.valleyGenerate.getBoolean(true)) HighlandsBiomes.subBiomes.add(HighlandsBiomes.valley);
		HighlandsBiomes.oasis = new BiomeGenOasis(Config.oasisID.getInt()).setBiomeName(biomePrefix+"Oasis");
		if(Config.oasisGenerate.getBoolean(true)) HighlandsBiomes.subBiomes.add(HighlandsBiomes.oasis);
		HighlandsBiomes.canyon = new BiomeGenOutback(Config.canyonID.getInt()).setBiomeName(biomePrefix+"Canyon");
		if(Config.canyonGenerate.getBoolean(true)) HighlandsBiomes.subBiomes.add(HighlandsBiomes.canyon);
		
		//border biomes
		HighlandsBiomes.shrubland = new BiomeGenShrubland(Config.shrublandID.getInt()).setBiomeName(biomePrefix+"Shrubland");
		HighlandsMain.borderBiomeFlag = Config.shrublandGenerate.getBoolean(true);
		
		HighlandsBiomes.biomesForDefault = (ArrayList<BiomeGenBase>)HighlandsBiomes.biomesForHighlands.clone();
		
		//vanilla biomes
		if(Config.plainsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.plains);
		if(Config.desertGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.desert);
		if(Config.extremeHillsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.extremeHills);
		if(Config.forestGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.forest);
		if(Config.swamplandGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.swampland);
		if(Config.jungleGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.jungle);
		if(Config.icePlainsGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.icePlains);
		if(Config.taigaGenerate.getBoolean(true)) HighlandsBiomes.biomesForHighlands.add(BiomeGenBase.taiga);
	}
}





