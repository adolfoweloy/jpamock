# jpamock

 * The best way to setup your JPA mapped database. Easier than traditional xml database fixtures.
 * It cares about all kinds of constraints (primary keys, nullability, unique, foreign keys, etc)
 * Retrieve the object tree to do asserts (mock frameworks like)
 * Fancy way to test Criteria and HQL
 * Don't concern at test time about persist/merge issues like: IdentifierGenerationException, TransientObjectException, ConstraintViolationException, PropertyValueException

## How does it work?
 Very simple concept. When you call *`jpaMock.mock(SomeMappedEntity.class)`* the framework instantiate all dependencies of your classes using default values and persist all data. For example, if your DB is empty and you call *`jpaMock.mock`* it going to appear a new row for each table on DB, if you call it again, other row is added, resulting two rows for each table. That's it. You can change the behavior and override default values, see bellow. The coolest part is that jpaMock.mock returns the whole persisted object tree, and this is very useful to do asserts.
 
![Tree](http://i.imgur.com/4GdYF1W.jpg)
![Inspector](http://i.imgur.com/j9Qwfr9.jpg)

 Magically new records are created in your DB for Body, Arm, Finger, Leg, Pants and Color.

If you call `jpaMock.mock(Pants.class)` you going to get back a root instance of Pants associated with Color. A new record would be created for Pants and Color. This happens becouse you don't have navigation from Pants to Leg. If the association between Leg and Pats was bidirectional, there would be created records for all entities and the unique diference between call `jpaMock.mock(Body.class)` and `jpaMock.mock(Pants.class)` would be  who is the root entity on the retrieved object tree. In other words, if your model are full bidirectional always will be created new records for all tables unless you force null (Ex: `jpaMock.when(Body.class, "arms").thenInject(null);`).

## Basic usage

```java
JPAMock jpaMock = new JPAMock(entityManagerFactory);
```

```java
//Field scope override
public void testFindOrderByDate(){
  Date orderDate = new Date();
  Order orderMock = jpaMock.when(Order.class, "date").thenInject(orderDate).mock(Order.class);
  Order orderDB = service.findOrderByDate(orderDate);
  assertEquals(orderMock.getId(), orderDB.getId());
}
```

```java
//Class scope override
public void testFindPersonsByCarBrand(){
  Car car = new Car();
  car.setBrand("BMW"); //remainder fields going to be filled with default values
  Person personMock = jpaMock.when(Car.class).thenInject(car).mock(Person.class);
  List<Person> personsDB = service.findPersonsByCarBrand("BMW");
  assertTrue(jpaMock.containsById(personsDB, personMock));
}
```

```java
//Collection setup
public void testFindCheaperItem(){
  List<Item> items = new ArrayList<Item>();
  items.add(new Item("Water", 5)); //name and price
  items.add(new Item("Cola", 5));
  items.add(new Item("Candy", 4));
  Order orderMock = jpaMock.when(Order.class, "items").thenInject(items).mock(Order.class);
  Item cheaperItem = service.findCheaperItem(orderMock.getId());
  assertEquals("Candy", cheaperItem.getName());
}
```

```java
//full navigation to improve your life!
public void testFindCabByDriverCountryId(){
  Cab cabMock = jpaMock.mock(Cab.class);
  List<Cab> cabsDB = service.findCabByDriverCountryId(cabMock.getDriver().getCountry().getId());
  assertTrue(jpaMock.containsById(cabsDB , cabMock));
}
```

```java
//Working with @BeanValidation (JSR303)

@Entity public class Image(){
  @Min(2) Float sizeInMB;
}

@Entity public class User(){
   @Email String email;
}

//just handle this on setup phase of testing lifecycle
@Override protected void setUp() throws Exception {
  jpaMock.when(Image.class, "sizeInMB").thenInject(2);
  jpaMock.when(User.class, "email").thenInject("valid@valid.com");
}
```

```java
//Override Anything
jpaMock.when(AbstractEntity1.class).thenInject(new Entity1()); //Class scope override
jpaMock.when(Set.class).thenInject(new TreeSet()); //Class scope override
jpaMock.when(int.class).thenInject(99); //Primitive Class scope override
jpaMock.when(String.class).thenInject("abc"); //Class scope override
jpaMock.when(Bus.class).thenInject(null);  //Class scope override *force null*
jpaMock.when(Person.class).thenInject("age", 26); //Field scope override
jpaMock.when(Airplane.class, "color").thenInject(null); //Field scope override *force null*
```

```java
//clean up all database tables if you want or need
jpaMock.clearAll(); 
//clean up single table
jpaMock.clear(House.class); //you have to handle constraints by yourself
```

## Avoiding new records

```java
//if there is a previous id==50 then reuse it (remainder fields going to be filled with default values), else create a new row
jpaMock.when(Shoe.class, "id").thenInject(50); 
//no fingers would be created, unless there is an alternative path to navegate to finger from hand
jpaMock.when(Hand.class, "fingers").thenInject(null); 
```

## Only object factory

Just pass null on constructor. You will still get the object tree back but the framework does not touch nothing related to persistence. Ids will assume default/overrided values and unique constraints wont be handled. This should be useful to setup database your way.
```java
JPAMock jpaMock = new JPAMock(null);
```

## Defaults

```java
//String
String.class                    ""

//Primitive and wrappers
Boolean.class                   new Boolean(false)
Byte.class                      new Byte((byte) 0)
Character.class                 new Character((char)0)
Short.class                     new Short((short)0)
Integer.class                   0
Long.class                      0L
Float.class                     0F
Double.class                    0D

//Others
BigInteger.class                new BigInteger("0")             
BigDecimal.class                new BigDecimal("0")             
Date.class                      new Date(0)             
Calendar.class                  Calendar.getInstance().setTime(new Date(0)) 
java.sql.Date.class             new java.sql.Date(0)            
Time.class                      new Time(0)             
Timestamp.class                 new Timestamp(0)                

//Collections
Collection.class                new ArrayList<Object>()
ArrayList.class                 new ArrayList<Object>()
List.class                      new ArrayList<Object>()
LinkedList.class                new LinkedList<Object>()                
Set.class                       new HashSet<Object>()
HashSet.class                   new HashSet<Object>()           
LinkedHashSet.class             new LinkedHashSet<Object>()             

//Maps
Map.class                       new HashMap<Object, Object>()
HashMap.class                   new HashMap<Object, Object>()           
LinkedHashMap.class             new LinkedHashMap<Object, Object>()             

//Arrays
byte[].class                    new byte[] { 0x00 }             
char[].class                    new char[] { '0' }              
Byte[].class                    new Byte[] { 0x00 }             
Character[].class               new Character[] { '0' }    
```
