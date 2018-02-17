package src.main.java.cwinterview;
import java.util.ArrayList;
class Ray { 
	int x1, y1, x2, y2;
    Ray( int x1, int y1, int x2, int y2 ) { 
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    //completely integer multi , good version , avoid float arith
    boolean  killed( int x, int y ) { 
        //for x-ray,  all "y" value is equal
        if( y == y1 || y == y2 ) { 
            return true;
        }
        int img1 = ( x - x1 ) * ( y - y2 );
        int img2 = ( y - y1 ) * ( x - x2 );
        if( img1 == img2 ) { 
            return true;
        }
        else {
            return false;
        }
    }
}

/* ^
 * | y
 * | ^
 * | |  
 * | |
 * |
 * |   ----> x
 * --------------------->
 */

public class NQueens{
    int size;
    int []solutionMap;
    ArrayList<Ray> rayset;
    ArrayList<int[]>    result;

    int []currentSolution;

    boolean isKilled( int x, int y ) { 
        for( int i = 0; i < rayset.size(); i++ ) { 
            if( rayset.get( i ).killed( x, y ) ) {
                return true ;
            }
        }
        return false;
    }

    int updateRayset( int x, int y ) { 
        this.solutionMap[y] = x;
        int marker = rayset.size();
        for( int i = 0; i < x; i++ ) { 
            //add new rayset
            int x1 = i;
            int y1 = currentSolution[x1];
            rayset.add( new Ray( x1, y1, x, y ) );
        } 
        return marker;
    }

    void layout(int xidx) {
        int yidx = 0;
        while ( yidx < this.size){
            int recoverMarker = rayset.size();
            if ( ! isKilled( xidx, yidx ) ) {
                recoverMarker = updateRayset( xidx, yidx );
                this.currentSolution[xidx] = yidx;
                if ( xidx < this.size) 
                    layout( xidx + 1);
                else {
                    this.result.add( this.currentSolution.clone() );
                    return ;
                }
            }
            rayset.subList( recoverMarker, rayset.size() ).clear();
            yidx++;
        }
    }

	public NQueens( int size ) { 
    	this.size = size;
    	this.currentSolution = new int[ size ];
        this.solutionMap = new int[size ];
    	this.rayset = new ArrayList<Ray>( size * size / 2 + 1 );
    	this.result = new ArrayList<int[]>( size/2 );
        for( int i = 0; i < size; i++ ) { 
            this.solutionMap[i] = -1;
        }
    }

	public ArrayList<int[]> layoutQueens( ) {
        layout( 0 );
        return this.result;
	}

	public void dumpResult() { 
		System.out.println( "result of queens layout:");
	}
}

