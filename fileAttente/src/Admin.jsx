// // Code for the Admin page
// import React, { useState, useEffect } from 'react';
// import api from './Api';
// import { TicketContext } from './TicketContext';

// function Admin() {
//     const [agents, setAgents] = useState([]);
//     const [error, setError] = useState(null);
//     const { currentTickets } = useContext(TicketContext);

//     useEffect(() => {
//         api.get('/agents')
//             .then(response => {
//                 if (response.data) {
//                     setAgents(response.data);
//                     response.data.forEach(agent => {
//                         const localisationId = agent.localisation.id;
//                         const serviceId = agent.serviceApp.id;

//                         if (localisationId && serviceId) {
//                             api.get(`/agents/current-ticket/${localisationId}/${serviceId}`)
//                                 .then(response => {
//                                     setCurrentTickets(prevState => ({ ...prevState, [`${localisationId}-${serviceId}`]: response.data }));
//                                 })
//                                 .catch(error => {
//                                     console.error("Erreur lors du chargement du numéro en cours:", error);
//                                 });
//                         } else {
//                             console.warn("Agent, localisation ou service manquant pour l'agent:", agent);
//                         }
//                     });
//                 } else {
//                     setError("Aucune donnée reçue de l'API.");
//                 }
//             })
//             .catch(error => {
//                 setError("Erreur lors du chargement des agents: " + error.message);
//             });
//     }, []);


//     return (
//         <> 
//         <h1>Liste des Agents</h1>
//         <div className="carousel carousel-vertical rounded-box h-96">
//         <div></div>
//         {error && <p style={{ color: 'red' }}>{error}</p>}
//         <div></div>
//             {agents.map(agent => (
//                 <div key={agent.id}>
//                     <h3>{agent.nameAgent}</h3>
//                     <p>Localisation : {agent.localisation.nomLocalisation}</p>
//                     <p>Service : {agent.serviceApp.nameService}</p>
//                     <h2>Numéro en cours de traitement : {currentTickets[`${agent.localisation.id}-${agent.serviceApp.id}`]}</h2>
//                    <div class="border-t-4 border-red-950 my-4"></div>

//                 </div>
                
//             ))}
//         </div>

//         </>
        
//     );
// }

// export default Admin;

// Admin.js
import React, { useContext } from 'react';
import { TicketContext } from './TicketContext';

function Admin() {
    const { currentTickets, agents } = useContext(TicketContext); // Récupérer agents du contexte

    return (
        <> 
        <h1>Dashboard Admin</h1>
        <div></div>
            {/* <h2>Liste des Agents</h2> */}
            <div className="carousel carousel-vertical rounded-box h-96">
                <div></div>
                {agents.map(agent => (
                    <div key={agent.id}>
                        <h3>{agent.nameAgent}</h3>
                        <p>Localisation : {agent.localisation.nomLocalisation}</p>
                        <p>Service : {agent.serviceApp.nameService}</p>
                        <h2>Numéro en cours : {currentTickets[`${agent.localisation.id}-${agent.serviceApp.id}`] || "Aucun"}</h2>
                        <div className="border-t-4 border-red-950 my-4"></div>
                    </div>
                ))}
            </div>
        </>
    );
}

export default Admin;
