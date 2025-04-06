# US001 - Create a map

## 1. Requirements Engineering

### 1.1. User Story Description

As an Editor, I want to create a map with a size and a name.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	The map must be rectangular.

>   Static elements can be placed on the map. Elements can be cities or industries.


**From the client clarifications:**

> **Question:** The map has a size limit?
>
> **Answer:** The limit should be 100000*100000.

> **Question:** Is there a predefined list of sizes, or should users be able to input custom dimensions?
>
> **Answer:** Custom dimensions but suggesting predefined sizes could be a good idea.

> **Question:** Will there be predefined maps, or will all maps be created by editors?
>
> **Answer:** No.

> **Question:** Are there any naming conventions for maps (no special characters, character limit)?
>
> **Answer:** File name.

### 1.3. Acceptance Criteria

* **AC1:** The maps dimensions are positive integers.
* **AC2:** Map name should be a valid file name.


### 1.4. Found out Dependencies

* Don't have any dependency with other's User Stories.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a name
    * a size
  
* Selected data:
    * none

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/US001-SSD.svg)


### 1.7 Other Relevant Remarks

* N/A