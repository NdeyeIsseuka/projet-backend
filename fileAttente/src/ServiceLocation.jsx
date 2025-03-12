import React, { useState, useEffect } from 'react';
import axios from 'axios';
import api from './Api';

function ServiceLocation({ onTicketGenerated }) {
    const [services, setServices] = useState([]);
    const [localisations, setLocalisations] = useState([]);
    const [selectedService, setSelectedService] = useState('');
    const [selectedLocalisation, setSelectedLocalisation] = useState('');

    useEffect(() => {
        // Charger les services depuis l'API
        api.get('/services') 
            .then(response => setServices(response.data))
            .catch(error => console.error("Erreur lors du chargement des services:", error));

        // Charger les localisations depuis l'API
        api.get('/localisations') 
            .then(response => setLocalisations(response.data))
            .catch(error => console.error("Erreur lors du chargement des localisations:", error));
    }, []);

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            // Envoyer une requête POST à l'API pour générer un ticket
            const response = await api.post('/ticket', { 
                serviceId: selectedService,
                localisationId: selectedLocalisation
            });

            // Passer les données du ticket au composant parent
            onTicketGenerated(response.data);
        } catch (error) {
            console.error("Erreur lors de la génération du ticket:", error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="service">Service:</label>
                <select id="service" value={selectedService} onChange={e => setSelectedService(e.target.value)}>
                    <option value="">Sélectionner un service</option>
                    {services.map(service => (
                        <option key={service.id} value={service.id}>{service.nameService}</option>
                    ))}
                </select>
            </div>
            <div>
                <label htmlFor="localisation">Localisation:</label>
                <select id="localisation" value={selectedLocalisation} onChange={e => setSelectedLocalisation(e.target.value)}>
                    <option value="">Sélectionner une localisation</option>
                    {localisations.map(localisation => (
                        <option key={localisation.id} value={localisation.id}>{localisation.nomLocalisation}</option>
                    ))}
                </select>
            </div>
            <button type="submit">Générer un ticket</button>
        </form>
    );
}

export default ServiceLocation;
