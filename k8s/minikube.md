# Minikube

## Customize config

````shell
minikube config set cpus 4
minikube config set memory 8192
````

## Basics

```shell
# start Minikube
minikube start

# stop Minikube
minikube stop
```

## Ingress

https://kubernetes.io/docs/tasks/access-application-cluster/ingress-minikube/

To enable the NGINX Ingress controller on Minikube:

```shell
minikube addons enable ingress
```

And to be able to access to cluster : 

```shell
 minikube tunnel
```
