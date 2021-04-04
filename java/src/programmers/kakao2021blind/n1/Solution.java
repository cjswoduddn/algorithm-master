package programmers.kakao2021blind.n1;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 05 ~ 25
public class Solution {



    public String solution(String str){
        str = str.toLowerCase();
        str = stepTwo(str);
        str = stepThree(str);
        if(str.length() > 0 && str.charAt(0) == '.')
            str = str.substring(1);
        if(str.length() > 0 && str.charAt(str.length()-1) == '.')
            str = str.substring(0, str.length()-1);
        if(str.length() == 0) str = "a";
        if(str.length() > 15)
            str = str.substring(0, 15);
        if(str.length() > 0 && str.charAt(str.length()-1) == '.')
            str = str.substring(0, str.length()-1);
        if(str.length() == 1)
            str = str+str+str;
        if(str.length() == 2)
            str = str+str.charAt(str.length()-1);
        return str;
    }

    private String stepThree(String str) {
        String[] strs = str.split("[\\.]{2,}");
        String ret = "";
        for(int i = 0; i  < strs.length-1; i++){
            ret += strs[i]+".";
        }
        return ret+strs[strs.length-1];
    }

    private String stepTwo(String str) {
        String ret = "";
        Pattern compile = Pattern.compile("[0-9a-z-_\\.]");
        Matcher matcher = compile.matcher(str);
        while(matcher.find()) ret += matcher.group();
        return ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String ret = solution.solution("abcdefghijklnmoptqrsvwxyz");
        System.out.println(ret);

        String test = "ab hello hello";
        String[] hellos = test.split("hello");
        System.out.println(hellos.length);
        /*
        spilt는 매칭이 없다면 원문자열 하나를 반환하지만
        매칭이 딱되면 아무것도 반환하지 않는다.
         */

    }
}
