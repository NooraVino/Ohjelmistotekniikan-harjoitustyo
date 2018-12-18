/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nooran.giftwish.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nooran.giftwish.dao.FileGiftDao;
import nooran.giftwish.dao.FileUserDao;
import nooran.giftwish.domain.Gift;
import nooran.giftwish.domain.MakeWishes;
import nooran.giftwish.domain.User;

/**
 *
 * @author vino
 */
public class giftWishUi extends Application {

    private Scene newUserScene;
    private Scene loginScene;
    private Scene mainScene;
    private Scene giftScene;
    private Scene othersScene;
    private VBox GiftNodes;
    private VBox GiftNodesOthers;

    private MakeWishes makeWishes;
    private int id;

    private Label menuLabel = new Label();
    private TextField giftNameInput = new TextField();
    private TextField contentInput = new TextField();

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        InputStream input = null;

        OutputStream output = new FileOutputStream("config.properties");
        properties.load(new FileInputStream("config.properties"));

        if (properties.getProperty("userFile") == null) {
            properties.setProperty("userFile", "users.txt");

        }
        if (properties.getProperty("giftFile") == null) {
            properties.setProperty("giftFile", "gifts.txt");

        }
        properties.store(output, null);

        String giftFile = properties.getProperty("giftFile");
        String userFile = properties.getProperty("userFile");

