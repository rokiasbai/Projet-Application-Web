package pack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.ejb.Singleton;
import javax.persistence.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Singleton
@Path("/")
public class Facade {
	
	@PersistenceContext
	private EntityManager em;
	
	float importanceMise= 0.001f;

	
	///////////////////////////////////////////////
	/////////////// Utilisateurs //////////////////
	///////////////////////////////////////////////
	
	//Ajout d'un nouvel utilisateur
	@POST
	@Path("/addutilisateur")
    @Consumes({ "application/json" })
	public void addUtilisateur(Utilisateur u) {
		em.persist(u);
	}
	
	//Ajout d'un montant
	@POST
	@Path("/ajoutersolde")
    @Consumes({ "application/json" })
	public void ajouterSolde(int idUtilisateur, float solde) {
		Utilisateur u = em.find(Utilisateur.class, idUtilisateur);
		u.setSolde(u.getSolde()+solde);
	}
	
	//Liste des utilisateurs
	@GET
	@Path("/getutilisateurs")
    @Produces({ "application/json" })
	public Collection<Utilisateur> listUtilisateurs() {
		return em.createQuery("from Utilisateur", Utilisateur.class).getResultList();
	}
	
	//Obtention d'un utilisateur selon son pseudo
	@GET
	@Path("/getutilisateur/{pseudo}")
	@Produces({ "application/json" })
	public Utilisateur getUtilisateur (@PathParam("pseudo") String pseudo) {
		return em.createQuery("from Utilisateur where pseudo='" + pseudo + "'", Utilisateur.class).getSingleResult();
	}
	
	//Obtention des paris de l'utilisateurs
	@GET
	@Path("/getparisutilisateur/{pseudo}")
	@Produces({ "application/json" })
	public Collection<Paris> getParisUtilisateur (@PathParam("pseudo") String pseudo) {
		Collection<Transaction> transactions = getUtilisateur(pseudo).getEnsembleDesTransactions();
		String idTransactions = "(";
		for (Iterator iterator = transactions.iterator(); iterator.hasNext();) {
			Transaction transaction = (Transaction) iterator.next();
			if (!iterator.hasNext()) {
				idTransactions += String.valueOf(transaction.getId());
			} else {				
				idTransactions += (String.valueOf(transaction.getId()) + ", ");
			}
		}
		idTransactions += ")";
		System.out.println(idTransactions);
		
		return em.createQuery("from Paris where id in " + idTransactions, Paris.class).getResultList();
	}
	
	
	///////////////////////////////////////////////
	/////////////////// Matchs ////////////////////
	///////////////////////////////////////////////
	@POST
	@Path("/ajouterMatch")
	@Consumes({ "application/json" })
	public void ajouterMatch(Match m) {
		em.persist(m);
	}
	
	@POST
	@Path("/lierMatchParis")
	@Consumes({ "application/json" })
	public void lierMatchParis(int id1,int id2) {
		Match e1 = em.find(Match.class,id1);
		Paris e2 = em.find(Paris.class, id2);
		e1.setParis(e2);
	}
	
	//Match lie a un paris
	@GET
	@Path("/getmatch/{idParis}")
	@Produces({ "application/json" })
	public Match getMatch (@PathParam("idParis") int idParis) {
		Paris p = em.find(Paris.class, idParis);
		return p.getMatch();
	}
	
	
	///////////////////////////////////////////////
	/////////////// compte bancaire ///////////////
	///////////////////////////////////////////////
	@POST
	@Path("/ajouterCompteBancaire")
    @Consumes({ "application/json" })
	public void ajouterCompteBancaire(CompteBancaire newCompte) {
		em.persist(newCompte);
	}
	
	@POST
	@Path("/lierCompteUtilisateur")
    @Consumes({ "application/json" })
	public void lierCompteUtilisateur(int cid,int uid) {
		CompteBancaire c = em.find(CompteBancaire.class,cid);
		Utilisateur u = em.find(Utilisateur.class, uid);
		c.setProprietaire(u);
		u.setCompte(c);
	}
	
	///////////////////////////////////////////////
	//////////////////// Cotes ////////////////////
	///////////////////////////////////////////////
	@POST
	@Path("/ajouterCote")
    @Consumes({ "application/json" })
	public void ajouterCote(Cote c) {
		em.persist(c);
	}

	@POST
	@Path("/lierCoteParis/")
    @Consumes({ "application/json" })
	public void lierCoteParis(int cid,int pid) {
		Cote c = em.find(Cote.class,cid);
		Paris p = em.find(Paris.class, pid);
		c.setParis(p);
		p.getCotes().add(c);
	}
	
	//permet de supprimer les cotes d'un paris quand celui-ci est terminé pour ne pas surcharger la BDD
	@POST
	@Path("/supprimerCoteBDD")
    @Consumes({ "application/json" })
	public void supprimerCoteBDD(Paris p) {
		Collection<Cote> lesCotes = p.getCotes();
		for(Cote cote:lesCotes) {
			em.remove(cote);
		}
		p.getCotes().clear();
	}
	
	
	///////////////////////////////////////////////
	///////////////////// Lieu ////////////////////
	///////////////////////////////////////////////
	@POST
	@Path("/ajouterLieu")
    @Consumes({ "application/json" })
	public void ajouterLieu(Lieu l) {
		em.persist(l);
	}
	
	@POST
	@Path("/lierLieuStade")
    @Consumes({ "application/json" })
	public void lierLieuStade(int lid,int sid) {
		Lieu l = em.find(Lieu.class,lid);
		Stade s = em.find(Stade.class, sid);
		l.setStade(s);
	}
	
