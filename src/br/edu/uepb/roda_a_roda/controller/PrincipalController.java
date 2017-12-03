package br.edu.uepb.roda_a_roda.controller;

import br.edu.uepb.roda_a_roda.model.Jogo;
import br.edu.uepb.roda_a_roda.model.JogoSalvo;
import br.edu.uepb.roda_a_roda.model.Usuario;
import br.edu.uepb.roda_a_roda.view.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Edsonneto
 */
public class PrincipalController implements Initializable {

    private Gerenciador manager;
    private MediaController media;

    private JogoSalvo jogoSalvo;
    private Jogo jogo;

    private App application;

    //Botões:
    @FXML
    Button btClose, btVoltar, btHelp, btResponder, btRoda;

    //Labels:
    @FXML
    Label lbUsuario, lbPontJog, lbPontNPc, lbTempo, lbDica, lbPontuacaoUsuario, lbPontuacaoNPC;

    /**
     * Variáveis para lógica do jogo
     */
    private int pontuacaoUsuario;
    private int pontuacaoNPC;

    /**
     * Método é chamado toda vez que o scene é criado.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        media = MediaController.getInstance();

        // Para música de abertura incial e toca a música de abertura do jogo
        media.stop(MediaSource.ABERTURA_1);

        /**
         * Toca música de ABERTURA_3 O loop é para não fazer nada até que esteja
         * tocando a música. Em seguida é tocado a música de ABERTURA_1 com
         * volume baixo. Essa ficará tocando durante o jogo.
         */
        media.playMusic(MediaSource.ABERTURA_3);
        while (media.isPlaying(MediaSource.ABERTURA_3)) {
            // faz nada
        }

