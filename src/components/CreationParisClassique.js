import React from "react";
import { invokePost } from "../liaisonBackend";
import { useState } from "react";

const CreationParisClassique = () => {

    const [nbCotes, setNbCotes] = useState(2);
    const [nomLieu, setNomLieu] = useState("");
    const [nomStade, setNomStade] = useState("");
    const [nomEquipe1, setNomEquipe1] = useState("");
    const [nomEquipe2, setNomEquipe2] = useState("");
    const [nomSport, setNomSport] = useState("");
    const [titre, setTitre] = useState("");
    const [description, setDescription] = useState("");

    function ajoutInputCotes() {
        let inputs = [];
        for (let i = 1; i <= nbCotes; i++) {
            let intituleCote = "intitule" + i;
            let valCote = "val" + i;
            inputs.push(<>
                <label>Descritpion resultat {i}</label>
                <input type="text" onChange={(e) => window[intituleCote] = e.target.value} /><br />
                <label>Valeur de la cote associée au resultat {i}</label>
                <input type="text" onChange={(e) => window[valCote] = e.target.value} /><br />
            </>);
        }
        return inputs;
    }

    function handleClickMoins() {
        if (nbCotes > 2) {
            setNbCotes(nbCotes - 1);
        }
    }

    function handleClickPlus() {
        setNbCotes(nbCotes + 1);
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        var lieu = {};
        lieu.adresse = nomLieu;

        var stade = {};
        stade.lieu = lieu;
        stade.nom = nomStade;

        var match = {};
        match.stade = stade;
        match.equipe1 = nomEquipe1;
        match.equipe2 = nomEquipe2;
        match.sport = nomSport;

        let cotes = [];
        for (let i = 1; i <= nbCotes; i++) {
            let cote = {};
            cote.intitule = window["intitule" + i];
            cote.cote = window["val" + i];
            cote.mise = 0;
            cote.resultat = i;
            cotes.push(cote);
        }
        var parisClassique = {};
        parisClassique.match = match;
        parisClassique.titre = titre;
        parisClassique.description = description;
        parisClassique.parisOuvert = false;
        parisClassique.enCours = true;
        parisClassique.miseTotal = 0;
        parisClassique.cotes = cotes;

        invokePost("ajouterParisClassique", parisClassique, "paris classique ajouté", "pb with ajouterParisClassique");
        //    .then(() => window.location.replace("/moncompte"));
    }


    return (
        <div class="back">
            <form onSubmit={handleSubmit}>
                <div class="groupe">
                    <label>Titre</label>
                    <input type="text" value={titre}
                        onChange={(e) => setTitre(e.target.value)} /><br />
                    <i class="fas fa-user"></i>
                </div>

                <div class="groupe">
                    <label>Descritpion</label>
                    <input type="text" value={description}
                        onChange={(e) => setDescription(e.target.value)} /><br />
                    <i class="fas fa-user"></i>
                </div>

                <div class="groupe">
                    <label>Lieu</label>
                    <input type="text" value={nomLieu}
                        onChange={(e) => setNomLieu(e.target.value)} /><br />
                    <i class="fas fa-user"></i>
                </div>

                <div class="groupe">
                    <label>Stade</label>
                    <input type="text" value={nomStade}
                        onChange={(e) => setNomStade(e.target.value)} /><br />
                    <i class="fas fa-user"></i>
                </div>

                <div class="groupe">
                    <label>Match</label>
                    <label>Equipe 1</label>
                    <input type="text" value={nomEquipe1}
                        onChange={(e) => setNomEquipe1(e.target.value)} /><br />
                    <i class="fas fa-user"></i>
                    <label>Equipe 2</label>
                    <input type="text" value={nomEquipe2}
                        onChange={(e) => setNomEquipe2(e.target.value)} /><br />
                    <i class="fas fa-user"></i>
                    <label>Sport</label>
                    <input type="text" value={nomSport}
                        onChange={(e) => setNomSport(e.target.value)} /><br />
                    <i class="fas fa-user"></i>
                </div>

                <div class="groupe">
                    <label>Cote associée</label>

                    <button type="button" onClick={(e) => handleClickMoins()}>-</button>
                    <button type="button" onClick={(e) => handleClickPlus()}>+</button>

                    {ajoutInputCotes()}

                    <i class="fas fa-user"></i>
                </div>


                <div class="pied-formulaire" align="left">
                    <input type="submit" value="Créer Paris Classique" />
                </div>
            </form>
        </div>
    );
};

export default CreationParisClassique;