	///////////////////////////////////////////////
	///////////////////// Paris ///////////////////
	///////////////////////////////////////////////
	@POST
	@Path("/ajouterParis")
	@Consumes({ "application/json" })
	public void ajouterParis(Paris p) {
		em.persist(p);
		Set<Cote> cotes = new HashSet<Cote>();
		for (Iterator iterator = p.getCotes().iterator(); iterator.hasNext();) {
			Cote cote = (Cote) iterator.next();
			cote.setParis(p);
			em.persist(cote);
			cotes.add(cote);
		}
	}
	
	//ajout d'un paris classique
	@POST
	@Path("/ajouterParisClassique")
	@Consumes({"application/json"})
	public void ajouterParisClassique(Paris p) {
		em.persist(p);
		for (Iterator iterator = p.getCotes().iterator(); iterator.hasNext();) {
			Cote cote = (Cote) iterator.next();
			cote.setParis(p);
			em.persist(cote);
		}
		em.persist(p.getMatch().getStade().getLieu());
		em.persist(p.getMatch().getStade());
		em.persist(p.getMatch());
	}
	
	//Liste de tout les paris
	public Collection<Paris> listerParis() {
		return em.createQuery("from Paris").getResultList();
	}
	
	//Renvoie d'un paris selon son id
	@GET
	@Path("/getparis/{id}")
	@Produces("application/json")
	public Paris getParis(@PathParam("id") int id) {
		return em.find(Paris.class, id);
	}
	
	//liste des paris ouvert
	@GET
	@Path("/listerParisOuvert")
	@Produces("application/json")
	public Collection<Paris> listerParisOuvert() {
		Collection<Paris> paris = listerParis();
		Collection<Paris> parisOuvert = new ArrayList<Paris>();
		for (Iterator iterator = paris.iterator(); iterator.hasNext();) {
			Paris pari = (Paris) iterator.next();
			if (pari.getParisOuvert() && pari.getEnCours()) {
				parisOuvert.add(pari);
			}
		}
		return parisOuvert;
	}
	
	//liste des autres paris
	@GET
	@Path("/listerParis")
	@Produces("application/json")
	public Collection<Paris> listerParisNonOuvert() {
		Collection<Paris> paris = listerParis();
		Collection<Paris> parisNonOuvert = new ArrayList<Paris>();
		for (Iterator iterator = paris.iterator(); iterator.hasNext();) {
			Paris pari = (Paris) iterator.next();
			if (!pari.getParisOuvert()) {
				parisNonOuvert.add(pari);
			}
		}
		return parisNonOuvert;
	}
	
	//Fin d'un paris, suppression des cotes du paris de la base de données
	@GET
	@Path("/finParis/{idParis}/{resultat}")
	@Consumes({ "application/json" })
	public void finParis(@PathParam("idParis") int idParis, @PathParam("resultat") int resultat) {
		Paris paris = em.find(Paris.class, idParis);
		supprimerCoteBDD(paris);
		paris.setEnCours(false);
		for (Iterator iterator = paris.getTransaction().iterator(); iterator.hasNext();) {
			Transaction t = (Transaction) iterator.next();
			if (resultat == t.getNumResultat()) { 
				float mise = t.getMontant();
				//Cote objetCote = paris.getCotes().get(resultat);
				ajouterSolde(t.getUtilisateur().getId(),mise*t.getCote());
			}
		}
	}
	
	///////////////////////////////////////////////
	//////////////////// Stade ////////////////////
	///////////////////////////////////////////////
	@POST
	@Path("/ajouterStade")
	@Consumes({ "application/json" })
	public void ajouterStade(Stade s) {
		em.persist(s);
	}

	@POST
	@Path("/lierStadeMatch")
	@Consumes({ "application/json" })
	public void lierStadeMatch(int id1,int id2) {
		Stade e1 = em.find(Stade.class,id1);
		Match e2 = em.find(Match.class, id2);
		e1.setMatch(e2);
	}
	
	///////////////////////////////////////////////
	///////////////// Transaction /////////////////
	///////////////////////////////////////////////
	@GET
	@Path("/ajouterTransaction/{uid}/{pid}/{mise}/{resultat}")
	@Produces({ "application/json" })
	public void ajouterTransaction(@PathParam("uid") int uid,@PathParam("pid") int pid,
			@PathParam("mise") float mise, @PathParam("resultat") int resultat) {
		
		Utilisateur u = em.find(Utilisateur.class,uid);
		ajouterSolde(u.getId(),-mise);
		if(u.getSolde()<0) {
			ajouterSolde(u.getId(),mise);
			System.out.println("transaction impossible vous êtes ruiné(e)");
		}
		else {
			Paris p = em.find(Paris.class, pid);
			Transaction t = new Transaction();
			t.setMontant(mise);
			t.setUtilisateur(u);
			t.setParis(p);
			//Cote ObjetCote = p.getCotes().get(resultat);
			//t.setCote(ObjetCote.getCote());
			//devient
			
			//on met a jour la mise total
			p.setMiseTotal(p.getMiseTotal()+mise);
			float miseTotal = p.getMiseTotal();
			//modification des classes cotes
			for (Iterator iterator = p.getCotes().iterator(); iterator.hasNext();) {
				Cote cote = (Cote) iterator.next();
				if (cote.getResultat()==resultat) {
					//on récupère la cote
					float laCote = cote.getCote();
					t.setCote(laCote);
					t.setNumResultat(resultat);
					//on modifie la mise
					cote.setMise(cote.getMise()+mise);
				}
				//on modifie la cote
				//plus il y a de monde qui on parié la même chose plus la cote diminue
				cote.setCote(cote.getCote()+(-(cote.getMise()/miseTotal)+(1/2*miseTotal))*cote.getCote()*importanceMise);
				
			}
			//lier paris transaction
			p.getTransaction().add(t);
			em.persist(t);
			
		}
	}

}
