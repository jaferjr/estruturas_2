package sample;

import java.util.LinkedList;
import java.util.List;

public class HashTable {
	
	private static final int DEFAUL_TABLE_SIZE = 101;
	private List<Integer>[] theLists;
	private int currentSize;

	public HashTable() {
		this(DEFAUL_TABLE_SIZE);
	}

	public HashTable(int size) {
//		theLists = new LinkedList[nextPrime(size)];
		theLists = new LinkedList[size];
		for(int i=0; i< theLists.length; i++)
			theLists[i]= new LinkedList<>();
	}
	
	private static int nextPrime(int n) {
		if(n%2==0) n++;
		for(;isPrime(n);n+=2)
			;
		return 0;
	}
	
	private static boolean isPrime( int n){
		if (n == 2||n==3 ) return true;
		if(n == 1 || n%2==0) return false;
		for(int i = 3; i*i <= n ; i+=2){
			if( n%i == 0) return false;
		}
		return true;
	}

	public void insert(int x){
		
		List<Integer> whichList = theLists[myHash(x)];
		if(!whichList.contains(x)){
			whichList.add(x);

			// to do inserir condição para rehash
		}
		
	}
	
	public void remove(int x){
		List<Integer> whichList = theLists[myHash(x)];
		if(whichList.contains(x)){
			whichList.remove(x);
			currentSize--;
		}
	}
	/*
	 * Busca
	 */
	public boolean contains(int x){
		List<Integer> whichList = theLists[myHash(x)];		
		return whichList.contains(x);
	}
	
	public void makeEmpty(){
		for(int i=0 ; i < theLists.length; i++)
			theLists[i].clear();
		currentSize =0;
	}
	
	private int myHash(int x){
		return x % theLists.length;
	}
	
	public void printHash(){
		for(int i=0; i< theLists.length; i++){
			for(int j: theLists[i]){
				System.out.println("Lista["+i+"] : "+j);
			}
			
		}
		
	}

    public int getSize() {
        return theLists.length;
    }
	

}
