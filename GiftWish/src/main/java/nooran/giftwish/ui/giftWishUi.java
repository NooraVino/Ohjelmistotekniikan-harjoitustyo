/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nooran.giftwish.ui;

import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nooran.giftwish.dao.FileGiftDao;
import nooran.giftwish.dao.FileUserDao;
import nooran.giftwish.domain.MakeWishes;
import nooran.giftwish.domain.User;

/**
 *
 * @author vino
 */
public class giftWishUi extends Application {
     private Scene newUserScene;
    private Scene loginScene;
    private Scene todoScene;
    
    private MakeWishes makeWishes;
    private Label menuLabel = new Label();
    
@Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        String giftFile = properties.getProperty("giftFile");
            
        FileUserDao userDao = new FileUserDao(userFile);
        FileGiftDao giftDao = new FileGiftDao();
        makeWishes = new MakeWishes(userDao);
    }

  @Override
    public void start(Stage primaryStage) {            
        // login scene
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("Kayttaja");
        Label loginLabel2 = new Label("Kayttaja");
        TextField usernameInput = new TextField();
        TextField usernameInput2 = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, loginLabel2, usernameInput, usernameInput2);
        Label loginMessage = new Label("Kirjaudu sisään");
        
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        loginButton.setOnAction(e->{
            String username = usernameInput.getText();
        menuLabel.setText(username + " logged in...");
            if ( makeWishes.login(username) ){
                loginMessage.setText("");
                //redrawTodolist();
                primaryStage.setScene(todoScene);  
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
        
        loginScene = new Scene(loginPane, 300, 250);    
        
        
        // new createNewUserScene
        
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
            String name = newNameInput.getText();
   
            if ( username.length()==2 || name.length()<2 ) {
                userCreationMessage.setText("username or name too short");
                userCreationMessage.setTextFill(Color.RED);  
             } else if (makeWishes.createUser(username, name) ){
                userCreationMessage.setText("");                
                loginMessage.setText("new user created");
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
           // System.out.println(todoService.getLoggedUser());
          
           
    });
                }
    public static void main(String[] args) {
        launch(giftWishUi.class);
    }
    
}
