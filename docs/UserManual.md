#   <p style="text-align: center;">USER MANUAL</p>
<br>

### <p style="text-align: right;">Oriented by:</p>
#### <p style="text-align: right;">Ana Barata</p>

### Created by:

* **Bruno Monteiro - 1240901**
* **Bruno Monteiro - 1240901**
* **Francisco Vieira - 1241039**
* **Lourenço Aragão - 1241478**
* **Gustavo Ferreira - 1241129**

<br>
<br>
<br>
<br>
<br>
<br>

## Table of Content

1. [Introduction](#introduction)
2. [Glossary](#2-glossary)
3. [System Overview](#3-system-overview)



# Introduction

This User Manual is a comprehensive guide designed to help users to effectively operate the Railway Network Simulator,
a software developed by G132 - Os Picas. The simulator provides tools to create, manage, and analyze railway networks,
including maps, stations, trains, cargo logistics, and much more.

The manual is organized to simplify and enhance the user experience, offering detailed instructions on the system’s 
purpose, scope, key features, and requirements. Additionally, it includes troubleshooting steps and answers to 
frequently asked questions to assist users in overcoming potential challenges.

By following this guide, users can fully harness the potential of the Railway Network Simulator to manage and analyze 
rail systems with efficiency and precision.

## 2. Glossary
- [Link to Glossary](system-documentation/global-artifacts/01.requirements-engineering/glossary.md)

## 3. System Overview

This section provides a comprehensive overview of the railway network simulation software, detailing its purpose, structure, and main functionalities.
### 3.1 Purpose and Use
The primary purpose of this software is to simulate the operation of railway networks, offering tools to:
- Create and edit maps and scenarios.
- Define and interact with the operation of a railway network.
- Analyze network performance and connectivity.

This software can be used for both management purposes and recreational simulation of railway systems.
### 3.2 System Structure
The software is organized into two main components and additional supporting modules:
#### 1. Map and Scenario Editor
- **Map Creation:** Allows users to design rectangular maps and place static elements, such as cities and industries.
- **Scenario Configuration:** Scenarios can be defined with historical periods, technological restrictions (e.g., available locomotives), and historical events (e.g., wars affecting cargo demand).
- **Industry Types:**
    - **Primary Sector:** (e.g., mines, farms) generates raw materials.
    - **Transforming Sector:** (e.g., steel mills, bakeries) processes raw materials into goods.
    - **Mixed Sector:** (e.g., ports) imports/exports and transforms cargo.

#### 2. Simulation Tool
- **Railway Network Management:**
    - Build stations (Depots, Stations, Terminals) with upgradable buildings (e.g., post offices, hotels).
    - Connect stations via railway lines (single/double, electrified/non-electrified).
    - Purchase locomotives (steam, diesel, electric) and assign trains to defined routes.

- **Cargo Dynamics:**
    - Cities and industries generate/consume cargo (passengers, mail, goods) based on proximity to stations.
    - Trains load/unload cargo at stations according to predefined routes.

- **Operational Controls:** Start, pause, and monitor simulation progress.

### 3.3 Main Features and Functionalities
The software provides tools and functionalities to:
- Create and edit detailed maps with cities and industries.
- Define scenarios with specific restrictions, such as technological limitations and historical periods.
- Build and upgrade stations.
- Construct railway lines and select their type (electrified, single, etc.).
- Acquire, manage, and maintain trains.
- Establish train routes and manage cargo transportation.
- Simulate railway network operations, including generating cargo and visualizing train journeys.
- Perform statistical analyses of network connectivity and operational performance.

### 3.4 Analytical Modules
The software also includes powerful analytical modules for optimization of railway network operations:
- **Connectivity Analysis:** Validate if trains can travel between stations (e.g., electrified trains require electrified lines).
- **Maintenance Planning:** Generate routes to cover the maintenance of all lines.
- **Statistical Tools:**
    - Calculate KPIs (e.g., average profit, passenger traffic).
    - Data visualization through graphs, such as boxplots, histograms, and pie charts, for analyzing cargo distribution, train allocation, and more.

### 3.5 Key Technologies and Dependencies
- **Core Development:** Java applications built using Test-Driven Development (TDD) principles and Object-Oriented Programming.
- **Visualization:** Tools such as Graphviz/GraphStream for network topology diagrams (e.g., station connections).
- **Data Analysis:** Python, including Jupyter Notebook, is used for advanced statistical calculations and visualizations.
- **Data Persistence:** Object serialization to ensure data is saved between sessions.

