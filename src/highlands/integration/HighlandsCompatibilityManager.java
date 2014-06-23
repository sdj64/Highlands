package highlands.integration;
import cpw.mods.fml.common.event.FMLInterModComms;
import forestry.api.apiculture.FlowerManager;
import forestry.api.recipes.RecipeManagers;
import highlands.Config;
import highlands.api.HighlandsBlocks;
import highlands.block.BlockHighlandsLeaves;
import highlands.block.BlockHighlandsLog;
import highlands.block.BlockHighlandsSapling;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import highlands.api.HighlandsBiomes;
import highlands.biome.BiomeGenBaseHighlands;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;


//import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
/**
import ttftcuts.atg.api.ATGBiomes;
import ttftcuts.atg.api.ATGBiomes.BiomeType;
import ttftcuts.atg.api.IGenMod;
*/


public class HighlandsCompatibilityManager{
	public static final BiomeGenBaseHighlands[] forestb = {
		(BiomeGenBaseHighlands)HighlandsBiomes.autumnForest,
		(BiomeGenBaseHighlands)HighlandsBiomes.woodlands,
		(BiomeGenBaseHighlands)HighlandsBiomes.woodsMountains,
		(BiomeGenBaseHighlands)HighlandsBiomes.birchHills,
		(BiomeGenBaseHighlands)HighlandsBiomes.lowlands,
		(BiomeGenBaseHighlands)HighlandsBiomes.forestIsland,
		(BiomeGenBaseHighlands)HighlandsBiomes.redwoodForest,
		//(BiomeGenBaseHighlands)HighlandsBiomes.valley
	};
	public static final BiomeGenBaseHighlands[] taigab = {
		(BiomeGenBaseHighlands)HighlandsBiomes.tallPineForest,
	};
	public static final BiomeGenBaseHighlands[] snowb = {
		(BiomeGenBaseHighlands)HighlandsBiomes.alps,
		(BiomeGenBaseHighlands)HighlandsBiomes.tundra,
		(BiomeGenBaseHighlands)HighlandsBiomes.snowMountains,
	};
	public static final BiomeGenBaseHighlands[] jungleb = {
		(BiomeGenBaseHighlands)HighlandsBiomes.tropics,
		(BiomeGenBaseHighlands)HighlandsBiomes.flyingMountains,
		(BiomeGenBaseHighlands)HighlandsBiomes.tropicalIslands,
		(BiomeGenBaseHighlands)HighlandsBiomes.rainforest,
	};
	public static final BiomeGenBaseHighlands[] hillb = {
		(BiomeGenBaseHighlands)HighlandsBiomes.highlandsb,
		(BiomeGenBaseHighlands)HighlandsBiomes.pinelands,
		(BiomeGenBaseHighlands)HighlandsBiomes.cliffs,
		(BiomeGenBaseHighlands)HighlandsBiomes.rockMountains,
		(BiomeGenBaseHighlands)HighlandsBiomes.mesa,
		(BiomeGenBaseHighlands)HighlandsBiomes.baldHill,
		(BiomeGenBaseHighlands)HighlandsBiomes.badlands,
		(BiomeGenBaseHighlands)HighlandsBiomes.desertMountains,
	};
	public static final BiomeGenBaseHighlands[] plainsb = {
		(BiomeGenBaseHighlands)HighlandsBiomes.meadow,
		(BiomeGenBaseHighlands)HighlandsBiomes.savannah
	};
	public static final BiomeGenBaseHighlands[] desertb = {
		(BiomeGenBaseHighlands)HighlandsBiomes.dunes,
		(BiomeGenBaseHighlands)HighlandsBiomes.outback,
		(BiomeGenBaseHighlands)HighlandsBiomes.sahel,
	};
	public static final BiomeGenBaseHighlands[] oceanb = {
		(BiomeGenBaseHighlands)HighlandsBiomes.lake,
		(BiomeGenBaseHighlands)HighlandsBiomes.ocean2,
		(BiomeGenBaseHighlands)HighlandsBiomes.volcanoIsland,
		(BiomeGenBaseHighlands)HighlandsBiomes.desertIsland,
		(BiomeGenBaseHighlands)HighlandsBiomes.estuary,
		(BiomeGenBaseHighlands)HighlandsBiomes.flyingMountains,
	};
/** clears the spawn lists from all highlands biomes,
* then sets it equal to the spawn lists from a similar default biome.
* only called if "Mob Mod Compatibility" is set to true.
*/
public static void mobload_biomes(){
	for(BiomeGenBaseHighlands a : forestb){
		if(a != null){
			a.setSpawnLists(
				BiomeGenBase.forest.getSpawnableList(EnumCreatureType.monster),
				BiomeGenBase.forest.getSpawnableList(EnumCreatureType.creature),
				BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.waterCreature)
			);
		}
	}
	for(BiomeGenBaseHighlands a : taigab){
		if(a != null){
			a.setSpawnLists(
				BiomeGenBase.taiga.getSpawnableList(EnumCreatureType.monster),
				BiomeGenBase.taiga.getSpawnableList(EnumCreatureType.creature),
				BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.waterCreature)
			);
		}
	}
	for(BiomeGenBaseHighlands a : snowb){
		if(a != null){
			a.setSpawnLists(
				BiomeGenBase.icePlains.getSpawnableList(EnumCreatureType.monster),
				BiomeGenBase.icePlains.getSpawnableList(EnumCreatureType.creature),
				BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.waterCreature)
			);
		}
	}
	for(BiomeGenBaseHighlands a : jungleb){
		if(a != null){
			a.setSpawnLists(
				BiomeGenBase.jungle.getSpawnableList(EnumCreatureType.monster),
				BiomeGenBase.jungle.getSpawnableList(EnumCreatureType.creature),
				BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.waterCreature)
			);
		}
	}
	for(BiomeGenBaseHighlands a : hillb){
		if(a != null){
			a.setSpawnLists(
				BiomeGenBase.extremeHills.getSpawnableList(EnumCreatureType.monster),
				BiomeGenBase.extremeHills.getSpawnableList(EnumCreatureType.creature),
				BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.waterCreature)
			);
		}
	}
	for(BiomeGenBaseHighlands a : plainsb){
		if(a != null){
			a.setSpawnLists(
				BiomeGenBase.plains.getSpawnableList(EnumCreatureType.monster),
				BiomeGenBase.plains.getSpawnableList(EnumCreatureType.creature),
				BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.waterCreature)
			);
		}
	}
	for(BiomeGenBaseHighlands a : desertb){
		if(a != null){
			a.setSpawnLists(
				BiomeGenBase.desert.getSpawnableList(EnumCreatureType.monster),
				BiomeGenBase.desert.getSpawnableList(EnumCreatureType.creature),
				BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.waterCreature)
			);
		}
	}
	for(BiomeGenBaseHighlands a : oceanb){
		if(a != null){
			a.setSpawnLists(
				BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.monster),
				BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.creature),
				BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.waterCreature)
			);
		}
	}
}


	public static void registerBlocksThaumcraft(){
		for( Block b : HighlandsBlocks.logs ){
			if (b == HighlandsBlocks.ironWood) {
				ThaumcraftApi.registerObjectTag(new ItemStack(b), new AspectList().add(Aspect.TREE, 4).add(Aspect.METAL, 2));
			}
			else {
				ThaumcraftApi.registerObjectTag(new ItemStack(b), new AspectList().add(Aspect.TREE, 4));
			}
		}
		for( Block b : HighlandsBlocks.leaves ){
			if (b == HighlandsBlocks.ironWood) {
				ThaumcraftApi.registerObjectTag(new ItemStack(b), new AspectList().add(Aspect.PLANT, 1).add(Aspect.METAL, 1));
			}
			else {
				ThaumcraftApi.registerObjectTag(new ItemStack(b), new AspectList().add(Aspect.PLANT, 1));
			}
		}
		for( Block b : HighlandsBlocks.saplings ){
			if (b == HighlandsBlocks.ironWood) {
				ThaumcraftApi.registerObjectTag(new ItemStack(b), new AspectList().add(Aspect.TREE, 1).add(Aspect.PLANT, 1).add(Aspect.PLANT, 1).add(Aspect.METAL, 1));
			}
			else {
				ThaumcraftApi.registerObjectTag(new ItemStack(b), new AspectList().add(Aspect.TREE, 1).add(Aspect.PLANT, 1).add(Aspect.PLANT, 1));
			}
		}

		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.hlplanks), new int[]{0,1,2,3}, new AspectList().add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.hlplankhalf), new int[]{0,1,2,3}, new AspectList().add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.hlplankstairs0), new AspectList().add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.hlplankstairs1), new AspectList().add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.hlplankstairs2), new AspectList().add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.hlplankstairs3), new AspectList().add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.berries), new AspectList().add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.cocoa2), new AspectList().add(Aspect.PLANT, 2).add(Aspect.SENSES, 1).add(Aspect.CROP, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.blueFlower), new AspectList().add(Aspect.PLANT, 2).add(Aspect.LIGHT, 1).add(Aspect.SENSES, 1));
		// ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.blueFlower), new AspectList().add(Aspect.PLANT, 2).add(Aspect.WATER, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.lavender), new AspectList().add(Aspect.PLANT, 2).add(Aspect.LIGHT, 1).add(Aspect.SENSES, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.cattail), new AspectList().add(Aspect.PLANT, 2).add(Aspect.CLOTH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.whiteFlower), new AspectList().add(Aspect.PLANT, 2).add(Aspect.LIGHT, 1).add(Aspect.SENSES, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.raspberryBush), new AspectList().add(Aspect.PLANT, 2).add(Aspect.CROP, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.blueberryBush), new AspectList().add(Aspect.PLANT, 2).add(Aspect.CROP, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.cattail), new AspectList().add(Aspect.PLANT, 2).add(Aspect.CLOTH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(HighlandsBlocks.thornbush), new AspectList().add(Aspect.PLANT, 2).add(Aspect.DEATH, 1));
	}


