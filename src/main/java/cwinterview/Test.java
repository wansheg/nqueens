package cwinterview;
import java.util.ArrayList;

public class Test {
    public static void main( String[] args ) { 
        int  sizeOfProblem = Integer.parseInt( args[1] );
        System.out.println( "resolving  " +   ( sizeOfProblem ) + " queens problem " );

        NQueens queens = new NQueens( sizeOfProblem );
        System.out.println( "\n-------------------- deathMap method --------" );
        queens.deathMapLay( );
        //queens.dumpResult();

        /*
        queens = new NQueens( sizeOfProblem );
        System.out.println( "\n-------------------- killer ray Methods --------" );
        queens.killRayLay( );
        */
        //queens.dumpResult();
    }
}
