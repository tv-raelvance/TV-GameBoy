package controllers;

import models.Player;
import models.Balloon;
import models.Tower;
import models.Map;

public class GameController {
    private Player player;
    private Map gameMap;

    public GameController(Player player, Map gameMap) {
        this.player = player;
        this.gameMap = gameMap;
    }

    public void handleInput(String input) {
        // Handle user input for placing towers or other actions
    }

    public void updateGameState() {
        // Update the game state based on player actions and game events
    }

    public void spawnBalloon(Balloon balloon) {
        // Logic to spawn a new balloon in the game
    }

    public void placeTower(Tower tower) {
        // Logic to place a tower on the map
    }

    public Player getPlayer() {
        return player;
    }

    public Map getGameMap() {
        return gameMap;
    }
}