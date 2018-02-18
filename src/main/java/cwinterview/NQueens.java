package cwinterview;
import java.util.ArrayList;


//it standfor a "line" between (x1, y1 ) and (x2, y2 )
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

/**
 * ^
 * | y
 * | ^
 * | |  
 * | |
 * |
 * |   ----&> x
 * ---------------------&>
 * NQueens class supply three methods to resolve enhanced-NQueens problem. 
 *  * killRayLay
 *  * deathMapLay
 *  * reducedSearchLay
 *
 * three methods  are all n**3 complexity 
 *
 * but the "reducedSearchLay" is best method, beacause "reducedSearchLay" reduced  
 * times of memory acces 
 *
 */

public class NQueens{
    int size;
    /* main solutionMap , record position by mapping "y" to "x" */
    int []solutionMap;
    /* record current solution, record position by mapping "x" to "y" */
    int []currentSolution;

    ArrayList<int[]>    result;


    /**********************  killer ray method **************************/
    ArrayList<Ray> rayset;
    boolean isKilled( int x, int y ) { 
        //veritical kill
        if( solutionMap[y] != -1 ) { 
            //System.out.println( "    killed vertical : solutionMap[" + y + "] is " + solutionMap[y]   );
            return true;
        }
        //45 degree kill 
        for( int i = 0; i < x; i ++ ) { 
            if( (currentSolution[i] - y == (x - i) )
                    ||
                (currentSolution[i] - y == ( i - x ) )
                ){
                //System.out.println( "    killed 45 degree " );
                return true;
            }

        }

        //three point line kill
        for( int i = 0; i < rayset.size(); i++ ) { 
            if( rayset.get( i ).killed( x, y ) ) {
                //System.out.println( "    killed 3 point line " );
                return true ;
            }
        }
        return false;
    }

    //add all "line" bewteen current point( x, y) and previous valid points 
    int updateRayset( int x, int y ) { 
        //System.out.println( "update Rayset: solutionMap[" + y + "]: " + x );
        int marker = rayset.size();
        for( int i = 0; i < x; i++ ) { 
            //add new rayset
            int x1 = i;
            int y1 = currentSolution[x1];
            rayset.add( new Ray( x1, y1, x, y ) );
        } 
        return marker;
    }
    void killRay(int xidx) {
        int yidx = 0;
        while ( yidx < this.size){
            //System.out.println( "check < " + xidx + ", " + yidx + " > " );
            if ( ! isKilled( xidx, yidx ) ) {
                int recoverMarker = updateRayset( xidx, yidx );
                this.currentSolution[xidx] = yidx;
                this.solutionMap[yidx] = xidx;
                if ( xidx < this.size - 1) 
                    killRay( xidx + 1);
                else {
                    this.result.add( this.currentSolution.clone() );

                    //dumpSolution( this.currentSolution );
                    solutionMap[yidx] = -1;

                    return ;
                }
                solutionMap[yidx] = -1;
                rayset.subList( recoverMarker, rayset.size() ).clear();
            }
            yidx++;
        }
    }

    /************************ deathMap method *******************************/
    int[]  deathMap ;

    boolean isDead( int x, int y ){

        //avoid heave access of deathMap
        //veritical kill
        if( solutionMap[y] != -1 ) { 
            //System.out.println( "    killed vertical : solutionMap[" + y + "] is " + solutionMap[y]   );
            return true;
        }
        //45 degree kill 
        for( int i = 0; i < x; i ++ ) { 
            if( (currentSolution[i] - y == (x - i) )
                    ||
                (currentSolution[i] - y == ( i - x ) )
                ){
                //System.out.println( "    killed 45 degree " );
                return true;
            }

        }

        if( this.deathMap[x* size +  y ] > 0 ) { 
            //killed by deathMap: 
            //System.out.println( "    killed by deathmap: "  );
            return true;
        }
        return false;
    }

    //compute deadMap according all previouse layed queens 
    int updateDeathMap( int[] deathSet,  int x, int y ) { 
        int count = 0;
        for( int i = 0; i < x; i++ ) { 
            int x1 = i;
            int y1 = currentSolution[i];
            int x2 = x;
            int y2 = y;
            for( int j = x+1; j < size; j++ ) { 
                int x3 = j;
                double fy3 = ((double)((x3 -x1)* ( y2-y1 ) )/ ( x2-x1 )) + (double)y1;
                int y3 = (int) fy3;
                if( y3 >= 0 && y3 < size && ((double) y3 ) == fy3 ) { 
                    //System.out.println( "deathMap:" + x3 + ","+ y3 );
                    deathMap[ x3 * size + y3 ]++;
                    deathSet[count++] = (  x3 * size + y3 );
                }
            }
        }
        return count;
    }

