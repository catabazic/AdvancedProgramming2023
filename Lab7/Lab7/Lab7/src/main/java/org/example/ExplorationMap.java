package org.example;

public class ExplorationMap {
    private final Cell[][] matrix;
    private boolean[][] visited;
    //Cell should be a wrapper or alias for List<Token>
    ExplorationMap(){

    }
    public boolean visit(int row, int col, Robot robot) {
        synchronized (matrix[row][col]) {
            /*if the cell is not visited {
                the robot extract tokens
                and store the tokens in the cell (it becomes visited)
                display a success message
                return true
            } ...*/
            if (!visited[row][col]) {
                visited[row][col] = true;
                return true;
            }
            return false;
        }
 //...
    }
    @Override
    public String toString() {
        String result=new String();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j< matrix.length;j++){
                result+=matrix[i][j]+" ";
            }
            result+="\n";
        }
        return result;
    }

}