public static void registerBiomesForgeBiomeDict(){
	registerBiomeType(HighlandsBiomes.woodsMountains, Type.MOUNTAIN, Type.FOREST);
	registerBiomeType(HighlandsBiomes.highlandsb, Type.HILLS);
	registerBiomeType(HighlandsBiomes.tundra, Type.FROZEN);
	registerBiomeType(HighlandsBiomes.cliffs, Type.MOUNTAIN);
	registerBiomeType(HighlandsBiomes.pinelands, Type.HILLS);
	registerBiomeType(HighlandsBiomes.autumnForest, Type.FOREST);
	registerBiomeType(HighlandsBiomes.alps, Type.FROZEN, Type.MOUNTAIN);
	registerBiomeType(HighlandsBiomes.tallPineForest, Type.FROZEN, Type.FOREST);
	registerBiomeType(HighlandsBiomes.meadow, Type.PLAINS);
	registerBiomeType(HighlandsBiomes.savannah, Type.PLAINS);
	registerBiomeType(HighlandsBiomes.tropics, Type.JUNGLE);
	registerBiomeType(HighlandsBiomes.outback, Type.DESERT, Type.PLAINS);
	registerBiomeType(HighlandsBiomes.woodlands, Type.FOREST);
	registerBiomeType(HighlandsBiomes.bog, Type.SWAMP);
	registerBiomeType(HighlandsBiomes.redwoodForest, Type.FOREST, Type.HILLS);
	registerBiomeType(HighlandsBiomes.dunes, Type.DESERT, Type.HILLS);
	registerBiomeType(HighlandsBiomes.lowlands, Type.FOREST, Type.PLAINS);
	registerBiomeType(HighlandsBiomes.sahel, Type.DESERT, Type.PLAINS);
	registerBiomeType(HighlandsBiomes.birchHills, Type.FOREST, Type.HILLS);
	registerBiomeType( HighlandsBiomes.tropicalIslands, Type.JUNGLE, Type.WATER);
	registerBiomeType(HighlandsBiomes.rainforest, Type.JUNGLE, Type.FOREST);
	registerBiomeType(HighlandsBiomes.estuary, Type.BEACH, Type.SWAMP);
	registerBiomeType(HighlandsBiomes.badlands, Type.WASTELAND, Type.HILLS);
	registerBiomeType( HighlandsBiomes.flyingMountains, Type.MOUNTAIN, Type.MAGICAL, Type.WATER);
	registerBiomeType( HighlandsBiomes.snowMountains, Type.MOUNTAIN, Type.FROZEN);
	registerBiomeType(HighlandsBiomes.rockMountains, Type.MOUNTAIN);
	registerBiomeType(HighlandsBiomes.desertMountains, Type.MOUNTAIN, Type.DESERT);
	registerBiomeType(HighlandsBiomes.steppe, Type.PLAINS, Type.HILLS);
	registerBiomeType( HighlandsBiomes.glacier, Type.FROZEN);
	registerBiomeType( HighlandsBiomes.ocean2, Type.WATER);
	//Sub Biomes
	registerBiomeType(HighlandsBiomes.forestIsland, Type.FOREST);
	registerBiomeType( HighlandsBiomes.jungleIsland, Type.JUNGLE);
	registerBiomeType(HighlandsBiomes.desertIsland, Type.BEACH);
	registerBiomeType(HighlandsBiomes.volcanoIsland, Type.WASTELAND, Type.MOUNTAIN);
	registerBiomeType(HighlandsBiomes.snowIsland, Type.FOREST, Type.FROZEN);
	registerBiomeType(HighlandsBiomes.windyIsland, Type.PLAINS);
	registerBiomeType(HighlandsBiomes.rockIsland, Type.WASTELAND);
	registerBiomeType( HighlandsBiomes.valley, Type.FOREST);
	registerBiomeType(HighlandsBiomes.lake, Type.WATER);
	registerBiomeType( HighlandsBiomes.mesa, Type.MOUNTAIN);
	registerBiomeType(HighlandsBiomes.baldHill, Type.MOUNTAIN);
	registerBiomeType(HighlandsBiomes.oasis, Type.JUNGLE);
	registerBiomeType(HighlandsBiomes.canyon, Type.DESERT);
	//Border Biomes
	registerBiomeType(HighlandsBiomes.shrubland, Type.PLAINS, Type.HILLS);
}

