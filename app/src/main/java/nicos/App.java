package nicos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nicos.controller.IController;
import nicos.controller.betting_controller.BettingController;
import nicos.controller.betting_controller.IBettingController;
import nicos.controller.betting_table_controller.BettingTableController;
import nicos.controller.betting_table_controller.IBettingTableController;
import nicos.controller.game_controller.GameController;
import nicos.controller.roulette_controller.IRoulleteController;
import nicos.controller.roulette_controller.RouletteController;
import nicos.controller.rules_controller.IRulesController;
import nicos.controller.rules_controller.RulesController;
import nicos.controller.user_controller.IUserController;
import nicos.controller.user_controller.UserController;
import nicos.model.betting.BettingModel;
import nicos.model.betting.IBettingModel;
import nicos.model.betting_table.BettingTableModel;
import nicos.model.betting_table.IBettingTableModel;
import nicos.model.casino_chip.CasinoChip;
import nicos.model.casino_chip.ICasinoChip;
import nicos.model.game.GameModel;
import nicos.model.game.IGameModel;
import nicos.model.roulette.IRouletteModel;
import nicos.model.roulette.RouletteModel;
import nicos.model.rules.IRulesModel;
import nicos.model.rules.RulesModel;
import nicos.model.user.IUserModel;
import nicos.model.user.UserModel;
import nicos.view.betting_node.BettingNode;
import nicos.view.betting_node.IBettingNode;
import nicos.view.betting_table_node.BettingTableNode;
import nicos.view.betting_table_node.IBettingTableNode;
import nicos.view.components.IComponent;
import nicos.view.components.MessageComponent;
import nicos.view.components.OnClickSectorComponent;
import nicos.view.components.TextInputComponent;
import nicos.view.game_node.GameNode;
import nicos.view.game_node.IGameNode;
import nicos.view.game_pane.GamePane;
import nicos.view.roulette_node.IRouletteNode;
import nicos.view.roulette_node.RouletteNode;
import nicos.view.rules_node.IRulesNode;
import nicos.view.rules_node.RulesNode;
import nicos.view.user_node.IUserNode;
import nicos.view.user_node.UserNode;

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

    private IController createBetting() {
        IBettingNode bettingNode = new BettingNode();
        IBettingModel bettingModel = new BettingModel();
        IBettingController bettingController = new BettingController(bettingModel, bettingNode);

        return bettingController;
    }

    private IController createUser() {
        IUserNode userNode = new UserNode();
        IUserModel userModel = new UserModel();
        IUserController userController = new UserController(userModel, userNode);
        ICasinoChip casinoChip = new CasinoChip();
        casinoChip.setChips(100, 10);
        userModel.setCasinoChip(casinoChip);

        return userController;
    }

    private IController createRules() {
        IRulesNode rulesNode = new RulesNode();
        IRulesModel rulesModel = new RulesModel();
        IRulesController rulesController = new RulesController(rulesModel, rulesNode);
        return rulesController;
    }

    private IController createBettingTable() {
        IController bettingController = createBetting();
        IController userController = createUser();
        IBettingTableModel bettingTableModel = new BettingTableModel();
        IBettingTableNode bettingTableNode = createBettingTableNode();

        IBettingTableController bettingTableController = new BettingTableController(bettingTableModel, bettingTableNode);

        bettingTableController.addController("BettingController", bettingController);
        bettingTableController.addController("UserController", userController);

        bettingTableController.setConfig();

        return bettingTableController;
    }
    private IBettingTableNode createBettingTableNode(){
        IBettingTableNode bettingTableNode = new BettingTableNode();

        // number 0
        IComponent number = new MessageComponent("○", 390, 203, Color.RED);
        bettingTableNode.addComponent(0+"", number);

        //numbers 1, 2, 3
        int positionX = 440;
        for(int i = 1; i<=34;i+=3){
            int positionY=260;
            for(int j = i; j<i+3;j++){
                //○ ●
                number = new MessageComponent("○", positionX, positionY, Color.YELLOW);
                bettingTableNode.addComponent(j+"", number);
                positionY -= 60;
            }
            positionX += 42;
        }
        //numbers 1+2, 2+3
        positionX = 440;
        for(int i = 1; i<=36;i+=3){
            int positionY=236;
            for(int j = i; j<i+2; j++){
                //○ ●
                number = new MessageComponent("○", positionX, positionY, Color.BLUE);
                bettingTableNode.addComponent( j +"-"+j+1, number);
                positionY -= 60;
            }
            positionX += 42;
        }
        //numbers 1+4,2+5
        positionX = 460;
        for(int i = 1; i<33;i+=3){
            int positionY=260;
            for(int j = i; j<i+3; j++){
                //○ ●
                number = new MessageComponent("○", positionX, positionY, Color.WHITE);
                bettingTableNode.addComponent(j +"-"+(j+3), number);
                positionY -= 60;
            }
            positionX += 42;
        }
        //numbers 1+2+4+5
        positionX = 460;
        for(int i = 1; i<33;i+=3){
            int positionY=230;
            for(int j = i+1; j<i+3; j++){
                //○ ●
                number = new MessageComponent("○", positionX, positionY, Color.SPRINGGREEN);
                bettingTableNode.addComponent((j-1) +"-"+ j +"-"+((j-1)+3)+"-"+(j+3), number);
                positionY -= 60;
                System.out.println((j-1) +"-"+ j +"-"+((j-1)+3)+"-"+(j+3));
            }
            positionX += 42;
        }

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
