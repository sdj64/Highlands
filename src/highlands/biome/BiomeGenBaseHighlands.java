package highlands.biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class BiomeGenBaseHighlands extends BiomeGenBase
{
	public ArrayList<BiomeGenBase> subBiomes;
    public final BiomeDecoratorHighlands biomedec;

	public BiomeGenBaseHighlands(int a, BiomeDecoratorHighlands decoratorHighlands){
		super(a);
        biomedec = decoratorHighlands;
        theBiomeDecorator = decoratorHighlands;
		subBiomes = new ArrayList<BiomeGenBase>();
	}

	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
        return null;
    }
	
	public void setSpawnLists(List monster, List creature, List waterCreature){
		this.spawnableCreatureList = creature;
		this.spawnableMonsterList = monster;
		this.spawnableWaterCreatureList = waterCreature;
	}

    @Override
    public BiomeDecorator createBiomeDecorator(){
        return biomedec;
    }
}