        media.playMusic(MediaSource.ABERTURA_1);
        media.setVolume(MediaSource.ABERTURA_1, 0.2);
        media.setAutoReplay(MediaSource.ABERTURA_1, true);
    }

    public void setApp(App application, Usuario usuario) {
        this.application = application;
        manager = new Gerenciador();
        jogo = new Jogo();
        jogo.setUsuario(usuario);
        lbUsuario.setText(usuario.getUsername());
    }

    /**
     * Pega a instância da App. O usuário logado é recebido com parâmetro
     * oriundo da tela de login
     *
     * @param application
     * @param jogoSalvo
     */
    public void setApp(App application, JogoSalvo jogoSalvo) {
        this.application = application;
        this.jogoSalvo = jogoSalvo;
    }

    /**
     * Mapeia as ações do clique do botão
     *
     * @param e
     */
    @FXML
    public void action(ActionEvent e) {
        // Toca audio click
        media.playAudioClip(MediaSource.CLICK);

        if (e.getSource().equals(btClose)) {
            application.destroy();
        } else if (e.getSource().equals(btVoltar)) {
            media.stop(MediaSource.ABERTURA_3);
            media.playMusic(MediaSource.ABERTURA_1);
            application.gotoInit(jogo.getUsuario(), 716.0, 478.0);
        }
    }
    boolean te = false;

    @FXML
    public void actionLetra(ActionEvent e) {
        // Toca audio click
        media.playAudioClip(MediaSource.CLICK);

        //Ação do Botão!
        Button btClicado = null;
        if (e.getSource() instanceof Button) {
            btClicado = (Button) e.getSource();
        }
    }

    /**
     * Seta a pontuação ao jogador correspondente e aplica animação no label.
     * Para setar no jogador tipo NPC basta passar como parâmetro true. Caso
     * queira que seja o jogador tipo usuário passe false.
     *
     * @param pontos
     * @param isNPC true se for NPC, false caso contrário
     */
    private void setPontuacao(int pontos, boolean isNPC) {
        /**
         * Prepara animação Scale para ser aplicada no label correspondente
         */
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(200));
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.3);
        fadeTransition.setCycleCount(4);
        fadeTransition.setAutoReverse(true);
        String styleDefault = "-fx-text-fill: #fffcd6; -fx-font-weight: bold; -fx-font-size: 30; -fx-line-height: 30; -fx-effect: dropshadow(three-pass-box, black, 10, 0, 2, 5);";

        /**
         * Verifica se é para o jogador do tipo NPC ou jogador do tipo usuário
         */
        if (isNPC) {
            pontuacaoNPC += pontos;
            lbPontuacaoNPC.setText("R$ " + pontuacaoNPC);
            lbPontuacaoNPC.setStyle(styleDefault);

            /**
             * Seta a animação ao label lbPontuacaoNPC e executa
             */
            fadeTransition.setNode(lbPontuacaoNPC);
            fadeTransition.play();
        } else {
            pontuacaoUsuario += pontos;
            lbPontuacaoUsuario.setText("R$ " + pontuacaoUsuario);
            lbPontuacaoUsuario.setStyle(styleDefault);

            /**
             * Seta a animação ao label lbPontuacaoNPC e executa
             */
            fadeTransition.setNode(lbPontuacaoUsuario);
            fadeTransition.play();
        }
    }

    /**
     * Seta o PASSOU A VEZ e aplica animação no label pontuação. Para setar no
     * jogador tipo NPC basta passar como parâmetro true. Caso queira que seja o
     * jogador tipo usuário passe false.
     *
     * OBS. O valor da pontuação não muda!
     *
     * @param isNPC true se for NPC, false caso contrário
     */
    private void setPassouVez(boolean isNPC) {
        /**
         * Prepara animação Scale para ser aplicada no label correspondente
         */
        ScaleTransition scalaTransition = new ScaleTransition(Duration.millis(200));
        scalaTransition.setByX(0);
        scalaTransition.setByY(1);
        scalaTransition.setCycleCount(4);
        scalaTransition.setAutoReverse(true);
        String style = "-fx-font-size: 18; -fx-text-fill: #FFFFFF; -fx-effect: dropshadow(three-pass-box, black, 0, 0, 0, 0); -fx-padding: -35 0 0 0;";

        /**
         * Verifica se é para o jogador do tipo NPC ou jogador do tipo usuário
         */
        if (isNPC) {
            lbPontuacaoNPC.setText("PASSOU A VEZ");
            lbPontuacaoNPC.setStyle(style);

            /**
             * Seta a animação ao label lbPontuacaoNPC e executa
             */
            scalaTransition.setNode(lbPontuacaoNPC);
            scalaTransition.play();
        } else {
            lbPontuacaoUsuario.setText("PASSOU A VEZ");
            lbPontuacaoUsuario.setStyle(style);

            /**
             * Seta a animação ao label lbPontuacaoNPC e executa
             */
            scalaTransition.setNode(lbPontuacaoUsuario);
            scalaTransition.play();
        }
    }

    /**
     * Seta a pontuação do jogador correspondente e aplica animação no label
     * pontuação. Para setar o PERDE TUDO no jogador tipo NPC basta passar como
     * parâmetro true. Caso queira que seja o jogador tipo usuário passe false.
     *
     * @param isNPC true se for NPC, false caso contrário
     */
    private void setPerdeuTudo(boolean isNPC) {
        /**
         * Prepara animação Scale para ser aplicada no label correspondente
         */
        ScaleTransition scalaTransition = new ScaleTransition(Duration.millis(200));
        scalaTransition.setByX(0);
        scalaTransition.setByY(1);
        scalaTransition.setCycleCount(4);
        scalaTransition.setAutoReverse(true);
        String style = "-fx-font-size: 18; -fx-text-fill: #000000; -fx-effect: dropshadow(three-pass-box, black, 0, 0, 0, 0); -fx-padding: -35 0 0 0;";

        /**
         * Verifica se é para o jogador do tipo NPC ou jogador do tipo usuário
         */
        if (isNPC) {
            pontuacaoNPC = 0;
            lbPontuacaoNPC.setText("PERDEU TUDO");
            lbPontuacaoNPC.setStyle(style);

            /**
             * Seta a animação ao label lbPontuacaoNPC e executa
             */
            scalaTransition.setNode(lbPontuacaoNPC);
            scalaTransition.play();
        } else {
            pontuacaoUsuario = 0;
            lbPontuacaoUsuario.setText("PERDEU TUDO");
            lbPontuacaoUsuario.setStyle(style);

            /**
             * Seta a animação ao label lbPontuacaoNPC e executa
             */
            scalaTransition.setNode(lbPontuacaoUsuario);
            scalaTransition.play();
        }
    }
}
