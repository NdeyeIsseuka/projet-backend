import React from 'react';

function Ticket({ ticket }) {
    if (!ticket) {
        return <p>Veuillez générer un ticket.</p>;
    }

    return (
        <div>
            <h3>Votre Ticket</h3>
            <p>Numéro de ticket: {ticket.numeroTicket}</p>
            <p>Position dans la file: {ticket.positionInFile} ème</p>
            <p>Nombre de personnes devant: {ticket.nbrPersonneAvant}</p>
            <p>Numéro en cours de traitement: {ticket.currentNumber}</p>
        </div>
    );
}

export default Ticket;
