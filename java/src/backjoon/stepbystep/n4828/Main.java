package backjoon.stepbystep.n4828;

import static java.lang.System.in;
import static java.lang.System.out;
import java.io .*;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));

    // &xax; <></>

    public static void main(String[] args) throws Throwable {

        String str;
        String incoder = "&(lt|gt|amp);";
        while ((str = br.readLine()) != null) {
            try {
                str = str.replaceAll(incoder, "");
                str = checkTagAndReplace(str);
                str = checkHex(str);
                for(int i = 0; i < str.length(); i++){
                    char c = str.charAt(i);
                    if(c == '<' || c == '>' || c == '&' || c < 32 || c > 127)
                        throw new InValidException();
                }
                bw.write("valid\n");
            }catch (InValidException ex){
                bw.write("invalid\n");
            }
        }

        bw.flush();
    }

    private static String checkHex(String str) {
        Pattern pattern = Pattern.compile("&x([\\da-fA-F]*);");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            if(matcher.group(1).length() == 0 || matcher.group(1).length()%2 == 1){
                throw new InValidException();
            }
        }
        return str.replaceAll(pattern.toString(), "");
    }

    private static String checkTagAndReplace(String str) {
        Stack<String> stack = new Stack<>();
        Pattern pattern = Pattern.compile("<(/?)([a-z0-9]+)(/?)>");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            if(matcher.group(1).length() > 0 && matcher.group(3).length() > 0){
                throw new InValidException();
            }
            if(matcher.group(1).length() > 0){
                if(stack.isEmpty() || stack.pop().compareTo(matcher.group(2)) != 0){
                    throw new InValidException();
                }
            }else if(matcher.group(1).length() == 0 && matcher.group(3).length() == 0){
                stack.push(matcher.group(2));
            }
        }
        if(!stack.isEmpty()) throw new InValidException();
        return str.replaceAll(pattern.toString(), "");
    }

    static class InValidException extends RuntimeException{
    }

}
