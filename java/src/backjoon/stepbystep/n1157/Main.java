package backjoon.stepbystep.n1157;
import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = new int[26];
        Arrays.fill(arr, 0);

        String str = br.readLine();
        String ustr = str.toUpperCase();

        for(char c : ustr.toCharArray()){
            arr[c-'A']++;
        }

        int max = 0;
        for(int ptr : arr){
            max = max > ptr ? max : ptr;
        }

        int cnt = 0;
        for(int ptr : arr){
            if(ptr == max) cnt++;
        }

        if(cnt > 1){
            bw.write("?");
        }else{
            for(int i = 0; i < 26; i++){
                if(arr[i] == max){
                    bw.write(((char)(i+'A')+"\n"));
                }
            }
        }
        bw.flush();
    }

}
