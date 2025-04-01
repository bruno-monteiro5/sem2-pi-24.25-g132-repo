# US007 - See the details of a station

## 1. Requirements Engineering

### 1.1. User Story Description

As a Player, I want to list all the stations to select one to see its
details, including the existing building and the demand/supply cargoes.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> There are three types of stations as represented in figure 
Depots (50k, 3x3 radius), Stations (100k, 4x4 radius), and 
Terminals (200k, 5x5 radius). Their cost and economic radius 
distinguish the stations.

> All industrial and population sites within the radius 
of a station send (supply) and receive (demand) 
business through the station.

> When viewing a station, in addition to the buildings 
present, the cargo that is ready for collection and the 
cargo that needs to be delivered should also be displayed. 

**From the client clarifications:**

> **Question:** When the user selects an existing station, does it 
automatically show all the associated info to that station or the user 
selects the station then has to select the required option ? eg: 'show details'
>
> **Answer:** That's a matter of UX/UI, each team can decide what works best.

### 1.3. Acceptance Criteria

There is no acceptance criteria yet.

### 1.4. Found out Dependencies

* There is a dependency on "US005 - Create a station" as there must be at least one station to list.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Request to list stations
	
* Selected data:
    * a specific station chosen by the player

**Output Data:**

* List of available stations
* Details of the selected station, including:
    * Existing building
    * Demand cargo list
    * Supply cargo list

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/US007-SSD.svg)

**_Other alternatives might exist._**

### 1.7 Other Relevant Remarks

* If no stations exist, the system should display an appropriate message.