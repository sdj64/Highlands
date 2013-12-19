package highlands;

import java.io.File;
import java.lang.Exception;
import java.lang.System;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.Loader;

//don't need these imports after testing is over

import highlands.HighlandsCompatibilityManager;
import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import highlands.biome.BiomeGenBaseHighlands;
import highlands.biome.BiomeGenDesertMountains;
import highlands.biome.BiomeGenRockMountains;
import highlands.biome.BiomeGenSnowMountains;
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
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="Highlands", name="Highlands", version="2.1.5",
		dependencies = "after:Forestry;after:MineFactoryReloaded;after:Thaumcraft;after:BuildCraft|Transport")
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
    
    @EventHandler
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
	
	@EventHandler
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
		Initializer.initRecipes();
		
		//set up sub-biomes
		Initializer.setUpAllSubBiomes();
		
		// DEBUG - COMMENT OUT IN RELEASE
		//debug();

		// This must NOT be in postInit.
		HighlandsCompatibilityManager.registerBlocksBuildcraft();
		
		proxy.registerRenderers();
	}
	
	@EventHandler
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
		
		GameRegistry.registerFuelHandler(new HighlandsFuelHandler());
		
		Block.blocksList[Block.cocoaPlant.blockID] = null;
		HighlandsBlocks.cocoa2 = new BlockCocoaPlant2(Block.cocoaPlant.blockID).setHardness(0.2F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cocoa");

		//Forestry PostInit
		if (Loader.isModLoaded("Forestry") ){
			try {
				HighlandsCompatibilityManager.registerBlocksForestry();
				HighlandsCompatibilityManager.registerRecipesForestry();
			}
			catch( Exception e ) {
				System.err.println("[Highlands] Failed to enable Forestry compatibility because: ");
				e.printStackTrace();
			}
		}

		//MFR PostInit
		if (Loader.isModLoaded("MineFactoryReloaded") ){
			try {
				HighlandsCompatibilityManager.registerBlocksMFR();
			}
			catch( Exception e ) {
				System.err.println("[Highlands] Failed to enable MFR compatibility because: ");
				e.printStackTrace();
			}
		}

		//Thaumcraft PostInit
		if (Loader.isModLoaded("Thaumcraft")){
			try {
				HighlandsCompatibilityManager.registerBlocksThaumcraft();
			}
			catch( Exception e ) {
				System.err.println("[Highlands] Failed to enable Thaumcraft compatibility because: ");
				e.printStackTrace();
			}
		}
		
		//ATG PostInit
		if (Loader.isModLoaded("ATG")){
			try {
				HighlandsCompatibilityManager.registerBiomesATG();
			}
			catch( Exception e ) {
				System.err.println("[Highlands] Failed to enable ATG compatibility because: ");
				e.printStackTrace();
			}
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