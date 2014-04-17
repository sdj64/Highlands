package highlands.biome;

import java.util.Random;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenHighlands extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.5F, 1.2F);
	private static final int trees = 3, grass = 12, flowers = 0, plants = 4;
	public BiomeGenHighlands(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers, plants));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        biomedec.generateLakes = true;
        this.setHeight(biomeHeight);
        this.temperature = 0.6F;
        this.rainfall = 0.2F;
        this.treeGenCache = new WorldGenHighlandsShrub(0, 0);
        this.smallPlantsGenCache = new WorldGenSmallPlants(HighlandsBlocks.raspberryBush);
    }

    @Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (rand.nextInt(3) == 0 ? this.smallPlantsGenCache.setPlant(HighlandsBlocks.thornbush)
				: (rand.nextInt(2) == 0 ? this.smallPlantsGenCache.setPlant(HighlandsBlocks.raspberryBush)
				: this.smallPlantsGenCache.setPlant(HighlandsBlocks.lavender)));
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
        biomedec.genOreHighlandsNoCheck(par1World, par2Random, par3, par4, 3 + par2Random.nextInt(6), biomedec.HLemerald, 4, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.coalGen, 0, 128);
    }
}
