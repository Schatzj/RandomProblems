package CCIProblems;

import java.util.*;
import java.io.*;
import java.math.*;
public class TowerOfHanio {
	//from GFG
//static void towerOfHanoi(int n, char src,char dest, char aux){
//    if (n == 0) {
//        return;
//    }
//    
//    towerOfHanoi(n - 1, src, aux, dest);
//    System.out.println("Move disk "+ n + " from rod " + src +" to rod " + dest );
//    towerOfHanoi(n - 1, aux, dest, src);
//}
// 
//// Driver code
//public static void  main(String args[]){
//    int n = 3; // Number of disks
//    towerOfHanoi(n, 'A', 'C', 'B'); // A, B and C are names of rods
//}
	
	/*
	 * My solution. it definitely makes unnecessary moves, but it appears to work. 
	 * Each peg has a role regarding where to move elements. 
	 * Keep following the rule until the peg is empty, or the move is illegal. 
	 * If the peg is empty, move to the next peg. 
	 * if the move is illegal, go to the pole you were trying to move the element to, and run its rules, until the peg is empty or an illegal move. 
	 * 
	 * Start Peg Rule: if even number of elements, move to the aux (b) peg, else move to the destination (c) peg. 
	 * aux peg rule: if even number of elements, move to the destination peg, else the start peg. 
	 * destination peg Rule: if even number of elements move to the start, else the aux. 
	 */
	public static void towerOfHanio(LinkedList<Integer> a, LinkedList<Integer> b, LinkedList<Integer> c, int towerOfInterest) {
		if(a.isEmpty() && b.isEmpty()) {
			System.out.println("Done");
			print(a, b, c);
			return;
		}
		
		if(towerOfInterest == 1) {
			int size = a.size();
			if(size < 1) {
				towerOfHanio(a, b, c, 2);
				return;
			}
			Integer element = a.pop();
			if(size % 2 == 0) {				
				boolean legalMove = isLegalMove(element, b);
				if(legalMove) {
					b.addFirst(element);
					print(a, b, c);
					towerOfHanio(a, b, c, 1);
					return;
				}else {
					a.addFirst(element);
					towerOfHanio(a, b, c, 2);
					return;
				}
			}else {
				boolean legalMove = isLegalMove(element, c);
				if(legalMove) {
					c.addFirst(element);
					print(a, b, c);
					towerOfHanio(a, b, c, 1);
					return;
				}else {
					a.addFirst(element);
					towerOfHanio(a, b, c, 3);
					return;
				}
			}
		}else if(towerOfInterest == 2) {
			int size = b.size();
			if(size < 1) {
				towerOfHanio(a, b, c, 1);
				return;
			}
			Integer element = b.pop();
			if(size % 2 == 0) {				
				boolean legalMove = isLegalMove(element, c);
				if(legalMove) {
					c.addFirst(element);
					print(a, b, c);
					towerOfHanio(a, b, c, 2);
					return;
				}else {
					b.addFirst(element);
					towerOfHanio(a, b, c, 3);
					return;
				}
			}else {
				boolean legalMove = isLegalMove(element, a);
				if(legalMove) {
					a.addFirst(element);
					print(a, b, c);
					towerOfHanio(a, b, c, 2);
					return;
				}else {
					b.addFirst(element);
					towerOfHanio(a, b, c, 1);
					return;
				}
			}
		}else {
			int size = c.size();
			if(size < 1) {
				towerOfHanio(a, b, c, 1);
				return;
			}
			Integer element = c.pop();
			if(size % 2 == 0) {				
				boolean legalMove = isLegalMove(element, a);
				if(legalMove) {
					a.addFirst(element);
					print(a, b, c);
					towerOfHanio(a, b, c, 3);
					return;
				}else {
					c.addFirst(element);
					towerOfHanio(a, b, c, 1);
					return;
				}
			}else {
				boolean legalMove = isLegalMove(element, b);
				if(legalMove) {
					b.addFirst(element);
					print(a, b, c);
					towerOfHanio(a, b, c, 3);
					return;
				}else {
					c.addFirst(element);
					towerOfHanio(a, b, c, 2);
					return;
				}
			}
		}
//		return;
	}
	
	private static void print(LinkedList<Integer> a, LinkedList<Integer> b, LinkedList<Integer> c) {
		System.out.print("a: ");
		printLL(a);
		System.out.print("b: ");
		printLL(b);
		System.out.print("c: ");
		printLL(c);
		System.out.println("");
	}

	private static boolean isLegalMove(Integer element, LinkedList<Integer> b) {
		if(b.isEmpty() || b == null || element < b.peek()) {
			return true;
		}
		return false;		
	}

	public static void printLL(LinkedList<Integer> list) {
		if(list.isEmpty() || list == null) {
			System.out.print("     ");
			return;
		}
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next() + ", ");
		}
		System.out.print("     ");
	}
	
	public static void  main(String args[]){
	  LinkedList<Integer> a = new LinkedList<Integer>();
	  a.add(1);
	  a.add(2);
	  a.add(3);
	  a.add(4);
	  LinkedList<Integer> b = new LinkedList<Integer>();
	  LinkedList<Integer> c = new LinkedList<Integer>();
	  towerOfHanio(a, b, c, 1);
	}
}
