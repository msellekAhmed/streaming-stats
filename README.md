# streaming-stats #

## Préambule ##

Le projet Streaming Stats, contenu dans ce *repository*, constitue ma réponse a la 2eme question du test technique que je me suis vu proposé.

La version actuelle est la `1.0.0`.

---

## Démarrage rapide ##
Deux possibilités s'offrent à vous:

1. Utiliser le JAR *standalone* proposé;
2. cloner ce *repository*, construire l'application et la lancer.

### Solution n°1: JAR *standalone* ###
#### Pré-requis ####
Vos devez disposer de Docker Installer.
Vous devez disposer d'un JRE en version 8 ou supérieure.
Vous devez disposer d'un maven en version 3 ou supérieure.
#### Etape n°1: télécharger l'application ####
Puller la derniere version de l'image Postgres depuis Docker

  docker pull postgres

#### Etape n°1: télécharger l'application ####
Cf Piece jointe du mail
#### Etape n°2: lancer l'application ####
Placez-vous dans le dossier où se trouve le JAR téléchargé (`lawnmower-core-1.0-jar-with-dependencies.jar`) que vous pouvez lancer avec la commande suivante:

	java -jar data-cli-1.0.0-jar-with-dependencies.jar <chemin vers le fichier de specification des mower>


### Solution n°2: construction de l'application ###
#### Pré-requis ####
Vous devez disposer:

* d'un JDK en version 8 ou supérieure;
* de Maven en version 3 ou supérieure;
* de Git.

#### Etape n°1: cloner l'application ####
Clonez l'application à l'aide de la commande suivante:

	https://github.com/msellekAhmed/Data_Splio.git

#### Etape n°2: construire l'application ####
Entrez dans le répertoire de l'application:

	cd Data_Splio/

Puis, lancez la construction de l'application via:

	mvn clean install

#### Etape n°3: lancer l'application ####
Une fois la construction terminée, rendez-vous dans le dossier `target` du module `lawn-mower-core`:

	cd data-cli/target/

Vous pouvez y trouver le JAR `lawn-mower-cli-1.0-SNAPSHOT-jar-with-dependencies.jar`que vous pouvez lancer avec la commande suivante:

	java -jar data-cli-1.0.0-jar-with-dependencies.jar <chemin vers le fichier de specification des mower>

---
## Guide d'utilisation ##
L'usage de l'application est le suivant:

	Usage: data-cli <file>

  		<file>
        	source file path

Un seul argument est attendu en entrée; ce dernier doit être un fichier existant. Cet usage vous est affiché pour rappel en cas d'erreur. Le libellé `lawn-mower-core`est bien évidemment à remplacer par le nom du JAR.

---
## Notes techniques ##
### Technologies utilisées ###

* Maven;
* Scala;
* [Scopt](https://github.com/scopt/scopt) pour la CLI.

### Architecture ###
L'application est découpée en modules selon la correspondance suivante:

Module            | Description
------------------|------
data-core         | la logique "pure" de l'application; Calcul des differentes moyennes.
data-parsing      | contient la logique de *parsing* des fichiers sources et le model et 
data-cli          | contient l'interface de ligne de commande et la main class permettant de faire usage de l'application.
