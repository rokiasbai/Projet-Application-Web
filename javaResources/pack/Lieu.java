package pack;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lieu {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    String adresse;
    
    @OneToOne
    @JsonIgnore
    Stade stade;

    public Lieu () {
    	
    }
    
    public Stade getStade() {
        return this.stade;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }



}