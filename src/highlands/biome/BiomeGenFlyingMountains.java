package highlands.biome;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenFlyingMountains extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(-1.5F, 7.0F);
    private static final int trees = 20, grass = 10, flowers = 0, plants = 4;
    public BiomeGenFlyingMountains(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers, plants));
        this.setHeight(biomeHeight);
        this.temperature = 0.7F;
        this.rainfall = 1.2F;
        this.spawnableCreatureList.clear();
        this.treeGenCache = new WorldGenHighlandsShrub(0, 0);
        this.genCache = new WorldGenTallGrass(Blocks.tallgrass, 2);
        this.smallPlantsGenCache = new WorldGenSmallPlants(HighlandsBlocks.blueFlower);
    }

    @Override
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return this.smallPlantsGenCache;
	}

	/**
    * Gets a WorldGen appropriate for this biome.
     */
    @Override
	public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
    	return this.treeGenCache;
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return this.genCache;
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.diamondGen, 0, 16);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.HLwater, 0, 128);
        
        //random water sources on top of the mountains
        for(int i = 0; i < 16; i++){
    		for(int j = 0; j < 16; j++){
                int a = par2Random.nextInt(10);
    			if(a == 9 && par1World.getBiomeGenForCoords(par3+i, par4+j) == HighlandsBiomes.flyingMountains){
	    			int topY = 128;
	    			Block var11 = par1World.getBlock(par3+i, topY, par4+j);
	    	        while(topY > 0 && (var11.isAir(par1World, par3+i, topY, par4+j) || var11.isLeaves(par1World, par3+i, topY, par4+j))){
                        --topY;
                        var11 = par1World.getBlock(par3+i, topY, par4+j);
	    	        }
	    	        if(topY > 65 && par2Random.nextInt(10) == 0){
		    			if(par1World.getBlock(par3+i, topY, par4+j).isAir(par1World, par3+i, topY, par4+j))
                            topY--;
                        par1World.setBlock(par3+i, topY, par4+j, Blocks.water, 0, 3);
                        par1World.setBlock(par3+i, topY+1, par4+j, Blocks.air, 0, 3);
	    	        }
	    		}
    		}
    	}
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeFoliageColor(int tempA, int tempB, int tempC){
    	return getModdedBiomeFoliageColor(0x00BA78);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int tempA, int tempB, int tempC)
    {
        return getModdedBiomeGrassColor(0x00BA78);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1)
    {
    	if(HighlandsMain.skyColorFlag)
            return 0x6868FF;
    	else
            return super.getSkyColorByTemp(par1);
    }
}
