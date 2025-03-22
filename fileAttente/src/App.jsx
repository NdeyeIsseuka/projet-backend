import React, { useState } from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom"
import './App.css';
import Home from './Home';
import Agent from './Agent';
import Admin from './Admin';
import { TicketProvider } from './TicketContext';

function App() {
   
    return (
        <TicketProvider>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/agent" element={<Agent />} />
                    <Route path="/admin" element={<Admin />} />

                </Routes>
            </BrowserRouter>
        </TicketProvider>
        // <BrowserRouter>
        //     <Routes>
        //         <Route path="/" element={<Home />} />
        //         <Route path="/agent" element={<Agent />} />
        //         <Route path="/admin" element={<Admin />} />

        //     </Routes>
        // </BrowserRouter>
    );
}
export default App;
