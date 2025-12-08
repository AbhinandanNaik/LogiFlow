import React from 'react';
import MapComponent from './components/MapComponent';

function App() {
  return (
    <div className="h-screen w-screen flex flex-col">
      <header className="bg-slate-900 text-white p-4 shadow-lg z-10">
        <h1 className="text-2xl font-bold flex items-center gap-2">
          🚛 LogiFlow <span className="text-sm font-normal text-slate-400">Enterprise Logistics</span>
        </h1>
      </header>
      <main className="flex-1 relative">
        <MapComponent />
        <div className="absolute top-4 right-4 bg-white p-4 rounded-lg shadow-xl z-[1000] w-64">
          <h2 className="font-bold mb-2">Live Status</h2>
          <div className="flex justify-between items-center mb-1">
            <span>Active Shipments</span>
            <span className="bg-green-100 text-green-800 px-2 py-0.5 rounded text-sm">12</span>
          </div>
          <div className="flex justify-between items-center">
            <span>Delayed</span>
            <span className="bg-red-100 text-red-800 px-2 py-0.5 rounded text-sm">2</span>
          </div>
        </div>
      </main>
    </div>
  );
}

export default App;
