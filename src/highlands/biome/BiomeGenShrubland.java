package highlands.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
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
	private static final Height biomeHeight = new Height(0.1F, 0.3F);
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
	    this.setHeight(biomeHeight);
        this.temperature = 0.6F;
        this.rainfall = 0.5F;
        
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		int i = rand.nextInt(9);
		switch(i){
		case 0: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.blueFlower);
		case 1: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.leafyFern);
		case 2: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.whiteFlower);
		case 3: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.cattail);
		case 4: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.lavender);
		case 5: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.raspberryBush);
		case 6: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.blueberryBush);
		case 7: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.thornbush);
		case 8: return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.cotton);
		default: return (WorldGenerator)new WorldGenSmallPlants(Blocks.air);
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
        return new WorldGenTallGrass(Blocks.tallgrass, 1);
    }

    public void decorate(World par1World, Random par2Random, BiomeGenBaseHighlands biome, int par3, int par4)
    {
        biomedec.decorate(par1World, par2Random, biome, par3, par4);
    }
}
