import React from "react";
import { useNavigate } from "react-router-dom";
import ParisClassique from "../components/ParisClassique";
import { getCookie } from "../utils";
import { useState } from "react";
import { invokeGet } from "../liaisonBackend";


var init = true;

const ParisClassiques = () => {
    var pseudo = getCookie("pseudo");

    const navigate = useNavigate();

    const [listeParis, setList] = useState([]);

    if (init) {
        init = false;
        let parameters = {}
        invokeGet("listerParisClassique", parameters, "pb with listerParisOuvert")
            .then(function (data) {
                if (data != null) {
                    setList(data);
                }
            });
    }

    function handleClick20() {
        navigate("/creationParisClassique");
    }

    function affichageAdmin() {
        if (pseudo == "admin") {
            return (<section class="temoignage" id="temoignage">
                <div class="contenu">
                    <div class="box">
                        <div class="text">
                            <h1>Formulaire pour ajouter un paris classique :</h1>
                            <p></p>

                            <button onClick={(e) => handleClick20()}>Ajouter paris classique </button>
                        </div>
                    </div>
                </div>
            </section>);
        }
    }

    return (
        <div>
            <header>
                <h1> MDRT,</h1>
                <h4>LE MEILLEUR SITE DE PARIS SPORTIFS.</h4>
            </header>
            <section class="temoignage" id="temoignage">
                <div class="contenu">
                    <div class="box">
                        <div class="text">
                            <h1>Liste des paris classiques</h1>
                        </div>
                    </div>
                </div>
            </section>

            {
                listeParis.map((paris, i) => (
                    <ParisClassique key={i} paris={paris} />
                ))
            }

            {affichageAdmin()}

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

        </div>


    );
};

export default ParisClassiques;