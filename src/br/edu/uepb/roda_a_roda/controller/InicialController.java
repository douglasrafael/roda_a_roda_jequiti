package br.edu.uepb.roda_a_roda.controller;

import br.edu.uepb.roda_a_roda.model.Usuario;
import br.edu.uepb.roda_a_roda.view.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Douglas Rafael
 */
public class InicialController implements Initializable {

    private App application;
    private MediaController media;
    public Usuario usuario;

    @FXML
    Label lbUsername;
    @FXML
    Button btActionNovoJogo, btActionJogoSalvo, btActionRank, btActionHelp;

    /**
     * Pega a instância da App. O usuário logado é recebido com parâmetro
     * oriundo da tela de login
     *
     * @param usuario
     * @param application
     */
    public void setApp(App application, Usuario usuario) {
        this.usuario = usuario;
        this.application = application;

        if (usuario != null) {
            lbUsername.setText(usuario.getUsername());
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        media = MediaController.getInstance();
    }

    /**
     * Mapeia qual a ação deverá ser executada de acordo com o botão clicado na
     * view
     *
     * @param e
     */
    @FXML
    public void action(ActionEvent e) {
        // Toca audio click
        media.playAudioClip(MediaSource.CLICK);

        if (e.getSource().equals(btActionNovoJogo)) {
            application.gotoGame(this.usuario, 1080, 683);
        } else if (e.getSource().equals(btActionHelp)) {
            application.gotoInstrucao(usuario, 716.0, 478.0);
        } else if (e.getSource().equals(btActionRank)) {
            application.gotoRank(usuario, 716.0, 478.0);
        }
    }

    /**
     * Encerra app
     *
     * @param e
     */
    public void actionClose(ActionEvent e) {
        // Toca audio click
        media.playAudioClip(MediaSource.CLICK);
        application.destroy();
    }
}