private static void registerBiomeType (BiomeGenBase biome, Type ... types) {
	if (biome != null) BiomeDictionary.registerBiomeType(biome, types);
}

public static void registerBlocksForestry(){
	StringBuilder sb;
	sb = new StringBuilder();
	for (Block b : HighlandsBlocks.saplings){
		if (b==null) continue;
		//TODO: untested
		FMLInterModComms.sendMessage("Forestry", "add-farmable-sapling", "farmArboreal@" + b + ".0");
		sb.append(b).append(".0;");
	}
	FMLInterModComms.sendMessage("Forestry", "add-backpack-items", "forester@" + sb.toString());
	sb = new StringBuilder();
	for (Block b : HighlandsBlocks.logs){
		if (b==null)
			continue;
		//TODO: untested
		sb.append(b).append(".0;");
	}
	FMLInterModComms.sendMessage("Forestry", "add-backpack-items", "forester@" + sb.toString());
	FlowerManager.plainFlowers.add(new ItemStack(HighlandsBlocks.blueFlower));
	FlowerManager.plainFlowers.add(new ItemStack(HighlandsBlocks.lavender));
	FlowerManager.plainFlowers.add(new ItemStack(HighlandsBlocks.whiteFlower));
}

public static void registerRecipesForestry(){
FluidStack juice = FluidRegistry.getFluidStack("juice", 1);
if (HighlandsBlocks.berries!=null && juice!=null){
FluidStack myjuice = juice.copy();
myjuice.amount = 20;
RecipeManagers.squeezerManager.addRecipe(
10,
new ItemStack[] { new ItemStack(HighlandsBlocks.berries) },
myjuice);
}
// Forestry GameMode.EASY == 250 by default for Forestry, lets just assume this for now
for (Block b : HighlandsBlocks.saplings){
if (b==null)
continue;
ItemStack sapling = new ItemStack(b);
FluidStack biomass = FluidRegistry.getFluidStack("biomass", 1);
FluidStack water = FluidRegistry.getFluidStack("water", 1);
FluidStack honey = FluidRegistry.getFluidStack("honey", 1);
int scalar = ( b==HighlandsBlocks.ironwoodSapling ? 2 : 1);
RecipeManagers.fermenterManager.addRecipe(sapling, 250 * scalar, 1.0F, biomass, water);
if (juice!=null)
RecipeManagers.fermenterManager.addRecipe(sapling, 250 * scalar, 1.5F, biomass, juice);
if (honey!=null)
RecipeManagers.fermenterManager.addRecipe(sapling, 250 * scalar, 1.5F, biomass, honey);
}
}

