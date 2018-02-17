package cwinterview;
import java.util.ArrayList;

public class Test {
    public static void main( String[] args ) { 
        int  sizeOfProblem = Integer.parseInt( args[1] );
        System.out.println( "resolving  " +   ( sizeOfProblem ) + " queens problem " );
        NQueens queens = new NQueens( sizeOfProblem );
        queens.layoutQueens( );
        queens.dumpResult();
    }
}
