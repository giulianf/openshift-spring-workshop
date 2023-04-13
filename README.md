# openshift-spring-workshop

Hands on Openshift event for a spring boot application. Deploy Micro-Services with logging connection (Kibana) and
monitoring connection (Grafana).

## Getting started

#### docker engine

build the app
issue with Mac

```
export DOCKER_BUILDKIT=0
export COMPOSE_DOCKER_CLI_BUILD=0
```

```
docker build -t finauxa/openshift-spring-workshop:0.0.1 .  
docker push finauxa/openshift-spring-workshop:0.0.1  
```
