package highlands;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
//don't need these imports after testing is over

import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import highlands.biome.BiomeGenBaseHighlands;
import highlands.biome.BiomeGenDesertMountains;
import highlands.biome.BiomeGenRockMountains;
import highlands.biome.BiomeGenSnowMountains;
//

import highlands.block.BlockCocoaPlant2;
import highlands.block.BlockHighlandsLeaves;
import highlands.block.BlockHighlandsLog;
import highlands.block.BlockHighlandsSapling;
import highlands.block.ItemBlockMetadata;
import highlands.block.ItemHighlandsBerries;
import highlands.worldgen.WorldGenUnderground2;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemMultiTextureTile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.FMLInjectionData;

@Mod(modid="Highlands", name="Highlands", version="2.1.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class HighlandsMain {

	public static String modid = "Highlands";
	
    // The instance of your mod that Forge uses.
	@Instance("Highlands")
	public static HighlandsMain instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="highlands.CommonProxy", serverSide="highlands.CommonProxy")
	public static CommonProxy proxy;
	
	//Highlands Worldtypes
	public static final WorldType HL = (WorldType) new WorldTypeHighlands(10, "Highlands");
	public static final WorldType HLLB = (WorldType) new WorldTypeHighlands(11, "Highlands LB");
	
	public static int HighlandsBiomeSizeDefault;
	public static int HighlandsBiomeSizeLB;
	
	public static boolean mocreaturescomp = false;
	public static boolean improvedOceans = true;
	public static boolean borderBiomeFlag = true;
	
	public static boolean skyColorFlag = false;
	public static boolean vanillaBlocksFlag = false;
	public static boolean plantsFlag = true;
	
	public static int islandRarity = 14;
    
    //Village Biomes list
	public static List hlvillagebiomes;
	
	public static List defaultvillagebiomes;
    
    //highlands in default flag
    public static boolean highlandsInDefaultFlag = false;
    
    public static boolean useOreGens = true;
    public static boolean useGenLayers = true;
    
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		//new settings set-up
		Configuration config = new Configuration(new File(event.getModConfigurationDirectory() + File.separator + "Highlands.cfg"));
		config.load();
		Config.setUpConfig(config);
		config.save();
		
		//register event manager
		MinecraftForge.TERRAIN_GEN_BUS.register(new HighlandsEventManager());
		MinecraftForge.EVENT_BUS.register(new HighlandsEventManager());
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		//construct all variables
		HighlandsBiomes.initBiomeArrays();
		Initializer.constructBiomes();
		Initializer.constructBlocks();
		Initializer.constructSettings();
		
		
		//set up worldtypes
		WorldTypeHighlands.addBiomeList(HL, HighlandsBiomes.biomesForHighlands);
		WorldTypeHighlands.addBiomeList(HLLB, HighlandsBiomes.biomesForHighlands);
		if(HL.getBiomesForWorldType().length == 0) HL.addNewBiome(BiomeGenBase.icePlains);
		if(HLLB.getBiomesForWorldType().length == 0) HLLB.addNewBiome(BiomeGenBase.icePlains);
		if(highlandsInDefaultFlag){
			WorldTypeHighlands.addBiomeList(WorldType.DEFAULT, HighlandsBiomes.biomesForDefault);
			WorldTypeHighlands.addBiomeList(WorldType.LARGE_BIOMES, HighlandsBiomes.biomesForDefault);
		}
		
		//add biomes to spawn strongholds in
		for(BiomeGenBase i : HighlandsBiomes.biomesForDefault){
			if(i != HighlandsBiomes.woodsMountains && i != HighlandsBiomes.flyingMountains && i != HighlandsBiomes.ocean2)
			MapGenStronghold.allowedBiomes.add(i);
		}
		
		// allow player spawning in biomes
		for(BiomeGenBase i : HighlandsBiomes.biomesForDefault){
			WorldChunkManager.allowedBiomes.add(i);
		}
		if(WorldChunkManager.allowedBiomes.size() == 0)WorldChunkManager.allowedBiomes.add(HighlandsBiomes.ocean2);
		
		//initiate all recipes and ore dictionary definitions
		initRecipes();
		
		//set up sub-biomes
		setUpAllSubBiomes();
		
		//construct ore generators
		HLsand = new WorldGenMinable(Block.sand.blockID, 32);
		HLice = new WorldGenMinable(Block.ice.blockID, 16);
		HLwater = new WorldGenUnderground2(Block.waterStill.blockID, 4);
		HLlava = new WorldGenUnderground2(Block.lavaStill.blockID, 8);
		HLdirt = new WorldGenUnderground2(Block.dirt.blockID, 72, Block.sand.blockID);
		HLrock = new WorldGenUnderground2(Block.stone.blockID, 72, Block.dirt.blockID);
		HLobsidian = new WorldGenMinable(Block.obsidian.blockID, 8);
		
		// DEBUG - COMMENT OUT IN RELEASE
		//debug();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		
		hlvillagebiomes = Arrays.asList(new BiomeGenBase[] {
				HighlandsBiomes.autumnForest,
				HighlandsBiomes.highlandsb,
				HighlandsBiomes.pinelands,
				HighlandsBiomes.tallPineForest,
				HighlandsBiomes.meadow,
				HighlandsBiomes.savannah,
				HighlandsBiomes.sahel,
				HighlandsBiomes.steppe,
				HighlandsBiomes.outback,
				HighlandsBiomes.lowlands,
				HighlandsBiomes.birchHills,
				HighlandsBiomes.tropicalIslands,
				HighlandsBiomes.steppe,
				HighlandsBiomes.bog,
				HighlandsBiomes.redwoodForest,
				HighlandsBiomes.rainforest,
				HighlandsBiomes.forestIsland,
				HighlandsBiomes.windyIsland,
				BiomeGenBase.jungle,
				BiomeGenBase.forest,
				BiomeGenBase.taiga,
				BiomeGenBase.swampland,
				BiomeGenBase.icePlains
		});
		
		defaultvillagebiomes = MapGenVillage.villageSpawnBiomes;
		
		
		//BiomeDictionary PostInit
		HighlandsCompatibilityManager.registerBiomesForgeBiomeDict();
		
		//Forestry PostInit
		HighlandsCompatibilityManager.registerBiomesForestry();
		
		GameRegistry.registerFuelHandler(new HighlandsFuelHandler());
		
		Block.blocksList[Block.cocoaPlant.blockID] = null;
		HighlandsBlocks.cocoa2 = new BlockCocoaPlant2(Block.cocoaPlant.blockID).setHardness(0.2F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cocoa");
		
		
	}
	
	
	
	public void initRecipes(){
		
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
			OreDictionary.registerOre("plankWood", new ItemStack(HighlandsBlocks.hlplanks, 1, OreDictionary.WILDCARD_VALUE));
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
		LanguageRegistry.addName(b, name);
	}
	

	//Method for ore generators: inside the decorate method in biome classes, call this.
	//here is the decorate method:
	/*
	public void decorate(World par1World, Random par2Random, int par3, int par4){
		genOreHighlands(par1World, par2Random, par3, par4, timesPerChunk, HLWorldGenerator, minH, maxH)
	}
	 */
	
	//WorldGenerators are in biomedec.goldGen, for example for gold.
	public static void genOreHighlands(World par1World, Random par2Random, int locX, int locZ, int timesPerChunk, WorldGenerator HLWorldGenerator, int minH, int maxH)
    {
		if(useOreGens){
	        for (int var5 = 0; var5 < timesPerChunk; ++var5)
	        {
	            int var6 = locX + par2Random.nextInt(16);
	            int var7 = par2Random.nextInt(maxH - minH) + minH;
	            int var8 = locZ + par2Random.nextInt(16);
	            HLWorldGenerator.generate(par1World, par2Random, var6, var7, var8);
	        }
		}
    }
	
	public static void genOreHighlandsNoCheck(World par1World, Random par2Random, int locX, int locZ, int timesPerChunk, WorldGenerator HLWorldGenerator, int minH, int maxH)
    {
        for (int var5 = 0; var5 < timesPerChunk; ++var5)
        {
            int var6 = locX + par2Random.nextInt(16);
            int var7 = par2Random.nextInt(maxH - minH) + minH;
            int var8 = locZ + par2Random.nextInt(16);
            HLWorldGenerator.generate(par1World, par2Random, var6, var7, var8);
        }
    }
	
	//Ore Generators
	public static WorldGenerator HLsand;
	public static WorldGenerator HLice;
	public static WorldGenerator HLwater;
	public static WorldGenerator HLlava;
	public static WorldGenerator HLdirt;
	public static WorldGenerator HLrock;
	public static WorldGenerator HLobsidian;
	
	
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
		for(int i = 0; i < islandRarity; i++){
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
	
	//method to print debug info
	public static void debug(){
		System.out.println("   Configured Biomes: ");
		for(BiomeGenBase i : HighlandsBiomes.biomesForDefault){
			if(i == null) System.out.println("Null Biome! Abort!");
			else System.out.println(i.biomeName + " " + i.biomeID);
		}
		
		System.out.println("   Highlands Biomes: ");
		for(BiomeGenBase i : HL.getBiomesForWorldType()){
			if(i == null) System.out.println("Null Biome! Error!");
			else System.out.println(i.biomeName + " " + i.biomeID);
		}
		System.out.println("   Default Biomes: ");
		for(BiomeGenBase i : WorldType.DEFAULT.getBiomesForWorldType()){
			System.out.println(i.biomeName + " " + i.biomeID);
		}
	}
}








