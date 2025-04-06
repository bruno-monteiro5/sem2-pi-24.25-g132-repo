# US009 - Buy a locomotive

## 1. Requirements Engineering

### 1.1. User Story Description

As a player, I want to buy a new locomotive.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>Locomotives have types (steam/diesel/electric)
> 
>Must match scenario restrictions
> 
>Requires sufficient funds

**From the client clarifications:**

> **Question:** Is there a limit to the number of locomotives a player can buy?
>
> **Answer:** No limit.

> 
> **Question:** What happens if the player does not have enough budget to buy a locomotive?
> 
> **Answer:** He can't buy! Bonds and loans will not be considered right now.


### 1.3. Acceptance Criteria

* **AC1:** The player should choose the locomotive from a list of available
  locomotives for the scenario as well as a current date.
* **AC2:** Validate player budget. If the player does not have enough funds, the
  operation should fail.
* 
### 1.4. Found out Dependencies

- Requires existing scenario (US04) 
- Needs at least one station (US05)
### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a date
	
* Selected data:
    * a locomotive

**Output Data:**

* List of existing locomotive categories
* Current date
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/US009-SSD.svg)

**_Other alternatives might exist._**

### 1.7 Other Relevant Remarks

- N/A
