import React, { useState, useEffect } from 'react';
import axios from 'axios';
import api from './Api';
import Agent from './Agent';
import Ticket from './Ticket';

function AgentTicket() {
    const [agents, setAgents] = useState([]);
    const [error, setError] = useState(null);
    const [currentTickets, setCurrentTickets] = useState({});
    const [selectedTicket, setSelectedTicket] = useState(null); // État pour le ticket sélectionné

    useEffect(() => {
        api.get('/agents')
            .then(response => {
                if (response.data) {
                    setAgents(response.data);
                    response.data.forEach(agent => {
                        const localisationId = agent.localisation.id;
                        const serviceId = agent.serviceApp.id;

                        if (localisationId && serviceId) {
                            api.get(`/agents/current-ticket/${localisationId}/${serviceId}`)
                                .then(response => {
                                    const ticket = response.data;
                                    setCurrentTickets(prevState => ({ ...prevState, [`${localisationId}-${serviceId}`]: ticket }));

                                    // Mettez à jour le ticket sélectionné par défaut
                                    setSelectedTicket(ticket);
                                })
                                .catch(error => {
                                    console.error("Erreur lors du chargement du numéro en cours:", error);
                                });
                        } else {
                            console.warn("Agent, localisation ou service manquant pour l'agent:", agent);
                        }
                    });
                } else {
                    setError("Aucune donnée reçue de l'API.");
                }
            })
            .catch(error => {
                setError("Erreur lors du chargement des agents: " + error.message);
            });
    }, []);

    const handleNext = (localisationId, serviceId) => {
        api.post('/agents/next', { localisationId, serviceId })
            .then(response => {
                api.get(`/agents/current-ticket/${localisationId}/${serviceId}`)
                    .then(response => {
                        const ticket = response.data;
                        setCurrentTickets(prevState => ({ ...prevState, [`${localisationId}-${serviceId}`]: ticket }));

                        // Mettez à jour le ticket sélectionné
                        setSelectedTicket(ticket);
                    })
                    .catch(error => {
                        console.error("Erreur lors du passage au ticket suivant:", error);
                    });
            })
            .catch(error => {
                setError("Erreur lors du passage au ticket suivant: " + error.message);
            });
    };

    const handlePrevious = (localisationId, serviceId) => {
        api.post('/agents/previous', { localisationId, serviceId })
            .then(response => {
                api.get(`/agents/current-ticket/${localisationId}/${serviceId}`)
                    .then(response => {
                        const ticket = response.data;
                        setCurrentTickets(prevState => ({ ...prevState, [`${localisationId}-${serviceId}`]: ticket }));

                        // Mettez à jour le ticket sélectionné
                        setSelectedTicket(ticket);
                    })
                    .catch(error => {
                        console.error("Erreur lors du retour au ticket précédent:", error);
                    });
            })
            .catch(error => {
                setError("Erreur lors du retour au ticket précédent: " + error.message);
            });
    };

    return (
        <div>
            <h2>Gestion des Agents et Tickets</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            
            {/* Affiche la liste des agents avec leurs actions */}
            <Agent 
                agents={agents} 
                currentTickets={currentTickets} 
                handleNext={handleNext} 
                handlePrevious={handlePrevious} 
            />

            {/* Affiche le ticket actuellement sélectionné */}
            <Ticket ticket={selectedTicket} />
        </div>
    );
}

export default AgentTicket;
