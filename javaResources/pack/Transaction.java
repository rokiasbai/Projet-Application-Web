package pack;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
//	attention a modifier
	@ManyToOne
    Utilisateur utilisateur;
	
	@ManyToOne
	Paris paris;
	
    float montant;
    float cote;
    int numResultat;
    Date date;


    public Transaction() {
    	
    }
    
    
    public float getCote() {
		return cote;
	}


	public void setCote(float cote) {
		this.cote = cote;
	}


	public int getNumResultat() {
		return numResultat;
	}


	public void setNumResultat(int numResultat) {
		this.numResultat = numResultat;
	}


	public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paris getParis() {
        return this.paris;
    }

    public void setParis(Paris leParis) {
        this.paris = leParis;
    }

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur user) {
        this.utilisateur= user;
    }

    public float getMontant() {
        return this.montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}