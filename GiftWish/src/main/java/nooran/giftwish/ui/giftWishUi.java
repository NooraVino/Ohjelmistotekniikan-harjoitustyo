/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nooran.giftwish.ui;

import java.io.FileInputStream;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
    private Scene GiftScene;
    private VBox GiftNodes ;
    
    private MakeWishes makeWishes;
    private Label menuLabel = new Label();
    private BorderPane asettelu = new BorderPane();
    
@Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        String giftFile = properties.getProperty("giftFile");
            
        FileUserDao userDao = new FileUserDao(userFile);
        FileGiftDao giftDao = new FileGiftDao(giftFile, userDao);
        makeWishes = new MakeWishes(userDao, giftDao);
    }
    
    public Node createGiftNode(Gift gift) {
        HBox box = new HBox(10);
        Label label  = new Label(gift.getContent());
        label.setMinHeight(28);
        Button button = new Button("done");
        button.setOnAction(e->{
            makeWishes.markDone(gift.getId());
            redrawGiftlist();
        });
                
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0,5,0,5));
        
        box.getChildren().addAll(label, spacer, button);
        return box;
    }
    
    public void redrawGiftlist() {
        GiftNodes.getChildren().clear();     

        List<Gift> undoneGifts = makeWishes.getUndone();
        undoneGifts.forEach(gift->{
            GiftNodes.getChildren().add(createGiftNode(gift));
        });     
    }
    

  @Override
    public void start(Stage primaryStage) {            
        // kirjautumissivu
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("Kayttaja");
        TextField usernameInput = new TextField();
        
        
        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label("Kirjaudu sisään");
        
        Button loginButton = new Button("Kirjaudu sisään");
        Button createButton = new Button("Luo uusi käyttäjä");
        loginButton.setOnAction(e->{
            String username = usernameInput.getText();
        menuLabel.setText(username + " logged in...");
            if ( makeWishes.login(username) ){
                loginMessage.setText("");
                redrawGiftlist();
                primaryStage.setScene(GiftScene);  
                usernameInput.setText("");
            } else {
                loginMessage.setText("use does not exist");
                loginMessage.setTextFill(Color.RED);
            }      
        });  
                 
        
        
        createButton.setOnAction(e->{
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);   
        });  
        
        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);       
        
        loginScene = new Scene(loginPane, 500, 550);    
        
        
        // luo uusi käyttäjä-sivu
        
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

        createNewUserButton.setOnAction(e->{
            String username = newUsernameInput.getText();
            String password = newNameInput.getText();
   
            if ( username.length()<= 3 || password.length()<=3 ) {
                userCreationMessage.setText("Käyttäjätunnus tai salasana liian lyhyitä");
                userCreationMessage.setTextFill(Color.RED);  
             } else if (makeWishes.createUser(username, password) ){
                userCreationMessage.setText("");                
                loginMessage.setText("Uusi käyttäjä luotu käyttäjänimellä:  " + username);
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);    
                
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }
 
        });  
        
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, createNewUserButton); 
       
        newUserScene = new Scene(newUserPane, 300, 250);
        
        primaryStage.setTitle("Make A Wish!");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->{
            System.out.println("closing");
            System.out.println(makeWishes.getLoggedUser());
            menuLabel.setText("Kirjaudu ulos ennen kuin suljet sovelluksen");
            if (makeWishes.getLoggedUser()!=null) {
                e.consume();   
            }
          
           
    });
                
 
            
        // pääsivu
        
        ScrollPane todoScollbar = new ScrollPane();       
        BorderPane mainPane = new BorderPane(todoScollbar);
        GiftScene = new Scene(mainPane, 300, 250);
                
        HBox menuPane = new HBox(10);    
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");      
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
        logoutButton.setOnAction(e->{
            makeWishes.logout();
            primaryStage.setScene(loginScene);
        });        
        
        HBox createForm = new HBox(10);    
        Button createTodo = new Button("create");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField newGiftInput = new TextField();
        TextField newContentInput = new TextField();
        createForm.getChildren().addAll(newGiftInput, newContentInput, spacer, createTodo);
        
        GiftNodes = new VBox(10);
        GiftNodes.setMaxWidth(280);
        GiftNodes.setMinWidth(280);
        redrawGiftlist();
        
        todoScollbar.setContent(GiftNodes);
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);
        
        createTodo.setOnAction(e->{
            makeWishes.makeNewWish(newGiftInput.getText(), newContentInput.getText());
            newGiftInput.setText(""); 
            newContentInput.setText("");
           redrawGiftlist();
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
