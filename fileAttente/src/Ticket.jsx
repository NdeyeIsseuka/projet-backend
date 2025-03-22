// import React , {useContext} from 'react';
// import { TicketContext } from './TicketContext';

// function Ticket({ ticket }) {
//     if (!ticket) {
//         return <p className="">Veuillez générer un ticket.</p>;
//     }
//     const { currentTickets, agents } = useContext(TicketContext); // Récupérer agents du contexte


//     return (
//         <div >
//             <h3>Votre Ticket</h3>
//             <p>Numéro de ticket: {ticket.numeroTicket}</p>
//             <p>Position dans la file: {ticket.numeroTicket - (ticket.currentNumber - 1)} ème</p>
//             <p>Nombre de personnes devant: {ticket.nbrPersonneAvant }</p>
//             {/* <p>Numéro en cours de traitement: {ticket.currentNumber -1}</p> */}
//             {agents.map(agent => (
//                     <div key={agent.id}>
//                        <h2>Numéro en cours : {currentTickets[`${agent.localisation.id}-${agent.serviceApp.id}`] || "Aucun"}</h2>
//                     </div>
//                 ))}
//             {/* <h2>Numéro en cours de traitement : {currentTickets[`${agent.localisation.id}-${agent.serviceApp.id}`]}</h2> */}

//         </div>
//     );
// }

// export default Ticket;

// import React, { useContext } from 'react';
// import { TicketContext } from './TicketContext';

// function Ticket({ ticket }) {
//     if (!ticket) {
//         return <p className="">Veuillez générer un ticket.</p>;
//     }

//     const { currentTickets, agents } = useContext(TicketContext);

//     return (
//         <div>
//             <h3>Votre Ticket</h3>
//             <p>Numéro de ticket: {ticket.numeroTicket}</p>
//             <p>Position dans la file: {ticket.numeroTicket - (ticket.currentNumber - 1)} ème</p>
//             <p>Nombre de personnes devant: {ticket.nbrPersonneAvant}</p>

//             {/*  Loop through agents to display their current ticket number */}
//             {agents.map(agent => (
//                 <div key={agent.id}>
//                     <h2>
//                          Numéro en cours (Agent {agent.nameAgent}):{" "}
//                          {currentTickets[`${agent.localisation.id}-${agent.serviceApp.id}`] || "Aucun"}
//                     </h2>
//                 </div>
//             ))}
//         </div>
//     );
// }

// export default Ticket;

// import React, { useContext } from 'react';
// import { TicketContext } from './TicketContext';

// function Ticket({ ticket, selectedLocalisationId, selectedServiceId }) {
//     if (!ticket) {
//         return <p className="">Veuillez générer un ticket.</p>;
//     }

//     const { currentTickets, agents } = useContext(TicketContext);

//     // Find the specific agent for the selected service and localisation
//     const selectedAgent = agents.find(
//         agent =>
//             agent.localisation.id === selectedLocalisationId &&
//             agent.serviceApp.id === selectedServiceId
//     );

//     return (
//         <div>
//             <h3>Votre Ticket</h3>
//             <p>Numéro de ticket: {ticket.numeroTicket}</p>
//             <p>Position dans la file: {ticket.numeroTicket - (ticket.currentNumber - 1)} ème</p>
//             <p>Nombre de personnes devant: {ticket.nbrPersonneAvant}</p>

//             {selectedAgent ? (
//                 <div>
//                     <h2>
//                         Numéro en cours ():
//                         {currentTickets[`${selectedLocalisationId}-${selectedServiceId}`] || "Aucun"}
//                     </h2>
//                 </div>
//             ) : (
//                 <p>Aucun agent trouvé pour ce service et cette localisation.</p>
//             )}
//         </div>
//     );
// }

// export default Ticket;

// import React, { useContext } from 'react';
// import { TicketContext } from './TicketContext';

// function Ticket({ ticket}) {
//     if (!ticket) {
//         return <p className="">Veuillez générer un ticket.</p>;
//     }

//     const { currentTickets, agents } = useContext(TicketContext);
//     const selectedLocalisationId = ticket?.localisation?.id;
//     const selectedServiceId = ticket?.serviceApp?.id;

//     // Trouver l'agent correspondant à la localisation et au service
//     const selectedAgent = agents.find(
//         agent =>
//             agent.localisation.id === selectedLocalisationId &&
//             agent.serviceApp.id === selectedServiceId
//     );

//     console.log("Agents:", agents);
//     console.log("Selected Localisation ID:", selectedLocalisationId);
//     console.log("Selected Service ID:", selectedServiceId);

//     return (
//         <div>
//             <h3>Votre Ticket</h3>
//             <p>Numéro de ticket: {ticket.numeroTicket}</p>
//             <p>Position dans la file: {ticket.numeroTicket - (ticket.currentNumber - 1)} ème</p>
//             <p>Nombre de personnes devant: {ticket.nbrPersonneAvant}</p>

//             {selectedAgent ? (
//                 <div>
//                     <h2>
//                         Numéro en cours (Agent {selectedAgent.nameAgent}):{" "}
//                         {currentTickets[`${selectedLocalisationId}-${selectedServiceId}`] || "Aucun"}
//                     </h2>
                 
//                 </div>
//             ) : (
//                 <p>Aucun agent trouvé pour ce service et cette localisation.</p>
//             )}
            
//         </div>
//     );
// }

// export default Ticket;

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

