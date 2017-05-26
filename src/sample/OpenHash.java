package sample;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.LinkedList;
import java.util.List;

public class OpenHash implements Structure{

    // Fields hash
    private static final int DEFAUL_TABLE_SIZE = 101;
    private int size;
    private List<Integer>[] theLists;
    private int currentSize;

    //Fields Draw
    private Group group;
    private TextFlow[] textFlows;
    private Label[] labels;

    private int fatorCarga;
    private int fatorBalanceamento;


    public OpenHash(int size, Group group) {
        fatorBalanceamento = 0;
        fatorCarga = 0;
        textFlows = new TextFlow[size];
        this.group = group;
        theLists = new LinkedList[size];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
            Label l = new Label();
            textFlows[i] = new TextFlow();
            textFlows[i].setLayoutX(85);
            textFlows[i].setId(i + "");
            textFlows[i].setLayoutY((i + 1.25) * 50);
            l.setText(Integer.toString(i));
            l.setLayoutX(10);
            l.setLayoutY((i + 1.25) * 50);
            Rectangle r = new Rectangle();
            r.setLayoutX(30);
            r.setLayoutY((i + 1) * 50);
            r.setWidth(50);
            r.setHeight(50);
            r.setFill(Color.TRANSPARENT);
            r.setStroke(Color.BLUE);
            group.getChildren().addAll(r, l, textFlows[i]);
        }

    }


    public void inserir(int x) {

        List<Integer> lista;

        lista = theLists[myHash(x)];
        lista.add(x);
        TextFlow label;


        label = textFlows[myHash(x)];
        Text text = new Text("->");
        text.setStyle("-fx-font-size: 20px");
        Text element = new Text(x + "");
        element.setStyle("-fx-font-size: 20px");
        label.getChildren().addAll(text, element);


        for (Integer integer : lista) {
            System.out.println(integer);
        }


    }

    public void remover(String elemento) {

        int x = Integer.parseInt(elemento);
        List<Integer> whichList = theLists[myHash(x)];
        if (whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
        TextFlow tf = textFlows[myHash(x)];
        for (int i = 1; i < tf.getChildren().size(); i ++) {
            Text text = (Text) tf.getChildren().get(i);
            if (text.getText().equals(elemento)) {
                tf.getChildren().remove(tf.getChildren().get(i));

            }
        }


    }


    public void buscar(String elemento) {
        int x = Integer.parseInt(elemento);
        TextFlow tf = textFlows[myHash(x)];
//	    String elemento = Controller.txtElemento.getText();
        for (int i = 1; i < tf.getChildren().size(); i += 2) {
            Text text = (Text) tf.getChildren().get(i);
            if (text.getText().equals(elemento)) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        text.setFill(Color.RED);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        text.setFill(Color.BLACK);

                    }
                }).start();
            }
        }

    }

    public int calcularFatorCarga() {
        int fatorCarga = 0;

        for( List list : theLists) {
            fatorCarga = list.size() > fatorCarga ? list.size() : fatorCarga;
        }

        return fatorCarga;
    }

    public double calculaFatorBalanceamento(){
        double fator = 0 ;
        double fatorBalanceamento = 0;
        int fatorCarga = calcularFatorCarga();

        for(List list: theLists){
            fator+= fatorCarga - list.size();
        }
        fatorBalanceamento = 1-(fator/(theLists.length * fatorCarga));
        return fatorBalanceamento;
    }

    public boolean contains(int x) {
        List<Integer> whichList = theLists[myHash(x)];
        return whichList.contains(x);
    }


    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++)
            theLists[i].clear();
        currentSize = 0;
    }

    private int myHash(int x) {
        return x % theLists.length;
    }

    public void printHash() {
        for (int i = 0; i < theLists.length; i++) {
            for (int j : theLists[i]) {
                System.out.println("Lista[" + i + "] : " + j);
            }

        }

    }

    public int getSize() {
        return theLists.length;
    }


}
