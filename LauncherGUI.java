
/*
Team Tei x Ren
Le Minh Truyen  - s3627577
Hoang Quang Huy - s3623383
Nguyen Tan Thanh s3634815
OOP - Project - the Chess Game
2017C
 */

/*
Resource:
- All of app icons and piece images can be found on google. They are standard chess images,
- SheetStyle can be found at - http://www.renderx.com/chess.html
- How to build a chess board - https://stackoverflow.com/questions/24082063/chessboard-with-automatic-resizing
- Attempting to try network services - Based on "JavaFX Software: Chat (Server-Client)" - https://www.youtube.com/watch?v=VVUuo9VO2II
- Chess rule - https://www.chess.com/learn-how-to-play-chess
 */


/*
This is a fun to play chess for two local players. We are working to make it playable via the internet in the future.
The software should work well following chess rules, with unique movements of pieces
 */


import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import Networking.TrialNetwork;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LauncherGUI extends Application {


    private Board board;
    public static TrialNetwork testNet;
    private boolean whitePlayer;
    private boolean localMode = false;


    // start the game.
    @Override
    public void start(Stage mainStage) {
        // load the sheet style as the background.
        mainStage.setTitle("Xtreme Chess Ultimate 'Lite'  ");
        mainStage.getIcons().add(new Image("resources/icons/app_icon.png"));

        BorderPane panel = new BorderPane();
        Scene mainScene = new Scene(panel);
        mainStage.setScene(mainScene);

        // using the sheet style for giving the
        mainScene.getStylesheets().add("resources/stylesheet.css");

        // let the user to select team color
        chooseStartingColor();

        // create the board
        board = new Board(whitePlayer);
        panel.setCenter(board); // sized 400x400

        // add menu
        MenuBar menuBar = createMenuBar();
        // set the menu on the top of the window
        panel.setTop(menuBar);

        mainStage.show();

    } // end start

    // Create the main menu bar
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        // create labels
        Menu gameMenu = new Menu("Game");
        MenuItem menuItemQuit = new MenuItem("Quit");
        Menu menuHelp = new Menu("Help");
        MenuItem menuItemAbout = new MenuItem("Credits");


        // apply action when the buttons are being selected.
        menuBar.getMenus().add(gameMenu);
        menuItemQuit.setOnAction(e -> quitGame());
        gameMenu.getItems().add(menuItemQuit);
        menuBar.getMenus().add(menuHelp);
        menuItemAbout.setOnAction(e -> displayCredit());
        menuHelp.getItems().add(menuItemAbout);

        return menuBar;
    } // end createMenuBar

    // choosing the options
    public void chooseStartingColor() {
        Alert GameInfo = new Alert(AlertType.CONFIRMATION);
        GameInfo.setTitle("New game");
        GameInfo.setHeaderText(null);
        GameInfo.setContentText("Choose color");

        // add label for the button
        ButtonType hostButton = new ButtonType("Host");
        ButtonType singleButton = new ButtonType("2 local player mode ");

        GameInfo.getButtonTypes().setAll(hostButton, singleButton);
        Optional<ButtonType> result = GameInfo.showAndWait();

        // set the white player in the bottom.
        this.whitePlayer = true;
        this.localMode = true;

    } // end chooseStartingColor


    // Quits the program
    public void quitGame() {
        Platform.exit();
        System.exit(0);
    } // end quitGame


    // Display information of the game.
    public void displayCredit() {
        Alert infoAlert = new Alert(AlertType.INFORMATION);
        infoAlert.setTitle("Credits");
        infoAlert.setHeaderText(null);

        // set window icon
        Stage alertStage = (Stage) infoAlert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image("resources/icons/about.png"));

        // the graphic replaces the standard icon

        infoAlert.setContentText("Programmed by Nguyen Tan Thanh, Le Minh Truyen, and Hoang Quang Huy.\n\n" +
                "Team \"Tei x Ren\".\n\n");
        infoAlert.showAndWait();
    } // end displayCredit


    // main program to launch.
    public static void main(String[] args) {

        try {
            launch(args);
            System.exit(0);
        } catch (Exception error) {
            error.printStackTrace();
            System.exit(0);
        }
    } // end main

}