    void search(int xidx) {
        int yidx = 0;
        int[] deathSet = new int[ ( this.size - xidx) * size ];
        while ( yidx < this.size){

            //System.out.println( "check < " + xidx + ", " + yidx + " > " );
            if ( ! isDead( xidx, yidx ) ) {
                int count = updateDeathMap( deathSet, xidx, yidx );

                this.currentSolution[xidx] = yidx;
                this.solutionMap[yidx] = xidx;
                //System.out.println( "ok: "+ xidx +", " + yidx );
                if ( xidx < this.size - 1) 
                    search( xidx + 1);
                else {
                    this.result.add( this.currentSolution.clone() );
                    //dumpSolution( this.currentSolution );
                    this.solutionMap[yidx] = -1;
                    return ;
                }
                //recover deathMap
                for( int i = 0; i < count; i++ ) { 
                    int idx = deathSet[i];
                    deathMap[idx]--;
                    if( deathMap[idx] < 0 ) { 
                        System.out.println( "error: " + idx );
                    }
                }
                this.solutionMap[yidx] = -1;
            }

            yidx++;
        }
        /*
        System.out.print( "<" );
        for( int i = 0; i < xidx; i++ ) { 
            System.out.print( this.currentSolution[i] + "," );
        }
        System.out.println( ">" );
        */
    }

    /************************** reduced search * *********************/

    boolean isInvalid( int x, int y){

        //avoid heave access of deathMap
        //veritical kill
        if( solutionMap[y] != -1 ) { 
            //System.out.println( "    killed vertical : solutionMap[" + y + "] is " + solutionMap[y]   );
            return true;
        }
        //45 degree kill 
        for( int i = 0; i < x; i ++ ) { 
            if( (currentSolution[i] - y == (x - i) )
                    ||
                (currentSolution[i] - y == ( i - x ) )
                ){
                //System.out.println( "    killed 45 degree " );
                return true;
            }

        }
        return false;
    }

    boolean checkResult( int[] solution ){
        //search space: n * n * n
        for( int i = 0; i < this.size; i++ ) { 
            for( int j = i + 1; j < this.size; j++ ) { 
                for( int k = j + 1; k < this.size; k++ ) { 
                    int x1 = i;
                    int x2 = j;
                    int x3 = k;
                    int y1 = solution[x1];
                    int y2 = solution[x2];
                    int y3 = solution[x3];

                    if( ( x2-x1) * ( y3 -y1 ) == ( x3 - x1 ) * ( y2-y1 ) ) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    /**
     * firstly, produce solution for "traditional" N-Queens problem
     * then valid the solution to find that there is not 
     * "angle attack" amonge arbitrary three Queens in the solution.
     */

    void reducedSearch( int xidx ){ 
        int yidx = 0;
        int[] deathSet = new int[ ( this.size - xidx) * size ];
        while ( yidx < this.size){

            //System.out.println( "check < " + xidx + ", " + yidx + " > " );
            if ( ! isInvalid( xidx, yidx ) ) {

                this.currentSolution[xidx] = yidx;
                this.solutionMap[yidx] = xidx;
                //System.out.println( "ok: "+ xidx +", " + yidx );
                if ( xidx < this.size - 1) 
                    reducedSearch( xidx + 1);
                else {
                    if( checkResult( this.currentSolution ) ) {
                        this.result.add( this.currentSolution.clone() );
                        //dumpSolution( this.currentSolution );
                    }
                    this.solutionMap[yidx] = -1;
                    return ;
                }
                this.solutionMap[yidx] = -1;
            }

            yidx++;
        }
        /*
        System.out.print( "<" );
        for( int i = 0; i < xidx; i++ ) { 
            System.out.print( this.currentSolution[i] + "," );
        }
        System.out.println( ">" );
        */
    }

    /*
     * construct NQueens with problem size 
     */
	public NQueens( int size ) { 
    	this.size = size;
    	this.currentSolution = new int[ size ];
        this.solutionMap = new int[size ];
    	this.result = new ArrayList<int[]>( size/2 );
        for( int i = 0; i < size; i++ ) { 
            this.solutionMap[i] = -1;
        }
    }


    /**
     * "rayset" is used to maintain a "line" which two "point" defined, 
     *  any point( x, y) location on the "line" is killed ( isKilled return true )
     *  the rayset is shift when recursive "killRay"  return
     */

	public ArrayList<int[]> killRayLay( ) {
        //init rayset, 
    	this.rayset = new ArrayList<Ray>( size * size / 2 + 1 );
        killRay( 0 );
        return this.result;
	}

    public ArrayList<int[]> deathMapLay(){ 
        //init deathMap
        deathMap = new int[ size * size ];
        for( int i = 0; i < size * size; i++ ) { 
            deathMap[i] = 0;
        }
        search( 0 );
        return this.result;
    }

    public ArrayList< int[] > reducedSearchLay(){ 
        //firstly produce solution for traditional n queens problem;
        //after get one solution, check whether it is valid for angle attack
        reducedSearch( 0 );
        return this.result;
    }

    void dumpSolution( int[] solution ){ 

        System.out.println( "----------------------------" );
        for( int x = 0; x < size; x++ ) { 
            System.out.printf("%2d" ,  x );
            for( int y = 0; y < size; y++ ) { 
                if( solution[x] == y  ) { 
                    System.out.printf( "%2d", y );
                }
                else {
                    System.out.print( " -" );
                }
            }
            System.out.println( "" );
        }
    }

	public void dumpResult() { 
		System.out.println( "result of queens layout: " + result.size()  );
        for( int i = 0; i < result.size(); i++ ) { 
            System.out.println( "-------------------------------" );
            int[]solution = result.get( i );
            dumpSolution( solution );
        }
	}
}

