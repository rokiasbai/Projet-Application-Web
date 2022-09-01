package pack;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cote {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	String intitule;
	float mise;
	float cote;
	int resultat;
	@ManyToOne
	@JsonIgnore
	Paris parisC;
	
	public Cote() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getResultat() {
		return resultat;
	}

	public void setResultat(int resultat) {
		this.resultat = resultat;
	}

	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public float getMise() {
		return mise;
	}
	public Paris getParis() {
		return parisC;
	}
	public void setParis(Paris paris) {
		this.parisC = paris;
	}
	public void setMise(float mise) {
		this.mise = mise;
	}
	public float getCote() {
		return cote;
	}
	public void setCote(float cote) {
		this.cote = cote;
	}
	
}
