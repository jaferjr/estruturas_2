package sample;

import javafx.scene.Group;

/**
 * Created by jafer on 26/05/17.
 */
public interface Structure {

    void inserir(int x);
    void buscar(String x);
    void remover(String x);
    int calcularFatorCarga();
    double calculaFatorBalanceamento();

}
