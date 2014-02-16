package highlands.biome;

import java.util.Random;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsBigTree;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenBog extends BiomeGenBaseHighlands
{
	private BiomeDecoratorHighlands biomedec;
	private static final Height biomeHeight = new Height(-0.2F, 0.1F);

	public BiomeGenBog(int par1){
		super(par1);
		int trees = 3;
	    int grass = 2;
	    int flowers = 0;
	    int plants = 6;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
	    biomedec.generateLakes = true;
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;
        this.setHeight(biomeHeight);
        this.temperature = 0.7F;
        this.rainfall = 1.2F;
        this.waterColorMultiplier = 0x84FF38;
	        
	}
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.cattail);
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
        return (WorldGenerator)(par1Random.nextInt(3) == 0? this.worldGeneratorSwamp : 
        	(par1Random.nextInt(3) == 0? new WorldGenHighlandsBigTree(false, false, 0, 0, 1, 0) : this.worldGeneratorBigTree));
    }

    public void decorate(World par1World, Random par2Random, BiomeGenBaseHighlands biome, int par3, int par4)
    {
        biomedec.decorate(par1World, par2Random, biome, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.coalGen, 0, 128);
    }
    
    
    @SideOnly(Side.CLIENT)

    public int getBiomeGrassColor()
    {
    	//TODO- all this commented out stuff does nothing... right?
//        double var1 = (double)this.getFloatTemperature();
//        double var3 = (double)this.getFloatRainfall();
        //return ((ColorizerGrass.getGrassColor(var1, var3) & 16711422) + 5115470) / 2;
        return 0x545B33;
    }

    /*
    @SideOnly(Side.CLIENT)

    public int getBiomeFoliageColor()
    {
        double var1 = (double)this.getFloatTemperature();
        double var3 = (double)this.getFloatRainfall();
        return ((ColorizerFoliage.getFoliageColor(var1, var3) & 16711422) + 5115470) / 2;
    }
    */
}











