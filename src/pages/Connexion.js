import React, { useEffect } from "react";
import { invokeGet } from "../liaisonBackend";
import { useState } from "react";
import { sha256 } from "js-sha256";
import { setCookie } from "../utils"
import ReactDOM from "react-dom";

const Connexion = () => {

    const [pseudo, setPseudo] = useState("");
    const [mdp, setMotdepasse] = useState("");
    const [mdpServeur, setMotdepasseServeur] = useState("");
    const [firstRender, setFirstRender] = useState(true);
    const [utilisateurId, setUtilisateurId] = useState(-1);

    useEffect(function () {
        if (!firstRender) {
            //verification du mot de passe
            if (sha256(mdp) == mdpServeur) {
                //connexion réussie, redirection sur la page Home du site et creation d'un cookie
                setCookie("pseudo", pseudo, 180);
                setCookie("uid", utilisateurId, 180);
                window.location.replace("/moncompte");
            }
        } else {
            setFirstRender(false);
        }
    }, [mdpServeur])

    const handleSubmit = (event) => {
        event.preventDefault();

        let getParameters = {};
        getParameters.pseudo = pseudo;
        invokeGet("getutilisateur", getParameters, "Pseudo ou mot de passe erroné")
        .then(function (data) {
            //affichage message erreur de connexion
            if (data == null) {
                ReactDOM.render(<p>Pseudo ou mot de passe erroné</p>, document.getElementById("messageErreur"));
            }
            setUtilisateurId(data.id);
            setMotdepasseServeur(data.mdp);
        });
    }

    return (
        <section class="back">
            <form onSubmit={handleSubmit}>
                <h1> Connectez vous ! </h1>
                <div class="groupe">
                    <label>Pseudo</label>
                    <input type="text" value={pseudo}
                        onChange={(e) => setPseudo(e.target.value)} /><br />
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
                    <input type="submit" value="Me connecter" />
                </div>

            </form>
        </section>

    );
};



export default Connexion;