/* Author: Francesco Podestà
   Age: 17 years old
   Student in Italy
   Contact me: francesco.podesta03@gmail.com

   Simple matrix library with basic operations.

 */
public class Matrix {
    public int rows;
    public int cols;
   public float[][] data;
    public Matrix(int rows, int cols){    //  By calling Matrix m = new Matrix(2,3) it will create a matrix with custom size
        this.rows = rows;
        this.cols = cols;
        data = new float[rows][cols];
        for (int i = 0; i<rows;i++){
            for (int j = 0; j<cols;j++){
                data[i][j] = 0;
            }
        }
    }


    public Matrix(){         // By calling Matrix m = new Matrix() it will create a matrix with a standard size 2 x 2
        this.rows = 2;
        this.cols = 2;
        data = new float[rows][cols];
        for (int i = 0; i<rows;i++){
            for (int j = 0; j<cols;j++){
                data[i][j] = 0;
            }
        }
    }

    /* add a number n to a matrix m     eg: Matrix.add(m,2) ---->
     [[0, 0, 0]
      [0, 0, 0]   +  2  =
      [0, 0, 0]]


     [[2, 2, 2]
      [2, 2, 2]
      [2, 2, 2]]


    */

    public static Matrix fromArray(float[] input_array){
        Matrix m = new Matrix(input_array.length,1);
        Main main = new Main();
        for (int i = 0; i<m.rows;i++){
            for (int j = 0; j<m.cols;j++){
                m.data[i][j] = input_array[i];
            }
        }

        return m;
    }
    public static void add(Matrix matrix, float n){
        for (int i = 0; i<matrix.rows;i++){
            for (int j = 0; j<matrix.cols;j++){
                matrix.data[i][j] += n;
            }
        }
    }
    public static  void subtract(Matrix matrix, float n){
        for (int i = 0; i<matrix.rows;i++){
            for (int j = 0; j<matrix.cols;j++){
                matrix.data[i][j] -= n;
            }
        }

    }
   // apply your function to all numbers in the matrix
    public static void map(Matrix matrix, Callable func){
        for (int i =0; i<matrix.rows;i++){
            for (int j = 0; j<matrix.cols;j++){
                float old = matrix.data[i][j];
                matrix.data[i][j] =sig(old); // replace it with your own function
            }
        }


    }
    // replace it with your own function
    public static float sig(float x) {
        return (float)(1 / (1+Math.exp(-x)) );
    }
   // from matrix to array
    public static float[] toArray(Matrix matrix){
        float[] res = new float[matrix.rows*matrix.cols];
        int c = 0;
        for (int i = 0; i<matrix.rows;i++){
            for (int j =0; j<matrix.cols;j++){
                c++;
                res[c-1] = matrix.data[i][j];
            }

        }
        return res;
    }



    /* multiply a number n with a matrix m     eg: Matrix.multiply(m,2) ---->
  [[1, 1, 1]
   [1, 1, 1]   *  2  =
   [1, 1, 1]]


  [[2, 2, 2]
   [2, 2, 2]
   [2, 2, 2]]


 */
    public static void multiply(Matrix matrix, float n){
        for (int i = 0; i<matrix.rows;i++){
            for (int j = 0; j<matrix.cols;j++){
                matrix.data[i][j] *= n;

            }
        }
    }
    /* put random numbers in a matrix m     eg: Matrix.randomize(m) ---->

      [[2, 8, 3]
       [3, 7, 2]
       [5, 2, 9]]


     */
    public static void randomize(Matrix matrix){
        for (int i = 0; i<matrix.rows;i++){
            for (int j = 0; j<matrix.cols;j++){
                matrix.data[i][j] = Rand(10,0);
            }
        }
    }

    /* add a matrix m1 to matrix m2     eg: Matrix.mAdd(m1,m2) ---->
     [[1, 1]      [[8, 8]
      [1, 1]]   +  [10, 3]]     =

     [[9, 9]
      [11, 4]]



     */
    public static  Matrix copy(Matrix matrix){
        Matrix matrix1 = new Matrix(matrix.rows,matrix.cols);
        for (int i = 0; i<matrix.rows;i++){
            for (int j =0; j<matrix.cols;j++){
                matrix1.data[i][j] = matrix.data[i][j];
            }
        }
        return matrix1;
    }
    public  static Matrix mAdd(Matrix m1, Matrix m2){
        Matrix m3 = new Matrix(m1.rows,m1.cols);
        if(m1.cols == m2.cols && m1.rows==m2.rows){

            for (int i = 0; i<m3.rows;i++){
                for (int j = 0; j<m3.cols;j++){
                    m3.data[i][j] = m1.data[i][j]+m2.data[i][j];
                }

            }

        }
        else {
            System.out.println("Error, matrix must have the same size!");
        }
        return m3;

    }

    public  static Matrix mSubtract(Matrix m1, Matrix m2){
        Matrix m3 = new Matrix(m1.rows,m1.cols);
        if(m1.cols == m2.cols && m1.rows==m2.rows){

            for (int i = 0; i<m3.rows;i++){
                for (int j = 0; j<m3.cols;j++){
                    m3.data[i][j] = m1.data[i][j]-m2.data[i][j];
                }

            }

        }
        else {
            System.out.println("Error, matrix must have the same size!");
        }
        return m3;

    }
    // used to multiply matrices
    private static float vectorMultiply(float[] vec1, float[][] m2,int rows, int index){
        float res = 0;
        float[] vec2 = new float[vec1.length];
        for (int i = 0; i<rows;i++){
            vec2[i] = m2[i][index];

        }

        for (int k =0; k<vec2.length;k++){
            res = res + vec1[k]*vec2[k];
        }



        return res;
    }
    /* multiply a matrix m1 with matrix m2     eg: Matrix.mMultiply(m1,m2) ---->
    [[7, 1]      [[8, 8]
     [4, 3]]   *  [10, 3]]     =

    [[66, 59]
     [62, 41]]



    */
    public  static Matrix mMultiply(Matrix m1, Matrix m2){
        Matrix m3 = new Matrix(m1.rows,m2.cols);
        if(m1.cols == m2.rows && m1.rows==m2.cols){

            for (int i = 0; i<m3.rows;i++){
                for (int j = 0; j<m3.cols;j++){
                   m3.data[i][j] = vectorMultiply(m1.data[i],m2.data,m2.rows,j);
                }

            }

        }
        else {
            System.out.println("Error, matrix must have the size flipped!");
        }
        return m3;

    }
    /* flip size of a matrix     eg: Matrix.transpose(m1) ---->
    [[7, 1]
     [2, 5]
     [4, 3]]    =

    [[7, 2, 4]
     [1, 5, 3]]



    */
    public  static  Matrix transpose(Matrix matrix){
        Matrix mxRes = new Matrix(matrix.cols,matrix.rows);
        for (int i = 0; i<matrix.rows;i++){
            for (int j = 0; j<matrix.cols;j++){
                mxRes.data[j][i] = matrix.data[i][j];
            }
        }
        return mxRes;
    }


   // rand function used to randomize a matrix
    static private int Rand(int max, int min){
        int range = max - min + 1;

        int res =  (int) ( Math.random()*range)+min;

        if(res==0){
            res = res+1;
        }
        return res;
    }
    // print a matrix in the console for debugging
    static public void PrintMatrix(float[][] data, int rows, int cols){
        for (int i = 0; i<rows;i++){
            for (int j = 0; j<cols;j++){
                System.out.print(data[i][j]);
                System.out.print(" ");

            }
            System.out.println(" ");
        }

    }



}

