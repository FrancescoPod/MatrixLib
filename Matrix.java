public class Matrix {
    public int rows;
    public int cols;
   public float[][] data;
    public Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new float[rows][cols];
        for (int i = 0; i<rows;i++){
            for (int j = 0; j<cols;j++){
                data[i][j] = 0;
            }
        }
    }


    public Matrix(){
        this.rows = 2;
        this.cols = 2;
        data = new float[rows][cols];
        for (int i = 0; i<rows;i++){
            for (int j = 0; j<cols;j++){
                data[i][j] = 0;
            }
        }
    }


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

    public static void map(Matrix matrix){
        for (int i =0; i<matrix.rows;i++){
            for (int j = 0; j<matrix.cols;j++){
                float old = matrix.data[i][j];
                matrix.data[i][j] =sig(old);
            }
        }


    }
    public static Matrix mapInv(Matrix matrix){
        Matrix res = new Matrix(matrix.rows,matrix.cols);
        for (int i =0; i<matrix.rows;i++){
            for (int j = 0; j<matrix.cols;j++){
                float old = matrix.data[i][j];
                res.data[i][j] =devsig(old);
            }
        }
        return res;


    }
    //
    public static float sig(float x) {
        return (float)(1 / (1+Math.exp(-x)) );
    }
    public static float devsig(float x) {
        return (float)(x*(1-x));
    }
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



    public static void multiply(Matrix matrix, float n){
        for (int i = 0; i<matrix.rows;i++){
            for (int j = 0; j<matrix.cols;j++){
                matrix.data[i][j] *= n;

            }
        }
    }

    public static void randomize(Matrix matrix){
        for (int i = 0; i<matrix.rows;i++){
            for (int j = 0; j<matrix.cols;j++){
                matrix.data[i][j] = Rand(1,-1);
            }
        }
    }

    public static  Matrix copy(Matrix matrix){
        Matrix matrix1 = new Matrix(matrix.rows,matrix.cols);
        for (int i = 0; i<matrix.rows;i++){
            for (int j =0; j<matrix.cols;j++){
                matrix1.data[i][j] = matrix.data[i][j];
            }
        }
        return matrix1;
    }
    public  Matrix multiply(Matrix m2){
        Matrix m3 = new Matrix(this.rows,this.cols);
        for (int i = 0; i<m3.rows;i++){
            for (int j = 0; j<m3.cols;j++){
                m3.data[i][j] = this.data[i][j]*m2.data[i][j];
            }

        }
        return m3;
    }
    public  static Matrix add(Matrix m1, Matrix m2){
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

    public  static Matrix subtract(Matrix m1, Matrix m2){
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
    public  static Matrix multiply(Matrix m1, Matrix m2){
        Matrix m3 = new Matrix(m1.rows,m2.cols);


            for (int i = 0; i<m3.rows;i++){
                for (int j = 0; j<m3.cols;j++){
                   m3.data[i][j] = vectorMultiply(m1.data[i],m2.data,m2.rows,j);
                }

            }

        return m3;

    }

    public  static  Matrix transpose( Matrix matrix){
        Matrix mxRes = new Matrix(matrix.cols,matrix.rows);
        for (int i = 0; i<matrix.rows;i++){
            for (int j = 0; j<matrix.cols;j++){
                mxRes.data[j][i] = matrix.data[i][j];
            }
        }
        return mxRes;
    }


    static private int Rand(int max, int min){
        int range = max - min + 1;

        int res =  (int) ( Math.random()*range)+min;

        if(res==0){
            res = res+1;
        }
        return res;
    }
    static public void print(Matrix matrix){
        for (int i = 0; i<matrix.rows;i++){
            for (int j = 0; j<matrix.cols;j++){
                System.out.print(matrix.data[i][j]);
                System.out.print(" ");

            }
            System.out.println(" ");
        }

    }



}

