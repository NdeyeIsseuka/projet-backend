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

    const handleNext = (localisationId, serviceId) => {
        api.post('/agents/next', { localisationId, serviceId })
            .then(response => {
                const updatedAgents = agents.map(agent => {
                    if (agent.localisationId === localisationId && agent.serviceId === serviceId) {
                        return { ...agent, currentTicketNumber: response.data };
                    }
                    return agent;
                });
                setAgents(updatedAgents);
            })
            .catch(error => {
                console.error("Erreur lors du passage au ticket suivant:", error);
            });
    };

    const handlePrevious = (localisationId, serviceId) => {
        api.post('/agents/previous', { localisationId, serviceId })
            .then(response => {
                const updatedAgents = agents.map(agent => {
                    if (agent.localisationId === localisationId && agent.serviceId === serviceId) {
                        return { ...agent, currentTicketNumber: response.data };
                    }
                    return agent;
                });
                setAgents(updatedAgents);
            })
            .catch(error => {
                console.error("Erreur lors du retour au ticket précédent:", error);
            });
    };

    return (
        <div>
            <h2>Liste des Agents</h2>
            {agents.map(agent => (
                <div key={agent.id}>
                    <h3>{agent.nameAgent}</h3>
                    {/* <p>Localisation ID : {agent.localisation.id}</p>
                    <p>Service ID : {agent.serviceApp.id}</p> */}
                    <p>Localisation : {agent.localisation.nomLocalisation}</p>
                    <p>Service : {agent.serviceApp.nameService}</p>
                    <p>Numéro en cours de traitement : {agent.currentTicketNumber}</p>
                    </div>
            ))}
        </div>
    );
}

export default Admin;
