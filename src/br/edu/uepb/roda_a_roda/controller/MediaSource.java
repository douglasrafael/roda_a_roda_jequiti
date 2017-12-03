package br.edu.uepb.roda_a_roda.controller;

/**
 * Mapeia as medias disponívels na app
 *
 * @author Douglas Rafael
 */
public enum MediaSource {
    ABERTURA_1("abertura_1.mp3"), ABERTURA_2("abertura_2.mp3"), ABERTURA_3("abertura_3.mp3"), CLICK("click.mp3"), EXIBE_LETRA("exibe_letra.mp3"),
    JOGO_GANHO_1("jogo_ganho_1.mp3"), LETRA_ERRADA_1("letra_errada_1.mp3"), LETRA_ERRADA_2("letra_errada_2.mp3"), PASSOU_A_VEZ_1("passou_a_vex_1.mp3"), PASSOU_A_VEZ_2("passou_a_vez_2.mp3"),
    RODA_A_RODA_1("roda_a_roda_1.mp3"), RODA_A_RODA_2("roda_a_roda_2.mp3"), RODA_A_RODA_3("roda_a_roda_3.mp3"), RODA_A_RODA_4("roda_a_roda_4.mp3"), RODA_A_RODA_5("roda_a_roda_5.mp3"),
    RODA_A_RODA_6("roda_a_roda_6.mp3"), RODA_A_RODA_7("roda_a_roda_7.mp3"), RODA_A_RODA_8("roda_a_roda_8.mp3"), RODA_RODANDO_1("roda_rodando_1.mp3"), RODA_RODANDO_2("roda_rodando_2_1000.mp3"),
    RODA_RODANDO_3("roda_rodando_3_500.mp3"), RODA_RODANDO_4("roda_rodando_4_550.mp3"), RODA_RODANDO_5("roda_rodando_5_250.mp3"), RODA_RODANDO_6("roda_rodando_6_400.mp3"), RODA_RODANDO_7("roda_rodando_7_300.mp3"),
    RODA_RODANDO_8("roda_rodando_8_950.mp3"), RODA_RODANDO_9("roda_rodando_9_passou.mp3"), RODA_RODANDO_10("roda_rodando_10_350.mp3"), RODA_RODANDO_11("roda_rodando_11_750.mp3"), RODA_RODANDO_12("roda_rodando_12_50.mp3"),
    RODA_RODANDO_13("roda_rodando_13_400.mp3"), RODA_RODANDO_14("roda_rodando_14_perdeu.mp3"), RODA_RODANDO_15("roda_rodando_15_200.mp3"), RODA_RODANDO_16("roda_rodando_16_650.mp3"), RODA_RODANDO_17("roda_rodando_17_900.mp3"),
    RODA_RODANDO_18("roda_rodando_18_450.mp3"), RODA_RODANDO_19("roda_rodando_19_600.mp3"), RODA_RODANDO_20("roda_rodando_20_100.mp3"), RODA_RODANDO_21("roda_rodando_21_900.mp3"), RODA_RODANDO_22("roda_rodando_22_850.mp3"),
    RODA_RODANDO_23("roda_rodando_23_800.mp3"), RODA_RODANDO_24("roda_rodando_24_700.mp3"), RODA_RODANDO_25("roda_rodando_25_passou.mp3"), TEM_LETRA("tem_letra.mp3"), TIC_TAC("tic-tac_relogio.mp3"),
    UMA_LETRA_1("uma_letra_1.mp3"), UMA_LETRA_2("uma_letra_2.mp3"), UMA_LETRA_3("uma_letra_3.mp3");

    private final String value;

    /**
     * Construtor
     *
     * @param value
     */
    MediaSource(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
