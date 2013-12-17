package highlands.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenShrubland extends BiomeGenBaseHighlands
{
	
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenShrubland(int par1)
    {
        super(par1);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        
	    int trees = 2;
	    int grass = 12;
	    int flowers = 2;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers);
        
	    biomedec.generateLakes = true;
	    
        this.minHeight = 0.1F;
        this.maxHeight = 0.3F;
        this.temperature = 0.6F;
        this.rainfall = 0.5F;
        
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		int i = rand.nextInt(9);
		switch(i){
		case 0: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.blueFlower.blockID);
		case 1: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.leafyFern.blockID);
		case 2: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.whiteFlower.blockID);
		case 3: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.cattail.blockID);
		case 4: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.lavender.blockID);
		case 5: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.raspberryBush.blockID);
		case 6: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.blueberryBush.blockID);
		case 7: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.thornbush.blockID);
		case 8: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.cotton.blockID);
		default: return (WorldGenerator)new WorldGenSmallPlants(0);
		}
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) != 0 ? new WorldGenHighlandsShrub(0, 0) : new WorldGenTrees(false, 2 + par1Random.nextInt(3), 0, 0, false));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorate(par1World, par2Random, par3, par4);
    }
}
