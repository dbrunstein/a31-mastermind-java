# Rapport de Projet : MasterMind 2077

## Introduction

Ce deuxième rapport présente les avancées effectuées sur le projet "MasterMind 2077", réalisé dans le cadre du BUT2.

## Dépendances
Les dépendances superflues ont été réduites, notamment les dépendances inter-classes (a possède b, b possède a).
- **Game/Round** : Utilisation de Game dans le constructeur de Round pour transmettre les paramètres.
- **GameWindow** : Abandon de l'utilisation de l'attribut Color au profit de String afin de n'avoir aucune dépendance au modèle.

## Observateurs
`GameWindow` devient le seul observateur du projet, puisqu'aucune autre vue n'utilise activement les données du modèle.

## Vues
Des boutons et labels personnalisés ont été conçus afin de pouvoir plus aisément modifier la vue :
* **ColorButton** : Possède un attribut Color (`String`) permettant de connaître la couleur sélectionnée par le joueur.
* **ColorLabel** : Possède un attribut Color (`String`) utilisé pour la conception de la combinaison du joueur et l'affichage des indices.
