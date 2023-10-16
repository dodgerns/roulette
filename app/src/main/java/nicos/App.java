package nicos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nicos.controller.IController;
import nicos.controller.betting_table_controller.BettingTableController;
import nicos.controller.betting_table_controller.IBettingTableController;
import nicos.controller.game_controller.GameController;
import nicos.controller.roulette_controller.IRoulleteController;
import nicos.controller.roulette_controller.RouletteController;
import nicos.model.betting_table.BettingTableModel;
import nicos.model.betting_table.IBettingTableModel;
import nicos.model.game.GameModel;
import nicos.model.game.IGameModel;
import nicos.model.roulette.IRouletteModel;
import nicos.model.roulette.RouletteModel;
import nicos.view.betting_table_node.BettingTableNode;
import nicos.view.betting_table_node.IBettingTableNode;
import nicos.view.components.IComponent;
import nicos.view.components.MessageComponent;
import nicos.view.components.OnClickSectorComponent;
import nicos.view.game_node.GameNode;
import nicos.view.game_node.IGameNode;
import nicos.view.game_pane.GamePane;
import nicos.view.roulette_node.IRouletteNode;
import nicos.view.roulette_node.RouletteNode;

public class App extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        IController gameController = createGame();
        IController rouletteController = createRoullete();
        IController bettingTableController = createBettingTable();

        gameController.addController("RouletteController", rouletteController);
        gameController.addController("BettingTableController", bettingTableController);
        

        IGameNode gameNode = new GameNode();
        gameNode.addComponents(gameController.getComponents());

        gameController.setConfig();

        GamePane gamePane = new GamePane();
        gamePane.addBackground("/images/ruleta.jpeg");
        
        gamePane.addStage("gameNode", gameNode);

        stage.setScene(new Scene(gamePane, 1000, 550));
        stage.setResizable(false);
        stage.show();
    }

    private IController createBettingTable() {
        IBettingTableModel bettingTableModel = new BettingTableModel();
        IBettingTableNode bettingTableNode = createBettingTableNode();
        IBettingTableController bettingTableController = new BettingTableController(bettingTableModel, bettingTableNode);

        bettingTableController.setConfig();

        return bettingTableController;
    }
    private IBettingTableNode createBettingTableNode(){
        IBettingTableNode bettingTableNode = new BettingTableNode();
        IComponent number1 = new MessageComponent("○", 440, 260, Color.YELLOW);
        IComponent number2 = new MessageComponent("○", 440, 200, Color.YELLOW);
        IComponent number3 = new MessageComponent("○", 440, 140, Color.YELLOW);

        //○ ●
        bettingTableNode.addComponent("number1", number1);
        bettingTableNode.addComponent("number2", number2);
        bettingTableNode.addComponent("number3", number3);

        return bettingTableNode;
    }

    private IController createGame() {
        IGameModel gameModel = new GameModel();
        IGameNode gameNode = new GameNode();
        IController gameController = new GameController(gameModel, gameNode);

        IComponent startGame = new OnClickSectorComponent("StarGame", 150, 428, Color.BEIGE);

        gameNode.addComponent("startGame", startGame);
        
        return gameController;
    }

    private IRoulleteController createRoullete(){
        IRouletteModel rouletteModel = new RouletteModel();
        IRouletteNode rouletteNode = new RouletteNode();
        IRoulleteController rouletteController = new RouletteController(rouletteModel, rouletteNode);

        IComponent messageInformationNumber = new MessageComponent("Número Ganador: ", 155, 50, Color.BEIGE);
        IComponent messageWinningNumber = new MessageComponent("-", 185, 72, Color.BEIGE);

        rouletteNode.addComponent("messageWinningNumber", messageWinningNumber);
        rouletteNode.addComponent("messageInformationNumber", messageInformationNumber);

        rouletteController.setConfig();

        return rouletteController;
    }
}
