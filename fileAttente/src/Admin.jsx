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
