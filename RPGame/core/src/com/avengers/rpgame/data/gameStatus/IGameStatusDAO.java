package com.avengers.rpgame.data.gameStatus;

public interface IGameStatusDAO {
    public void saveGameStatus();
    public GameStatus loadGameStatus(int saveSlot);
}
