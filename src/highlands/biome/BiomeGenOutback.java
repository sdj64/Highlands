package highlands.biome;

import java.util.Random;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.worldgen.WorldGenHighlandsGroundcover;
import highlands.worldgen.WorldGenHighlandsShrub;

public class BiomeGenOutback extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.1F, 0.3F);
    private static final int trees = 1, grass = 10 ,flowers = 0;
	public BiomeGenOutback(int par1){
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers));

        biomedec.deadBushPerChunk = 40;
        biomedec.cactiPerChunk = 4;
        this.setHeight(biomeHeight);
        this.temperature = 1.6F;
        this.rainfall = 0.1F;
        this.treeGenCache = new WorldGenHighlandsShrub(0, 0);
        this.genCache = new WorldGenHighlandsGroundcover(Blocks.tallgrass, 1, 2);
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sand;

        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
    }
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return this.genCache;
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return this.treeGenCache;
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 2, biomedec.goldGen, 0, 32);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 10, biomedec.HLsand, 0, 64);

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

    @Override
    public int getBiomeFoliageColor(int tempA, int tempB, int tempC){
        return getModdedBiomeFoliageColor(0xA6C968);
    }

    @Override
    public int getBiomeGrassColor(int tempA, int tempB, int tempC)
    {
        return getModdedBiomeGrassColor(0xEEE980);
    }
}







