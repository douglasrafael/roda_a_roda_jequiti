package br.edu.uepb.roda_a_roda.controller;

import br.edu.uepb.roda_a_roda.model.Jogo;
import br.edu.uepb.roda_a_roda.model.Usuario;
import br.edu.uepb.roda_a_roda.tools.MyDateTime;
import br.edu.uepb.roda_a_roda.view.App;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author geovanniovinhas
 */
public class RankController implements Initializable {

    private App application;
    private Gerenciador manager;
    private MediaController media;

    private Usuario usuario;

    @FXML
    HBox box1;
    @FXML
    AnchorPane ancPane3;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manager = new Gerenciador();
        media = MediaController.getInstance();

        popular();
    }

    public void setApp(App application, Usuario usuario) {
        this.application = application;
        this.usuario = usuario;
    }

    /*
    *funcao pegar os dados do BD para Tela Rank
     */
    public void popular() {
        List<Jogo> listaRank = manager.getRank();
        int index = 0;
        for (Jogo j : listaRank) {
            Node nod = ancPane3.getChildren().get(index);
            if (nod instanceof HBox) {
                HBox box = (HBox) nod;
                int indexCol = 0;
                for (Node node : box.getChildren()) {
                    Label l = (Label) node;
                    switch (indexCol) {
                        case 0:
                            l.setText(j.getUsuario().getUsername());
                            break;
                        case 1:
                            l.setText(String.valueOf(j.getPontuacao()));
                            break;
                        case 2:
                            l.setText(String.valueOf(j.getTempoJogado()));
                            break;
                        case 3:
                            l.setText(j.getPalavra().getTitulo().toUpperCase());
                            break;
                        case 4:
                            l.setText(MyDateTime.toStringDateTime(j.getDataInicio(), "dd/MM/yyyy 'às' HH:mm:ss"));
                            break;
                        case 5:
                            l.setText(MyDateTime.toStringDateTime(j.getDataFim(), "dd/MM/yyyy 'às' HH:mm:ss"));
                            break;
                        default:
                            break;
                    }
                    indexCol += 1;
                }
            }
            index += 1;
        }
    }

    /**
     * Volta para tela inicial
     *
     * @param e
     */
    public void actionClose(ActionEvent e) {
        // Toca audio click
        media.playAudioClip(MediaSource.CLICK);
        
        application.gotoInit(usuario, 716.0, 478.0);
    }
}
