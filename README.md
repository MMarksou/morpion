#Projet Morpion

*Fabien LAURET M1-DID informatique*

***

Voici le rendu final du petit projet du jeu de morpion. Celui-ci a la possibilité de :

1. Créer une partie personnalisée en changeant la taille de la grille et le nom des joueurs,
2. De relancer une partie avec les paramètres précédents,
3. D'afficher le score de chaque joueur ainsi que le nombre de partie jouée.

***

##Lancement du programme

```shell
mvn clean
mvn package
java -cp "target/morpion-1.0.0-SNAPSHOT-dist/*" fr.utln.flauret316.MorpionMain
```

***

##Manuel d'utilisation

1. Une fois l'application démarré, il faut cliquer sur le bouton "paramètre de la partie",
2. Il faut choisir la taille de la grille (*exemple : 3 pour une grille de 3x3*),
3. Par défaut, les noms des joueurs seront respectivement **"joueur1"** et **"joueur2"**,
4. Il faut appuyer sur **"confirmer"** pour valider la création de la première partie,
5. Une fois la partie finie, le bouton "relancer une partie" est disponible pour refaire une partie avec la même configuration que précédemment.

***
