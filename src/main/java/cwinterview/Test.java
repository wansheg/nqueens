package cwinterview;
import java.util.ArrayList;

public class Test {
    public static void main( String[] args ) { 
        int  sizeOfProblem = Integer.parseInt( args[0] );
        System.out.println( "resolving  " +   ( sizeOfProblem ) + " queens problem " );

        NQueens queens = new NQueens( sizeOfProblem );
        System.out.println( "\n-------------------- killer ray Methods --------" );

        long start = System.currentTimeMillis();
        queens.killRayLay( );
        long end = System.currentTimeMillis();
        System.out.println( "cost time: " +  (end - start ) );
        queens.dumpResult();




        /*
        queens = new NQueens( sizeOfProblem );
        System.out.println( "\n-------------------- deathMap method --------" );
        start = System.currentTimeMillis();
        queens.deathMapLay( );
        end = System.currentTimeMillis();
        System.out.println( "cost time: " +  ( end - start ) );
        queens.dumpResult();


        queens = new NQueens( sizeOfProblem );
        System.out.println( "\n-------------------- reduced search Methods --------" );
        start = System.currentTimeMillis();
        queens.reducedSearchLay( );
        end = System.currentTimeMillis();
        System.out.println( "cost time: " +  ( end - start ) );
        queens.dumpResult();
        */
    }
}