        FileUserDao userDao = new FileUserDao(userFile);
        FileGiftDao giftDao = new FileGiftDao(giftFile, userDao);
        makeWishes = new MakeWishes(userDao, giftDao);
    }

    public Node createGiftNode(Gift gift, Stage primaryStage) {
        HBox buttons = new HBox(10);
        BorderPane box = new BorderPane();
        box.setPadding(new Insets(10, 10, 10, 10));
        Label name = new Label(gift.getName());
        Label content = new Label(gift.getContent());
        content.setMinHeight(20);
        name.setMinHeight(20);

        Button deleteButton = new Button("poista");
        deleteButton.setOnAction(e -> {
            makeWishes.markDone(gift.getId());
            redrawGiftlist(primaryStage);
        });
        Button remakeButton = new Button("muokkaa");
        remakeButton.setOnAction(e -> {
            id = gift.getId();
            giftNameInput.setText(gift.getName());
            contentInput.setText(gift.getContent());

            primaryStage.setScene(giftScene);

        });
        buttons.getChildren().addAll(remakeButton, deleteButton);

        box.setLeft(name);
        box.setCenter(content);
        box.setRight(buttons);

        return box;
    }

    public void redrawGiftlist(Stage primaryStage) {
        GiftNodes.getChildren().clear();

        List<Gift> undoneGifts = makeWishes.getUndone();
        undoneGifts.forEach(gift -> {
            GiftNodes.getChildren().add(createGiftNode(gift, primaryStage));
        });
    }
    
    public void redrawGiftlist2(Stage primaryStage) {
        GiftNodesOthers.getChildren().clear();

        List<Gift> undoneGifts = makeWishes.getUndoneOthers();
        undoneGifts.forEach(gift -> {
            GiftNodes.getChildren().add(createGiftNode(gift, primaryStage));
        });
    }


    @Override
    public void start(Stage primaryStage) {

        // loginScene
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(10, 10, 10, 10));
        GridPane grid = new GridPane();

        Label loginLabel = new Label("Kayttaja: ");
        TextField usernameInput = new TextField();
        Label passwordLabel = new Label("Salasana: ");
        TextField passwordInput = new TextField();

        Label loginMessage = new Label("");

        Button loginButton = new Button("Kirjaudu sisään");
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            menuLabel.setText(username + " logged in...");
            if (makeWishes.login(username)) {
                loginMessage.setText("");
                redrawGiftlist(primaryStage);
                primaryStage.setScene(mainScene);
                usernameInput.setText("");
                passwordInput.setText("");
            } else {
                loginMessage.setText("use does not exist");
                loginMessage.setTextFill(Color.RED);
            }
        });

        grid.add(loginLabel, 0, 0);
        grid.add(usernameInput, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordInput, 1, 1);
        grid.add(loginButton, 1, 2);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button createButton = new Button("Luo uusi käyttäjä");
        createButton.setOnAction(e -> {
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);
        });

        border.setCenter(grid);
        border.setBottom(createButton);

        loginScene = new Scene(border, 300, 250);

        // new userScene
        VBox newUserPane = new VBox(10);

        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("username");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);

        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("create");
        createNewUserButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e -> {
            String username = newUsernameInput.getText();
            String password = newNameInput.getText();

            if (username.length() <= 3 || password.length() <= 3) {
                userCreationMessage.setText("Käyttäjätunnus tai salasana liian lyhyitä");
                userCreationMessage.setTextFill(Color.RED);
            } else if (makeWishes.createUser(username, password)) {
                menuLabel.setText(username + " logged in...");
                makeWishes.login(username);
                loginMessage.setText("");
                redrawGiftlist(primaryStage);
                primaryStage.setScene(mainScene);
                usernameInput.setText("");
                primaryStage.setScene(mainScene);
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);
            }

        });

        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, createNewUserButton);

        newUserScene = new Scene(newUserPane, 300, 250);

        // giftScene
        Label nameLabel = new Label("Nimi :");
        Label contentLabel = new Label("Sisältö: ");
        BorderPane giftPane = new BorderPane();
        giftPane.setPadding(new Insets(10, 10, 10, 10));
        GridPane centerPane = new GridPane();
        BorderPane topPane = new BorderPane();
        topPane.setPadding(new Insets(10, 10, 10, 10));

        Button logoutButton2 = new Button("kirjaudu ulos");
        topPane.setRight(logoutButton2);
        logoutButton2.setOnAction(e -> {
            makeWishes.remakeWish(id, giftNameInput.getText(), contentInput.getText());
            makeWishes.logout();
            primaryStage.setScene(loginScene);
        });

        Button saveButton = new Button("tallenna");
        saveButton.setOnAction(e -> {
            makeWishes.remakeWish(id, giftNameInput.getText(), contentInput.getText());
            redrawGiftlist(primaryStage);
            primaryStage.setScene(mainScene);
        });

        Button discardButton = new Button("älä tallenna");
        discardButton.setOnAction(e -> {
            primaryStage.setScene(mainScene);
        });
        centerPane.add(nameLabel, 0, 0);
        centerPane.add(giftNameInput, 0, 1);
        centerPane.add(contentLabel, 1, 0);
        centerPane.add(contentInput, 1, 1);
        centerPane.add(saveButton, 1, 2);
        centerPane.add(discardButton, 1, 3);
        centerPane.setHgap(10);
        centerPane.setVgap(10);

        giftPane.setTop(topPane);
        giftPane.setCenter(centerPane);

        giftScene = new Scene(giftPane, 900, 350);

        //mainScene
        ScrollPane giftScollbar = new ScrollPane();
        BorderPane mainPane = new BorderPane(giftScollbar);
        mainScene = new Scene(mainPane, 500, 550);

        HBox menuPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("kirjaudu ulos");
        Button othersButton = new Button("muiden");
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton, othersButton);
        logoutButton.setOnAction(e -> {
            makeWishes.logout();
            primaryStage.setScene(loginScene);
        });
        
        othersButton.setOnAction(e -> {
        primaryStage.setScene(othersScene);    
        });

        HBox createForm = new HBox(10);

        Button createNewGiftButton = new Button("luo uusi");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField newGiftInput = new TextField();
        TextField newContentInput = new TextField();
        createForm.getChildren().addAll(newGiftInput, newContentInput, spacer, createNewGiftButton);

        GiftNodes = new VBox(10);
        GiftNodes.setMaxWidth(500);
        GiftNodes.setMinWidth(500);
        redrawGiftlist(primaryStage);

        giftScollbar.setContent(GiftNodes);
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);

        createNewGiftButton.setOnAction(e -> {
            makeWishes.makeNewWish(newGiftInput.getText(), newContentInput.getText());
            newGiftInput.setText("");
            newContentInput.setText("");
            redrawGiftlist(primaryStage);
        });
        
        // othersScene
        
        HBox othersTop = new HBox(10);
        othersTop.setPadding(new Insets(10));
       
        Button logoutButton3 = new Button("kirjaudu ulos");
        Button returnButton = new Button("takaisin pääsivulle");
        othersTop.getChildren().addAll(logoutButton3, returnButton);
        logoutButton3.setOnAction(e -> {
            makeWishes.logout();
            primaryStage.setScene(loginScene);
        });
        
        returnButton.setOnAction(e -> {
            primaryStage.setScene(mainScene);
        });
        
       
        ScrollPane giftScollbar2 = new ScrollPane();
        BorderPane othersPane = new BorderPane(giftScollbar2);
        
        
        GiftNodesOthers = new VBox(10);
        GiftNodesOthers.setMaxWidth(500);
        GiftNodesOthers.setMinWidth(500);
        redrawGiftlist2(primaryStage);

        giftScollbar2.setContent(GiftNodesOthers);
//        mainPane.setBottom(createForm);
//        mainPane.setTop(menuPane);
        
        
//        Button bu = new Button ("");
//        othersMain.getChildren().addAll(bu);
        
        
        othersPane.setTop(othersTop);
        
        othersScene = new Scene(othersPane, 500, 550);


        /**
         * setup primaryStage
         */
        primaryStage.setTitle("Tee toiveita!");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("closing");
            System.out.println(makeWishes.getLoggedUser());
            menuLabel.setText("Kirjaudu ulos ennen kuin suljet sovelluksen");
            if (makeWishes.getLoggedUser() != null) {
                e.consume();
            }

        });
    }

    @Override
    public void stop() {
        // tee lopetustoimenpiteet täällä
        System.out.println("sovellus sulkeutuu");
    }

    public static void main(String[] args) {
        launch(giftWishUi.class);
    }

}
