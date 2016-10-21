/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videos;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author Haythemlp
 */
public class Videos extends Application {
    
 
    ObservableList<data> l = FXCollections.observableArrayList();
      
  MediaPlayer player= null ,pl,tab;
   
 final      TableView<data> mediatab=   new TableView<>();

 
      int i=0;
      int num =0;
    
    
      

     private Stage primaryStage;
    private AnchorPane medipalyer;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        
       
        
        this.primaryStage = stage;
         login();
   
  
  
    }
    
    
    
    
    
    
    
      public void login() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Videos.class
                    .getResource("vidoes.fxml"));
            medipalyer = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(medipalyer);
            primaryStage.setScene(scene);
   
 primaryStage.setMinWidth(670);
        primaryStage.setMinHeight(400);
            // Give the controller access to the main app.

                 VidoesController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) { System.out.println(e); }
        }


      
      
    public static void main(String[] args) {
        launch(args);
    }

  public Stage getPrimaryStage() {
        return primaryStage;
    }
          
   
    
    public  ObservableList<data>  filetostring(List<File>  f){
        num=-1;
        ObservableList<data> item = FXCollections.observableArrayList();
        
            
        f.stream().map((file) -> {
            num++;
             return file;
         }).forEach((file) -> {

             String name =file.getName();
             String   path= new File(file.getPath()).getAbsolutePath();
             item.add(new data(path,name)) ;
         });
 return item;


}
      public  ObservableList<data>  listtostring(File []  f){
        ObservableList<data> item = FXCollections.observableArrayList();
        num=-1;
        
        for ( File file :f) {
      num++;
          String name =file.getName();
  String path= new File(file.getPath()).getAbsolutePath();
               item.add(new data(path,name)) ;
} 
        return item;
               


     
 
}

 



}

