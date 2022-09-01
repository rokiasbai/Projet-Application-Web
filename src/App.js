import { React, useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom"; //dans node_modules
import MonCompte from "./pages/MonCompte";
import Accueil from "./pages/Accueil";
import Connexion from "./pages/Connexion";
import CreationCompte from "./pages/CreationCompte";
import ParisClassiques from "./pages/ParisClassiques";
import CreationParisClassique from "./components/CreationParisClassique";
import ParisOuverts from "./pages/ParisOuverts";
import CreationParisOuvert from "./components/CreationParisOuvert";


const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Accueil />} />
        <Route path="/moncompte" element={<MonCompte />} />
        <Route path="/seconnecter" element={<Connexion />} />
        <Route path="/creercompte" element={<CreationCompte />} />
        <Route path="/parisclassiques" element={<ParisClassiques />} />
        <Route path="/parisouverts" element={<ParisOuverts />} />
        <Route path="/creationParisOuvert" element={<CreationParisOuvert />} />
        <Route path="/creationParisClassique" element={<CreationParisClassique />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;