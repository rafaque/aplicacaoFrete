package com.gerenciamento.enums;



public enum Status {

    AGUARDANDO_MOVIMENTACAO("Aguardando movimentação"),
    ACEITO("Aceito"),
    ROTA_DE_ENTREGA("Rota de entrega"),
    FINALIZADO("Finalizado");


    private String status;

    private Status(String status) {
        this.status = status;
    }
}
