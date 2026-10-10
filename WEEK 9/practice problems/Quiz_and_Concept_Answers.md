# Week 9 - Quiz and Concept Answers

## Part B - Quiz answers

| Q | Answer | Why |
|---|--------|-----|
| 1 | B - O(1) | An index gives the memory location directly. |
| 2 | C - Insertion | Adding a new element is called insertion. |
| 3 | C | Choose the structure whose strengths match the required operations and constraints. |
| 4 | C - Time vs space trade-offs | A cache spends extra memory to save computation time. |
| 5 | B - O(m + n) | Two separate (non-nested) loops add up. |
| 6 | C - Linear data structure | Each element has one predecessor and one successor. |
| 7 | C - O(n) | Drop the constant multiplier and the lower-order term. |
| 8 | D - Linked list | int, char and boolean are primitive types. |
| 9 | B - O(m x n) | Every one of the m x n cells is visited once. |
| 10 | C | A data structure organizes, stores and accesses data efficiently for specific operations. |

## Part C - Concept questions

### 1. Searching an unsorted vs a sorted 1D array
In an unsorted array the target could be anywhere, so every element may have to be checked and the time is O(n) (linear search). In a sorted array each comparison tells us which half the target can be in, so half of the remaining elements can be discarded at every step, giving binary search in O(log n). The difference comes from the order: sorted data lets one comparison eliminate many candidates, while unsorted data gives no such information.

### 2. Why 2n is O(n) and not O(2n)
Big-O describes how the running time grows as the input becomes very large, not the exact number of operations. A constant multiplier such as 2 only changes the speed by a fixed factor (for example, a faster or slower machine), and it does not change the shape of the growth: doubling n still doubles the work. So 2n, 5n and n all grow linearly and are all written as O(n). The same reasoning drops lower-order terms, so 2n + 5 is also O(n).

### 3. Traversal in linear vs non-linear data structures
*(The question text in the file was cut off after "non-linear data", so this answer assumes it asks about structures such as trees and graphs.)*
In a linear structure such as an array there is one natural order, so traversal is a single pass from the first element to the last (index 0, 1, 2, ...), visiting each element once. In a non-linear structure such as a tree or graph, an element can connect to several others, so there is no single "next" element. Traversal needs a chosen strategy such as depth-first or breadth-first, and for graphs it also needs a record of visited nodes so that none is visited twice.
