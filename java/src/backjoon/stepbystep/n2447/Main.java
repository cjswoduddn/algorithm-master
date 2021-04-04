package backjoon.stepbystep.n2447;
import java.io.*;

public class Main {

    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        map = new int[size][size];

        recur(size, 0, 0);
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(map[i][j] == 1) bw.write("*");
                else bw.write(" ");
            }
            bw.write("\n");
        }
        bw.flush();
    }

    static void recur(int size, int x, int y){
        if(size == 1){
            map[x][y] = 1;
            return;
        }

        int nsize = size/3;
        recur(nsize, x, y);
        recur(nsize, x+nsize, y);
        recur(nsize, x+2*nsize, y);
        recur(nsize, x, y+nsize);
        recur(nsize, x, y+2*nsize);
        recur(nsize, x+nsize, y+2*nsize);
        recur(nsize, x+2*nsize, y+nsize);
        recur(nsize, x+2*nsize, y+2*nsize);
    }

}
