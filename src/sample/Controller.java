package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private Label labelStatus;

    @FXML
    private Label lblCarga;

    @FXML
    private Label lblBalanceamento;


    @FXML
    private ComboBox<String> cmbEstruturas;



    @FXML
    private TextField txtElemento;


    @FXML
    private ScrollPane scroll;

    private ObservableList<String> itens;

    private Structure structure;

    private Group group;


    private TextFlow[] textFlow;

    public Controller() {
        group = new Group();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        itens = FXCollections.observableArrayList("OpenHash", "ClosedHash", "Hash Meio-Aberta");
        cmbEstruturas.setItems(itens);
        scroll.setContent(group);

    }

    @FXML
    private void escolherEstruturaCombo() {
        String valor = cmbEstruturas.getValue();

        switch (valor){
            case "OpenHash":
                TextInputDialog dialog = new TextInputDialog("7");
                dialog.setTitle("OpenHash");
                dialog.setHeaderText("Tamanho da tabela: ");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    int size = Integer.parseInt(result.get());
                    structure = new OpenHash(size, group);
                }
                txtElemento.setPromptText("chave");
                break;

            case "ClosedHash":
                break;

            case "Hash Meio-Aberta":
                break;



        }



    }




    @FXML
    private void inserirButtonClicked() {

        labelStatus.setText("testando...");
        int elemento = Integer.parseInt(txtElemento.getText());
        structure.inserir(elemento);

        String sBalanceamento =  Double.toString(structure.calculaFatorBalanceamento());
        lblBalanceamento.setText(sBalanceamento);

        String sCarga =  Double.toString(structure.calcularFatorCarga());
        lblCarga.setText(sCarga);



        scroll.setContent(group);


    }


    @FXML
    private void buscar() {
        String elemento = txtElemento.getText();
        structure.buscar(elemento);
        labelStatus.setText("clicou em buscar");

    }

    @FXML
    private void remocao() {
        String elemento = txtElemento.getText();
        structure.remover(elemento);

        String sBalanceamento =  Double.toString(structure.calculaFatorBalanceamento());
        lblBalanceamento.setText(sBalanceamento);

        String sCarga =  Double.toString(structure.calcularFatorCarga());
        labelStatus.setText("clicou em remover");


    }

}
