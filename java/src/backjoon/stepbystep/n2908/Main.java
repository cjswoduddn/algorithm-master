package backjoon.stepbystep.n2908;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        String str1 = st.nextToken();
        String str2 = st.nextToken();

        String rstr1 = new StringBuilder(str1).reverse().toString();
        String rstr2 = new StringBuilder(str2).reverse().toString();

        if(rstr1.compareTo(rstr2) > 0){
            bw.write(rstr1);
        }else{
            bw.write(rstr2);
        }
        bw.flush();
    }

}
