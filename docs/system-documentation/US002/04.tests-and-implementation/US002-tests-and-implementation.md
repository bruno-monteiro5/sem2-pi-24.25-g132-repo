# US002 - Add an industry 

## 4. Tests 

**Test 1:** 

	@Test
    public void ensureValidIndustryCanBeCreated() {
        Position position = new Position(10, 20);
        IndustryTypes.IndustryType type = IndustryTypes.IndustryType.PRIMARY;
        Industry industry = new Industry("IndustryName", position, type);

        assertNotNull(industry);
        assertEquals("IndustryName", industry.getName());
        assertEquals(position, industry.getPosition());
        assertEquals(type, industry.getType());
    }
	

**Test 2:**  

	@Test
    public void testIsAtPositionTrue() {
        Position position = new Position(10, 20);
        Industry industry = new Industry("IndustryName", position, IndustryTypes.IndustryType.PRIMARY);

        assertTrue(industry.isAtPosition(new Position(10, 20)));
    }


**Test 3:**

	@Test
    public void testIsAtPositionFalse() {
        Position position = new Position(10, 20);
        Industry industry = new Industry("IndustryName", position, IndustryTypes.IndustryType.PRIMARY);

        assertFalse(industry.isAtPosition(new Position(5, 5)));
    }



**Test 4:**

	@Test
    public void testSetName() {
        Industry industry = new Industry("OldName", new Position(10, 20), IndustryTypes.IndustryType.PRIMARY);
        industry.setName("NewName");

        assertEquals("NewName", industry.getName());
    }



**Test 5:**

	@Test
    public void testSetPosition() {
        Industry industry = new Industry("Industry", new Position(10, 20), IndustryTypes.IndustryType.PRIMARY);
        Position newPosition = new Position(15, 25);
        industry.setPosition(newPosition);

        assertEquals(newPosition, industry.getPosition());
    }




**Test 6:**

	@Test
    public void testSetType() {
        Industry industry = new Industry("Industry", new Position(10, 20), IndustryTypes.IndustryType.PRIMARY);
        IndustryTypes.IndustryType newType = IndustryTypes.IndustryType.MIXED;
        industry.setType(newType);

        assertEquals(newType, industry.getType());
    }




**Test 7:**

	@Test
    public void testToString() {
        Industry industry = new Industry("Industry", new Position(10, 20), IndustryTypes.IndustryType.PRIMARY);
        String expected = "Industry{-Name='Industry'-Position=Position{x=10, y=20}-Type=PRIMARY}";
        assertEquals(expected, industry.toString());
    }


**Test 8:**

	@Test
    public void testIsAtPositionFalse() {
        Position position = new Position(10, 20);
        Industry industry = new Industry("IndustryName", position, IndustryTypes.IndustryType.PRIMARY);

        assertFalse(industry.isAtPosition(new Position(5, 5)));
    }


**Test 9:**

	@Test
    public void testGetAllTypes() {
        String[] expected = {"PRIMARY", "TRANSFORMING", "MIXED"};
        String[] actual = IndustryTypes.getAllTypes();

        assertArrayEquals(expected, actual);
    }

**Test 10:**

	@Test
    public void testIndustryTypeDescriptions() {
        assertEquals("Primary Industry", IndustryTypes.IndustryType.PRIMARY.getDescription());
        assertEquals("Transforming Industry", IndustryTypes.IndustryType.TRANSFORMING.getDescription());
        assertEquals("Mixed Industry", IndustryTypes.IndustryType.MIXED.getDescription());
    }


## 5. Construction (Implementation)

### Class CreateTaskController 

```java
public Task createTask(String reference, String description, String informalDescription, String technicalDescription,
                       Integer duration, Double cost, String taskCategoryDescription) {

	TaskCategory taskCategory = getTaskCategoryByDescription(taskCategoryDescription);

	Employee employee = getEmployeeFromSession();
	Organization organization = getOrganizationRepository().getOrganizationByEmployee(employee);

	newTask = organization.createTask(reference, description, informalDescription, technicalDescription, duration,
                                      cost,taskCategory, employee);
    
	return newTask;
}
```

### Class Organization

```java
public Optional<Task> createTask(String reference, String description, String informalDescription,
                                 String technicalDescription, Integer duration, Double cost, TaskCategory taskCategory,
                                 Employee employee) {
    
    Task task = new Task(reference, description, informalDescription, technicalDescription, duration, cost,
                         taskCategory, employee);

    addTask(task);
        
    return task;
}
```


## 6. Integration and Demo 

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a