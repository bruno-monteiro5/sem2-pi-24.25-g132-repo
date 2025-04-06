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


| Concept (A) 		    |   Association   	   |            Concept (B) |
|-------------------|:-------------------:|-----------------------:|
| Map  	            |      contains       |                   City |
| Map               |      contains	      |                   City |
| Map	              |      contains	      |               Industry |
| Map	              |      contains	      |                Station |
| Station	          |       serves	       |                   City |
| Station	          |       serves	       |               Industry |
| Train	            |     comprises	      |             Locomotive |
| Train	            |     comprises	      |               Carriage |
| Train	            |    assigned to	     |                  Route |
| Route	            |     comprises	      |              RouteStop |
| RouteStop	        |     refers to	      |                Station |
| CargoTransaction	 |      contains	      |              CargoItem |
| CargoTransaction	 |    recorded in	     | CargoTransactionRecord |
| City	             | generates/consumes	 |       CargoTransaction |
| Industry	         | generates/consumes	 |       CargoTransaction |
| Player	           |       builds	       |                Station |
| Editor	           |      creates	       |                    Map |
| Product Owner	    |      oversees	      |        FinancialRecord |
| RailwayOperator	  |      manages	       |                Station |
| RailwayOperator	  |      manages	       |                  Train |
| Scenario	         |     restricts	      |                    Map |
| Station	          |   upgraded with	    |               Building |
| Simulator	        |     generates	      |                  Cargo |



## Domain Model

**Do NOT forget to identify concept atributes too.**

**Insert below the Domain Model Diagram in a SVG format**

![Domain Model](svg/DM.svg)