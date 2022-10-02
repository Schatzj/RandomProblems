package helperClasses;

public class CoinHelper {
	
	public final static int QUERTER = 25;
	public final static int DIME = 10;
	public final static int NICKEL = 5;
	public final static int PENNY = 1;
	
	private int numQuerters;
	private int numDimes;
	private int numNickels;
	private int numPennies;
	
	public CoinHelper() {
		numQuerters = 0;
		numDimes = 0;
		numNickels = 0;
		numPennies = 0; 
	}
	 
	public CoinHelper(int numQuerters, int numDimes, int numNickels, int numPennies) {
		super();
		this.numQuerters = numQuerters;
		this.numDimes = numDimes;
		this.numNickels = numNickels;
		this.numPennies = numPennies;
	}
	
	public CoinHelper(int target) {
		this.numQuerters = target/QUERTER;
		target = ((target/QUERTER) > 0) ? target%QUERTER : target;
		this.numDimes = target/DIME;
		target = ((target/DIME) > 0) ? target%DIME : target;
		this.numNickels = target/NICKEL;
		target = ((target/NICKEL) > 0) ? target%NICKEL : target;
		this.numPennies = target/PENNY;
	}

	public int getNumQuerters() {
		return numQuerters;
	}

	public void setNumQuerters(int numQuerters) {
		this.numQuerters = numQuerters;
	}

	public int getNumDimes() {
		return numDimes;
	}

	public void setNumDimes(int numDimes) {
		this.numDimes = numDimes;
	}

	public int getNumNickels() {
		return numNickels;
	}

	public void setNumNickels(int numNickels) {
		this.numNickels = numNickels;
	}

	public int getNumPennies() {
		return numPennies;
	}

	public void setNumPennies(int numPennies) {
		this.numPennies = numPennies;
	}
	
	public int getTotal() {
		int total = 0; 
		total += (getNumQuerters() * 25);
		total += (getNumDimes() * 10);
		total += (getNumNickels() * 5);
		total += (getNumPennies());
		
		return total;
	}
	
	public int getNumberOFTargetCoins(int target) {
		switch (target) {
		case QUERTER: {
			return getNumQuerters();
		} case DIME: {
			return getNumDimes();
		} case NICKEL: {
			return getNumNickels();
		} case PENNY: {
			return getNumPennies();
		}
		default:
			return 0;
		}
	}
	
	public CoinHelper createCopy() {
		return new CoinHelper(this.numQuerters, this.numDimes, this.numNickels, this.numPennies);
	}
	
	@Override
	public String toString() {
		return this.numQuerters + " | " + this.numDimes + " | " + numNickels + " | " + numPennies;
	}
	
	public boolean reduceAndSolve(int coinToReduce, int target) {
		switch(coinToReduce) {
			case QUERTER: {
				this.numQuerters--;
				if(this.numQuerters < 0) 
					return false;
				break;
			} case DIME: {
				this.numDimes--;
				if(this.numDimes < 0) 
					return false;
				break;
			} case NICKEL: {
				this.numNickels--;
				if(this.numNickels < 0) 
					return false;
				break;
			} case PENNY: {
				this.numPennies--;
				if(this.numPennies < 0) 
					return false;
				break;
			}
			default:
				return false;
		}
		
		if(coinToReduce != QUERTER) {
			this.numQuerters = this.numQuerters + ((target - this.getTotal()) / QUERTER); 
		}
		if(coinToReduce != DIME) {
			this.numDimes = this.numDimes + ((target - this.getTotal()) / DIME); 
		}
		if(coinToReduce != NICKEL) {
			this.numNickels = this.numNickels + ((target - this.getTotal()) / NICKEL); 
		}
		if(coinToReduce != PENNY) {
			this.numPennies = this.numPennies + ((target - this.getTotal()) / PENNY); 
		}
		
		if(this.getTotal() == target) {
			return true;
		}else {
			return false;
		}
	}
	
	public int findSmallerTargetCoin(int currentTarget) {
		switch (currentTarget) {
		case QUERTER: {
			return DIME;
		} case DIME: {
			return NICKEL;
		} case NICKEL: {
			return PENNY;
		} case PENNY: {
			return 0;
		}
		default:
			return 0;
		}
	}
}
