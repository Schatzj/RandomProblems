/*
 * This checks to see if two strings are one CRUD operation apart from each other
 *  from Cracking the coding interview test question
 */
package CCIProblems;

public class SimilarString {
    public static boolean doWork(String a, String b) {
        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();
        int differences = 0;
        
        if(charA.length < charB.length -1) {
        	return false;
        }
        
        for(int i = 0; i<charA.length; i++) {
        	if(i < charB.length) {
        		if(charA[i] != charB[i]){
        			if(i + differences < charB.length && charA[i] != charB[i + differences]) {
        				differences++;	
        			}
        		}
        	}else {
        		differences++;
        	}
        	
        	//short circuit incase a is much larger than B
        	if(differences > 2) {
        		break;
        	}
        }
        
        if(differences > 1) {
        	if(charA.length != charB.length) {
        		differences--;
        	}
        }
    	
    	return differences < 2;
    }
    
    public static void main(String[] args) {
    	System.out.println(doWork("Pale", "Ple"));
    	System.out.println(doWork("Pales", "Pale"));
    	System.out.println(doWork("Pale", "bale"));
    	System.out.println(doWork("Pale", "bake"));
    	System.out.println(doWork("Palesings", "bake"));
    	System.out.println(doWork("Pale", "bakeing"));
    }
}
