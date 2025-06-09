# US003 - Add a City

## 1. Requirements Engineering

### 1.1. User Story Description

As an Editor, I want to add a city in a position XY of the selected
map, with a name and a positive number of house blocks.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> A city bears a name, a location (with XY coordinates on the map), and a set of blocks that represent housing.

> Cities generate and consume passengers and mail, and also consume final products (e.g., food, textiles, cars).

> House blocks can be assigned manually or automatically around the city center.

> There is no maximum number of house blocks, but the number must be positive.

> Cities must be placed within map boundaries and cannot overlap with existing cities or industries.

> Automatically generated house blocks must also respect map boundaries and be positioned near the city center.

**From the client clarifications:**

> **Question:** Dear client, can the city name contain spaces in its nomenclature?
>
> **Answer:** Yes, it can, "Torres Novas," for example.

> **Question:** Should the city name be unique within the same map?
>
> **Answer:** It can be repeated, but it may become confusing for the 
players. The editor should be alerted to this situation.

> **Question:** Is there a minimum or maximum number of house blocks 
that can be assigned?
>
> **Answer:** The number needs to be a positive on; there is no maximum, 
it's up to the editor to decide.

> **Question:** Is it possible for a city to be destroyed by a natural 
disaster or a war? If so, is it necessary to create a mechanism to 
remove the city from the map and create a new one to transfer the 
active elements from the first one(people, mail, ...)?
>
> **Answer:** A city can be removed in edit mode, but not in 
simulation mode.


### 1.3. Acceptance Criteria

* **AC1:** A city name cannot have special characters or digits.
* **AC2:** The house blocks can be assigned manually or automatically (randomly around the city tag position).

### 1.4. Found out Dependencies

* There is a dependency on "US001 - Create a map" as there must be at least one map to creat the city.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
  * City name (validated to contain only letters and spaces)
  * X coordinate (positive integer, within map width)
  * Y coordinate (positive integer, within map height)
  * Number of house blocks (positive integer)
  * House block positions (if manual placement is chosen)

* Selected data:
  * A map (must already exist and be selected)
  * Manual or automatic placement of house blocks

**Output Data:**

* System feedback during input:
  * Error messages if city name is invalid or already exists
  * Error messages if coordinates are out of map bounds
  * Error if block position is invalid (e.g., overlaps city, too far, outside map)

* Before final creation:
  * System displays city name, position and block list (if manual)
  * System asks for confirmation (Y/N)

* After confirmation:
  * If confirmed: success message with city details
  * If cancelled: operation aborted message


### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/US003-SSD.svg)

**_Other alternatives might exist._**

### 1.7 Other Relevant Remarks

* A city cannot be placed on top of another city or industry.