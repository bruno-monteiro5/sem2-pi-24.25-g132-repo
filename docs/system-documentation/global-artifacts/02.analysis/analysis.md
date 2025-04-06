# OO Analysis

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used.

## Rationale to identify domain conceptual classes
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development".


### _Conceptual Class Category List_

**Business Transactions**

* CargoTransaction

---

**Transaction Line Items**

* CargoItem 

---

**Product/Service related to a Transaction or Transaction Line Item**

* Passengers
* Mail
* Coal
* Iron ore
* Steel
* Cars
* Final products

---

**Transaction Records**

* OperationLog

---  

**Roles of People or Organizations**

* Product Owner
* Editor
* Player


---

**Places**

* Map
* City
* Industry (Primary/Transforming/Mixed)
* Station (Depot/Station/Terminal)
* Railway Line

---

**Noteworthy Events**

* War
* Vaccination campaign

---

**Physical Objects**

* Train
* Locomotive (Steam/Diesel/Electric)
* Carriage
* Buildings (Telegraph/Café/Customs/Post office/Hotel/Silo/Liquid storage)

---

**Descriptions of Things**

* ScenarioDescription 

---

**Catalogs**

* LocomotiveCatalog
* CarriageCatalog
* IndustryCatalog
* StationCatalog



---

**Containers**

* Route

---

**Elements of Containers**

* RouteStop

---

**Organizations**

* TheCompany (ou RailwayOperator)

---

**Other External/Collaborating Systems**

* MapService

---

**Records of finance, work, contracts, legal matters**

* BudgetRecord

---

**Financial Instruments**

* 
    
---

**Documents mentioned/used to perform some work/**

* ScenarioDocument
* UserManual

---


## Rationale to identify associations between conceptual classes

An association is a relationship between instances of objects that indicates a relevant connection and that is worth of remembering, or it is derivable from the List of Common Associations:

- **_A_** is physically or logically part of **_B_**
- **_A_** is physically or logically contained in/on **_B_**
- **_A_** is a description for **_B_**
- **_A_** known/logged/recorded/reported/captured in **_B_**
- **_A_** uses or manages or owns **_B_**
- **_A_** is related with a transaction (item) of **_B_**
- etc.


| Concept (A) 		|  Association   	|  Concept (B) |
|----------	   		|:-------------:		|------:       |
| C1  	| verb1    		 	| C2  |
| ...  	| ...    		 	| ...  |



## Domain Model

**Do NOT forget to identify concept atributes too.**

**Insert below the Domain Model Diagram in a SVG format**

![Domain Model](svg/DM.svg)