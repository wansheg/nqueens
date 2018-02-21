# solution for derivated N-Queens problem

## how to run ?
    gradle run

**default problem size  is 10, you can pass problemSize args to the Test**  

    gradle run -PproblemSize=8


## how to build ?
    gradle build
    
  **you will get nqueens.jar in build/libs/**



## API manual
### the  package cwinterview supply following class 

####    class cwinterview.NQueens  
        which supply three methods to obtain solutions for the problem.  
        recommend to use  "killRayLay" which has best performance  

        
        new NQueens( int size )
            size of chess board
        
        killRayLay( )
            **recommend API , it is best performance** 
            check current position is valid or not by check it is  
            on the line of two determined "queens" 

        deathMapLay( )  
            deprecated, obsoluted, because it is a little slow :(  
            mark the unvisited "position" as death if  
            the "position" is on the line of two queens   
    
        reducedSearchLay( )  
            deprecated, obsoluted, because it is a little slow :(  
            firstly, get solution of "traditional" N-Queens problem, 
            then check the solution contain no "angle attack"  
            amonge arbitrary three queens, it is also "n**3" complexity,  
        
####    class cwinterview.Test  
        main class to run the test with "intege" args 


        
