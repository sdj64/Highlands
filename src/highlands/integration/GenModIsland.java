package highlands.integration;

import java.util.Random;

import ttftcuts.atg.api.IGenMod;

public class GenModIsland implements IGenMod{

	private final int SEA_LEVEL = 62;
	private final int OCEAN_FLOOR = 55;
	
	@Override
	public int modify(int height, Random random, double rawHeight) {
		int newHeight = height;
		if(height < SEA_LEVEL){
			newHeight = SEA_LEVEL + height - OCEAN_FLOOR;
		}
		return newHeight;
	}

	@Override
	public double noiseFactor() {
		return 1;
	}
	
	
}
