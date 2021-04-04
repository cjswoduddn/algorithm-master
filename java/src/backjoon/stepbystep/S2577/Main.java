package backjoon.stepbystep.S2577;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int first = Integer.parseInt(br.readLine());
        int second = Integer.parseInt(br.readLine());
        int third = Integer.parseInt(br.readLine());

        String str = String.valueOf(first * second * third);
        int[] arr = new int[10];

        for(int i = 0; i < str.length(); i++)
            arr[str.charAt(i)-'0']++;

        for(int i = 0; i < 10; i++)
            bw.write(arr[i]+"\n");
        bw.flush();

    }
}
