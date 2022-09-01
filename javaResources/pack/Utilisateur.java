package pack;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Utilisateur {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    String nom;
    String prenom;
    String adresse;
    String pseudo;
    String mdp;
//    @ManyToMany
//    Collection<Paris> listeParis = new ArrayList<Paris>();
    
    @OneToOne(mappedBy="proprietaire", fetch = FetchType.EAGER)
    CompteBancaire compte;
    
    float solde;
    
    @OneToMany(mappedBy="utilisateur", fetch = FetchType.EAGER)
    Collection <Transaction> ensembleDesTransactions = new ArrayList<Transaction>();

    public Utilisateur() {
    	
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

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

//    public Collection <Paris> getListeParis() {
//		return this.listeParis;
//	}
//
//    public void setListeParis(Collection <Paris> listeParis) {
//		this.listeParis = listeParis;
//	}

    public CompteBancaire getCompte() {
        return this.compte;
    }

	public void setCompte(CompteBancaire compte) {
		this.compte = compte;
	}
	
	public Collection<Transaction> getEnsembleDesTransactions() {
		return ensembleDesTransactions;
	}

	public void setEnsembleDesTransactions(Collection<Transaction> ensembleDesTransactions) {
		this.ensembleDesTransactions = ensembleDesTransactions;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

}