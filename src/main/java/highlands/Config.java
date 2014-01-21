package highlands;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config
{
	public static String CATEGORY_BIOME_ID = "Biomes.Biome IDs";
	public static String CATEGORY_BIOME_GENERATE = "Biomes.Generate Biomes true/false?";
	
	public static String CATEGORY_SUBBIOME_ID = "Biomes.Sub-biome IDs";
	public static String CATEGORY_SUBBIOME_GENERATE = "Biomes.Generate Sub-biomes true/false?";
	
	public static String CATEGORY_BLOCK_ID = "Block IDs";
	
	public static String CATEGORY_VANILLABIOME_GENERATE = "Biomes.Generate Vanilla Biomes true/false?";
	
	//Block ID Properties
	public static Property firSaplingID;
    public static Property acaciaSaplingID;
    public static Property redwoodSaplingID;
    public static Property poplarSaplingID;
    public static Property canopySaplingID;
    public static Property greatOakSaplingID;
    public static Property beechSaplingID;
    public static Property deadSaplingID;
    public static Property evergreenBushSaplingID;
    public static Property deciduousBushSaplingID;
    public static Property palmSaplingID;
    public static Property ironwoodSaplingID;
    public static Property mangroveSaplingID;
    public static Property ashSaplingID;
    public static Property autumnYellowSaplingID;
    public static Property autumnOrangeSaplingID;
    public static Property firWoodID;
    public static Property acaciaWoodID;
    public static Property redwoodWoodID;
    public static Property poplarWoodID;
    public static Property canopyWoodID;
    public static Property palmWoodID;
    public static Property ironWoodID;
    public static Property mangroveWoodID;
    public static Property ashWoodID;
    public static Property firLeavesID;
    public static Property acaciaLeavesID;
    public static Property redwoodLeavesID;
    public static Property poplarLeavesID;
    public static Property canopyLeavesID;
    public static Property palmLeavesID;
    public static Property ironwoodLeavesID;
    public static Property mangroveLeavesID;
    public static Property ashLeavesID;
    public static Property autumnYellowLeavesID;
    public static Property autumnOrangeLeavesID;
    public static Property blueFlowerID;
    public static Property leafyFernID;
    public static Property lavenderID;
    public static Property cattailID;
    public static Property whiteFlowerID;
    public static Property raspberryBushID;
    public static Property blueberryBushID;
    public static Property cottonID;
    public static Property thornbushID;
    public static Property berriesID;
    public static Property hlplanksID;
    public static Property hlPlankStairs0ID;
    public static Property hlPlankStairs1ID;
    public static Property hlPlankStairs2ID;
    public static Property hlPlankStairs3ID;
    public static Property hlPlankHalfID;
    public static Property hlPlankHalfDoubleID;
	
	
	//Biome ID Properties
	public static Property woodlandMountainsID;
    public static Property highlandsbID;
    public static Property tundraID;
    public static Property cliffsID;
    public static Property pinelandsID;
    public static Property autumnForestID;
    public static Property alpsID;
    public static Property tallPineForestID;
    public static Property meadowID;
    public static Property savannahID;
    public static Property tropicsID;
    public static Property outbackID;
    public static Property woodlandsID;
    public static Property bogID;
    public static Property redwoodForestID;
    public static Property dunesID;
    public static Property lowlandsID;
    public static Property sahelID;
    public static Property birchHillsID;
    public static Property tropicalIslandsID;
    public static Property rainforestID;
    public static Property estuaryID;
    public static Property badlandsID;
    public static Property flyingMountainsID;
    public static Property snowMountainsID;
    public static Property rockMountainsID;
    public static Property desertMountainsID;
    public static Property steppeID;
    public static Property glacierID;
    //public static Property evergladesID;
    public static Property ocean2ID;
    public static Property forestIslandID;
    public static Property jungleIslandID;
    public static Property desertIslandID;
    public static Property volcanoIslandID;
    public static Property snowIslandID;
    public static Property windyIslandID;
    public static Property rockIslandID;
    public static Property valleyID;
    public static Property lakeID;
    public static Property mesaID;
    public static Property baldHillID;
    public static Property oasisID;
    public static Property canyonID;
    public static Property shrublandID;
    
    //Biome Generate Properties
  	public static Property woodlandMountainsGenerate;
	public static Property highlandsbGenerate;
	public static Property tundraGenerate;
	public static Property cliffsGenerate;
	public static Property pinelandsGenerate;
	public static Property autumnForestGenerate;
	public static Property alpsGenerate;
	public static Property tallPineForestGenerate;
	public static Property meadowGenerate;
	public static Property savannahGenerate;
	public static Property tropicsGenerate;
	public static Property outbackGenerate;
	public static Property woodlandsGenerate;
	public static Property bogGenerate;
	public static Property redwoodForestGenerate;
	public static Property dunesGenerate;
	public static Property lowlandsGenerate;
	public static Property sahelGenerate;
	public static Property birchHillsGenerate;
	public static Property tropicalIslandsGenerate;
	public static Property rainforestGenerate;
	public static Property estuaryGenerate;
	public static Property badlandsGenerate;
	public static Property flyingMountainsGenerate;
	public static Property snowMountainsGenerate;
	public static Property rockMountainsGenerate;
	public static Property desertMountainsGenerate;
	public static Property steppeGenerate;
	public static Property glacierGenerate;
	//public static Property evergladesGenerate;
	public static Property ocean2Generate;
	public static Property forestIslandGenerate;
	public static Property jungleIslandGenerate;
	public static Property desertIslandGenerate;
	public static Property volcanoIslandGenerate;
	public static Property snowIslandGenerate;
	public static Property windyIslandGenerate;
	public static Property rockIslandGenerate;
	public static Property valleyGenerate;
	public static Property lakeGenerate;
	public static Property mesaGenerate;
	public static Property baldHillGenerate;
	public static Property oasisGenerate;
	public static Property canyonGenerate;
	public static Property shrublandGenerate;
	
	public static Property plainsGenerate;
	public static Property desertGenerate;
	public static Property extremeHillsGenerate;
	public static Property forestGenerate;
	public static Property swamplandGenerate;
	public static Property jungleGenerate;
	public static Property icePlainsGenerate;
	public static Property taigaGenerate;
	
	//Settings Properties
	public static Property biomePrefix;
	public static Property moreOceans;
	public static Property islandRarity;
	public static Property biomeSize;
	public static Property LBbiomeSize;
	public static Property genDefault;
	public static Property skyColors;
	public static Property modWoodAndLeaves;
	public static Property smallPlants;
	public static Property genOre;
	public static Property mobModCompatibility;
	public static Property safeMode;
	
	
	
	
	
	public static void setUpConfig(Configuration config)
	{
		addBiomeEntries(config);
		addSettingsEntries(config);
	}
	
	private static void addBiomeEntries(Configuration config) 
	{
		alpsID = config.get(CATEGORY_BIOME_ID, "Alps ID", 150);
		alpsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Alps Generate", true);
		autumnForestID = config.get(CATEGORY_BIOME_ID, "Autumn Forest ID", 151);
		autumnForestGenerate = config.get(CATEGORY_BIOME_GENERATE, "Autumn Forest Generate", true);
		badlandsID = config.get(CATEGORY_BIOME_ID, "Badlands ID", 152);
		badlandsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Badlands Generate", true);
		birchHillsID = config.get(CATEGORY_BIOME_ID, "Birch Hills ID", 153);
		birchHillsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Birch Hills Generate", true);
		bogID = config.get(CATEGORY_BIOME_ID, "Bog ID", 154);
		bogGenerate = config.get(CATEGORY_BIOME_GENERATE, "Bog Generate", true);
		cliffsID = config.get(CATEGORY_BIOME_ID, "Cliffs ID", 155);
		cliffsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Cliffs Generate", true);
		desertMountainsID = config.get(CATEGORY_BIOME_ID, "Desert Mountains ID", 156);
		desertMountainsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Desert Mountains Generate", true);
		dunesID = config.get(CATEGORY_BIOME_ID, "Dunes ID", 157);
		dunesGenerate = config.get(CATEGORY_BIOME_GENERATE, "Dunes Generate", true);
		estuaryID = config.get(CATEGORY_BIOME_ID, "Estuary ID", 158);
		estuaryGenerate = config.get(CATEGORY_BIOME_GENERATE, "Estuary Generate", true);
		//evergladesID = config.get(CATEGORY_BIOME_ID, "Everglades ID", 194);
		//evergladesGenerate = config.get(CATEGORY_BIOME_GENERATE, "Everglades Generate", true);
		flyingMountainsID = config.get(CATEGORY_BIOME_ID, "Flying Mountains ID", 159);
		flyingMountainsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Flying Mountains Generate", true);
		glacierID = config.get(CATEGORY_BIOME_ID, "Glacier ID", 160);
		glacierGenerate = config.get(CATEGORY_BIOME_GENERATE, "Glacier Generate", true);
		highlandsbID = config.get(CATEGORY_BIOME_ID, "Highlands ID", 161);
		highlandsbGenerate = config.get(CATEGORY_BIOME_GENERATE, "Highlands Generate", true);
		lowlandsID = config.get(CATEGORY_BIOME_ID, "Lowlands ID", 162);
		lowlandsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Lowlands Generate", true);
		meadowID = config.get(CATEGORY_BIOME_ID, "Meadow ID", 163);
		meadowGenerate = config.get(CATEGORY_BIOME_GENERATE, "Meadow Generate", true);
		outbackID = config.get(CATEGORY_BIOME_ID, "Outback ID", 164);
		outbackGenerate = config.get(CATEGORY_BIOME_GENERATE, "Outback Generate", true);
		pinelandsID = config.get(CATEGORY_BIOME_ID, "Pinelands ID", 165);
		pinelandsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Pinelands Generate", true);
		rainforestID = config.get(CATEGORY_BIOME_ID, "Rainforest ID", 166);
		rainforestGenerate = config.get(CATEGORY_BIOME_GENERATE, "Rainforest Generate", true);
		redwoodForestID = config.get(CATEGORY_BIOME_ID, "Redwood Forest ID", 167);
		redwoodForestGenerate = config.get(CATEGORY_BIOME_GENERATE, "Redwood Forest Generate", true);
		rockMountainsID = config.get(CATEGORY_BIOME_ID, "Rock Mountains ID", 168);
		rockMountainsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Rock Mountains Generate", true);
		sahelID = config.get(CATEGORY_BIOME_ID, "Sahel ID", 169);
		sahelGenerate = config.get(CATEGORY_BIOME_GENERATE, "Sahel Generate", true);
		savannahID = config.get(CATEGORY_BIOME_ID, "Savannah ID", 170);
		savannahGenerate = config.get(CATEGORY_BIOME_GENERATE, "Savannah Generate", true);
		steppeID = config.get(CATEGORY_BIOME_ID, "Steppe ID", 171);
		steppeGenerate = config.get(CATEGORY_BIOME_GENERATE, "Steppe Generate", true);
		snowMountainsID = config.get(CATEGORY_BIOME_ID, "Snow Mountains ID", 172);
		snowMountainsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Snow Mountains Generate", true);
		tallPineForestID = config.get(CATEGORY_BIOME_ID, "Tall Pine Forest ID", 173);
		tallPineForestGenerate = config.get(CATEGORY_BIOME_GENERATE, "Tall Pine Forest Generate", true);
		tropicsID = config.get(CATEGORY_BIOME_ID, "Tropics ID", 174);
		tropicsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Tropics Generate", true);
		tropicalIslandsID = config.get(CATEGORY_BIOME_ID, "Tropical Islands ID", 175);
		tropicalIslandsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Tropical Islands Generate", true);
		tundraID = config.get(CATEGORY_BIOME_ID, "Tundra ID", 176);
		tundraGenerate = config.get(CATEGORY_BIOME_GENERATE, "Tundra Generate", true);
		woodlandsID = config.get(CATEGORY_BIOME_ID, "Woodlands ID", 177);
		woodlandsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Woodlands Generate", true);
		woodlandMountainsID = config.get(CATEGORY_BIOME_ID, "Woodland Mountains ID", 178);
		woodlandMountainsGenerate = config.get(CATEGORY_BIOME_GENERATE, "Woodland Mountains Generate", true);
		
		ocean2ID = config.get(CATEGORY_BIOME_ID, "Improved Oceans ID", 179);
		ocean2Generate = config.get(CATEGORY_BIOME_GENERATE, "Improved Oceans Generate", true);
		ocean2Generate.comment = "If this is false you will have regular Minecraft oceans instead.";
		
		desertIslandID = config.get(CATEGORY_SUBBIOME_ID, "DesertIsland ID", 180);
		desertIslandGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Desert Island Generate", true);
		forestIslandID = config.get(CATEGORY_SUBBIOME_ID, "Forest Island ID", 181);
		forestIslandGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Forest Island Generate", true);
		jungleIslandID = config.get(CATEGORY_SUBBIOME_ID, "Jungle Island ID", 182);
		jungleIslandGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Jungle Island Generate", true);
		volcanoIslandID = config.get(CATEGORY_SUBBIOME_ID, "Volcano Island ID", 183);
		volcanoIslandGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Volcano Island Generate", true);
		snowIslandID = config.get(CATEGORY_SUBBIOME_ID, "Snow Island ID", 184);
		snowIslandGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Snow Island Generate", true);
		rockIslandID = config.get(CATEGORY_SUBBIOME_ID, "Rock Island ID", 185);
		rockIslandGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Rock Island Generate", true);
		windyIslandID = config.get(CATEGORY_SUBBIOME_ID, "Windy Island ID", 186);
		windyIslandGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Windy Island Generate", true);
		lakeID = config.get(CATEGORY_SUBBIOME_ID, "Lake ID", 187);
		lakeGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Lake Generate", true);
		baldHillID = config.get(CATEGORY_SUBBIOME_ID, "Bald Hill ID", 188);
		baldHillGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Bald Hill Generate", true);
		mesaID = config.get(CATEGORY_SUBBIOME_ID, "Mesa ID", 189);
		mesaGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Mesa Generate", true);
		valleyID = config.get(CATEGORY_SUBBIOME_ID, "Valley ID", 190);
		valleyGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Valley Generate", true);
		oasisID = config.get(CATEGORY_SUBBIOME_ID, "Oasis ID", 191);
		oasisGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Oasis Generate", true);
		canyonID = config.get(CATEGORY_SUBBIOME_ID, "Canyon ID", 192);
		canyonGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Canyon Generate", true);
		
		shrublandID = config.get(CATEGORY_SUBBIOME_ID, "Shrublands ID", 193);
		shrublandGenerate = config.get(CATEGORY_SUBBIOME_GENERATE, "Shrublands Generate", true);
		
		plainsGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Plains Generate", true);
		desertGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Desert Generate", true);
		extremeHillsGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Extreme Hills Generate", true);
		forestGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Forest Generate", true);
		swamplandGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Swampland Generate", true);
		jungleGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Jungle Generate", true);
		icePlainsGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Ice Plains Generate", true);
		taigaGenerate = config.get(CATEGORY_VANILLABIOME_GENERATE, "Taiga Generate", true);
	}


	private static void addSettingsEntries(Configuration config) 
	{
		biomePrefix = config.get(config.CATEGORY_GENERAL, "Biome Prefix", false);
		biomePrefix.comment = "use a prefix of \"Highlands_\" + biome name for all biomes?";
		moreOceans = config.get(config.CATEGORY_GENERAL, "More Oceans", 1);
		moreOceans.comment = "How often oceans appear in your world (not less than 0).  1 is default. ";
		islandRarity = config.get(config.CATEGORY_GENERAL, "Island Rarity", 14);
		islandRarity.comment = "How often should Islands spawn in the ocean.  Higher = less islands. ";
		biomeSize = config.get(config.CATEGORY_GENERAL, "Biome Size", 4);
		biomeSize.comment = "Biome size in Highlands worlds - 4 is default (same as Default worldtype)";
		LBbiomeSize = config.get(config.CATEGORY_GENERAL, "Large Biomes Biome Size", 6);
		LBbiomeSize.comment = "Biome size in Highlands LB worlds - 6 is default (same as Large Biomes worldtype)";
		genDefault = config.get(config.CATEGORY_GENERAL, "Highands biomes in Default worlds", false);
		genDefault.comment = "Should Highlands generate in the Default and Large Biomes worldtype (for compatibilty with other biome and worldgen mods)";
		skyColors = config.get(config.CATEGORY_GENERAL, "Sky Colors", false);
		skyColors.comment = "Use custom sky colors?";
		modWoodAndLeaves = config.get(config.CATEGORY_GENERAL, "Highlands Wood and Leaves", true);
		modWoodAndLeaves.comment = "Set to false to use vanilla wood and leaves instead of this mod's wood and leaves.";
		smallPlants = config.get(config.CATEGORY_GENERAL, "Generate Small Plants", true);
		smallPlants.comment = "Set to false to disable this mod's small plants.";
		genOre = config.get(config.CATEGORY_GENERAL, "Generate Biome-specific Ores", true);
		genOre.comment = "Set to false to disable extra ores of different types in different biomes.";
		mobModCompatibility = config.get(config.CATEGORY_GENERAL, "Mob Mod Compatiblity", false);
		mobModCompatibility.comment = "DOES NOT WORK WITH MO CREATURES.  See Readme for more information.";
		safeMode = config.get(config.CATEGORY_GENERAL, "Safe Mode", false);
		safeMode.comment = "Disables sub-biomes, borders, and improved oceans.  See Readme for more information.";
	}
}
