package br.edu.uepb.roda_a_roda.controller;

import br.edu.uepb.roda_a_roda.exceptions.DAOException;
import br.edu.uepb.roda_a_roda.exceptions.ValidacaoException;
import br.edu.uepb.roda_a_roda.model.Usuario;
import br.edu.uepb.roda_a_roda.tools.MyDateTime;
import br.edu.uepb.roda_a_roda.view.App;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Controller do Login.fxml que representa a interface gráfica da tela de Login
 * e cadastro de usuário
 *
 * @author Douglas Rafael
 */
public class LoginController extends AnchorPane implements Initializable {

    private final String BT_ENTRAR = "Entrar";
    private final String BT_CADASTRAR = "Cadastrar";

    private Gerenciador manager;
    private MediaController media;

    @FXML
    Label errorMessage;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button btAction, btNovoCadastro, btPrev;
    @FXML
    ImageView imageBackground;

    private App application;

    /**
     * Pega a instância da App. Assim é possível pegar objetos
     *
     * @param application
     */
    public void setApp(App application) {
        this.application = application;
    }

    /**
     * Inicializa o controlador e coisas úteis da class
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manager = new Gerenciador();
        media = MediaController.getInstance();
        
        // Play música de abertura
        media.playMusic(MediaSource.ABERTURA_1);

        errorMessage.setText("");
        btPrev.setVisible(false);
    }

    /**
     * Mesmo método para efetuar o login e cadastrar usuário. Verifica pelo
     * titulo do botão qual ação realizar
     *
     * @param event
     */
    @FXML
    public void actionLogin(ActionEvent event) {
        // Toca audio click
        media.playAudioClip(MediaSource.CLICK);
        // Limpa mensagem de erro
        errorMessage.setText("");

        // Validação
        if (username.getText().length() < 3) {
            errorMessage.setText("O nome de usuário é obrigatório. No mínimo 3 caracteres");
        } else if (password.getText().length() < 3) {
            errorMessage.setText("A senha é obrigatória. No mínimo 3 caracteres");
        } else // Passou na validação!
         if (btAction.getText().equals(BT_ENTRAR)) {
                entrar();
            } else if (btAction.getText().equals(BT_CADASTRAR)) {
                cadastrar();
                defaultLogin();
            }
    }

    /**
     * Envia o form quando o enter for pressionado. Apaga a mensagem caso o
     * contrário.
     *
     * @param event
     */
    @FXML
    public void actionKeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            actionLogin(null);
        } else {
            errorMessage.setText("");
        }
    }

    /**
     * Acão do botão + cadastro, para ir para tela de cadastro
     *
     * @param event
     */
    @FXML
    public void novoCadastro(ActionEvent event) {
        // Toca audio click
        media.playAudioClip(MediaSource.CLICK);
        // Limpa mensagem de erro
        errorMessage.setText("");

        imageBackground.setImage(new Image("/br/edu/uepb/roda_a_roda/assets/images/background_tela_cadastro.png"));

        btAction.setText("Cadastrar");
        btNovoCadastro.setVisible(false);
        btPrev.setVisible(true);
    }

    /**
     * Chama o método defaultLogin()
     *
     * @param event
     */
    @FXML
    public void actionVoltar(ActionEvent event) {
        // Toca audio click
        media.playAudioClip(MediaSource.CLICK);
        defaultLogin();
    }

    /**
     * Método para voltar a tela de login normal
     */
    private void defaultLogin() {
        btNovoCadastro.setVisible(true);
        btAction.setText(BT_ENTRAR);
        btPrev.setVisible(false);
        imageBackground.setImage(new Image("/br/edu/uepb/roda_a_roda/assets/images/background_tela_login.png"));
    }

    /**
     * Ação de login de usuário
     */
    public void entrar() {
        if (application != null) {
            try {
                Usuario usuarioLogado = manager.autenticaUsuario(username.getText(), password.getText());

                // Carrega tela inicial
                application.gotoInit(usuarioLogado, 716.0, 478.0);

                /**
                 * Carrega tela principal do jogo com o usuário logado
                 */
                //openGame(usuarioLogado);
            } catch (ValidacaoException ex) {
                errorMessage.setText(ex.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Ação de cadastro de usuário
     */
    public void cadastrar() {
        if (application != null) {
            try {
                Usuario novoUsuario = new Usuario(username.getText(), password.getText(), MyDateTime.getDateTime());
                manager.inserirUsuario(novoUsuario);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Cadastro de Usuário");
                alert.setHeaderText(null);
                alert.setContentText(novoUsuario.getUsername() + " cadastrado(a) com sucesso!");
                alert.showAndWait();
            } catch (DAOException ex) {
                errorMessage.setText(ex.getMessage());
            }
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
