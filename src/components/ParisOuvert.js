import React, { useEffect } from "react";
import { invokeGet, invokePost } from "../liaisonBackend";
import { useState } from "react";
import { getCookie } from "../utils";
import ReactDOM from 'react-dom';
import { useNavigate } from "react-router-dom";

function ParisOuvert(props) {
    var pseudo = getCookie("pseudo");

    const [mise, setMise] = useState(0.0);
    const [resultat, setResultat] = useState(0);
    const navigate = useNavigate();

    function affichageAdmin() {
        if (pseudo == "admin") {
            return (<><input type="submit" value="METTRE FIN AU PARIS" /></>);
        } else {
            return (<><p>Montant de la mise : </p>
                <input type="text" name="mise" onChange={(e) => setMise(e.target.value)} />
                <input type="submit" value="PARIER" />
                <div id="messageErreur"></div></>);
        }
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        let parameters = {};
        if (pseudo == "admin") {
            parameters.pid = props.paris.id;
            parameters.resultat = resultat;
            invokeGet("finParis", parameters, "pb with finParis");
        }
        else {
            parameters.uid = getCookie("uid");
            parameters.pid = props.paris.id;
            parameters.mise = mise;
            parameters.resultat = resultat;
            if (mise != 0) {
                invokeGet("ajouterTransaction", parameters, "pb with ajouterTransaction");
                navigate("/moncompte");
            } else {
                ReactDOM.render(<p>Mise à zero impossible</p>, document.getElementById("messageErreur"));
            }
        }
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <h1>{props.paris.titre}</h1>
                <p>{props.paris.description}</p>
                <p>Votre choix :</p>
                {
                    props.paris.cotes.map((c) => (
                        <>
                            <label>{c.intitule} </label>
                            <input name={props.paris.titre} value={c.cote} type="radio" onClick={() => setResultat(c.resultat)} /><label> Cote : {c.cote}</label><br />
                        </>
                    ))
                }
                {affichageAdmin()}
            </form>
        </div>

    );
};

export default ParisOuvert;