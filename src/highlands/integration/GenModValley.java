package highlands.integration;

import java.util.Random;

import ttftcuts.atg.api.IGenMod;

public class GenModValley implements IGenMod{

	
	@Override
	public int modify(int height, Random random, double rawHeight) {
		int newHeight = (int)(height*.75);
		return newHeight;
	}

	@Override
	public double noiseFactor() {
		return 1;
	}
	
	
}
