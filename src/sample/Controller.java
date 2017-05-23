package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable{

    @FXML
    private Label labelStatus;


    @FXML
    private ComboBox<String> cmbEstruturas;

    @FXML
    private Button btnInserir;

    @FXML
    private TextField txtElemento;

    @FXML
    private AnchorPane pane;

    @FXML
    private ScrollPane scroll;

    private ObservableList<String> itens;

    private HashTable hashTable;


    boolean changeButton = true;
    private Group group;
    private int size;//tamanho da hash
    private TextFlow [] textFlow;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        itens = FXCollections.observableArrayList("OpenHash","ClosedHash","Hash Meio-Aberta");
        cmbEstruturas.setItems(itens);

        scroll.setContent(group);



    }



    @FXML
    private void escolherEstruturaCombo(){
        String valor= cmbEstruturas.getValue();
        if(valor == "OpenHash"){
            btnInserir.setText("Tamanho");
            txtElemento.setPromptText("insira o changeButton");

        }
    }

    @FXML
    private void inserirButtonClicked(){

        if (changeButton){

            size = Integer.parseInt(txtElemento.getText());
            hashTable= new HashTable(size);

            btnInserir.setText("Inserir");
            desenhaHash(size);
            changeButton = false;
        }else {

            labelStatus.setText("testando...");
            int elemento =  Integer.parseInt(txtElemento.getText());
            hashTable.insert(elemento);
            TextFlow label = this.textFlow[elemento%hashTable.getSize()];
            Text text = new Text("->");
            text.setStyle("-fx-font-size: 20px");
            Text element = new Text(elemento+"");
            element.setStyle("-fx-font-size: 20px");
            label.getChildren().addAll(text, element);
        }
        scroll.setContent(group);


    }

    private void desenhaHash(int size) {
        group = new Group();
        textFlow = new TextFlow[size];
        for(int i = 0 ; i<size;i++){
            Label l = new Label();
            textFlow[i] = new TextFlow();
            textFlow[i].setLayoutX(85);
            textFlow[i].setId(i+"");
            textFlow[i].setLayoutY((i+1.25)*50);
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
            group.getChildren().addAll(r,l, textFlow[i]);
        }

    }

    private void desenhaInsercao(int x, int y, int valor){
        Label l = new Label();
        l.setLayoutX(x);
        l.setLayoutY(y);
        l.setFont(Font.font("Cambria", 32));
        l.setText("->"+Integer.toString(valor));
        group.getChildren().add(l);
    }
    @FXML
    public void buscar() {
        String elemento = txtElemento.getText();
        int listIndex = Integer.parseInt(elemento)%hashTable.getSize();
        TextFlow tf = textFlow[listIndex];
        for (int i = 1; i < tf.getChildren().size(); i+=2) {
            Text text = (Text) tf.getChildren().get(i);
            if(text.getText().equals(elemento)) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        text.setFill(Color.RED);
                        try{
                            Thread.sleep(5000);
                        }catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        text.setFill(Color.BLACK);

                    }
                }).start();
            }
        }
    }
    private void remocao(){




    }

}
