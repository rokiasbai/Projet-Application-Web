import React from "react";
import { invokePost } from "../liaisonBackend";
import { useState } from "react";
import { sha256 } from "js-sha256";
import ReactDOM from "react-dom"

const CreationCompte = () => {
        const [pseudo, setPseudo] = useState("");
        const [nom, setNom] = useState("");
        const [prenom, setPrenom] = useState("");
        const [adresse, setAdresse] = useState("");
        const [mdp, setMotdepasse] = useState("");

        const handleSubmit = (event) => {
                event.preventDefault();

                if (pseudo == "" || nom == "" || prenom == "" || adresse == "" || mdp == "") {
                        ReactDOM.render(<p>Vous devez compléter tout les champs !</p>, document.getElementById("messageErreur"));
                } else {
                        let utilisateur = {};
                        utilisateur.pseudo = pseudo;
                        utilisateur.nom = nom;
                        utilisateur.prenom = prenom;
                        utilisateur.adresse = adresse;
                        utilisateur.solde = 100;

                        //cryptage du mot de passe
                        utilisateur.mdp = sha256(mdp);
                        invokePost("addutilisateur", utilisateur, "utilisateur ajouté", "pb with addutilisateur")
                                .then(() => window.location.replace("/"));
                }

        }

        return (
                <div class="back">
                        <form onSubmit={handleSubmit}>

                                <h1> Enregistrez vous ! </h1>
                                <div class="groupe">
                                        <label>Pseudo</label>
                                        <input type="text" value={pseudo}
                                                onChange={(e) => setPseudo(e.target.value)} /><br />
                                        <i class="fas fa-user"></i>
                                </div>
                                <div class="groupe">
                                        <label>Nom</label>
                                        <input type="text" value={nom}
                                                onChange={(e) => setNom(e.target.value)} /><br />
                                        <i class="fas fa-user"></i>
                                </div>
                                <div class="groupe">
                                        <label>Prénom</label>
                                        <input type="text" value={prenom}
                                                onChange={(e) => setPrenom(e.target.value)} /><br />
                                        <i class="fas fa-user"></i>
                                </div>
                                <div class="groupe">
                                        <label>Adresse</label>
                                        <input type="text" value={adresse}
                                                onChange={(e) => setAdresse(e.target.value)} /><br />
                                        <i class="fas fa-user"></i>
                                </div>
                                <div class="groupe">
                                        <label>Mot de passe</label>
                                        <input type="password" value={mdp}
                                                onChange={(e) => setMotdepasse(e.target.value)} /><br />
                                        <i class="fas fa-user"></i>
                                </div>
                                <div id="messageErreur"></div>
                                <div class="pied-formulaire" align="left">
                                        <input type="submit" value="Créer mon compte" />
                                </div>
                        </form>
                </div>
        );
};

export default CreationCompte;