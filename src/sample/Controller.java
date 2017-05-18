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




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        itens = FXCollections.observableArrayList("OpenHash","ClosedHash","Hash Meio-Aberta");
        cmbEstruturas.setItems(itens);



    }



    @FXML
    private void escolherEstruturaCombo(){
        String valor= cmbEstruturas.getValue();
        if(valor == "OpenHash"){
            btnInserir.setText("Tamanho");
            txtElemento.setPromptText("insira o tamanho");

        }
    }

    @FXML
    private void inserirButtonClicked(){

        int size = Integer.parseInt(txtElemento.getText());
        hashTable= new HashTable(size);
        labelStatus.setText("testando...");

        btnInserir.setText("Inserir");
        desenhaHash(size);

        int elemento = Integer.parseInt(txtElemento.getText());

        hashTable.insert(elemento);
//        Label lElemento = new Label(Integer.toString(elemento));
//        lElemento.setLayoutX();


    }

    private void desenhaHash(int size) {
        Group g  = new Group();
        for(int i = 0 ; i<size;i++){
            Label l = new Label();
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
            g.getChildren().addAll(r,l);
        }
        scroll.setContent(g);
    }
}
