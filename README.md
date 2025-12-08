# LogiFlow 🚛 📦

**Intelligent Supply Chain & Logistics Orchestration Platform**

LogiFlow is an enterprise-grade distributed system designed to provide real-time visibility, automated routing, and predictive analytics for global supply chains.

## 🚀 Tech Stack

- **Core**: Java 21, Spring Boot 3.2
- **Reactive**: Spring WebFlux (Project Reactor)
- **Messaging**: RabbitMQ
- **Data**: PostgreSQL, Redis (Geo), Elasticsearch
- **Infrastructure**: Docker, Kubernetes

## 📂 Modules

- **`ingestion-service`**: High-throughput IoT telemetry ingestion (Netty/WebFlux).
- **`tracking-service`**: Real-time geospatial tracking and geofencing (Redis Geo).
- **`shipment-service`**: Core domain logic for shipments, orders, and carriers.

## 🛠️ Getting Started

### Prerequisites
- JDK 21
- Docker Desktop

### Run Infrastructure
```bash
docker-compose up -d
```

### Build
```bash
mvn clean install
```
