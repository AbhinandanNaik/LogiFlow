import React, { useEffect, useState } from 'react';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import { Client } from '@stomp/stompjs';

const MapComponent = () => {
    const [shipments, setShipments] = useState([]);

    useEffect(() => {
        // Initial mock data
        setShipments([
            { id: 1, trackingNumber: 'TRK-123', lat: 40.7128, lng: -74.0060, status: 'IN_TRANSIT' },
            { id: 2, trackingNumber: 'TRK-456', lat: 34.0522, lng: -118.2437, status: 'DELIVERED' },
        ]);

        const client = new Client({
            brokerURL: 'ws://localhost:8080/ws',
            onConnect: () => {
                console.log('Connected to WebSocket');
                client.subscribe('/topic/tracking', (message) => {
                    const update = JSON.parse(message.body);
                    console.log('Received update:', update);

                    setShipments((prev) => {
                        const existing = prev.find(s => s.trackingNumber === update.deviceId); // Assuming deviceId maps to trackingNumber for demo
                        if (existing) {
                            return prev.map(s => s.trackingNumber === update.deviceId ? { ...s, lat: update.latitude, lng: update.longitude } : s);
                        } else {
                            return [...prev, { id: Date.now(), trackingNumber: update.deviceId, lat: update.latitude, lng: update.longitude, status: 'LIVE' }];
                        }
                    });
                });
            },
            onStompError: (frame) => {
                console.error('Broker reported error: ' + frame.headers['message']);
                console.error('Additional details: ' + frame.body);
            },
        });

        client.activate();

        return () => {
            client.deactivate();
        };
    }, []);

    return (
        <MapContainer center={[39.8283, -98.5795]} zoom={4} scrollWheelZoom={true} className="leaflet-container">
            <TileLayer
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            {shipments.map((shipment) => (
                <Marker key={shipment.id} position={[shipment.lat, shipment.lng]}>
                    <Popup>
                        <div className="p-2">
                            <h3 className="font-bold">{shipment.trackingNumber}</h3>
                            <p>Status: <span className="text-blue-600">{shipment.status}</span></p>
                        </div>
                    </Popup>
                </Marker>
            ))}
        </MapContainer>
    );
};

export default MapComponent;
