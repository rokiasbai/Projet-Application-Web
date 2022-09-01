package pack;

import javax.persistence.*;

@Entity
public class CompteBancaire {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    int numeroCarte;
    
    @OneToOne
    Utilisateur proprietaire;

    public CompteBancaire () {
    	
    }
    
    public Utilisateur getProprietaire() {
		return this.proprietaire;
	}

	public void setProprietaire(Utilisateur proprietaire) {
		this.proprietaire = proprietaire;
	}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroCarte() {
        return this.numeroCarte;
    }

    public void setNumeroCarte(int numeroCarte) {
        this.numeroCarte = numeroCarte;
    }


}