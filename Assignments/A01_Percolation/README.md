## Assignment1 Percolation

##### A program to estimate the value of the percolation threshold via Monte Carlo simulation.

1. main goal: practice union-find data structure
2. score: 101.25/100 (bonus for memory performance)
3. Key idea: set virtual-top / virtual-bottom nodes to tell percolates in O(1) time.
    ```java
    #              virtual-top
    #             /    |    \
    #            1     2     3
    #            4     5     6
    #            7     8     9
    #            \     |    /
    #            virtual-bottom
    # See detail in Percolation.java
    ```
4. Key challenge:
   how to avoid `backwash` bug. Details see [here](http://coursera.cs.princeton.edu/algs4/checklists/percolation.html)  and [possible solutions](http://tech-wonderland.net/blog/avoid-backwash-in-percolation.html)
- [Assignment web page](https://class.coursera.org/algs4partI-008/assignment/view?assignment_id=1)

- [Detailed instructions](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html)

- [Q & A](http://coursera.cs.princeton.edu/algs4/checklists/percolation.html)

