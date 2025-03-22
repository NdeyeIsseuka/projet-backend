// import { createContext, useState } from 'react';

// const TicketContext = createContext();

// const TicketProvider = ({ children }) => {
//     const [currentTickets, setCurrentTickets] = useState({});

//     return (
//         <TicketContext.Provider value={{ currentTickets, setCurrentTickets }}>
//             {children}
//         </TicketContext.Provider>
//     );
// };

// export { TicketProvider, TicketContext };

import { createContext, useState, useEffect } from 'react';
import api from './Api';

const TicketContext = createContext();

const TicketProvider = ({ children }) => {
    const [currentTickets, setCurrentTickets] = useState({});
    const [agents, setAgents] = useState([]);

    // Chargement initial des agents et des tickets en cours 
    useEffect(() => {
        const loadData = async () => {
            try {
                const agentsResponse = await api.get('/agents');
                setAgents(agentsResponse.data);

                agentsResponse.data.forEach(async agent => {
                    const locId = agent.localisation.id;
                    const servId = agent.serviceApp.id;
                    
                    if (locId && servId) {
                        const ticketResponse = await api.get(`/agents/current-ticket/${locId}/${servId}`);
                        setCurrentTickets(prev => ({
                            ...prev,
                            [`${locId}-${servId}`]: ticketResponse.data
                        }));
                    }
                });
            } catch (error) {
                console.error("Erreur de chargement initial:", error);
            }
        };

        loadData();
    }, []);

    return (
        <TicketContext.Provider value={{ currentTickets, agents, setCurrentTickets }}>
            {children}
        </TicketContext.Provider>
    );
};

export { TicketProvider, TicketContext };

