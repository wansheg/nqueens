# solution for derivated N-Queens problem

## how to run ?
    gradlew run build
    gradlew run 


## API manual
* the  package cwinterview supply following class 

    class cwinterview.NQueens
        
        new NQueens( int size )
            size of chess board
        
        killRayLay( )
            deprecated, obsoluted
            use "killRay" method to get solution
            this is not good method, too slow

        deathMapLay( )
            deprecated , obsoluted
            mark the unvisited "position" as death when 
            the "position" is on the line of two queens 
    
        reducedSearchLay( )
            get solution of "traditional" N-Queens problem, 
            then check the solution contain no "angle attack" 
            amonge arbitrary three queens


    class cwinterview.Test
        main class to run the test 


        
