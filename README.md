# Energy-Management-System
=======


# Project Setup Guide

## Creating the network
First, we need to create our network and subnet it using this command:
sudo docker network create --driver bridge --subnet 172.30.0.0/16 --gateway 172.30.0.1 backendservices

## How to run the front-end app

Navigate to the SDProject-Frontend directory, and then run this command:
sudo docker-compose up -d --build
Then u will have the app deployed on docker, with the container named: sdproject-frontend_angular_1 and the IPv4Address: 172.30.0.10/16, and connected to the docker network named backendservices with the subnet: 172.30.0.0/16

## How to run the DeviceService app

Navigate to the DeviceService directory, and then run this commands:
sudo docker-compose up -d
sudo docker-compose up --build
Then u will have the app deployed on docker, with the container named: deviceservice_application-deviceservice_1 and the IPv4Address: 172.30.0.2/16, 
Now we have to connect the container to the docker network named backendservices with the subnet: 172.30.0.0/16 using this command:
sudo docker network connect backendservices

## How to run the UserService app

Navigate to the UserService directory, and then run this commands:
sudo docker-compose up -d
sudo docker-compose up --build
Then u will have the app deployed on docker, with the container named: userservice_application-userservice_1 and the IPv4Address: 172.30.0.3/16, 
Now we have to connect the container to the docker network named backendservices with the subnet: 172.30.0.0/16 using this command
sudo docker network connect backendservices

# Now, you can acces http://localhost:4200/login, where the app is running.

