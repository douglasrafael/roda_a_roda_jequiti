package br.edu.uepb.roda_a_roda.view;

import br.edu.uepb.roda_a_roda.controller.InicialController;
import br.edu.uepb.roda_a_roda.controller.InstrucaoController;
import br.edu.uepb.roda_a_roda.controller.LoginController;
import br.edu.uepb.roda_a_roda.controller.PrincipalController;
import br.edu.uepb.roda_a_roda.controller.RankController;
import br.edu.uepb.roda_a_roda.model.Usuario;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * Main do jogo.
 *
 * @author Douglas Rafael
 */
public class App extends Application {

    private final String TELA_TITULO = "Roda a Roda Jequiti";

    private static double xOffset = 0;
    private static double yOffset = 0;

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            stage.setTitle(TELA_TITULO);
            stage.setResizable(false);
            stage.getIcons().add(new Image("/br/edu/uepb/roda_a_roda/assets/images/ic_launcher.png"));

            gotoLogin(716.0, 478.0);

            // Deixa janela transparente
            stage.initStyle(StageStyle.TRANSPARENT);

            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Application.launch(App.class, (java.lang.String[]) null);
    }

    /**
     * Abre tela de login
     *
     * @param width
     * @param height
     */
    private void gotoLogin(double width, double height) {
        try {
            LoginController loginController = (LoginController) replaceSceneContent("Login.fxml", width, height);
            loginController.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre janela inicial do jogo
     *
     * @param usuario
     * @param width
     * @param height
     */
    public void gotoInit(Usuario usuario, double width, double height) {
        try {
            InicialController profile = (InicialController) replaceSceneContent("Inicial.fxml", width, height);
            // Passa o usuário logado
            profile.setApp(this, usuario);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a tela principal do jogo
     *
     * @param usuario
     * @param width
     * @param height
     */
    public void gotoGame(Usuario usuario, double width, double height) {
        try {
            PrincipalController jogoPrinc = (PrincipalController) replaceSceneContent("Principal.fxml", width, height);
            jogoPrinc.setApp(this, usuario);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a janela instrucao
     *
     * @param usuario
     * @param width
     * @param height
     */
    public void gotoInstrucao(Usuario usuario, double width, double height) {
        try {
            InstrucaoController instrucao = (InstrucaoController) replaceSceneContent("Instrucao.fxml", width, height);
            instrucao.setApp(this, usuario);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre tela de Rank
     *
     * @param usuario
     * @param width
     * @param heigth
     */
    public void gotoRank(Usuario usuario, double width, double heigth) {
        try {
            RankController rank = (RankController) replaceSceneContent("Rank.fxml", width, heigth);

            rank.setApp(this, usuario);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Troca scene de acordo com o fxml passado como parâmetro. Ativa o arrastar
     * janela.
     *
     * @param fxml
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    public Initializable replaceSceneContent(String fxml, double width, double height) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        InputStream in = App.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(App.class.getResource(fxml));
        AnchorPane page;

        try {
            page = (AnchorPane) loader.load(in);

            page.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            page.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
        } finally {
            in.close();
        }

        // Garante que a janela abra sempre centralizada independente do seu tamanho
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - width) / 2);
        stage.setY((primScreenBounds.getHeight() - height) / 2);

        /**
         * Aplica Animação fade se a tela for a principal
         */
        if (fxml.equals("Principal.fxml")) {
            FadeTransition ft = new FadeTransition(Duration.millis(400), page);
            ft.setFromValue(5.0);
            ft.setToValue(0);
            ft.setCycleCount(2);
            ft.setAutoReverse(true);
            ft.play();
        }

        Scene scene = new Scene(page, width, height);
        scene.setFill(null);

        stage.setScene(scene);
        stage.sizeToScene();

        return (Initializable) loader.getController();
    }

    /**
     * Encerra a app
     */
    public void destroy() {
        Platform.exit();
    }
}
