package videos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;




public class data {

    private final StringProperty Path;
    private final StringProperty Name;

 
    public data() {
        this(null,null);
    }

    
    public data(String path,String name) 
 
    {
        this.Path = new SimpleStringProperty(path);
        this.Name = new SimpleStringProperty(name);

      
    }

  
 
    

    public String getPath() {
        return Path.get();
    }

    public void setPath(String Path) {
        this.Path.set(Path);
    }

    public StringProperty PathProperty() {
        return Path;
    }

    public String getName() {
        return Name.get();
    }

    public void setName(String Name) {
        this.Name.set(Name);
    }

    public StringProperty NameProperty() {
        return Name;
    }

}