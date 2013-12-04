package highlands;

import java.util.Random;
import ttftcuts.atg.api.IGenMod;


public class GenModMesa implements IGenMod {
	
	@Override
	public int modify(int height, Random random, double rawHeight) {
		return height + 7;
	}
	
	@Override
	public double noiseFactor() {
		return 10.0;
	}
}
