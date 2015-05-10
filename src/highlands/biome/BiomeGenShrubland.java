package highlands.biome;

import java.util.Random;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenShrubland extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.1F, 0.3F);
    private static final int trees = 2 ,grass = 12, flowers = 2;

	public BiomeGenShrubland(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
	    biomedec.generateLakes = true;
	    this.setHeight(biomeHeight);
        this.temperature = 0.6F;
        this.rainfall = 0.5F;
        this.treeGenCache = new WorldGenHighlandsShrub(0, 0);
        this.smallPlantsGenCache = new WorldGenSmallPlants(Blocks.air);
    }

    @Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		int i = rand.nextInt(9);
		switch(i){
		case 0: return this.smallPlantsGenCache.setPlant(HighlandsBlocks.blueFlower);
		case 1: return this.smallPlantsGenCache.setPlant(HighlandsBlocks.leafyFern);
		case 2: return this.smallPlantsGenCache.setPlant(HighlandsBlocks.whiteFlower);
		case 3: return this.smallPlantsGenCache.setPlant(HighlandsBlocks.cattail);
		case 4: return this.smallPlantsGenCache.setPlant(HighlandsBlocks.lavender);
		case 5: return this.smallPlantsGenCache.setPlant(HighlandsBlocks.raspberryBush);
		case 6: return this.smallPlantsGenCache.setPlant(HighlandsBlocks.blueberryBush);
		case 7: return this.smallPlantsGenCache.setPlant(HighlandsBlocks.thornbush);
		case 8: return this.smallPlantsGenCache.setPlant(HighlandsBlocks.cotton);
		default: return null;
		}
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (par1Random.nextInt(3) != 0 ? this.treeGenCache : new WorldGenTrees(false, 2 + par1Random.nextInt(3), 0, 0, false));
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
    }
}
