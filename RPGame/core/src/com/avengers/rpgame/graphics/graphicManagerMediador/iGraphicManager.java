package com.avengers.rpgame.graphics.graphicManagerMediador;

public abstract class iGraphicManager {
    private iMediador mediador;

    public iMediador getMediador() {
        return mediador;
    }

    public void setMediador(iMediador mediador) {
        this.mediador = mediador;
    }
}
