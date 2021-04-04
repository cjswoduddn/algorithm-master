package backjoon.stepbystep.n1316;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());
        int ret = 0;

        for(int i = 0; i < k; i++){
            String str = br.readLine();
            String regex = "([a-z])\\1*";
            Pattern p = Pattern.compile(regex);
            Matcher matcher = p.matcher(str);
            List<String> brr = new ArrayList<>();
            while(matcher.find()){
                brr.add(matcher.group());
            }

            String[] strs = brr.toArray(new String[brr.size()]);

            int[] arr = new int[26];
            Arrays.fill(arr, 0);
            for(String pc : strs){
                arr[pc.charAt(0)-'a']++;
            }
            boolean flag = true;
            for(int ptr : arr){
                if(ptr > 1) flag = false;
            }
            if(flag) ret++;
        }

        bw.write(ret+"\n");
        bw.flush();
    }

}

/*
spilt는 정규표현식을 기준으로 파싱을 하는 기법, 즉 정규표현식은 제외된다 ㅠㅠ
 */
