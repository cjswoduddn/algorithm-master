package backjoon.stepbystep.n10809;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        int[] arr = new int[26];
        Arrays.fill(arr, -1);

        for(int i = 0; i < str.length(); i++){
            if(arr[str.charAt(i)-'a'] != -1) continue;
            arr[str.charAt(i)-'a'] = i;
        }

        for(int ptr : arr){
            bw.write(ptr+" ");
        }

        bw.flush();
    }

}
