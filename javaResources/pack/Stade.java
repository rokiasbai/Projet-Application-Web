package pack;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Stade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    String nom;
    
    @OneToOne(mappedBy="stade", fetch = FetchType.EAGER)
    Lieu lieu;

    @OneToOne
    @JsonIgnore
    Match match;
    
    public Stade(){
    	
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Lieu getLieu() {
        return this.lieu;
    }

    public void setLieu(Lieu unLieu) {
        this.lieu = unLieu;
    }
    
    public Match getMatch() {
    	return this.match;
    }
    
    public void setMatch(Match unMatch) {
    	this.match = unMatch;
    }

}