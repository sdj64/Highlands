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
import highlands.worldgen.BiomeDecoratorHighlands;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenHighlands extends BiomeGenBaseHighlands
{
	
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenHighlands(int par1)
	    {
	        super(par1);
	        
	        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
	        
		    int trees = 3;
		    int grass = 12;
		    int flowers = 0;
		    int plants = 4;
		    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
	        
		    biomedec.generateLakes = true;
		    
	        this.minHeight = 0.5F;
	        this.maxHeight = 1.2F;
	        this.temperature = 0.6F;
	        this.rainfall = 0.2F;
	        
	    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(3) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.thornbush.blockID)
				: (rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.raspberryBush.blockID)
				: new WorldGenSmallPlants(HighlandsBlocks.lavender.blockID)));
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
	        int var5 = 3 + par2Random.nextInt(6);

	        for (int var6 = 0; var6 < var5; ++var6)
	        {
	            int var7 = par3 + par2Random.nextInt(16);
	            int var8 = par2Random.nextInt(28) + 4;
	            int var9 = par4 + par2Random.nextInt(16);
	            int var10 = par1World.getBlockId(var7, var8, var9);

	            if (var10 == Block.stone.blockID)
	            {
	            	par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
	            }
	        }
	        
	        HighlandsMain.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.coalGen, 0, 128);
	    }
}
