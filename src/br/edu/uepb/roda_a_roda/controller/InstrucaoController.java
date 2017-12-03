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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author geovanniovinhas
 */
public class InstrucaoController implements Initializable {

    private final String DESC_1 = "Ao iniciar o jogo aparecerá um painel com alguns quadros brancos, estes quadros indicam a quantidade de letras que formam a palavra. Abaixo do painel estará uma dica para ajudá-lo a resolver a questão.";
    private final String DESC_2 = "Antes de escolher uma letra é necessário rodar a roda para descobrir que valor você pode ganhar se acertar uma letra da palavra.\n\nPara rodar a roda basta clicar no meio dela. Se o botão ficar verde, sinal que pode rodar. Caso contrário é uma ação não permitida no momento.";
    private final String DESC_3 = "Após rodar a roda e acertar a letra, você ganhará os pontos e irá rodar a roda novamente para mais um palpite. Caso erre, a vez passará a ser do computador.";
    private final String DESC_4 = "Entretanto não existem apenas valores para pontuar na roleta, como também as opcões \"Perde Tudo\" e \"Passa a Vez\".\n\nPASSA A VEZ\nComo o nome já diz, passa a vez para o próximo jogador (compuador).\n\nPERDE TUDO\nO jogador perde os seus pontos acumulados.";
    private final String DESC_5 = "Existe abaixo da roleta o botão responder. Serve para responder sem precisar rodar. Ao utilizá-lo você deve responder corretamente a pergunta, se isso não ocorrer é decretado fim do jogo. Responder corretamente você vence e ganha os pontos feitos até apertar o botão responder.";
    private final String DESC_6 = "Após apertar o botão no responder, você irá preencher a palavra com as letras que faltam para completá-la. Para isso utilize o teclado virtual disponível.\nNo exemplo ao lado, observe que o campo da letra a ser inserida está em destaque, após escolher a letra correspondente será destacado a próxima letra e assim por diante. Caso seja necessário utilize a tecla < para remover a última letra inserida. Ao acabar de preencher, o jogo analisa se a resposta está correta automaticamente.";
    private final String DESC_7 = "Para inserir letras no jogo, seja após rodar a roda ou mesmo após escolher o botão responder, basta utilizar o teclado virtual que seu encontra no disponível no jogo, clicando com o mouse sobre a letra a ser usada.\nAs letras que se encontram em destaque, indicam que as já foram usadas e não podem ser usadas novamente.";

    private App application;
    private MediaController media;

    private Usuario usuario;
    private int index;

    @FXML
    Button btAnt, btProx;
    @FXML
    Label lblTitle, lblDesc;
    @FXML
    VBox boxImg, boxText;
    @FXML
    ImageView img;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        media = MediaController.getInstance();
        index = 1;
        btAnt.setVisible(false);

