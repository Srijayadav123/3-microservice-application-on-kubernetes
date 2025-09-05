# 3-microservice-application-on-kubernetes
# Microservices Demo (Java + Spring Boot + Kubernetes)

## Services
1. **User Service** 🧑 → Manages users
2. **Order Service** 📦 → Manages orders, fetches user details from User Service
3. **Payment Service** 💳 → Manages payments, fetches order details from Order Service

## Flow
Client → Payment Service → Order Service → User Service

## Tech Stack
- Java 17
- Spring Boot 3
- Docker
- Kubernetes

## Deployment
1. Build JARs: `mvn clean package -DskipTests`
2. Build Docker images and push
3. Apply Kubernetes manifests:
   ```bash
   kubectl apply -f user-service/k8s-deployment.yaml
   kubectl apply -f order-service/k8s-deployment.yaml
   kubectl apply -f payment-service/k8s-deployment.yaml
Access: http://<EXTERNAL-IP>/payments