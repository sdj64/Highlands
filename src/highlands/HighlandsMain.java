package highlands;

import java.lang.System;
import java.util.Arrays;
import java.util.List;

import highlands.api.*;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid=HighlandsMain.modid, name=HighlandsMain.modid, version="2.2.0",
		dependencies = "after:Forestry;after:MineFactoryReloaded;after:Thaumcraft;after:BuildCraft|Transport")
public class HighlandsMain {

	public static final String modid = "Highlands";
	
    // The instance of your mod that Forge uses.
	@Instance(modid)
	public static HighlandsMain instance;
	
	//Highlands Worldtypes
	public static final WorldTypeHighlands HL = new WorldTypeHighlands(modid);
	public static final WorldTypeHighlands HLLB = new WorldTypeHighlands("Highlands LB");
	
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

    public static Logger logger;

    @EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//new settings set-up
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		Config.setUpConfig(config);
		config.save();

        //construct all variables
        HighlandsBiomes.initBiomeArrays();
        Initializer.constructBiomes();
        Initializer.constructBlocks();
        Initializer.constructSettings();
        //initiate all recipes and ore dictionary definitions
        Initializer.initRecipes();
		//register event manager
		MinecraftForge.TERRAIN_GEN_BUS.register(new HighlandsEventManager());
		MinecraftForge.EVENT_BUS.register(new HighlandsEventManager());
        //initiate logger
        logger = event.getModLog();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {

		//set up worldtypes
		HL.addBiomeList(HighlandsBiomes.biomesForHighlands);
		HLLB.addBiomeList(HighlandsBiomes.biomesForHighlands);
		if(HL.getBiomeListSize() == 0) HL.addBiomeList(BiomeGenBase.icePlains);
		if(HLLB.getBiomeListSize() == 0) HLLB.addBiomeList(BiomeGenBase.icePlains);

		//add biomes to spawn strongholds in
		for(BiomeGenBase i : HighlandsBiomes.biomesForDefault){
			if(i != HighlandsBiomes.woodsMountains && i != HighlandsBiomes.flyingMountains && i != HighlandsBiomes.ocean2)
			BiomeManager.addStrongholdBiome(i);
		}
		
		// allow player spawning in biomes
		for(BiomeGenBase i : HighlandsBiomes.biomesForDefault){
			BiomeManager.addSpawnBiome(i);
		}
		if(WorldChunkManager.allowedBiomes.size() == 0)
            WorldChunkManager.allowedBiomes.add(HighlandsBiomes.ocean2);

		
		//set up sub-biomes
		Initializer.setUpAllSubBiomes();
		
		// DEBUG - COMMENT OUT IN RELEASE
		//debug();

		// This must NOT be in postInit.
		//HighlandsCompatibilityManager.registerBlocksBuildcraft();

	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		hlvillagebiomes = Arrays.asList(
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
		);
		
		defaultvillagebiomes = MapGenVillage.villageSpawnBiomes;

		//TODO- readd compat
		//BiomeDictionary PostInit
		//HighlandsCompatibilityManager.registerBiomesForgeBiomeDict();
		
		GameRegistry.registerFuelHandler(new HighlandsFuelHandler());
		
//		Block.blocksList[Block.cocoaPlant.blockID] = null;
//		HighlandsBlocks.cocoa2 = new BlockCocoaPlant2(Block.cocoaPlant.blockID).setHardness(0.2F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cocoa");

		//Forestry PostInit
//		if (Loader.isModLoaded("Forestry") ){
//			try {
//				HighlandsCompatibilityManager.registerBlocksForestry();
//				HighlandsCompatibilityManager.registerRecipesForestry();
//			}
//			catch( Exception e ) {
//				System.err.println("[Highlands] Failed to enable Forestry compatibility because: ");
//				e.printStackTrace();
//			}
//		}
//
//		//MFR PostInit
//		if (Loader.isModLoaded("MineFactoryReloaded") ){
//			try {
//				HighlandsCompatibilityManager.registerBlocksMFR();
//			}
//			catch( Exception e ) {
//				System.err.println("[Highlands] Failed to enable MFR compatibility because: ");
//				e.printStackTrace();
//			}
//		}
//
//		//Thaumcraft PostInit
//		if (Loader.isModLoaded("Thaumcraft")){
//			try {
//				HighlandsCompatibilityManager.registerBlocksThaumcraft();
//			}
//			catch( Exception e ) {
//				System.err.println("[Highlands] Failed to enable Thaumcraft compatibility because: ");
//				e.printStackTrace();
//			}
//		}
//		
//		//ATG PostInit
//		if (Loader.isModLoaded("ATG")){
//			try {
//				HighlandsCompatibilityManager.registerBiomesATG();
//			}
//			catch( Exception e ) {
//				System.err.println("[Highlands] Failed to enable ATG compatibility because: ");
//				e.printStackTrace();
//			}
//		}
	}	
	
	//method to print debug info
	public static void debug(){
        System.out.println("   Highlands Biomes for default: ");
		for(BiomeGenBase i : HighlandsBiomes.biomesForDefault){
			if(i != null) System.out.println(i.biomeName + " " + i.biomeID);
		}
		
		System.out.println("   Highlands Biomes: ");
		for(BiomeGenBase i : HL.getBiomeList()){
			if(i != null) System.out.println(i.biomeName + " " + i.biomeID);
		}
	}
}