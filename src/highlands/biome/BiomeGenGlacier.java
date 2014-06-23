package highlands.biome;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.Highlands;
import highlands.worldgen.WorldGenHighlandsShrub;

public class BiomeGenGlacier extends BiomeGenBaseHighlands
{
    private static final Height biomeHeight = new Height(1.0F, 0.7F);

	public BiomeGenGlacier(int par1)
    {
        super(par1);
        int trees = -999;
	    int grass = 0;
	    int flowers = 0;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
	    
        this.spawnableCreatureList.clear();
        this.topBlock = Blocks.snow;
        this.fillerBlock = Blocks.ice;
        this.setHeight(biomeHeight);
        this.temperature = 0.0F;
        this.rainfall = 0.3F;
        
        this.setEnableSnow();
    }
    
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)(new WorldGenHighlandsShrub(1, 1));
        
        //par1Random.nextInt(2) == 0 ? new WorldGenHighlandsShrub(1, 1) : new WorldGenTallGrass(Block.tallGrass.blockID, 1)
    }
    
    @Override
	public void decorate(World world, Random random, int x, int z) {
		BiomeGenBaseHighlands biome = this;
		this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
		((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 20, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLice, 0, 128);
		((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 20, this.theBiomeDecorator.ironGen, 0, 64);
    }
    
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1)
    {
    	if(Highlands.skyColorFlag)return 0xC6E3FF;
    	else return super.getSkyColorByTemp(par1);
    }
}
