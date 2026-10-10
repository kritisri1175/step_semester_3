# Week 10 - Quiz and Concept Answers

## Part B - Quiz answers

| Q | Answer | Why |
|---|--------|-----|
| 1 | B - `# age` and `+ getName()` | A subclass can use protected and public members, but not private ones. |
| 2 | C - Diagram 3 | Class S extends both Q and R, and single class inheritance allows only one parent class. |
| 3 | B - Person -> Employee -> Manager | The base constructor runs first, so the chain finishes at the most derived class. |
| 4 | C | `protectedField` is accessible in the subclass; private is not, a final method cannot be overridden, and access cannot be weakened. |
| 5 | B - the Bus version | Overridden methods are chosen at run time from the actual object. |
| 6 | B | Two `print` methods with different parameters are overloading; `SpecialPrinter.print(String)` is overriding. |
| 7 | A | A Shape reference only exposes members declared in Shape, even if it holds a Circle. |
| 8 | C | Each element runs its own overriding `area()` (dynamic dispatch). |
| 9 | B | The object is really a Current, so `(Savings) a` fails (ClassCastException) and `(Current) a` succeeds. |
| 10 | B | One loop over the base type handles all subtypes without if-else type checks. |
| 11 | A | `printDocument()` is the exposed behavior; the buffer and helper methods are hidden details. |
| 12 | B | An abstract class cannot be instantiated, but a Vehicle reference can refer to a Car. |
| 13 | B | A subclass that skips an abstract method must itself be declared abstract. |
| 14 | B | A concrete class that does not implement the inherited abstract method is a compile error. |
| 15 | A | An interface lists the operations a type promises, without per-object state or implementation. |
| 16 | B | Interface methods are implicitly `public abstract`, and fields are `public static final`. |
| 17 | C | A class must implement every method of every interface it realizes. |
| 18 | C - Interface | A pure protocol for unrelated classes, with no state and no default implementation. |
| 19 | B | Dog IS-A Animal (generalization); Eagle CAN-DO Flyable (realization). |
| 20 | A | `equals()`, `hashCode()` and `toString()` are inherited from Object and often redefined. |

## Part C - Concept questions

### 1. Overloading vs overriding
Overloading means several methods in the same class share a name but have different parameter lists, and the compiler picks the version at compile time from the argument types. Overriding means a subclass redefines an inherited method with the same signature, and the version that runs is chosen at run time from the actual object (dynamic dispatch). So overloading is resolved at compile time and overriding at run time.

### 2. Constructor order in inheritance
When a derived object is created, constructors run from the top of the hierarchy down: the base class constructor finishes first, then each derived constructor in turn. If the derived constructor needs to pass values to the base constructor, it calls `super(...)` with those values as its first statement. If it doesn't, the compiler calls the base no-argument constructor automatically.

### 3. Concrete class vs abstract class vs interface
A concrete class can be instantiated, can hold per-object state and has full implementations. An abstract class cannot be instantiated, but can hold state and mix implemented methods with abstract ones that subclasses must complete. An interface cannot be instantiated and has no per-object state (only constants); it mainly lists abstract operations (a contract), and can have default methods. Use a concrete class for a complete, ready-to-use type, an abstract class for a family of related classes that share state and code but need subclass-specific behavior, and an interface for a capability that unrelated classes can share.

### 4. Two interfaces with the same method signature
The class provides a single implementation of that method, and it satisfies both interfaces at once. This works because an interface only declares what must exist, not how, so there is nothing to conflict. (If the signatures matched but the return types were incompatible, it would be a compile error.) It shows that a class can fulfill several contracts at the same time.

### 5. Why override both equals() and hashCode()
Content equality means two objects with the same field values should be treated as equal, so `equals()` must be overridden. Hash collections such as HashSet and HashMap first use `hashCode()` to choose a bucket and only then call `equals()`. The contract says equal objects must return the same hash code. If `equals()` is overridden but `hashCode()` is not, two "equal" objects usually land in different buckets, so a HashSet can hold duplicates and `contains()` or `get()` can fail to find an object that is equal to one stored.

### 6. Shallow copy vs deep copy
A shallow copy copies the object's fields, so any nested object is shared between the original and the copy. A deep copy also creates new copies of the nested objects. Example: a Student has an Address object. After a shallow copy, changing the copy's address city also changes the original's city, because both point to the same Address. A deep copy is needed so the two students are completely independent.

### 7. Inner type vs static nested type
An inner (non-static) class belongs to an instance of the outer class, needs that instance to be created (`outer.new Inner()`), and can access all members of the outer instance, including private ones. A static nested class does not need an outer instance, is created directly (`new Outer.Nested()`), and can only use the outer class's static members directly. Use an inner class for something tied to one outer object, such as an iterator reading a list's fields; use a static nested class for a helper that stands alone, such as a Node class or a Builder.

### 8. Time complexity of array operations
Access by index is O(1): an array is stored in contiguous memory, so the address of element i is calculated directly from the start address and i. Inserting or deleting in the middle is O(n): the array has a fixed size and its elements must stay contiguous, so all the elements after that position have to be shifted by one place, which in the worst case moves about n elements.

### 9. Processing different subtypes through a base-type reference
Objects of different derived types can be stored in one array of the base type, because each derived object "is a" base object. A loop calls the same method on every element through the base reference. Dynamic method dispatch makes Java choose the overriding version according to the actual object at run time, so each element behaves in its own way without any `instanceof` checks or if-else chains. New subtypes can be added without changing the loop.

### 10. IS-A (inheritance) vs CAN-DO (interface)
Use inheritance when the subclass really is a more specific kind of the parent and should share its state and behavior (a Dog IS-A Animal). Use an interface when a class simply has a capability, especially one shared by unrelated classes (CAN-DO). Example: Bird, Airplane and Drone can all fly, but they don't share a parent; a `Flyable` interface lets one method work on all of them, and it avoids forcing an artificial common parent. Because a class can implement many interfaces but extend only one class, interfaces also keep the design flexible.
