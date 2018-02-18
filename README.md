# solution for derivated N-Queens problem

## how to run ?
    gradle run

    ***default problem size  is 10,***

## how to build ?
    gradle build
    
    ***youwill get nqueens.jar in build/libs/***



## API manual
* the  package cwinterview supply following class 

    class cwinterview.NQueens
        which supply three methods to obtain solutions for the problem.
        
        new NQueens( int size )
            size of chess board
        
        killRayLay( )
            deprecated, obsoluted, because it is too slow :( 
            check current position is valid or not by check it is 
            on the line of two determined "queens" 

        deathMapLay( )
            deprecated, obsoluted, because it is a little slow :( 
            mark the unvisited "position" as death if 
            the "position" is on the line of two queens 
    
        reducedSearchLay( )
            *** recommend API , it is best performance *** 
            firstly, get solution of "traditional" N-Queens problem, 
            then check the solution contain no "angle attack" 
            amonge arbitrary three queens, it is also "n**3" complexity ,
            but it reduced the search space and memory access. so it is 


    class cwinterview.Test
        main class to run the test with "intege" args 


        
