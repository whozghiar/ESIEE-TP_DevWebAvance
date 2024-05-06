# TP Garage Automobile

## Description

Ce projet est une application web de gestion de garage automobile. Il permet aux utilisateurs de gérer les véhicules, les rendez-vous, les techniciens et les clients.

## Technologies Utilisées

- **Frontend**: Le frontend de l'application est construit avec Angular, un framework JavaScript pour la création d'applications web.

- **Backend**: Le backend de l'application est construit avec Java et Spring Boot. Le backend fournit des API REST pour le frontend. Hibernate est utilisé pour la persistance des données.

- **Base de Données**: La base de données utilisée est MariaDB contenue dans un conteneur Docker.

- ** Keycloak**: Keycloak est utilisé pour la gestion des utilisateurs et des rôles. En fonction du rôle de l'utilisateur, certaines fonctionnalités de l'application sont accessibles ou non.

## Descriptif des fonctionnalités

- ### Utilisateur Authentifié comme Administrateur
  
  - **Gestion des Clients**: L'administrateur peut modifier et supprimer des clients. Il peut également consulter la liste des clients.
  - **Gestion des Techniciens**: L'administrateur modifier et supprimer des techniciens. Il peut également consulter la liste des techniciens.
  - **Gestion des Véhicules**: L'administrateur modifier et supprimer des véhicules. Il peut également consulter la liste des véhicules.
  - **Gestion des Rendez-vous**: L'administrateur modifier et supprimer des rendez-vous. Il peut également consulter la liste des rendez-vous.
  
- ### Utilisateur Authentifié comme Client

  - **Gestion des Clients**: Le client peut consulter ses informations personnelles.
  - **Gestion des Véhicules**: L'utilisateur peut consulter, à partir de son espace personnel, la liste de ses véhicules et ajouter un nouveau véhicule.
  - **Gestion des Rendez-vous**: L'utilisateur peut consulter la liste de ses rendez-vous et prendre un nouveau rendez-vous depuis un calendrier.

- ### Utilisateur authentifié comme Technicien

  - **Gestion des Rendez-vous**: Le technicien peut consulter la liste de ses rendez-vous et les modifier.

- ## Identifiants

## Keycloak 
- **Administrateur**: 
  - **Username**: admin
  - **Password**: admin
  
- **Client**: 
  - **Username**: client
  - **Password**: client
  
- **Technicien**: 
  - **Username**: technicien
  - **Password**: technicien
  
## Base de Données
- **Username**: root
- **Password**: Password123!

