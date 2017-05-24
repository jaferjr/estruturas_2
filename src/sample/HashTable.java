package sample;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.LinkedList;
import java.util.List;

public class HashTable {

	// Fields hash
	private static final int DEFAUL_TABLE_SIZE = 101;
	private List<Integer>[] theLists;
	private int currentSize;

	//Fields Draw
	private Group group;
	private TextFlow[] textFlows;

	public HashTable() {
//		this(DEFAUL_TABLE_SIZE);
	}

	public HashTable(int size, Group parent) {
	    this.group = parent;
//		theLists = new LinkedList[nextPrime(size)];
		theLists = new LinkedList[size];
		for(int i=0; i< theLists.length; i++){
			theLists[i]= new LinkedList<>();
			Label l = new Label();
			textFlows[i] = new TextFlow();
			textFlows[i].setLayoutX(85);
			textFlows[i].setId(i+"");
			textFlows[i].setLayoutY((i+1.25)*50);
			l.setText(Integer.toString(i));
			l.setLayoutX(10);
			l.setLayoutY((i+1.25)*50);
			Rectangle r = new Rectangle();
			r.setLayoutX(30);
			r.setLayoutY((i+1)*50);
			r.setWidth(50);
			r.setHeight(50);
			r.setFill(Color.TRANSPARENT);
			r.setStroke(Color.BLUE);
			group.getChildren().addAll(r,l,textFlows[i]);
		}

	}
	
//	private static int nextPrime(int n) {
//		if(n%2==0) n++;
//		for(;isPrime(n);n+=2)
//			;
//		return 0;
//	}
	
	private static boolean isPrime( int n){
		if (n == 2||n==3 ) return true;
		if(n == 1 || n%2==0) return false;
		for(int i = 3; i*i <= n ; i+=2){
			if( n%i == 0) return false;
		}
		return true;
	}

	public void insert(int x){

	    TextFlow label;

	    List<Integer> lista = new LinkedList<>();
		
		List<Integer> whichList = theLists[myHash(x)];
		if(!whichList.contains(x)){
//			whichList.add(x);
            lista.add(x);
            theLists[myHash(x)] = lista;
            label = textFlows[myHash(x)];
            Text text = new Text("->");
            text.setStyle("-fx-font-size: 20px");
            Text element = new Text(x+"");
            element.setStyle("-fx-font-size: 20px");
            label.getChildren().addAll(text, element);
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
