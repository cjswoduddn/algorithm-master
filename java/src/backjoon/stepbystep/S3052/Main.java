package backjoon.stepbystep.S3052;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = new int[42];
        Arrays.fill(arr, 0);

        for(int i = 0; i < 10; i++){
            int value = Integer.parseInt(br.readLine());
            arr[value%42]++;
        }

        int ret = 0;
        for(int ptr : arr) {
            if (ptr != 0) ret++;
        }
        bw.write(ret+"\n");
        bw.flush();

    }
}
