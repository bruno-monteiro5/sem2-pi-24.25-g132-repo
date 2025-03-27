# US006 - Create a map

## 1. Requirements Engineering

### 1.1. User Story Description

As an Editor, I want to create a map with a size and a name.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Each task is characterized by having a unique reference per organization, a designation, an informal and a technical description, an estimated duration and cost, as well as a task category. 

>	As long as it is not published, access to the task is exclusive to the employees of the respective organization. 

**From the client clarifications:**

> **Question:** The map has a size limit?
>
> **Answer:** The limit should be 100000*100000


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

**_Other alternatives might exist._**

### 1.7 Other Relevant Remarks

* N/A