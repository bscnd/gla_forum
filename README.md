# Description
Un forum sécurisé pour l'UE GLA. Mise en oeuvre d'un maximum de bonnes pratiques de développement. Les fonctionnalités du forum sont les suivantes :
- Gestion des utilisateurs (Création, Supression, Connexion, Déconnexion ...)
- Accès et modification de données dans un espace personnel dédié à l'utilisateur (avatar, informations personnelles ...)
- Fils de discussion

## Matrice des différents rôles pour les utilisateurs :

| Rôle           |  Supprimer un fil entier | Supprimer un message  | Supprimer un utilisateur | Ajouter un message | Lire un fil de discussion |
|:--------------:|:------------------------:|:---------------------:|:------------------------:|:------------------:|:-------------------------:|
| Administrateur |          X               |          X            |            X             |         X          |             X             |
| Modérateur     |                          |          X            |                          |         X          |             X             |
| Utilisateur    |                          |                       |                          |         X          |             X             |
| Invité         |                          |                       |                          |                    |             X             |

# Infrastructure
J'utilise Docker pour faciliter le développement et le déploiement inter-plateformes. Le fichier docker-compose lance :
- Un conteneur mysql (port 3306)
- Un conteneur phpmyadmin (port 8081)
- Un conteneur tomcat (port 8082)

Le code respect les design patten MVC et DAO :
- Les traitements liés à l'accès aux données sont séparés des traitements métier
- Les vues = fichier .jsp
- Les contôleurs = classes java "servlets"
- Les modèles = classes java de traitement dit "métier"

