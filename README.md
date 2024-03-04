# Rollin
Here’s a simple game using ordinary six sided dice. You have six dice, initially random.
On each turn you roll one more die and can then exchange it (if you wish) for any one of
your current dice. Your objective is to form two sets of three dice as quickly as possible
where a set consists either of three dice showing the same number, e.g., 444, or three
dice in sequence, e.g., 123 or 345.
## On Paper Plan
> Have check to break when two conditions are met, Remove numbers from being checked once they are found to meet one condition.
1. Check if already complete (not including 7th die)
2. Check for incremental set
3. Check for identical set
4. Check for incremental 2 numbers by one or two increments
    - Prefer increments that do not search for one number (5,6) (1,2) (or two increment difference)
5. Check for identical 2 numbers
6. Add 1 to turn counter
7. Override first number that does not match the above.  
## Task
You must provide a class that extends the abstract class ```Rollin.java```. Please give
your class a distinctive (but polite) name associated with your group. Comments in
```Rollin.java``` specify the contracts for the methods that you need to implement.
The main purpose of this étude is to get you working with your group – during one
or two labs in the first week you should be able to discuss the problem, come up with
some strategies for solving it, and write a program to implement your chosen solution.
Your solution will be compared to the other groups’ in the town hall on either March
14 or March 15, so as a matter of pride I suggest that you not submit a program that, for
example, just acts randomly hoping to get lucky at some point!
This étude must be completed and submitted by March 12 so that the results can be
discussed and compared in the town halls that week.
## Standards
For an achieved standard the program must operate as specified.
Merit criteria include well-structured and readable code, and a strategy that works relatively well compared to say just random choices.
Excellence criteria include optimal, or near optimal strategies, or extensions to the program.
## Objectives
2.1, 2.2, 2.6, 2.9, 3.5, 3.6, 4.1, 4.3, 4.4.
<<<<<<< HEAD
(Group)

=======
(Group)
>>>>>>> 818f8e1f3d0d16fcc31224f5c4ce07556de0e146
