# US004 - Create a Scenario

## 1. Requirements Engineering

### 1.1. User Story Description
As an Editor, I want to create a new scenario to configure simulation parameters.
### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**
> **Ports**: Define imports/exports (e.g., coal→steel)
> 
> **Locomotives**: Restrict types (steam/diesel/electric)
> 
> **Industries**: Set production frequency (months) and demand multipliers

**From the client clarifications:**

> **Question:** Are the rates of production and quantity produce from industries decided in the scenario's specifications?
>
> **Answer:** No answer has been given.


### 1.3. Acceptance Criteria

* **AC1:**  Definition of the behaviour of ports, which cargoes they import/export and/or transform;
* **AC2:** Definition of the available locomotion types (steam, diesel, and/or
  electric).
* **AC3:** (Re)Definition of the factors that alter the generation (frequency)
  of generating industries
### 1.4. Found out Dependencies

* There is a dependency on "US001 - Create a map" as there must be a map to create a scenario.

### 1.5 Input and Output Data

**Input Example:**  

- Ports: imports coal, exports steel
- Locomotives: steam only
- Industries: coal every 6 months

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/US004-SSD.svg)

**_Other alternatives might exist._**

### 1.7 Other Relevant Remarks

- N/A