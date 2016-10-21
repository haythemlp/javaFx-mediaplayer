/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videos;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * FXML Controller class
 *
 * @author Haythemlp
 */
public class VidoesController implements Initializable {
    
    int i=0;
    int back=0;
    
    
         ObservableList<data> item = FXCollections.observableArrayList();
         
     
    
    private Duration duration;
   
    @FXML private Label playTime;
    @FXML private Slider volumeSlider;
    @FXML private ToggleButton loop ,shuffle; 
    @FXML private Button play,full,forward;
    @FXML private StackPane root;
    @FXML private Slider slider;
    @FXML private MediaView view; 
    File file;
    File n=null;
    String path;
 
      Media  media ;
  MediaPlayer player= null ,pl;
List<String> list = Arrays.asList("*.mp3", "*.mp4");
  
  
  FileChooser.ExtensionFilter extFilter =     new FileChooser.ExtensionFilter("media",list );

   FilenameFilter textFilter = (File dir, String name) -> {
          String lowercaseName = name.toLowerCase();
          if (lowercaseName.endsWith( ".mp3")	||lowercaseName.endsWith(".mp4") ) {
              return true;
          } else {
              return false;
          }
      };

  
@FXML  private  void folder () {
    setPersonFilePath(null);

          DirectoryChooser fileChooser = new DirectoryChooser();
          
                File selectedDirectory = 
                        fileChooser.showDialog(MainAPP.getPrimaryStage());
              
        File[] fil= selectedDirectory.listFiles(textFilter) ;
       
         
        if (fil!= null) {
i=0;


       item= MainAPP.listtostring(fil);
    
     data medi;
     
     if(shuffle.isSelected()){
         Random rn = new Random();
 i = rn.nextInt( item.size() + 1) ;
         
}
     medi=item.get(i);
      
          String m= medi.getPath();
       Media med =new Media(new File(m).toURI().toString());
      MainAPP.getPrimaryStage().setTitle(medi.getName());
         if(player!=null){
           player.stop();
       }
        player=new MediaPlayer(med);
       
        view.setMediaPlayer(player);
        player.setAutoPlay(true);
refresh();
       
    
        } 
    }
         
         
  @FXML  private  void  openmultiple () {
          setPersonFilePath(null);
      
           FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(extFilter);
        List<File> fil =fileChooser.showOpenMultipleDialog(MainAPP.getPrimaryStage());
        if (fil!= null) {
           i=0; 
         item= MainAPP.filetostring(fil);
   data medi;
     
     if(shuffle.isSelected()){
         Random rn = new Random();
 i = rn.nextInt( item.size() + 1) ;
         
}
     medi=item.get(i);
          String m= medi.getPath();
       Media med =new Media(new File(m).toURI().toString());
      MainAPP.getPrimaryStage().setTitle(medi.getName());
         if(player!=null){
           player.stop();
       }
        player=new MediaPlayer(med);
       
        view.setMediaPlayer(player);
        player.setAutoPlay(true);
     
refresh();

         
    
        }
    }
  
  @FXML  private  void  openlist (){
      
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter lol = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(lol);

        // Show save file dialog
        File filer = fileChooser.showOpenDialog(MainAPP.getPrimaryStage());

        if (filer != null) {
            loadPersonDataFromFile(filer);
    
        }
     
     
     
 }
@FXML  private  void  save (){
     
          File personFile = getPersonFilePath();
        if (personFile != null) {
            savePersonDataToFile(personFile);
        } else {
            saveaslist();
        }
     
 }

@FXML  private  void  saveaslist (){
     
      FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File filer = fileChooser.showSaveDialog(MainAPP.getPrimaryStage());

        if (filer != null) {
            // Make sure it has the correct extension
            if (!filer.getPath().endsWith(".xml")) {
                filer = new File(filer.getPath() + ".xml");
            }
            savePersonDataToFile(filer);
        }
     
 }
           
