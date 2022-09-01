package pack;

//import java.util.ArrayList;
//import java.util.Collection;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Paris {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
	
	@OneToOne(mappedBy="paris", fetch = FetchType.EAGER)
	@JsonIgnore
    Match match;
    
    
    @OneToMany(mappedBy="paris", fetch = FetchType.EAGER)
    @JsonIgnore
    Set<Transaction> transaction;
    
    //pour un paris ouvert
    String titre;
    String description;
    //////////////////////
    
    Boolean parisOuvert; //pour savoir si c'est un paris classique ou un paris mis par un user (paris ouvert)
    Boolean enCours; //indique si le paris est encore disponible ou non
    
    //differentes cotes de chaque cote
    @OneToMany(mappedBy="parisC",fetch = FetchType.EAGER)
    Set<Cote> cotes;
    
    float miseTotal;
//    float cote1;
//    float cote2;
//    //pour les paris utilisateurs
//    float cote3;
//    float cote4;
//    float cote5;
//    
//    float mise1;
//    float mise2;
//    float mise3;
//    float mise4;
//    float mise5;
    
    
    public Paris() {
    	
    }
    
    public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getMiseTotal() {
		return miseTotal;
	}

	public void setMiseTotal(float miseTotal) {
		this.miseTotal = miseTotal;
	}

	public Boolean getParisOuvert() {
		return parisOuvert;
	}

	public void setParisOuvert(Boolean parisOuvert) {
		this.parisOuvert = parisOuvert;
	}

	public Boolean getEnCours() {
		return enCours;
	}

	public void setEnCours(Boolean enCours) {
		this.enCours = enCours;
	}

	public Set<Cote> getCotes() {
		return cotes;
	}

	public void setCotes(Set<Cote> cotes) {
		this.cotes = cotes;
	}
	

	public int getId() {
        return this.id;
    }

//    public float getCote1() {
//		return cote1;
//	}
//
//	public void setCote1(float cote1) {
//		this.cote1 = cote1;
//	}
//
//	public float getCote2() {
//		return cote2;
//	}
//
//	public void setCote2(float cote2) {
//		this.cote2 = cote2;
//	}
//
//	public float getCote3() {
//		return cote3;
//	}
//
//	public void setCote3(float cote3) {
//		this.cote3 = cote3;
//	}
//
//	public float getCote4() {
//		return cote4;
//	}
//
//	public void setCote4(float cote4) {
//		this.cote4 = cote4;
//	}
//
//	public float getCote5() {
//		return cote5;
//	}
//
//	public void setCote5(float cote5) {
//		this.cote5 = cote5;
//	}
//
//	public float getMise1() {
//		return mise1;
//	}
//
//	public void setMise1(float mise1) {
//		this.mise1 = mise1;
//	}
//
//	public float getMise2() {
//		return mise2;
//	}
//
//	public void setMise2(float mise2) {
//		this.mise2 = mise2;
//	}
//
//	public float getMise3() {
//		return mise3;
//	}
//
//	public void setMise3(float mise3) {
//		this.mise3 = mise3;
//	}
//
//	public float getMise4() {
//		return mise4;
//	}
//
//	public void setMise4(float mise4) {
//		this.mise4 = mise4;
//	}
//
//	public float getMise5() {
//		return mise5;
//	}
//
//	public void setMise5(float mise5) {
//		this.mise5 = mise5;
//	}

	public void setId(int id) {
        this.id = id;
    }

    public Match getMatch() {
        return this.match;
    }

    public void setMatch(Match unMatch) {
        this.match = unMatch;
    }



	public Set<Transaction> getTransaction() {
		return transaction;
	}
	
	public void setTransaction(Set<Transaction> transaction) {
		this.transaction = transaction;
	}


}