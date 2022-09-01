import React from "react";
import CreationCompte from "../pages/CreationCompte";
import Connexion from "../pages/Connexion";
import ReactDOM from 'react-dom';
import { useNavigate } from "react-router-dom";
import { invokePost } from "../liaisonBackend";

const Accueil = () => {
  const connexion = () => {
    ReactDOM.render(<Connexion />, document.getElementById("Worker"));
  }
  const creationCompte = () => {
    ReactDOM.render(<CreationCompte />, document.getElementById("Worker"));
  }
  const navigate = useNavigate();
  function handleClick() {
    navigate("/seconnecter")
  }
  function handleClick2() {
    navigate("/creercompte")
  }

  return (
    <div>

      <div id="Worker"></div>


      <body>
        <nav>
          <div class="navbar">
            <button onClick={() => connexion() & handleClick()}>Connexion</button>
            <button onClick={() => creationCompte() & handleClick2()}>S'inscrire</button>
          </div>
        </nav>


        <header>
          <h1> MDRT,</h1>
          <h4>LE MEILLEUR SITE DE PARIS SPORTIFS.</h4>

        </header>



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

export default Accueil;