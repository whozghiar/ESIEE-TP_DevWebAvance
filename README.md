# TP Garage Automobile

## Description

Ce projet est une application web de gestion de garage automobile. Il permet aux utilisateurs de gérer les véhicules, les rendez-vous, les techniciens et les clients.

## Technologies Utilisées

- **Frontend**: Le frontend de l'application est construit avec Angular, un framework JavaScript pour la création d'applications web.

- **Backend**: Le backend de l'application est construit avec Java et Spring Boot. Le backend fournit des API REST pour le frontend. Hibernate est utilisé pour la persistance des données.

- **Base de Données**: La base de données utilisée est MariaDB contenue dans un conteneur Docker.

- ** Keycloak**: Keycloak est utilisé pour la gestion des utilisateurs et des rôles. En fonction du rôle de l'utilisateur, certaines fonctionnalités de l'application sont accessibles ou non.

## Descriptif des fonctionnalités
  
_Page d'accueil_
![client_workflow_1.PNG](readme_src/client_workflow_1.PNG)
- ### Utilisateur Authentifié comme Administrateur
  
  - **Gestion des Clients**: L'administrateur peut modifier et supprimer des clients. 
    - Il peut également consulter la liste des clients.
![admin_workflow_7.PNG](readme_src/admin_workflow_7.PNG)
  - **Gestion des Techniciens**: L'administrateur modifier et supprimer des techniciens. 
    - Il peut également consulter la liste des techniciens.
![admin_workflow_6.PNG](readme_src/admin_workflow_6.PNG)
  - **Gestion des Véhicules**: L'administrateur modifier et supprimer des véhicules. 
    - Il peut également consulter la liste des véhicules.
![admin_workflow_4.PNG](readme_src/admin_workflow_4.PNG)
    
  **Gestion des Rendez-vous**: L'administrateur modifier et supprimer des rendez-vous.
   
_Modification du technicien attitré au RDV_
![admin_workflow_2.PNG](readme_src/admin_workflow_2.PNG) 
    
_On obtient le résultat suivant_
![admin_workflow_3.PNG](readme_src/admin_workflow_3.PNG)
   
  - Il peut également consulter la liste des rendez-vous.
![admin_workflow_1.PNG](readme_src/admin_workflow_1.PNG)
  
- ### Utilisateur Authentifié comme Client
  - **Gestion des Clients**: Le client peut consulter ses informations personnelles.

![client_workflow_2.PNG](readme_src/client_workflow_2.PNG)

  - **Gestion des Véhicules**: L'utilisateur peut consulter, à partir de son espace personnel, la liste de ses véhicules et ajouter un nouveau véhicule.

_Modification d'un véhicule_
![client_workflow_4.PNG](readme_src/client_workflow_4.PNG)
    
_Ajout d'un véhicule personnel_
![client_workflow_3.PNG](readme_src/client_workflow_3.PNG)
  - **Gestion des Rendez-vous**: L'utilisateur peut consulter la liste de ses rendez-vous et prendre un nouveau rendez-vous depuis un calendrier.

_Le calendrier permet de choisir une date et un créneau horaire pour le rendez-vous_
![client_workflow_8.PNG](readme_src/client_workflow_8.PNG)
_Le rendez-vous à la date rend la case rouge pour indiquer qu'elle est prise_
![client_workflow_7.PNG](readme_src/client_workflow_7.PNG)
_Le rendez-vous est ajouté à la liste des rendez-vous du client dans son espace personnel_
![client_workflow_9.PNG](readme_src/client_workflow_9.PNG)
- ### Utilisateur authentifié comme Technicien

  - **Gestion des Rendez-vous**: Le technicien peut consulter la liste de ses rendez-vous et les modifier.
![technicien_workflow_1.PNG](readme_src/technicien_workflow_1.PNG)

## Identifiants

### Keycloak 
- **Administrateur**: 
  - **Username**: admin
  - **Password**: admin
  
- **Client**: 
  - **Username**: client
  - **Password**: client
  
- **Technicien**: 
  - **Username**: technicien
  - **Password**: technicien
  
_Egalement un export de la configuration Keycloak est disponible dans le dossier Configuration <span style="color:green">projet/config</span>_

## Base de Données
- **Username**: root
- **Password**: Password123!

_Egalement un export de la BDD est disponible dans le dossier Configuration <span style="color:green">projet/config</span>_


