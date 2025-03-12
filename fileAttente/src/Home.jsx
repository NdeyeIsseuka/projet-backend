import React, { useState } from 'react';
import ServiceLocation from './ServiceLocation';
import Ticket from './Ticket';

function Home() {
    const [ticket, setTicket] = useState(null);

    const handleTicketGenerated = (ticketData) => {
        setTicket(ticketData);
    };

    return (
        <div className="Home">
            <h1>File d'attente</h1>
            <ServiceLocation onTicketGenerated={handleTicketGenerated} />
            <Ticket ticket={ticket} />
        </div>
    );
}

export default Home;
