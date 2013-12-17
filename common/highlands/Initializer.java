package highlands;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
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
import highlands.block.ItemBlockMetadata;
import highlands.block.ItemHighlandsBerries;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBeach;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;


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
		
		HighlandsBlocks.berries = new ItemHighlandsBerries(Config.berriesID.getInt()).setUnlocalizedName("hl_berries");
		
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
	
	
	
	public static void initRecipes(){
		
		//SAPLINGS
		
		//Fir Sapling
		if(HighlandsBlocks.firSapling != null){
			registerBlock(HighlandsBlocks.firSapling, "Fir Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.firSapling),
					new ItemStack(Block.sapling, 1, 1), new ItemStack(Block.wood, 1, 1));
		}
		//Acacia Sapling
		if(HighlandsBlocks.acaciaSapling != null){
			registerBlock(HighlandsBlocks.acaciaSapling, "Acacia Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.acaciaSapling),
					new ItemStack(Block.sapling, 1, 3), new ItemStack(Block.wood, 1, 0));
		}
		//Poplar Sapling
		if(HighlandsBlocks.poplarSapling != null){
			registerBlock(HighlandsBlocks.poplarSapling, "Poplar Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.poplarSapling),
					new ItemStack(Block.sapling, 1, 2), new ItemStack(Block.wood, 1, 0));
		}
		//Sequoia Sapling
		if(HighlandsBlocks.redwoodSapling != null && HighlandsBlocks.firSapling != null){
			registerBlock(HighlandsBlocks.redwoodSapling, "Redwood Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.redwoodSapling),
					new ItemStack(Item.dyePowder, 1, 15), new ItemStack(HighlandsBlocks.firSapling, 1, 0), new ItemStack(Block.wood, 1, 0));
		}
		//Canopy Tree Sapling
		if(HighlandsBlocks.canopySapling != null){
			registerBlock(HighlandsBlocks.canopySapling, "Canopy Tree Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.canopySapling),
					new ItemStack(Block.sapling, 1, 3), new ItemStack(Block.wood, 1, 3));
		}
		//Great Oak Sapling
		if(HighlandsBlocks.greatOakSapling != null){
			registerBlock(HighlandsBlocks.greatOakSapling, "Great Oak Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.greatOakSapling),
					new ItemStack(Block.sapling, 1, 0), new ItemStack(Block.wood, 1, 0));
		}
		//Beech Sapling
		if(HighlandsBlocks.beechSapling != null){
			registerBlock(HighlandsBlocks.beechSapling, "Beech Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.beechSapling),
					new ItemStack(Block.sapling, 1, 2), new ItemStack(Block.wood, 1, 2));
		}
		//Evergreen Bush Sapling
		if(HighlandsBlocks.evergreenBushSapling != null){
			registerBlock(HighlandsBlocks.evergreenBushSapling, "Evergreen Bushling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.evergreenBushSapling, 2, 0),
					new ItemStack(Block.sapling, 1, 1), new ItemStack(Item.stick));
		}
		//Deciduous Bush Sapling
		if(HighlandsBlocks.deciduousBushSapling != null){
			registerBlock(HighlandsBlocks.deciduousBushSapling, "Deciduous Bushling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.deciduousBushSapling, 2, 0),
					new ItemStack(Block.sapling, 1, 0), new ItemStack(Item.stick));
		}
		//Palm Sapling
		if(HighlandsBlocks.palmSapling != null){
			registerBlock(HighlandsBlocks.palmSapling, "Palm Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.palmSapling),
					new ItemStack(Block.sapling, 1, 3), new ItemStack(Block.wood, 1, 2));
		}
		//Dead Tree Sapling
		if(HighlandsBlocks.deadSapling != null){
			registerBlock(HighlandsBlocks.deadSapling, "Dead Tree Sapling");
		}
		//Ironwood Sapling
		if(HighlandsBlocks.ironwoodSapling != null && HighlandsBlocks.beechSapling != null && HighlandsBlocks.greatOakSapling != null
				&& HighlandsBlocks.redwoodSapling != null && HighlandsBlocks.canopySapling != null){
			registerBlock(HighlandsBlocks.ironwoodSapling, "Ironwood Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.ironwoodSapling),
					new ItemStack(HighlandsBlocks.redwoodSapling, 1, 0), new ItemStack(HighlandsBlocks.greatOakSapling, 1, 0),
					new ItemStack(HighlandsBlocks.canopySapling, 1, 0), new ItemStack(HighlandsBlocks.beechSapling, 1, 0), new ItemStack(Block.blockIron));
		}
		//Mangrove Sapling
		if(HighlandsBlocks.mangroveSapling != null){
			registerBlock(HighlandsBlocks.mangroveSapling, "Mangrove Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.mangroveSapling),
					new ItemStack(Block.sapling, 1, 2), new ItemStack(Block.wood, 1, 3));
		}
		//Ash Sapling
		if(HighlandsBlocks.ashSapling != null){
			registerBlock(HighlandsBlocks.ashSapling, "Ash Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.ashSapling),
					new ItemStack(HighlandsBlocks.greatOakSapling, 1, 0), new ItemStack(Block.wood, 1, 3));
		}
		//Orange Sapling
		if(HighlandsBlocks.autumnOrangeSapling != null){
			registerBlock(HighlandsBlocks.autumnOrangeSapling, "Orange Autumn Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.autumnOrangeSapling),
					new ItemStack(Block.sapling, 1, 0), new ItemStack(Item.dyePowder, 1, 14));
		}
		//Yellow Sapling
		if(HighlandsBlocks.autumnYellowSapling != null){
			registerBlock(HighlandsBlocks.autumnYellowSapling, "Yellow Autumn Sapling");
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.autumnYellowSapling), new ItemStack(Block.sapling, 1, 0), new ItemStack(Item.dyePowder, 1, 11));
		}
		
		
		//WOODS
		
		//Fir Log
		if(HighlandsBlocks.firWood != null){
			registerBlock(HighlandsBlocks.firWood, "Fir Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.firWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (Block.planks, 4, 1), new ItemStack(HighlandsBlocks.firWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.firWood.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
		}
		//Acacia Log
		if(HighlandsBlocks.acaciaWood != null){
			registerBlock(HighlandsBlocks.acaciaWood, "Acacia Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.acaciaWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.hlplanks, 4, 0), new ItemStack(HighlandsBlocks.acaciaWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.acaciaWood.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
		}
		//Poplar Log
		if(HighlandsBlocks.poplarWood != null){
			registerBlock(HighlandsBlocks.poplarWood, "Poplar Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.poplarWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.hlplanks, 4, 1), new ItemStack(HighlandsBlocks.poplarWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.poplarWood.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
		}
		//Sequoia Log
		if(HighlandsBlocks.redwoodWood != null){
			registerBlock(HighlandsBlocks.redwoodWood, "Redwood Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.redwoodWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.hlplanks, 4, 2), new ItemStack(HighlandsBlocks.redwoodWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.redwoodWood.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
		}
		//Canopy Tree Log
		if(HighlandsBlocks.canopyWood != null){
			registerBlock(HighlandsBlocks.canopyWood, "Canopy Tree Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.canopyWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (Block.planks, 4, 2), new ItemStack(HighlandsBlocks.canopyWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.canopyWood.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
		}
		//Mangrove Log
		if(HighlandsBlocks.mangroveWood != null){
			registerBlock(HighlandsBlocks.mangroveWood, "Mangrove Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.mangroveWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.hlplanks, 4, 3), new ItemStack(HighlandsBlocks.mangroveWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.mangroveWood.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
		}
		//Ash Log
		if(HighlandsBlocks.ashWood != null){
			registerBlock(HighlandsBlocks.ashWood, "Ash Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.ashWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (Block.planks, 4, 0), new ItemStack(HighlandsBlocks.ashWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.ashWood.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
		}
		//Palm Log
		if(HighlandsBlocks.palmWood != null){
			registerBlock(HighlandsBlocks.palmWood, "Palm Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.palmWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (Block.planks, 4, 0), new ItemStack(HighlandsBlocks.palmWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.palmWood.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
		}
		//Ironwood Log
		if(HighlandsBlocks.ironWood != null){
			registerBlock(HighlandsBlocks.ironWood, "Ironwood Log");
			OreDictionary.registerOre("logWood", new ItemStack(HighlandsBlocks.ironWood, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.hlplanks, 4, 3), new ItemStack(HighlandsBlocks.ironWood, 1, 0));
			GameRegistry.addSmelting(HighlandsBlocks.ironWood.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
		}
		
		//Highlands Planks
		if(HighlandsBlocks.hlplanks != null){
			GameRegistry.registerBlock(HighlandsBlocks.hlplanks, ItemBlockMetadata.class, "hl_woodPlanks");
			LanguageRegistry.addName(new ItemStack(HighlandsBlocks.hlplanks, 1, 0), "Yellow Wood Planks");
			LanguageRegistry.addName(new ItemStack(HighlandsBlocks.hlplanks, 1, 1), "White Wood Planks");
			LanguageRegistry.addName(new ItemStack(HighlandsBlocks.hlplanks, 1, 2), "Red Wood Planks");
			LanguageRegistry.addName(new ItemStack(HighlandsBlocks.hlplanks, 1, 3), "Grey Wood Planks");
			//OreDictionary.registerOre("plankWood", new ItemStack(HighlandsBlocks.hlplanks, 1, OreDictionary.WILDCARD_VALUE));
		}
		
		//Planks - stairs
		if(HighlandsBlocks.hlplanks != null && HighlandsBlocks.hlplankstairs0 != null){
			registerBlock(HighlandsBlocks.hlplankstairs0, "Yellow Wood Stairs");
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankstairs0, 4, 0), "x  ", "xx ", "xxx", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 0));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankstairs0, 4, 0), "  x", " xx", "xxx", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 0));
		}
		if(HighlandsBlocks.hlplanks != null && HighlandsBlocks.hlplankstairs1 != null){
			registerBlock(HighlandsBlocks.hlplankstairs1, "White Wood Stairs");
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankstairs1, 4, 0), "x  ", "xx ", "xxx", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 1));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankstairs1, 4, 0), "  x", " xx", "xxx", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 1));
		}
		if(HighlandsBlocks.hlplanks != null && HighlandsBlocks.hlplankstairs2 != null){
			registerBlock(HighlandsBlocks.hlplankstairs2, "Red Wood Stairs");
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankstairs2, 4, 0), "x  ", "xx ", "xxx", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 2));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankstairs2, 4, 0), "  x", " xx", "xxx", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 2));
		}
		if(HighlandsBlocks.hlplanks != null && HighlandsBlocks.hlplankstairs3 != null){
			registerBlock(HighlandsBlocks.hlplankstairs3, "Grey Wood Stairs");
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankstairs3, 4, 0), "x  ", "xx ", "xxx", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 3));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankstairs3, 4, 0), "  x", " xx", "xxx", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 3));
		}

		
		//Planks - half blocks
		if(HighlandsBlocks.hlplanks != null && HighlandsBlocks.hlplankhalf != null){
			GameRegistry.registerBlock(HighlandsBlocks.hlplankhalf, ItemBlockMetadata.class, "hl_woodSlab");
			LanguageRegistry.addName(new ItemStack(HighlandsBlocks.hlplankhalf, 1, 0), "Yellow Wood Slab");
			LanguageRegistry.addName(new ItemStack(HighlandsBlocks.hlplankhalf, 1, 1), "White Wood Slab");
			LanguageRegistry.addName(new ItemStack(HighlandsBlocks.hlplankhalf, 1, 2), "Red Wood Slab");
			LanguageRegistry.addName(new ItemStack(HighlandsBlocks.hlplankhalf, 1, 3), "Grey Wood Slab");
			GameRegistry.registerBlock(HighlandsBlocks.hlplankhalfdouble, ItemBlockMetadata.class, "hl_woodSlabDouble");
			LanguageRegistry.addName(new ItemStack(HighlandsBlocks.hlplankhalfdouble, 1, 0), "Yellow Wood Double Slab");
			LanguageRegistry.addName(new ItemStack(HighlandsBlocks.hlplankhalfdouble, 1, 1), "White Wood Double Slab");
			LanguageRegistry.addName(new ItemStack(HighlandsBlocks.hlplankhalfdouble, 1, 2), "Red Wood Double Slab");
			LanguageRegistry.addName(new ItemStack(HighlandsBlocks.hlplankhalfdouble, 1, 3), "Grey Wood Double Slab");
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 0), "   ", "   ", "xxx", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 0));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 1), "   ", "   ", "xxx", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 1));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 2), "   ", "   ", "xxx", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 2));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 3), "   ", "   ", "xxx", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 3));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 0), "   ", "xxx", "   ", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 0));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 1), "   ", "xxx", "   ", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 1));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 2), "   ", "xxx", "   ", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 2));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 3), "   ", "xxx", "   ", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 3));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 0), "xxx", "   ", "   ", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 0));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 1), "xxx", "   ", "   ", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 1));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 2), "xxx", "   ", "   ", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 2));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplankhalf, 6, 3), "xxx", "   ", "   ", 'x', new ItemStack(HighlandsBlocks.hlplanks, 1, 3));
			
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplanks, 1, 0), "x ", "x ", 'x', new ItemStack(HighlandsBlocks.hlplankhalf, 1, 0));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplanks, 1, 1), "x ", "x ", 'x', new ItemStack(HighlandsBlocks.hlplankhalf, 1, 1));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplanks, 1, 2), "x ", "x ", 'x', new ItemStack(HighlandsBlocks.hlplankhalf, 1, 2));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplanks, 1, 3), "x ", "x ", 'x', new ItemStack(HighlandsBlocks.hlplankhalf, 1, 3));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplanks, 1, 0), " x", " x", 'x', new ItemStack(HighlandsBlocks.hlplankhalf, 1, 0));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplanks, 1, 1), " x", " x", 'x', new ItemStack(HighlandsBlocks.hlplankhalf, 1, 1));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplanks, 1, 2), " x", " x", 'x', new ItemStack(HighlandsBlocks.hlplankhalf, 1, 2));
			GameRegistry.addRecipe(new ItemStack(HighlandsBlocks.hlplanks, 1, 3), " x", " x", 'x', new ItemStack(HighlandsBlocks.hlplankhalf, 1, 3));
		}
		//Planks - double half blocks
		if(HighlandsBlocks.hlplanks != null && HighlandsBlocks.hlplankhalfdouble != null){
			GameRegistry.registerBlock(HighlandsBlocks.hlplankhalfdouble, ItemBlockMetadata.class, "hl_woodDoubleSlab");
		}
		
		
		
		//LEAVES
		
		//Fir Leaves
		if(HighlandsBlocks.firLeaves != null){
			registerBlock(HighlandsBlocks.firLeaves, "Fir Leaves");
		}
		//Acacia Leaves
		if(HighlandsBlocks.acaciaLeaves != null){
			registerBlock(HighlandsBlocks.acaciaLeaves, "Acacia Leaves");
		}
		//Poplar Leaves
		if(HighlandsBlocks.poplarLeaves != null){
			registerBlock(HighlandsBlocks.poplarLeaves, "Poplar Leaves");
		}
		//Sequoia Leaves
		if(HighlandsBlocks.redwoodLeaves != null && HighlandsBlocks.firLeaves != null){
			registerBlock(HighlandsBlocks.redwoodLeaves, "Redwood Leaves");
		}
		//Canopy Tree Leaves
		if(HighlandsBlocks.canopyLeaves != null){
			registerBlock(HighlandsBlocks.canopyLeaves, "Canopy Tree Leaves");
		}
		//Ironwood Leaves
		if(HighlandsBlocks.ironwoodLeaves != null){
			registerBlock(HighlandsBlocks.ironwoodLeaves, "Ironwood Leaves");
		}
		//Mangrove Leaves
		if(HighlandsBlocks.mangroveLeaves != null){
			registerBlock(HighlandsBlocks.mangroveLeaves, "Mangrove Leaves");
		}
		//Ash Leaves
		if(HighlandsBlocks.ashLeaves != null){
			registerBlock(HighlandsBlocks.ashLeaves, "Ash Leaves");
		}
		//Palm Leaves
		if(HighlandsBlocks.palmLeaves != null){
			registerBlock(HighlandsBlocks.palmLeaves, "Palm Leaves");
		}
		//Yellow Leaves
		if(HighlandsBlocks.autumnOrangeLeaves != null){
			registerBlock(HighlandsBlocks.autumnOrangeLeaves, "Yellow Autumn Leaves");
		}
		//Orange Leaves
		if(HighlandsBlocks.autumnYellowLeaves != null){
			registerBlock(HighlandsBlocks.autumnYellowLeaves, "Orange Autumn Leaves");
		}
		
		//PLANTS
		
		//Blue Flower
		if(HighlandsBlocks.blueFlower != null){
			registerBlock(HighlandsBlocks.blueFlower, "Blue Flower");
			GameRegistry.addShapelessRecipe(new ItemStack (Item.dyePowder, 1, 6), new ItemStack(HighlandsBlocks.blueFlower, 1, 0));
		}
		if(HighlandsBlocks.leafyFern != null){
			registerBlock(HighlandsBlocks.leafyFern, "Leafy Fern");
			GameRegistry.addShapelessRecipe(new ItemStack (Item.dyePowder, 1, 10), new ItemStack(HighlandsBlocks.leafyFern, 1, 0));
		}
		if(HighlandsBlocks.whiteFlower != null){
			registerBlock(HighlandsBlocks.whiteFlower, "White Flower");
			GameRegistry.addShapelessRecipe(new ItemStack (Item.dyePowder, 1, 7), new ItemStack(HighlandsBlocks.whiteFlower, 1, 0));
		}
		if(HighlandsBlocks.cattail != null){
			registerBlock(HighlandsBlocks.cattail, "Cattail");
			GameRegistry.addShapelessRecipe(new ItemStack (Item.paper, 1, 0), new ItemStack(HighlandsBlocks.cattail, 1, 0));
		}
		if(HighlandsBlocks.lavender != null){
			registerBlock(HighlandsBlocks.lavender, "Lavender");
			GameRegistry.addShapelessRecipe(new ItemStack (Item.dyePowder, 1, 5), new ItemStack(HighlandsBlocks.lavender, 1, 0));
		}
		if(HighlandsBlocks.raspberryBush != null){
			registerBlock(HighlandsBlocks.raspberryBush, "Raspberry Bush");
			if(HighlandsBlocks.berries != null)
				GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.berries, 1, 0), new ItemStack(HighlandsBlocks.raspberryBush, 1, 0));
		}
		if(HighlandsBlocks.blueberryBush != null){
			registerBlock(HighlandsBlocks.blueberryBush, "Blueberry Bush");
			if(HighlandsBlocks.berries != null)
				GameRegistry.addShapelessRecipe(new ItemStack (HighlandsBlocks.berries, 1, 0), new ItemStack(HighlandsBlocks.blueberryBush, 1, 0));
		}
		if(HighlandsBlocks.thornbush != null){
			registerBlock(HighlandsBlocks.thornbush, "Thornbush");
			GameRegistry.addShapelessRecipe(new ItemStack (Item.stick, 2, 0), new ItemStack(HighlandsBlocks.thornbush, 1, 0));
		}
		if(HighlandsBlocks.cotton != null){
			registerBlock(HighlandsBlocks.cotton, "Cotton Plant");
			GameRegistry.addShapelessRecipe(new ItemStack (Item.silk, 1, 0), new ItemStack(HighlandsBlocks.cotton, 1, 0));
		}
		
		if(HighlandsBlocks.berries != null){
			GameRegistry.registerItem(HighlandsBlocks.berries, "hl_berries");
			LanguageRegistry.addName(HighlandsBlocks.berries, "Berries");
		}
		
	}
	
	public static void registerBlock(Block b, String name){
		GameRegistry.registerBlock(b, b.getUnlocalizedName());
		//LanguageRegistry.addName(b, name);
	}
	
	//sets up sub-biome lists after all biomes are initialized.
	public static void setUpAllSubBiomes(){
		ArrayList<BiomeGenBase> enabledBiomes = new ArrayList<BiomeGenBase>();
		for(BiomeGenBase b : HighlandsBiomes.biomesForHighlands)enabledBiomes.add(b);
		for(BiomeGenBase b : HighlandsBiomes.subBiomes)enabledBiomes.add(b);
		
		
		addSubBiome(HighlandsBiomes.alps, HighlandsBiomes.tallPineForest, enabledBiomes);
		addSubBiome(HighlandsBiomes.alps, HighlandsBiomes.glacier, enabledBiomes);
		addSubBiome(HighlandsBiomes.alps, BiomeGenBase.frozenRiver, enabledBiomes);
		addSubBiome(HighlandsBiomes.tropicalIslands, HighlandsBiomes.volcanoIsland, enabledBiomes);
		for(int i = 0; i < 3; i++){
			addSubBiome(HighlandsBiomes.tropicalIslands, HighlandsBiomes.tropicalIslands, enabledBiomes);
		}
		addSubBiome(HighlandsBiomes.autumnForest, HighlandsBiomes.baldHill, enabledBiomes);
		addSubBiome(HighlandsBiomes.autumnForest, HighlandsBiomes.lake, enabledBiomes);
		addSubBiome(HighlandsBiomes.birchHills, HighlandsBiomes.meadow, enabledBiomes);
		addSubBiome(HighlandsBiomes.cliffs, HighlandsBiomes.valley, enabledBiomes);
		addSubBiome(HighlandsBiomes.dunes, HighlandsBiomes.oasis, enabledBiomes);
		addSubBiome(HighlandsBiomes.estuary, HighlandsBiomes.lake, enabledBiomes);
		addSubBiome(HighlandsBiomes.meadow, HighlandsBiomes.lake, enabledBiomes);
		addSubBiome(HighlandsBiomes.meadow, HighlandsBiomes.birchHills, enabledBiomes);
		addSubBiome(HighlandsBiomes.woodlands, HighlandsBiomes.baldHill, enabledBiomes);
		addSubBiome(HighlandsBiomes.woodlands, HighlandsBiomes.lake, enabledBiomes);
		addSubBiome(HighlandsBiomes.woodlands, BiomeGenBase.plains, enabledBiomes);
		addSubBiome(HighlandsBiomes.highlandsb, HighlandsBiomes.woodlands, enabledBiomes);
		addSubBiome(HighlandsBiomes.lowlands, HighlandsBiomes.baldHill, enabledBiomes);
		addSubBiome(HighlandsBiomes.lowlands, HighlandsBiomes.lake, enabledBiomes);
		for(int i = 0; i < 3; i++){
			addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.forestIsland, enabledBiomes);
			addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.jungleIsland, enabledBiomes);
			addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.desertIsland, enabledBiomes);
			addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.windyIsland, enabledBiomes);
		}
		for(int i = 0; i < HighlandsMain.islandRarity; i++){
			addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.ocean2, enabledBiomes);
		}
		addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.snowIsland, enabledBiomes);
		addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.volcanoIsland, enabledBiomes);
		addSubBiome(HighlandsBiomes.outback, HighlandsBiomes.mesa, enabledBiomes);
		addSubBiome(HighlandsBiomes.outback, BiomeGenBase.desert, enabledBiomes);
		addSubBiome(HighlandsBiomes.pinelands, HighlandsBiomes.autumnForest, enabledBiomes);
		addSubBiome(HighlandsBiomes.redwoodForest, HighlandsBiomes.highlandsb, enabledBiomes);
		addSubBiome(HighlandsBiomes.redwoodForest, HighlandsBiomes.lake, enabledBiomes);
		addSubBiome(HighlandsBiomes.sahel, HighlandsBiomes.mesa, enabledBiomes);
		addSubBiome(HighlandsBiomes.sahel, BiomeGenBase.desert, enabledBiomes);
		addSubBiome(HighlandsBiomes.sahel, HighlandsBiomes.savannah, enabledBiomes);
		addSubBiome(HighlandsBiomes.savannah, HighlandsBiomes.mesa, enabledBiomes);
		addSubBiome(HighlandsBiomes.steppe, HighlandsBiomes.canyon, enabledBiomes);
		addSubBiome(HighlandsBiomes.tallPineForest, HighlandsBiomes.alps, enabledBiomes);
		addSubBiome(HighlandsBiomes.tallPineForest, BiomeGenBase.frozenRiver, enabledBiomes);
		addSubBiome(HighlandsBiomes.rainforest, HighlandsBiomes.baldHill, enabledBiomes);
		addSubBiome(HighlandsBiomes.rainforest, HighlandsBiomes.lake, enabledBiomes);
		addSubBiome(HighlandsBiomes.tropics, HighlandsBiomes.lake, enabledBiomes);
		addSubBiome(HighlandsBiomes.tundra, HighlandsBiomes.alps, enabledBiomes);
		addSubBiome(HighlandsBiomes.tundra, HighlandsBiomes.tallPineForest, enabledBiomes);
	}
	
	public static void addSubBiome(BiomeGenBase parent, BiomeGenBase sub, ArrayList<BiomeGenBase> list){
		if(parent != null && sub != null && list.contains(parent) && list.contains(sub)){
			((BiomeGenBaseHighlands)parent).subBiomes.add(sub);
		}
	}
}








