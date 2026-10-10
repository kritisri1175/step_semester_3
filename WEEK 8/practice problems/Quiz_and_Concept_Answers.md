# Week 8 - Quiz and Concept Answers

## Quiz answers

| Q | Answer | Why |
|---|--------|-----|
| 1 | C - Polymorphism | TicketProcessor treats every event type through the common parent type. |
| 2 | C - Encapsulation | The field is hidden and changed only through a validating method. |
| 3 | C - Author 1 --- 1..* Book | One author writes one or more books; each book has exactly one author. |
| 4 | D - Composition | Cart items cannot exist without their cart. |
| 5 | D - Generalization | ProgrammingCourse is an "is-a" specialization of Course. |
| 6 | C - Realization | PdfDocument implements the Printable contract. |
| 7 | B - Association | Customer and Order are related but have no ownership or lifecycle link. |
| 8 | C - State diagram | It models the lifecycle and allowed transitions of a Task. |
| 9 | B - Abstraction | Callers use the interface and ignore how each report is built. |
| 10 | C - Sequence diagram | It shows messages between objects in time order. |

## Concept questions

### 1. Encapsulation in the User object
The User object owns its email state and is the only place allowed to change it. If the field were public, any code could set it to an empty string, a malformed address or one already used by another account, and the object would end up in an invalid state with no way to detect it. By keeping the field private and routing every change through changeEmail, the object can check the format and uniqueness first and reject bad values. Validation then lives in one place, so every caller gets the same guarantee and the object is always valid.

### 2. Inheritance vs composition for DataExport and DataVisualization
Inheritance fits when a subclass really "is a" specialized version of the parent and shares its core behavior. Export and visualization are optional capabilities that only some reports need, so they are "has a" relationships and suit composition: a report is given a DataExporter or a ChartRenderer only when needed. Forcing everything into one inheritance hierarchy produces awkward classes such as ExportableVisualizableReport, and Java allows only one parent class, so combinations multiply and subclasses inherit methods they never use. Composition lets new capabilities be added or swapped without touching the existing hierarchy.

### 3. Abstraction, overriding and polymorphism in NotificationService
Notification is an interface (or abstract class) with a method such as send(). EmailNotification, SMSNotification and PushNotification each override it with their own behavior. NotificationService holds a Notification reference and calls send(), and at runtime Java runs the overriding method of the actual object, so no if/else on the type is needed. When InAppNotification is added, it only has to implement send(); NotificationService is unchanged because it depends on the abstraction, not on concrete classes.

### 4. Composition vs aggregation (Organization, Department, Employee)
Composition is a strong "owns" relationship where the part cannot outlive the whole; aggregation is a weaker "has" relationship where the part exists independently. Organization to Department is composition, because departments are dissolved when the organization ceases to exist. Department to Employee is aggregation, because employees survive a dissolved department and can move to another one.

### 5. Multiplicity for Student and Course
The relationship is many-to-many: a Student enrolls in 0..* Courses and a Course has 0..* Students, usually modeled through an Enrollment class between them. Multiplicity only says how many objects can be linked, not whether the same pair can be linked twice. The "no duplicate enrollment" rule is a business constraint, enforced in logic such as checking for an existing Enrollment before creating one, or a unique constraint on the (student, course) pair in the database.
