# streaming-stats #

## Préambule ##

Le projet Streaming Stats. 

La version actuelle est la `1.0.0`.

---

## Démarrage rapide ##
Deux possibilités s'offrent à vous:

1. Utiliser le JAR *standalone* proposé;
2. cloner ce *repository*, construire l'application et la lancer.

### Solution n°1: JAR *standalone* ###
#### Pré-requis ####

* Vous devez disposer de Docker Installer.
* Vous devez disposer d'un JRE en version 8 ou supérieure.
* Vous devez disposer d'un maven en version 3 ou supérieure.

#### Etape n°1: télécharger l'application ####
Puller la derniere version de l'image Postgres depuis Docker pour éviter de l'installer
  ```
  docker pull postgres
  ```
#### Etape n°2: démmarrer postgres ####
Lancer le server postgres
```
docker run --name server-postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres  
```
#### Etape n°3: télécharger l'application stats-1.0.0.jar####

Cf Piece jointe du mail

#### Etape n°2: lancer l'application ####
Placez-vous dans le dossier où se trouve le JAR téléchargé (`stats-1.0.0.jar`) que vous pouvez lancer avec la commande suivante:
  ```
  java -jar stats-1.0.0.jar
  ```

### Solution n°2: construction de l'application et lancement via Docker Compose ###
#### Pré-requis ####
Vous devez disposer:

* de Docker installer sur votre machine
* d'un JDK en version 8 ou supérieure;
* de Maven en version 3 ou supérieure;
* de Git.

#### Etape n°1: cloner l'application ####
Clonez l'application à l'aide de la commande suivante:

	git clone https://github.com/msellekAhmed/streaming-stats.git

#### Etape n°2: Modifier l'url du serveur postgres pour pointer sur votre adresse ip ####
Modifier le application properties pour mettre l'adresse ip de votre machine au niveau de l'URL de postgres:

	cd streaming-stats/

	vim src/main/resources/application.properties
	
#### Etape n°3: Builder l'image Docker du Server pour etre sure de lancer a chanque fois la derniére version ####
Lancez la construction de l'application via:

	mvn clean install
	
Puis, Builder l'image Docker de notre application
 
 	docker build --tag=stats:latest .
 

#### Etape n°3: lancer l'application Avec Docker compose ####
Ici on va utiliser Docker compose afin de lancer multiple container docker, notamment un pour l'application stats et un autre por la postgres. La configuration necessaire a ceci est definis dans les fichier Dockerfile et docker-compose.yml du projet  :

	docker-compose up


## Notes techniques ##
### Technologies utilisées ###

* Maven;
* java;
* Docker
* Postgres
* Spring-boot 2.4
* Spring Data JPA
* Spring REST

### Architecture ###
L'application est découpée en couche selon la correspondance suivante:

Couche                          | Description
--------------------------------|---------------------------------------------------
packages entity et repository   | la couche de persistence et d'accées a la donnée.
package service                 | contient la logique metier de l'application en terme d'aggreagation et calcul 
package controller              | contient la couche d'exposition du web service REST.


### Tests ###
un ensemble de tests unitaires ont été réalisés pour assurer le bon fonctionnement et la logique de l'application
