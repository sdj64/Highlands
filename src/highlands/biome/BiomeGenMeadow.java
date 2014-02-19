package highlands.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreePoplar;

public class BiomeGenMeadow extends BiomeGenBaseHighlands
{
    private BiomeDecoratorHighlands biomedec;
    private static final Height biomeHeight = new Height(0.1F, 0.1F);

	public BiomeGenMeadow(int par1)
    {
        super(par1);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        
	    int trees = 0;
	    int grass = 15;
	    int flowers = 8;
	    int plants = 4;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
        
        this.theBiomeDecorator.generateLakes = false;
        this.setHeight(biomeHeight);
        
        this.temperature = 0.6F;
        this.rainfall = 0.8F;
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.cotton)
				: new WorldGenSmallPlants(HighlandsBlocks.lavender));
	}
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
	    return new WorldGenTallGrass(Blocks.tallgrass, 1);
	}
    
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)new WorldGenTreePoplar(10, 4, false);
    }
    
    public void decorate(World par1World, Random par2Random, BiomeGenBaseHighlands biome, int par3, int par4)
    {
        biomedec.decorate(par1World, par2Random, biome, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.diamondGen, 0, 16);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 10, biomedec.HLwater, 0, 64);
    }

}