/**
public static void registerBlocksMFR(){
for (Block b : HighlandsBlocks.saplings){
if (!(b instanceof BlockHighlandsSapling))
continue;
FactoryRegistry.registerPlantable((BlockHighlandsSapling)b );
FactoryRegistry.registerFertilizable((BlockHighlandsSapling)b );
}
for (Block b : HighlandsBlocks.logs){
if (!(b instanceof BlockHighlandsLog))
continue;
FactoryRegistry.registerHarvestable((BlockHighlandsLog)b);
}
for (Block b : HighlandsBlocks.leaves){
if (!(b instanceof BlockHighlandsLeaves))
continue;
FactoryRegistry.registerHarvestable((BlockHighlandsLeaves)b);
}
}
*/

/** start current code
public static void registerBiomesATG(){
 
 BiomeType land = BiomeType.LAND;
 BiomeType coast = BiomeType.COAST;
 BiomeType sea = BiomeType.SEA;
 
 //ATGBiomes.addBiomeGroup(land, "Mountains", 0.3, 0.5, 1.0, 0.75, 2.5, 10);
 ATGBiomes.addBiomeGroup(land, "Hills", 0.6, 0.3, 1.0, 0.5, 1.5, 0);
 ATGBiomes.addBiomeGroup(sea, "Island", 0.5, 0.5, 0.3, -0.5, 0.5, 0);
 
 GenModIsland genIsland = new GenModIsland();
 GenModValley genValley = new GenModValley();
 
 ATGBiomes.modGroupSuitability(land, "Hills", .2);
 ATGBiomes.modGroupSuitability(land, "Tropical Shrubland", -.3);
 ATGBiomes.modGroupSuitability(land, "Mountains", .1);
 ATGBiomes.modGroupSuitability(land, "Shrubland", -.1);
 ATGBiomes.modGroupSuitability(land, "Island", -.3);
 
 if(Config.tallPineForestGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Taiga", HighlandsBiomes.tallPineForest, 1.0);
 if(Config.dunesGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Desert", HighlandsBiomes.dunes, 1.0);
 //ATGBiomes.addBiome(land, "Forest", HighlandsBiomes.woodsMountains, 0.0); //Woodland Mountains doesn't fit with ATG
 if(Config.autumnForestGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Forest", HighlandsBiomes.autumnForest, 1.0);
 if(Config.redwoodForestGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Forest", HighlandsBiomes.redwoodForest, 1.0);
 //ATGBiomes.addBiome(land, "Forest", HighlandsBiomes.flyingMountains, 0.0); //Flying Mountains doesn't fit with ATG
 if(Config.cliffsGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Hills", HighlandsBiomes.cliffs, 1.0);
 if(Config.alpsGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Taiga", HighlandsBiomes.alps, 1.0);
 //ATGBiomes.addBiome(land, "Mountains", HighlandsBiomes.snowMountains, 1.0);
 if(Config.glacierGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Ice Plains", HighlandsBiomes.glacier, 1.0);
 if(Config.tropicsGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Jungle", HighlandsBiomes.tropics, 1.0);
 
 if(Config.rainforestGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Jungle", HighlandsBiomes.rainforest, 1.0);
 if(Config.outbackGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Desert", HighlandsBiomes.outback, 1.0);
 if(Config.badlandsGenerate.getBoolean(true)){
 ATGBiomes.addBiome(land, "Mesa", HighlandsBiomes.badlands, 1.0);
 ATGBiomes.addBiome(land, "Hills", HighlandsBiomes.badlands, 1.0);}
 //ATGBiomes.addBiome(land, "Mountains", HighlandsBiomes.desertMountains, 1.0);
 if(Config.tropicalIslandsGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Tropical Shrubland", HighlandsBiomes.tropicalIslands, 1.0);
 if(Config.savannahGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Savanna", HighlandsBiomes.savannah, 1.0);
 if(Config.sahelGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Savanna", HighlandsBiomes.sahel, 1.0);
 if(Config.meadowGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Plains", HighlandsBiomes.meadow, 1.0);
 if(Config.shrublandGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Shrubland", HighlandsBiomes.shrubland, 1.0);
 if(Config.highlandsbGenerate.getBoolean(true)){
 ATGBiomes.addBiome(land, "Hills", HighlandsBiomes.highlandsb, 1.0);
 ATGBiomes.addBiome(land, "Woodland", HighlandsBiomes.highlandsb, 1.0);}
 //ATGBiomes.addBiome(land, "Mountains", HighlandsBiomes.rockMountains, 1.0);
 if(Config.steppeGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Mesa", HighlandsBiomes.steppe, 1.0);
 if(Config.bogGenerate.getBoolean(true))
 ATGBiomes.addBiome(coast, "Swampland", HighlandsBiomes.bog, 0.5);
 if(Config.estuaryGenerate.getBoolean(true))
 ATGBiomes.addBiome(coast, "Swampland", HighlandsBiomes.estuary, 1.0);
 if(Config.pinelandsGenerate.getBoolean(true)){
 ATGBiomes.addBiome(land, "Boreal Forest", HighlandsBiomes.pinelands, 1.0);
 ATGBiomes.addBiome(land, "Hills", HighlandsBiomes.pinelands, 1.0);}
 if(Config.tundraGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Tundra", HighlandsBiomes.tundra, 1.0);
 if(Config.lowlandsGenerate.getBoolean(true)){
 ATGBiomes.addBiome(land, "Shrubland", HighlandsBiomes.lowlands, 1.0);
 ATGBiomes.addBiome(land, "Woodland", HighlandsBiomes.lowlands, 1.0);}
 if(Config.woodlandsGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Forest", HighlandsBiomes.woodlands, 1.0);
 if(Config.birchHillsGenerate.getBoolean(true))
 ATGBiomes.addBiome(land, "Woodland", HighlandsBiomes.birchHills, 0.5);
 if(Config.ocean2Generate.getBoolean(true))
 ATGBiomes.addBiome(sea, "Deep Ocean", HighlandsBiomes.ocean2, 1.0);
 */ //end current code
 
 /*
if(Config.desertIslandGenerate.getBoolean(true)){
ATGBiomes.addBiome(sea, "Island", HighlandsBiomes.desertIsland, 1.0);
ATGBiomes.addGenMod(HighlandsBiomes.desertIsland, genIsland);}
if(Config.forestIslandGenerate.getBoolean(true)){
ATGBiomes.addBiome(sea, "Island", HighlandsBiomes.forestIsland, 1.0);
ATGBiomes.addGenMod(HighlandsBiomes.forestIsland, genIsland);}
if(Config.snowIslandGenerate.getBoolean(true)){
ATGBiomes.addBiome(sea, "Island", HighlandsBiomes.snowIsland, 1.0);
ATGBiomes.addGenMod(HighlandsBiomes.snowIsland, genIsland);}
if(Config.jungleIslandGenerate.getBoolean(true)){
ATGBiomes.addBiome(sea, "Island", HighlandsBiomes.jungleIsland, 1.0);
ATGBiomes.addGenMod(HighlandsBiomes.jungleIsland, genIsland);}
if(Config.windyIslandGenerate.getBoolean(true)){
ATGBiomes.addBiome(sea, "Island", HighlandsBiomes.windyIsland, 1.0);
ATGBiomes.addGenMod(HighlandsBiomes.windyIsland, genIsland);}
if(Config.rockIslandGenerate.getBoolean(true)){
ATGBiomes.addBiome(sea, "Island", HighlandsBiomes.rockIsland, 1.0);
ATGBiomes.addGenMod(HighlandsBiomes.rockIsland, genIsland);}
//ATGBiomes.addBiome(sea, "Island", HighlandsBiomes.volcanoIsland, 1.0);
*/
 
 /*
ATGBiomes.addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.desertIsland, 1.0);
ATGBiomes.addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.forestIsland, 1.0);
ATGBiomes.addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.snowIsland, 1.0);
ATGBiomes.addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.jungleIsland, 1.0);
ATGBiomes.addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.windyIsland, 1.0);
ATGBiomes.addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.rockIsland, 1.0);
ATGBiomes.addSubBiome(HighlandsBiomes.ocean2, HighlandsBiomes.volcanoIsland, 1.0);
*/

/** current code
 if(Config.valleyGenerate.getBoolean(true) && Config.cliffsGenerate.getBoolean(true))
 ATGBiomes.addSubBiome(HighlandsBiomes.cliffs, HighlandsBiomes.valley, 1.0);
 if(Config.oasisGenerate.getBoolean(true) && Config.dunesGenerate.getBoolean(true))
 ATGBiomes.addSubBiome(HighlandsBiomes.dunes, HighlandsBiomes.oasis, 0.2);
 //ATGBiomes.addSubBiome(HighlandsBiomes.savannah, HighlandsBiomes.mesa, 0.5); //modify
 //ATGBiomes.addGenMod(HighlandsBiomes.mesa, new GenModMesa());
 if(Config.baldHillGenerate.getBoolean(true) && Config.woodlandsGenerate.getBoolean(true))
 ATGBiomes.addSubBiome(HighlandsBiomes.woodlands, HighlandsBiomes.baldHill, 0.5); //modify
 //ATGBiomes.addGenMod(HighlandsBiomes.baldHill, new GenModMesa());
 
}
*/

}
