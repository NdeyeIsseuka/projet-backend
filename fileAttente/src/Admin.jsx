import React, { useState, useEffect } from 'react';
import axios from 'axios';
import api from './Api';

function Admin() {
    const [agents, setAgents] = useState([]);

    useEffect(() => {
        api.get('/agents')
            .then(response => {
                setAgents(response.data);
            })
            .catch(error => {
                console.error("Erreur lors du chargement des agents:", error);
            });
    }, []);


    return (
        <>
                    <h1>Liste des Agents</h1>

        <div className="carousel carousel-vertical rounded-box h-96">
            {agents.map(agent => (
                <div key={agent.id}>
                    <h3>{agent.nameAgent}</h3>
                    {/* <p>Localisation ID : {agent.localisation.id}</p>
                    <p>Service ID : {agent.serviceApp.id}</p> */}
                    <p>Localisation : {agent.localisation.nomLocalisation}</p>
                    <p>Service : {agent.serviceApp.nameService}</p>
                    <p>Numéro en cours de traitement : {agent.currentTicketNumber}</p>
                    <div class="border-t-4 border-red-950 my-4"></div>

                    </div>
            ))}
        </div>
        </>
        
    );
}

export default Admin;
