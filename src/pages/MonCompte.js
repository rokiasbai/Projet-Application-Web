import React, { useState } from "react";
import { invokeGet } from "../liaisonBackend";
import { getCookie } from "../utils";
import { useNavigate } from "react-router-dom";

var init = false;

const MonCompte = () => {
  const [listeParis, setListeParis] = useState([]);
  const [utilisateur, setUtilisateur] = useState({});
  const [ensembleDesTransactions, SetEnsembleDesTransactions] = useState([]);
  const [paris, setParis] = useState({});

  const navigate = useNavigate();

  function handleClick3() {
    navigate("/parisclassiques");
  }

  function handleClick4() {
    navigate("/parisouverts");
  }

  function handleClickCreationParisOuvert() {
    navigate("/creationParisOuvert");
  }

  function handleClickAccueil() {
    navigate("/");
  }

  function affichageTransaction() {
    let list = [];
    for (let i = 0; i < ensembleDesTransactions.length; i++) {
      const t = ensembleDesTransactions[i];

      console.log(t)
      list.push(<li>
        Paris : {t.paris.titre} Montant misé : {t.montant} Cote : {t.cote}
      </li>)

    }

    return list;
  }

  //récupération des données de l'utilisateur
  if (!init) {
    init = true;

    let getParameters = {};
    getParameters.pseudo = getCookie("pseudo");
    invokeGet("getutilisateur", getParameters, "pb with getutilisateur")
      .then(function (data) {
        setUtilisateur(data);
        SetEnsembleDesTransactions(data.ensembleDesTransactions);
        console.log(ensembleDesTransactions);
      });
    invokeGet("getparisutilisateur", getParameters, "pb with getparisutilisateur")
      .then(data => setListeParis(data));
  }

  return (
    <>

      <div class="box">
        <div class="text">
          <button onClick={(e) => handleClickAccueil()}>Accueil</button>
        </div>
      </div>

      <section class="temoignage" id="temoignage">
        <div class="contenu">
          <div class="box">
            <div class="text">
              <h1> Bienvenue sur votre compte !</h1>

              <h1>Solde actuel : </h1>
              <p>{utilisateur.solde}</p>

              <h1>Liste des transactions effectuées : </h1>
              <ul>
                {affichageTransaction()}
              </ul>
            </div>
          </div>
        </div>
      </section>
      <section class="temoignage" id="temoignage">
        <div class="contenu">
          <div class="box">
            <div class="text">
              <h1>Les Paris classiques</h1>
              <p></p>

              <button onClick={(e) => handleClick3()}>Paris classiques</button>
            </div>

          </div>
          <div class="box">
            <div class="text">
              <h1>Paris des utilisateurs</h1>
              <p></p>
              <button onClick={(e) => handleClick4()}>Paris des utilisateurs</button>
            </div>

          </div>

          <div class="box">
            <div class="text">
              <h1>Creation Paris Ouvert</h1>
              <button onClick={(e) => handleClickCreationParisOuvert()}>Créer un paris ouvert</button>
            </div>

          </div>

        </div>
        <footer>

          <h1>Nous connaitre </h1>
          <div class="services">
            <div class="service">
              <h3>Paiement en ligne</h3>
              <p>Faites nous confiance.</p>
            </div>

            <div class="service">
              <h3>Satisfait ou remboursé</h3>
              <p></p>
            </div>

          </div>

          <p id="contact">Contact : mdrt@gmail.com | &copy; 2022, MDRT.</p>
        </footer>
      </section>
    </>
  );
};

export default MonCompte;