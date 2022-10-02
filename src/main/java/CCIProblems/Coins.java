package CCIProblems;

import java.util.ArrayList;
import java.util.List;

import helperClasses.CoinHelper;

/*
 * My solution seems bad and does not work, and at the moment I don't care enough to do anything about it. 
 * Here is something to compare against: https://illuminations.nctm.org/uploadedFiles/Content/Lessons/Resources/preK-2/561-AS-WaystoMake25KEY.pdf
 */
public class Coins {
	
	public static List<CoinHelper> calculateCoins(int target){
		List<CoinHelper> result = new ArrayList<CoinHelper>();
		
		return calculateCoins(target, result, CoinHelper.QUERTER);
	}
	
	private static List<CoinHelper> calculateCoins(int target, List<CoinHelper> result, int targetCoin) {
		CoinHelper previousSolution;
		if(result.isEmpty() == false) {
			previousSolution = result.get(result.size() - 1);
		}else {
			CoinHelper firstSolution = new CoinHelper(target);
			if(firstSolution.getTotal() == target) {
				result.add(firstSolution);
			}else {
				return result;
			}
			previousSolution = result.get(result.size() - 1);
		}
		
		CoinHelper newSolution = previousSolution.createCopy();
		if(previousSolution.getNumberOFTargetCoins(targetCoin) > 0) {
			if(newSolution.reduceAndSolve(newSolution.findSmallerTargetCoin(targetCoin), target)) {
				result.add(newSolution);
				calculateCoins(target, result, newSolution.findSmallerTargetCoin(targetCoin));
			}else {
				newSolution = previousSolution.createCopy();
				if(newSolution.reduceAndSolve(targetCoin, target)) {
					result.add(newSolution);
					calculateCoins(target, result, targetCoin);
				}
			}
		}
			
//		newSolution = result.get(result.size() - 1).createCopy();
		int newTargetCoin = newSolution.findSmallerTargetCoin(targetCoin);
		if(newTargetCoin == 0) {
			return result;
		}
		calculateCoins(target, result, newTargetCoin);
		
		return result;
	}

	public static void main(String[] args) {
//		CoinHelper helper = new CoinHelper(25);
//		System.out.println(helper);
//		helper = new CoinHelper(15);
//		System.out.println(helper);
//		helper = new CoinHelper(9);
//		System.out.println(helper);
		List<CoinHelper> result = calculateCoins(25);
		result.forEach(r -> System.out.println(r));
	}
}
