package highlands.biome;

import java.util.Random;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenBaldHill extends BiomeGenBaseHighlands
{
	
	private BiomeDecoratorHighlands biomedec;

	public BiomeGenBaldHill(int par1){
		super(par1);
		
		int trees = 0;
	    int grass = 4;
	    int flowers = 1;
	    int plants = 2;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
        
        this.topBlock = (byte)Block.grass.blockID;
        this.fillerBlock = (byte)Block.dirt.blockID;
        
        this.minHeight = 1.0F;
        this.maxHeight = 1.0F;
        this.temperature = 0.5F;
        this.rainfall = 0.7F;
        
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)(rand.nextInt(3) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.whiteFlower.blockID)
				: (rand.nextInt(2) == 0 ? new WorldGenSmallPlants(HighlandsBlocks.raspberryBush.blockID)
				: new WorldGenSmallPlants(HighlandsBlocks.cotton.blockID)));
	}

	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)(par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees);
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
        
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.ironGen, 0, 64);
        
    }
	    
}

