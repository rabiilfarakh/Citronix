# Citronix - Application de Gestion de Ferme de Citrons

## Contexte du Projet
Le projet **Citronix** consiste à développer une application de gestion pour une ferme de citrons. Cette application permet aux fermiers de suivre la production, la récolte et la vente de leurs produits, tout en optimisant le suivi de la productivité des arbres en fonction de leur âge. L'application gère également la ferme, les champs, les arbres, les récoltes, et les ventes.

## Fonctionnalités Principales

### 1. **Gestion des Fermes**
- Créer, modifier et consulter les informations d'une ferme (nom, localisation, superficie, date de création).
- Recherche multicritère via Criteria Builder pour faciliter la recherche de fermes.

### 2. **Gestion des Champs**
- Associer des champs à une ferme avec une superficie définie.
- Contrôle de la cohérence des superficies : la somme des superficies des champs d'une ferme ne doit pas dépasser celle de la ferme.

### 3. **Gestion des Arbres**
- Suivi des arbres par date de plantation, âge et champ d'appartenance.
- Calcul de l'âge des arbres.
- Productivité annuelle en fonction de l'âge des arbres :
  - Arbre jeune (< 3 ans) : 2,5 kg / saison.
  - Arbre mature (3-10 ans) : 12 kg / saison.
  - Arbre vieux (> 10 ans) : 20 kg / saison.

### 4. **Gestion des Récoltes**
- Suivi des récoltes par saison (hiver, printemps, été, automne).
- Enregistrement de la date de récolte et de la quantité totale récoltée.

### 5. **Détail des Récoltes**
- Suivi des quantités récoltées par arbre pour chaque récolte.
- Association de chaque récolte à un arbre spécifique.

### 6. **Gestion des Ventes**
- Enregistrement des ventes avec la date, le prix unitaire, le client, et la récolte associée.
- Calcul du revenu des ventes : Revenu = quantité * prixUnitaire.

## Contraintes et Règles Métier

- Superficie minimale des champs : 0.1 hectare (1 000 m²).
- Superficie maximale d'un champ : Aucun champ ne peut dépasser 50% de la superficie totale de la ferme.
- Nombre maximal de champs : Une ferme ne peut contenir plus de 10 champs.
- Espacement entre les arbres : Chaque champ doit respecter une densité maximale de 100 arbres par hectare (10 arbres par 1 000 m²).
- Durée de vie des arbres : Un arbre ne peut être productif au-delà de 20 ans.
- Période de plantation : Les arbres ne peuvent être plantés qu'entre mars et mai.
- Limite saisonnière : Chaque champ peut être associé à une seule récolte par saison.
- Arbres non récoltés deux fois par saison : Un arbre ne peut être inclus dans plus d’une récolte pour une même saison.

## Exigences Techniques

- **Spring Boot** : Utilisé pour créer l'API REST.
- **Architecture en couches** : Utilisation des couches Controller, Service, Repository, Entity.
- **Validation des données** : Utilisation des annotations Spring pour valider les entrées.
- **Gestion centralisée des exceptions** : Pour une gestion uniforme des erreurs.
- **Tests unitaires** : Implémentation de tests unitaires avec **JUnit** et **Mockito**.
- **Lombok et Builder Pattern** : Pour simplifier la gestion des entités et leur instanciation.
- **MapStruct** : Utilisation de MapStruct pour la conversion entre les entités, DTOs et View Models.
