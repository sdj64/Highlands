package highlands.biome;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.Highlands;
import highlands.worldgen.WorldGenHighlandsGroundcover;
import highlands.worldgen.WorldGenHighlandsShrub;

public class BiomeGenOutback extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.1F, 0.3F);

	public BiomeGenOutback(int par1){
			super(par1);
			
			int trees = 1;
		    int grass = 10;
		    int flowers = 0;
		    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
		    
		    this.theBiomeDecorator.deadBushPerChunk = 40;
		    this.theBiomeDecorator.cactiPerChunk = 4;
		    this.setHeight(biomeHeight);
	        this.temperature = 1.6F;
	        this.rainfall = 0.1F;
	        
	        this.topBlock = Blocks.sand;
	        this.field_150604_aj = 1; // metadata ?
	        this.fillerBlock = Blocks.sand;
	        
	        this.spawnableCreatureList.clear();
	        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
	        
	    }
	
		

	    /**
	     * Gets a WorldGen appropriate for this biome.
	     */
	    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	    {
	        return new WorldGenHighlandsGroundcover(Blocks.tallgrass, 1, 2);
	    }
	    
	    @Override
	    public WorldGenAbstractTree func_150567_a(Random par1Random)
	    {
	        return (WorldGenAbstractTree)new WorldGenHighlandsShrub(0, 0);
	    }

	    @Override
		public void decorate(World world, Random random, int x, int z) {
	    	BiomeGenBaseHighlands biome = this;
			this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
	    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 2, this.theBiomeDecorator.goldGen, 0, 32);
	        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 10, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLsand, 0, 64);
	        
	        //the code for the random half grass
	        /*
	    	for(int i = 0; i < 16; i++){
	    		for(int j = 0; j < 16; j++){
	    			if(par1World.getBiomeGenForCoords(par3+i, par4+j) == HighlandsMain.outback){
		    			int topY = 128;
		    			int var11;
		    	        for (boolean var6 = false; ((var11 = par1World.getBlockId(par3+i, topY, par4+j)) == 0 || var11 == Block.leaves.blockID) && topY > 0; --topY)
		    	        {
		    	            ;
		    	        }
		    			//System.out.println("the top block is id" + par1World.getBlockId(par3+i, topY, par4+j));
		    			if(par1World.getBlockId(par3+i, topY, par4+j) == 0)topY--;
		    			if(par1World.getBlockId(par3+i, topY, par4+j) == Block.sand.blockID && par2Random.nextInt(2) == 0){
		    				par1World.setBlock(par3+i, topY, par4+j, Block.grass.blockID);
		    				topY++;
		    				if(par2Random.nextInt(3) != 0 && par1World.getBlockId(par3+i, topY, par4+j) == 0){
		    					par1World.setBlockAndMetadata(par3+i, topY, par4+j, Block.tallGrass.blockID, 1);
		    				}
		    			}
		    		}
	    		}
	    	}
	    	*/
	    	
	    	
	    }
	    
	    //TODO- could break all the things- has no @Override
	    public int getBiomeFoliageColor(){
	    	return 0xA6C968;
	    }
	    
	    @SideOnly(Side.CLIENT)
	    @Override
	    public int getBiomeGrassColor(int x, int y, int z)
	    {
	        return getModdedBiomeGrassColor(0xEEE980);
	    }
}







