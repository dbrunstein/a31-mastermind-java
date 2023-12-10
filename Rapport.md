# Rapport de Projet : MasterMind 2077

## Introduction

Ce rapport présente le projet "MasterMind 2077" réalisé dans le cadre du BUT2. Le projet vise à implémenter le célèbre jeu de société MasterMind en utilisant le modèle de conception MVC (Modèle-Vue-Contrôleur) et en intégrant les différents designs pattern ainsi que les compétences acquises au cour de la formation en a31.

## Modèle de Conception : Modèle-Vue-Contrôleur (MVC)

Pour le MVC qu'il nous a été demandé de mettre en place, nous avons découpé la vue, le modèle, et les contrôleur de manière suivante :

- **Modèle** : La classe `Board` a été désignée comme le modèle principal, englobant les règles du jeu, la gestion du score, et les interactions avec le joueur. Cette classe est responsable de l'état du jeu.

- **Vue** : Les classes `StartWindow` et `GameWindow` ont été utilisées comme vues pour présenter différentes interfaces graphiques. `StartWindow` gère le lancement de nouvelles parties, tandis que `GameWindow` représente l'interface de jeu en cours.

- **Contrôleur** : La classe `MasterController` agit comme le contrôleur principal, connectant le modèle et la vue. Elle interprète les actions de l'utilisateur et déclenche les mises à jour nécessaires dans le modèle et la vue.

## Design Pattern Observer

Le pattern Observer a été implémenté pour permettre aux différentes parties du programme d'être informées des changements dynamiques du modèle sans dépendre directement de celui-ci.

- **Observateurs** : Les classes `StartWindow` et `GameWindow` ont été configurées en tant qu'observateurs du modèle (`Board`). Elles sont informées des changements dans le modèle et peuvent mettre à jour leurs interfaces en conséquence. Cependant la classe `EndWindow`sera plus tard également implémentée comme observateur de la board car nous n'avons pas eu le temps d'y toucher.

- **Observable** : La classe `Board` a été conçue pour être observable, permettant l'ajout, la suppression et la notification d'observateurs. Ainsi, lorsqu'un événement important se produit dans le jeu, tous les observateurs enregistrés sont notifiés.

## Connexion des Classes

La classe `MasterController` agit comme le lien central entre les différentes parties du projet. Elle crée les instances de `Board`, `StartWindow`, et `GameWindow`, configure les relations entre les classes et assure la cohérence du système.

Les interfaces graphiques (`StartWindow` et `GameWindow`) sont mises à jour dynamiquement grâce à la communication entre le contrôleur et le modèle à travers le pattern Observer. Lorsque le modèle change, les vues sont notifiées et actualisent leurs affichages en conséquence.


