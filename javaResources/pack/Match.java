package pack;

import javax.persistence.*;

@Entity
public class Match {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    
	String equipe1;
    String equipe2;
    String sport;
    
	@OneToOne(mappedBy="match", fetch = FetchType.EAGER)
    Stade stade;
	
	@OneToOne
	Paris paris;

    public Match () {
    	
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Stade getStade() {
        return this.stade;
    }

    public void setStade(Stade unStade) {
        this.stade = unStade;
    }

    public String getEquipe1() {
        return this.equipe1;
    }

    public void setEquipe1(String equipe1) {
        this.equipe1 = equipe1;
    }

    public String getEquipe2() {
        return this.equipe2;
    }

    public void setEquipe2(String equipe2) {
        this.equipe2 = equipe2;
    }

    public String getSport() {
        return this.sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
    
    public Paris getParis() {
    	return this.paris;
    }
    
    public void setParis(Paris paris) {
    	this.paris=paris;
    }


}