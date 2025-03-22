import React, { useContext } from 'react';
import { TicketContext } from './TicketContext';

function Ticket({ ticket }) {
    const { currentTickets, agents } = useContext(TicketContext);


    if (!ticket) {
        return <p>Veuillez générer un ticket.</p>;
    }

    const { localisationId, serviceId } = ticket;

     // Recherche de l'agent correspondant à la localisation et au service
     const selectedAgent = agents.find(
        agent =>
            agent.localisation.id === localisationId &&
            agent.serviceApp.id === serviceId
    );

    return (
        <div>
            <h3>Votre Ticket</h3>
            <p>Numéro de ticket: {ticket.numeroTicket}</p>
            <p>Position dans la file: {ticket.numeroTicket - currentTickets[`${localisationId}-${serviceId}`] +1 } ème</p>
            <p>Nombre de personnes devant: {ticket.numeroTicket - currentTickets[`${localisationId}-${serviceId}`]}</p>

            {selectedAgent ? (
                <div>
                    <h2>
                        Numéro en cours de traitement : 
                        {/* (Agent {selectedAgent.nameAgent}):{" "} */}

                        {currentTickets[`${localisationId}-${serviceId}`] || "Aucun"}
                    </h2>
                </div>
            ) : (
                <p>Aucun agent trouvé pour ce service et cette localisation.</p>
            )}
        </div>
    );
}

export default Ticket;

