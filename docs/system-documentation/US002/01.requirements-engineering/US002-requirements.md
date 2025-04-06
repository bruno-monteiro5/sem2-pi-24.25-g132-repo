# US002 - Add an industry

## 1. Requirements Engineering

### 1.1. User Story Description

As an Editor, I want to add an industry (selected from the available industries) in a position XY of the selected (current) map.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	 There are three different types of industries :
>* Primary sector industries (generate resources, such as mines and farms)
>* Transforming industries (Transform resources provided by the Primary Sector into new products)
>* Mixed industries (Ports pottencially have the capacity, depending on what is defined in the scneario, to import or export any cargo)


**From the client clarifications:**

> **Question:** When adding a new industry to a map, is there any other necessary input aside from position and industry type?
>
> **Answer:** No.

> **Question:** As an editor, when adding a new industry, do I need to check for overbuild?
>
> **Answer:** Yes.

### 1.3. Acceptance Criteria

* N/A

### 1.4. Found out Dependencies

* There is a dependency on "US001 - Create a map" there must be a map to choose a position XY. 

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * an industry type
    * an industry name
    * an XY position

* Selected data:
    * a map

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/US002-SSD.svg)


### 1.7 Other Relevant Remarks

* N/A