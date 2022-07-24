package com.avengers.rpgame.graphics.graphicManagerMediador;

import com.avengers.rpgame.RPGame;

public interface iMediador {

    public void inicializeGraphicManagers (RPGame game);
    public void renderGraphicManagers (float delta);

}
