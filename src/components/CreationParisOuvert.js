import React from "react";
import { invokePost } from "../liaisonBackend";
import { useState } from "react";

const CreationParisOuvert = () => {

    const [nbCotes, setNbCotes] = useState(2);
    const [titre, setTitre] = useState("");
    const [description, setDescription] = useState("");

    const handleSubmit = (event) => {
        event.preventDefault();
        let cotes = [];
        for (let i = 1; i <= nbCotes; i++) {
            let cote = {};
            cote.intitule = window["intitule" + i];
            cote.cote = window["val" + i];
            cote.mise = 0;
            cote.resultat = i;
            cotes.push(cote);
        }
        let parisouvert = {};
        parisouvert.titre = titre;
        parisouvert.description = description;
        parisouvert.parisOuvert = true;
        parisouvert.enCours = true;
        parisouvert.miseTotal = 0;
        parisouvert.cotes = cotes;
        invokePost("ajouterParis", parisouvert, "paris ouvert", "pb with ajouterParis")
        //.then(() => window.location.replace("/moncompte"));
    }

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
                    <label>Cote associée</label>

                    <button type="button" onClick={(e) => handleClickMoins()}>-</button>
                    <button type="button" onClick={(e) => handleClickPlus()}>+</button>

                    {ajoutInputCotes()}

                    <i class="fas fa-user"></i>
                </div>

                <div class="pied-formulaire" align="left">
                    <input type="submit" value="Créer Paris Ouvert" />
                </div>
            </form>
        </div>
    );
};

export default CreationParisOuvert;