        refresh("INICIO DO JOGO", DESC_1, "br/edu/uepb/roda_a_roda/assets/images/img_tela_1.gif");
    }

    /**
     * Pega a instância da App. O usuário logado é recebido com parâmetro
     * oriundo da tela de login
     *
     * @param application
     */
    public void setApp(App application, Usuario usuario) {
        this.application = application;
        this.usuario = usuario;
    }

    /**
     * Muda as telas de acordo com a navegação dos botões
     *
     * @param e
     */
    @FXML
    public void action(ActionEvent e) {
        // Toca audio click
        media.playAudioClip(MediaSource.CLICK);

        if (index == 1 && e.getSource().equals(btProx)) { // ir para tela 2
            index += 1;
            btProx.setVisible(true);
            btAnt.setVisible(true);
            img.setFitWidth(210.0);
            refresh("RODAR A RODA", DESC_2, "br/edu/uepb/roda_a_roda/assets/images/img_tela_2.png");
        } else if (index == 2 && e.getSource().equals(btAnt)) { // voltar para tela 1
            index -= 1;
            btAnt.setVisible(false);
            btProx.setVisible(true);
            img.setFitWidth(330.0);
            refresh("INICIO DO JOGO", DESC_1, "br/edu/uepb/roda_a_roda/assets/images/img_tela_1.gif");
        } else if (index == 2 && e.getSource().equals(btProx)) { // ir para tela 3
            index += 1;
            btProx.setVisible(true);
            img.setFitWidth(250.0);
            refresh("PONTOS", DESC_3, "br/edu/uepb/roda_a_roda/assets/images/img_tela_3.png");
        } else if (index == 3 && e.getSource().equals(btAnt)) { // voltar para tela 2
            index -= 1;
            btProx.setVisible(true);
            img.setFitWidth(210.0);
            refresh("RODAR A RODA", DESC_2, "br/edu/uepb/roda_a_roda/assets/images/img_tela_2.png");
        } else if (index == 3 && e.getSource().equals(btProx)) {//ir para tela 4
            index += 1;
            img.setFitWidth(250.0);
            refresh("VALORES NA RODA", DESC_4, "br/edu/uepb/roda_a_roda/assets/images/img_tela_4.png");
        } else if (index == 4 && e.getSource().equals(btAnt)) { // voltar para tela 3
            index -= 1;
            btProx.setVisible(true);
            img.setFitWidth(250.0);
            refresh("PONTOS", DESC_3, "br/edu/uepb/roda_a_roda/assets/images/img_tela_3.png");
        } else if (index == 4 && e.getSource().equals(btProx)) { // ir para tela 5
            index += 1;
            btProx.setVisible(true);
            img.setFitWidth(210.0);
            refresh("RESPONDER", DESC_5, "br/edu/uepb/roda_a_roda/assets/images/img_tela_5.png");
        } else if (index == 5 && e.getSource().equals(btAnt)) { // voltar para tela 4
            index -= 1;
            btProx.setVisible(true);
            img.setFitWidth(250.0);
            refresh("VALORES NA RODA", DESC_4, "br/edu/uepb/roda_a_roda/assets/images/img_tela_4.png");
        } else if (index == 5 && e.getSource().equals(btProx)) { // ir para tela 6
            index += 1;
            btProx.setVisible(true);
            img.setFitWidth(330.0);
            refresh("RESPONDENDO", DESC_6, "br/edu/uepb/roda_a_roda/assets/images/img_tela_6.gif");
        } else if (index == 6 && e.getSource().equals(btAnt)) { // voltar para tela 5
            index -= 1;
            btProx.setVisible(true);
            img.setFitWidth(210.0);
            refresh("RESPONDER", DESC_5, "br/edu/uepb/roda_a_roda/assets/images/img_tela_5.png");
        } else if (index == 6 && e.getSource().equals(btProx)) { // ir para tela 7
            index += 1;
            btProx.setVisible(false);
            img.setFitWidth(400.0);

            lblDesc.setPrefWidth(650.0);
            lblDesc.setPrefHeight(150.0);

            boxImg.setLayoutX(38.0);
            boxImg.setLayoutY(320.0);
            refresh("INSERINDO LETRAS", DESC_7, "br/edu/uepb/roda_a_roda/assets/images/img_tela_7.png");
        } else if (index == 7 && e.getSource().equals(btAnt)) { // voltar para tela 6
            index -= 1;
            btProx.setVisible(true);
            img.setFitWidth(300.0);

            lblDesc.setPrefWidth(335.0);
            lblDesc.setPrefHeight(254.0);

            boxImg.setLayoutX(365.0);
            boxImg.setLayoutY(150.0);
            refresh("RESPONDENDO", DESC_6, "br/edu/uepb/roda_a_roda/assets/images/img_tela_6.gif");
        }
    }

    /**
     * Refresh na tela. Troca o titulo, descrição e imagem.
     *
     * @param title
     * @param descricao
     * @param url
     */
    private void refresh(String title, String descricao, String url) {
        lblTitle.setText(title);
        lblDesc.setText(descricao);
        img.setImage(new Image(url));
    }

    /**
     * Fecha tela e abre a tela inicial
     *
     * @param e
     */
    public void actionClose(ActionEvent e) {
        // Toca audio click
        media.playAudioClip(MediaSource.CLICK);
        application.gotoInit(usuario, 716, 478);
    }
}
