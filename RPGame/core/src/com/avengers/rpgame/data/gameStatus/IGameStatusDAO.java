package com.avengers.rpgame.data.gameStatus;

public interface IGameStatusDAO {
    public void saveGameStatus(GameStatus gameStatus);
    public GameStatus loadGameStatus(int saveSlot);
}
