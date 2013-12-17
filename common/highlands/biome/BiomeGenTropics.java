package highlands.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;
import highlands.worldgen.WorldGenTreeCanopy;

public class BiomeGenTropics extends BiomeGenBaseHighlands
{
	private BiomeDecoratorHighlands biomedec;


	public BiomeGenTropics(int par1)
	{
        super(par1);
        int trees = 20;
	    int grass = 25;
	    int flowers = 4;
	    int plants = 3;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);

	    biomedec.generateLakes = true;
	    
        this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
        
        this.maxHeight = 0.4F;
        this.minHeight = 0.1F;
        this.temperature = 1.2F;
        this.rainfall = 1.0F;
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.leafyFern.blockID);
	}


	 
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(2) == 0 ? 
        		(par1Random.nextInt(4) == 0 ? new WorldGenTreeCanopy(13, 6, false, true) :
        			new WorldGenTreeCanopy(13, 6, false, false)) :
        			new WorldGenHighlandsShrub(3, 0));
    }
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Block.tallGrass.blockID, 2);
    }

    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorate(par1World, par2Random, par3, par4);
        WorldGenVines var5 = new WorldGenVines();

        for (int var6 = 0; var6 < 50; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16) + 8;
            byte var8 = 64;
            int var9 = par4 + par2Random.nextInt(16) + 8;
            var5.generate(par1World, par2Random, var7, var8, var9);
        }
        for (int var6 = 0; var6 < 50; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16) + 8;
            byte var8 = 36;
            int var9 = par4 + par2Random.nextInt(16) + 8;
            var5.generate(par1World, par2Random, var7, var8, var9);
        }
        
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 8, biomedec.redstoneGen, 0, 16);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.lapisGen, 0, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 10, biomedec.HLsand, 0, 64);
    }
    
}
