## Assignment2 [Randomized Queues and Deques](https://class.coursera.org/algs4partI-008/assignment/view?assignment_id=3)

Write a generic data type for a deque and a randomized queue using linked list and resizing array.

1. Implement deque using doubly-linked list to support operations in constant worst-case time.
2. Implement randomized queue using resizing array to support constant `amortized` time operations.
    - double size of array when queue is full
    - halve size of array when queue is one-quarter full
    - independent random iterator: each time iterator created, create an array of indices and shuffle it
3. Select k samples from N items using reservior sampling. (O(N) time && O(k) space) see [here](http://www.geeksforgeeks.org/reservoir-sampling/). 
4. Chi-squared test to determine whether there is a significant difference between the expected frequencies and the observed frequencies. See [Test 15 in RandomizedQueueTest](https://github.com/interviewcoder/princeton_algorithms_coursera/blob/master/Assignments/A02_RandomizedQueuesAndDeques/test/RandomizedQueueTest.java) for details.
5. Reference

    * [assignment instructions page](http://coursera.cs.princeton.edu/algs4/assignments/queues.html)
    * [assignment check lists](http://coursera.cs.princeton.edu/algs4/checklists/queues.html)
    * assignment assessment summary: **score 100.19 out of 100**
        ```shell
        Compilation:  PASSED
        Style:        PASSED
        Findbugs:     No potential bugs found.
        API:          PASSED
        
        Correctness:  37/37 tests passed
        Memory:       54/53 tests passed
        Timing:       43/43 tests passed    
        
        Aggregate score: 100.19% [Correctness: 65%, Memory: 10%, Timing: 25%, Style: 0%]
        ```

