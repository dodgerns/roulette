package nicos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nicos.controller.AController;
import nicos.controller.betting_controller.BettingController;
import nicos.controller.betting_table_controller.BettingTableController;
import nicos.controller.game_controller.GameController;
import nicos.controller.roulette_controller.RouletteController;
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
import nicos.model.user.IUserModel;
import nicos.model.user.UserModel;
import nicos.view.ANode;
import nicos.view.betting_node.BettingNode;
import nicos.view.betting_node.IBettingNode;
import nicos.view.betting_table_node.BettingTableNode;
import nicos.view.betting_table_node.IBettingTableNode;
import nicos.view.components.ButtonComponent;
import nicos.view.components.IComponent;
import nicos.view.components.MessageComponent;
import nicos.view.game_node.GameNode;
import nicos.view.game_node.IGameNode;
import nicos.view.game_pane.GamePane;
import nicos.view.roulette_node.IRouletteNode;
import nicos.view.roulette_node.RouletteNode;
import nicos.view.user_node.IUserNode;
import nicos.view.user_node.UserNode;

public class App extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AController gameController = createGame();
        AController rouletteController = createRoullete();
        AController bettingTableController = createBettingTable();

        gameController.addController("RouletteController", rouletteController);
        gameController.addController("BettingTableController", bettingTableController);


        ANode gameNode = new GameNode();
        gameNode.addComponents(gameController.getComponents());

        gameController.setConfig();

        GamePane gamePane = new GamePane();
        gamePane.addBackground("/images/ruleta.jpeg");
        
        gamePane.addStage("gameNode", gameNode);

        stage.setScene(new Scene(gamePane, 1000, 550));
        stage.setResizable(false);
        stage.show();
    }

    private AController createBetting() {
        IBettingNode bettingNode = new BettingNode();
        IBettingModel bettingModel = new BettingModel();
        AController bettingController = new BettingController(bettingModel, bettingNode);

        bettingModel.addPrize( Integer.toString(18),  Double.toString(1));
        bettingModel.addPrize( Integer.toString(12),  Double.toString(2));
        bettingModel.addPrize( Integer.toString(24),  Double.toString(0.5));
        bettingModel.addPrize( Integer.toString(6),  Double.toString(5));
        bettingModel.addPrize( Integer.toString(4),  Double.toString(8));
        bettingModel.addPrize( Integer.toString(3),  Double.toString(11));
        bettingModel.addPrize( Integer.toString(2),  Double.toString(17));
        bettingModel.addPrize( Integer.toString(1),  Double.toString(35));

        return bettingController;
    }

    private AController createUser() {
        IUserNode userNode = new UserNode();
        IUserModel userModel = new UserModel();
        AController userController = new UserController(userModel, userNode);

        ICasinoChip casinoChip = new CasinoChip();
        casinoChip.setChips(100, 10);
        casinoChip.setChips(50, 5);
        userModel.setCasinoChip(casinoChip);

        return userController;
    }
    
    private AController createGame() {
        IGameModel gameModel = new GameModel();
        IGameNode gameNode = new GameNode();
        AController gameController = new GameController(gameModel, gameNode);

        ButtonComponent userState= new ButtonComponent("Estado", 800, 470);
        gameNode.addComponent("UserState", userState);
        
        ButtonComponent newRound= new ButtonComponent("Nueva Ronda", 518, 470);
        gameNode.addComponent("NewRound", newRound);

        ButtonComponent startRound= new ButtonComponent("Iniciar Ronda", 670, 470);
        gameNode.addComponent("StartGame", startRound);
        
        return gameController;
    }

    private AController createRoullete(){
        IRouletteModel rouletteModel = new RouletteModel();
        IRouletteNode rouletteNode = new RouletteNode();
        AController rouletteController = new RouletteController(rouletteModel, rouletteNode);

        IComponent messageInformationNumber = new MessageComponent("Número Ganador: ", 155, 50, Color.BEIGE);
        IComponent messageWinningNumber = new MessageComponent("-", 185, 72, Color.BEIGE);

        rouletteNode.addComponent("messageWinningNumber", messageWinningNumber);
        rouletteNode.addComponent("messageInformationNumber", messageInformationNumber);

        rouletteController.setConfig();

        return rouletteController;
    }

    private AController createBettingTable() {
        AController bettingController = createBetting();
        AController userController = createUser();
        IBettingTableModel bettingTableModel = new BettingTableModel();
        IBettingTableNode bettingTableNode = createBettingTableNode();

        AController bettingTableController = new BettingTableController(bettingTableModel, bettingTableNode);

        bettingTableController.addController("BettingController", bettingController);
        bettingTableController.addController("UserController", userController);

        bettingTableController.setConfig();

        return bettingTableController;
    }

    private IBettingTableNode createBettingTableNode(){
        IBettingTableNode bettingTableNode = new BettingTableNode();

        // number 0 ok
        IComponent number = new MessageComponent("○", 390, 203, Color.RED);
        bettingTableNode.addComponent(0+"", number);

        //numbers 1, 2, 3... ok
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
        //numbers 1+2, 2+3 ok
        positionX = 440;
        for(int i = 1; i<=36;i+=3){
            int positionY=236;
            for(int j = i; j<i+2; j++){
                //○ ●
                number = new MessageComponent("○", positionX, positionY, Color.BLUE);
                bettingTableNode.addComponent( j +"-"+(j+1), number);
                positionY -= 60;
            }
            positionX += 42;
        }
        //numbers 1+4, 2+5 ok
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
        //numbers 1+2+4+5 ok
        positionX = 460;
        for(int i = 1; i<33;i+=3){
            int positionY=230;
            for(int j = i+1; j<i+3; j++){
                //○ ●
                number = new MessageComponent("○", positionX, positionY, Color.SPRINGGREEN);
                bettingTableNode.addComponent((j-1) +"-"+ j +"-"+((j-1)+3)+"-"+(j+3), number);
                positionY -= 60;
            }
            positionX += 42;
        }

        //numbers 1+2+3
        positionX = 440;
        for(int i = 1; i<=34;i+=3){
            int positionY=294;
            //○ ●
            number = new MessageComponent("○", positionX, positionY, Color.WHEAT);
            bettingTableNode.addComponent((i) +"-"+ (i+1) +"-"+(i+2), number);
            positionX += 42;
        }


        //numbers 0-1-2
        number = new MessageComponent("○", 420, 232, Color.LIGHTSALMON);
        bettingTableNode.addComponent("0-1-2", number);

        //numbers 0-2-3
        number = new MessageComponent("○", 420, 171, Color.LIGHTSALMON);
        bettingTableNode.addComponent("0-2-3", number);


        //numbers 1+2+3+4+5+6
        positionX = 460;
        for(int i = 1; i<=33;i+=3){
            int positionY=294;
            //○ ●
            number = new MessageComponent("○", positionX, positionY, Color.SKYBLUE);
            bettingTableNode.addComponent((i) +"-"+ (i+1) +"-"+(i+2)+"-"+(i+3) +"-"+ (i+4) +"-"+(i+5), number);
            positionX += 42;
        }

        //numbers even
        positionX = 544;
        int positionY=358;
        String key = "2";
        for(int i = 4; i<=36;i+=2){
            //○ ●
            key += "-"+i;
        }
        number = new MessageComponent("○", positionX, positionY, Color.CYAN);
        bettingTableNode.addComponent(key, number);

        //numbers odd
        positionX = 800;
        positionY=358;
        key = "1";
        for(int i = 3; i<36;i+=2){
            //○ ●
            key += "-"+i;
        }
        number = new MessageComponent("○", positionX, positionY, Color.CYAN);
        bettingTableNode.addComponent(key, number);

        //numbers 1-18
        positionX = 456;
        positionY=358;
        key = "1";
        for(int i = 2; i<=18;i+=1){
            //○ ●
            key += "-"+i;
        }
        number = new MessageComponent("○", positionX, positionY, Color.THISTLE);
        bettingTableNode.addComponent(key, number);

        //numbers 19-36
        positionX = 880;
        positionY=358;
        key = "19";
        for(int i = 20; i<=36;i+=1){
            //○ ●
            key += "-"+i;
        }
        number = new MessageComponent("○", positionX, positionY, Color.THISTLE);
        bettingTableNode.addComponent(key, number);

        //numbers red
        positionX = 625;
        positionY = 360;
        key = "1";
        for(int i = 3; i<=36;i+=2){
            //○ ●
            if(i==18)key += "-"+i;
            if(i==11||i==18||i==29)i++;
            key += "-"+i;
        }
        number = new MessageComponent("○", positionX, positionY, Color.BLACK);
        bettingTableNode.addComponent(key, number);

        //numbers black
        positionX = 710;
        positionY = 360;
        key = "2";
        for(int i = 4; i<=36;i+=2){
            //○ ●
            if(i==10||i==28)key += "-"+i;
            if(i==10||i==19||i==28)i++;
            key += "-"+i;
        }
        number = new MessageComponent("○", positionX, positionY, Color.RED);
        bettingTableNode.addComponent(key, number);

        //numbers 1-12
        positionX = 500;
        positionY = 315;
        key = "1";
        for(int i = 2; i<=12;i+=1){
            //○ ●
            key += "-"+i;
        }
        number = new MessageComponent("○", positionX, positionY, Color.FUCHSIA);
        bettingTableNode.addComponent(key, number);

        //numbers 13-24
        positionX = 666;
        positionY = 315;
        key = "13";
        for(int i = 14; i<=24;i+=1){
            //○ ●
            key += "-"+i;
        }
        number = new MessageComponent("○", positionX, positionY, Color.FUCHSIA);
        bettingTableNode.addComponent(key, number);

        //numbers 25-36
        positionX = 842;
        positionY = 315;
        key = "25";
        for(int i = 26; i<=36;i+=1){
            //○ ●
            key += "-"+i;
        }
        number = new MessageComponent("○", positionX, positionY, Color.FUCHSIA);
        bettingTableNode.addComponent(key, number);

        //numbers 1-24
        positionX = 586;
        positionY = 315;
        key = "1";
        for(int i = 2; i<=24;i+=1){
            //○ ●
            key += "-"+i;
        }
        number = new MessageComponent("○", positionX, positionY, Color.HONEYDEW);
        bettingTableNode.addComponent(key, number);

        //numbers 13-36
        positionX = 755;
        positionY = 315;
        key = "13";
        for(int i = 14; i<=36;i+=1){
            //○ ●
            key += "-"+i;
        }
        number = new MessageComponent("○", positionX, positionY, Color.HONEYDEW);
        bettingTableNode.addComponent(key, number);

        //numbers 1-4-7-...-28-31-34
        positionX = 947;
        positionY = 262;
        key = "1";
        for(int i = 4; i<=34;i+=3){
            //○ ●
            key += "-"+i;
        }
        number = new MessageComponent("○", positionX, positionY, Color.FUCHSIA);
        bettingTableNode.addComponent(key, number);

        //numbers 2-5-8-...-29-32-35
        positionX = 947;
        positionY = 200;
        key = "2";
        for(int i = 5; i<=35;i+=3){
            //○ ●
            key += "-"+i;
        }
        number = new MessageComponent("○", positionX, positionY, Color.FUCHSIA);
        bettingTableNode.addComponent(key, number);

        //numbers 3-6-9-...-30-33-36
        positionX = 947;
        positionY = 140;
        key = "3";
        for(int i = 6; i<=36;i+=3){
            //○ ●
            key += "-"+i;
        }
        number = new MessageComponent("○", positionX, positionY, Color.FUCHSIA);
        bettingTableNode.addComponent(key, number);

        //numbers 1-2-4-5-...-31-32-34-35
        positionX = 947;
        positionY = 230;
        key="1-2";
        for(int i = 4; i<=35;i+=3){
            //○ ●
            key += "-"+i+"-"+(i+1);
        }
        number = new MessageComponent("○", positionX, positionY, Color.LIGHTBLUE);
        bettingTableNode.addComponent(key, number);

        //numbers 2-3-5-6-...-32-33-35-36
        positionX = 947;
        positionY = 170;
        key = "2-3";
        for(int i = 5; i<=36;i+=3){
            //○ ●
            key += "-"+i+"-"+(i+1);
        }
        number = new MessageComponent("○", positionX, positionY, Color.LIGHTBLUE);
        bettingTableNode.addComponent(key, number);

        return bettingTableNode;
    }

}
