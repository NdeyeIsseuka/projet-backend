import React, {useContext, useState, useEffect } from 'react';
import api from './Api';
import { TicketContext } from './TicketContext';

function Agent() {
    const [agents, setAgents] = useState([]);
    const [error, setError] = useState(null);
    // const [currentTickets, setCurrentTickets] = useState({});
    const { currentTickets, setCurrentTickets } = useContext(TicketContext);
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
                                    setCurrentTickets(prevState => ({ ...prevState, [`${localisationId}-${serviceId}`]: response.data }));
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
                        setCurrentTickets(prevState => ({ ...prevState, [`${localisationId}-${serviceId}`]: response.data }));
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
                        setCurrentTickets(prevState => ({ ...prevState, [`${localisationId}-${serviceId}`]: response.data }));
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
        <> 
        <h1>Liste des Agents</h1>
        <div className="carousel carousel-vertical rounded-box h-96">
        <div></div>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <div></div>
            {agents.map(agent => (
                <div key={agent.id}>
                    <h3>{agent.nameAgent}</h3>
                    <p>Localisation : {agent.localisation.nomLocalisation}</p>
                    <p>Service : {agent.serviceApp.nameService}</p>
                    <h2>Numéro en cours de traitement : {currentTickets[`${agent.localisation.id}-${agent.serviceApp.id}`]}</h2>
                    <button onClick={() => handlePrevious(agent.localisation.id, agent.serviceApp.id)} className="btn btn-dash btn-error ">Précédent</button>
                    <button onClick={() => handleNext(agent.localisation.id, agent.serviceApp.id)} className="btn btn-dash btn-error">Suivant</button>
                    <div class="border-t-4 border-red-950 my-4"></div>

                </div>
                
            ))}
        </div>

        </>
        
    );
}

export default Agent;