  @FXML  private  void  forward () {
    
      
    
      
        
 data medi;
     
     if(shuffle.isSelected()){
         Random rn = new Random();
 i = rn.nextInt( item.size() + 1) ;
         
}else{
             i++;
        if(i>=item.size()){
            i=0;
            
        }
     }
     medi=item.get(i);
          String m= medi.getPath();
             MainAPP.getPrimaryStage().setTitle(medi.getName());
       Media med =new Media(new File(m).toURI().toString());
          if(player!=null){
           player.stop();
       }
        player=new MediaPlayer(med);
 
    
       
 
        view.setMediaPlayer(player);
        player.setAutoPlay(true);
        
        refresh();

         
    
        
    }
 
 @FXML  private  void stop (){
     
     player.stop();
     play.setText("Play");
     
 } 
 
 @FXML  private  void back (){
     
              
          i--;
        if(i<0){
            i=item.size();
            
        }
           data medi=item.get(i);
          String m= medi.getPath();
             MainAPP.getPrimaryStage().setTitle(medi.getName());
       Media med =new Media(new File(m).toURI().toString());
       
       if(player!=null){
           player.stop();
       }
        player=new MediaPlayer(med);
 
       
 
        view.setMediaPlayer(player);
        player.setAutoPlay(true);
        
        refresh();
        
     
 }
      @FXML  private  void pause () {
          
          Status status = player.getStatus();
        if (status == Status.UNKNOWN  || status == Status.HALTED)
        {
           
           return;
        }
 
          if ( status == Status.PAUSED
             || status == Status.READY
             || status == Status.STOPPED)
          {
             
             player.play();
             } else {
               player.pause();
             }
           updateValues();
           
       }
  @FXML private void full (ActionEvent event){
      if (MainAPP.getPrimaryStage().isFullScreen()==false)
      {   
     MainAPP.getPrimaryStage().setFullScreen(true);
     full.setText("Exit fullscreen");
     
  }
      else {
               MainAPP.getPrimaryStage().setFullScreen(false);
     full.setText("fullscreen");
      }
  }
  

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        
        
        
        
  
        

                          
        
        


        
   
     
   
      
      
   
        root.setOnMouseClicked((MouseEvent event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                if (  MainAPP.getPrimaryStage().isFullScreen()==false)
                {
                      MainAPP.getPrimaryStage().setFullScreen(true);
                    full.setText("Exit fullscreen");
                    
                }
                else {
                      MainAPP.getPrimaryStage().setFullScreen(false);
                    full.setText("fullscreen");
                }
                
            }});
           root.widthProperty().addListener((Observable observable) -> {
               view.setFitWidth(root.getWidth());
         });
        root.heightProperty().addListener((Observable observable) -> {
            view.setFitHeight(root.getHeight());
         });
        
    }    


    public void updateValues() {
  
  if (playTime != null && slider != null && volumeSlider != null && duration != null) {
        Platform.runLater(() -> {
            Duration currentTime = player.getCurrentTime();
            playTime.setText(formatTime(currentTime, duration));
            slider.setDisable(duration.isUnknown());
            
        if (!slider.isDisabled() 
            && duration.greaterThan(Duration.ZERO) 
            && !slider.isValueChanging()) {
              slider.setValue(currentTime.divide(duration).toMillis()
                  * 100.0);
        }
            if (!volumeSlider.isValueChanging()) {
                volumeSlider.setValue((int) Math.round(player.getVolume() * 100));
            }
        });
    }
}
    
    
    
    
    private static String formatTime(Duration elapsed, Duration duration) {
   int intElapsed = (int)Math.floor(elapsed.toSeconds());
   int elapsedHours = intElapsed / (60 * 60);
   if (elapsedHours > 0) {
       intElapsed -= elapsedHours * 60 * 60;
   }
   int elapsedMinutes = intElapsed / 60;
   int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 
                           - elapsedMinutes * 60;
 
   if (duration.greaterThan(Duration.ZERO)) {
      int intDuration = (int)Math.floor(duration.toSeconds());
      int durationHours = intDuration / (60 * 60);
      if (durationHours > 0) {
         intDuration -= durationHours * 60 * 60;
      }
      int durationMinutes = intDuration / 60;
      int durationSeconds = intDuration - durationHours * 60 * 60 - 
          durationMinutes * 60;
      if (durationHours > 0) {
         return String.format("%d:%02d:%02d/%d:%02d:%02d", 
            elapsedHours, elapsedMinutes, elapsedSeconds,
            durationHours, durationMinutes, durationSeconds);
      } else {
          return String.format("%02d:%02d/%02d:%02d",
            elapsedMinutes, elapsedSeconds,durationMinutes, 
                durationSeconds);
      }
      } else {
          if (elapsedHours > 0) {
             return String.format("%d:%02d:%02d", elapsedHours, 
                    elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",elapsedMinutes, 
                    elapsedSeconds);
            }
        }
    }
    
    
    
    
    
    
    public void refresh(){
        
        root.getChildren().clear();
         root.getChildren().add(view);
        
        
      player.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
          updateValues();
      });
         
      
         volumeSlider.valueProperty().addListener((Observable ov) -> {
             if (volumeSlider.isValueChanging()) {
               
                 player.setVolume(volumeSlider.getValue() / 100.0);
                
             }
      });
         
          slider.setOnMouseClicked((MouseEvent mouseEvent) -> {
              player.seek(duration.multiply(slider.getValue() / 100.0));
        });
         
        
       

         
         
         player.setOnPlaying(() -> {
       
                 play.setText("Pause");
                 
             
      });
 
        player.setOnPaused(() -> {
          
            play.setText("Play");
      });
 
 
        player.setOnReady(() -> {
            duration = player.getMedia().getDuration();
            updateValues();
        });
 
      
        
        player.setOnEndOfMedia(() -> {
            if(shuffle.isSelected()){
                forward();
            }
            else{
                
                forward();
                    player.stop();
                    play.setText("play");
               
                
            }
        });
         
    }
    
    
    
    
    
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Videos.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

   
    public void setPersonFilePath(File f) {
        Preferences prefs = Preferences.userNodeForPackage(Videos.class);
        if (f != null) {
            prefs.put("filePath", f.getPath());

           
        } else {
            prefs.remove("filePath");

          
        }
    }
    
   
    public void loadPersonDataFromFile(File f) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

        
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(f);

            item.clear();
            item.addAll(wrapper.getPersons());
            i=0;
           
 data medi;
     
     if(shuffle.isSelected()){
         Random rn = new Random();
 i = rn.nextInt( item.size() + 1) ;
         
}
     medi=item.get(i);
      String m= medi.getPath();
       
       Media med =new Media(new File(m).toURI().toString());
      MainAPP.getPrimaryStage().setTitle(medi.getName());
         if(player!=null){
           player.stop();
       }
        player=new MediaPlayer(med);
       
        view.setMediaPlayer(player);
        player.setAutoPlay(true);
       refresh();
         setPersonFilePath(f);

        } catch (Exception e) { // catches ANY exception
        	Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Could not load data");
        	alert.setContentText("Could not load data from file:\n" + file.getPath());
        	
        	alert.showAndWait();
        }
    }

   
    public void savePersonDataToFile(File l) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(item);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, l);

            // Save the file path to the registry.
            setPersonFilePath(l);
        } catch (Exception e) { // catches ANY exception
        	Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Could not save data");
        	alert.setContentText("Could not save data to file:\n" + l.getPath());
        	
        	alert.showAndWait();
        }
    }
    
  
    
    Videos MainAPP;
    void setMainApp(Videos vidoes) {
        
           this.MainAPP = vidoes;
        
        
    }
}
