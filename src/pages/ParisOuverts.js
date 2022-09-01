import React from "react";
import ParisOuvert from "../components/ParisOuvert";
import { useState } from "react";
import { invokeGet } from "../liaisonBackend";

var init = true;

const ParisOuverts = () => {

    const [listeParis, setList] = useState([]);

    if (init) {
        init = false;
        let parameters = {}
        invokeGet("listerParisOuvert", parameters, "pb with listerParisOuvert")
            .then(data => setList(data));
    }

    return (
        <div>
            <body>

                <header>
                    <h1> MDRT,</h1>
                    <h4>LE MEILLEUR SITE DE PARIS SPORTIFS.</h4>
                </header>


                {
                    listeParis.map((paris, i) => (
                        <ParisOuvert key={i} paris={paris} />
                    ))
                }


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
            </body>
        </div>


    );
};

export default ParisOuverts;