package videos;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 * 
 * @author Marco Jakob
 */
@XmlRootElement(name = "DataList")
public class PersonListWrapper {

    private List<data> Data;

    @XmlElement(name = "Data")
    public List<data> getPersons() {
        return Data;
    }

    public void setPersons(List<data> data) {
        this.Data = data;
    }
}