package backjoon.stepbystep.S10818;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Throwable {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Integer k = Integer.valueOf(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> arr = new ArrayList<>();
        while(st.hasMoreTokens()){
           arr.add(Integer.valueOf(st.nextToken()));
        }

        arr.sort(Integer::compareTo);
        System.out.println(arr.get(0));
        System.out.println(arr.get(arr.size() - 1));
    }
}
