import React, { useContext, useState } from 'react';
import ServiceLocation from './ServiceLocation';
import Ticket from './Ticket';
import { TicketContext } from './TicketContext';

function Home() {
    const [ticket, setTicket] = useState(null);
    const { currentTickets, setCurrentTickets } = useContext(TicketContext);
   
    const handleTicketGenerated = (ticketData) => {
        setTicket(ticketData);
    };

    return (
        <>
        <h1 className="text-center text-4xl text-white ">File d'attente</h1> 

            <div className="Home card card-border py-2 px-4  bg-red-950 p-10 w-96 flex  items-center">
            <ServiceLocation onTicketGenerated={handleTicketGenerated} />
            <Ticket ticket={ticket} />
        </div>
        </>
    );
}

export default Home;
