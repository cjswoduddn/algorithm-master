package programmers.kakao2020intern.n2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    List<String> parse = new ArrayList<>();
    List<String> ops = new ArrayList<>();

    public long solution(String exp){
        Pattern p = Pattern.compile("([0-9]+|[\\+\\*\\-])");
        Matcher mat = p.matcher(exp);


        while(mat.find()){
            parse.add(mat.group());
        }

        long ret = process("+-*");
        ret = Math.max(ret, process("+*-"));
        ret = Math.max(ret, process("-+*"));
        ret = Math.max(ret, process("-*+"));
        ret = Math.max(ret, process("*-+"));
        ret = Math.max(ret, process("*+-"));
        return ret;
    }

    long process(String str){
        for(int i = 0; i < 3; i++){
            if(i == 0) ops = new ArrayList<>(parse);
            innerProcess(str.charAt(i));
        }
        return Long.parseLong(ops.get(0));
    }

    private void innerProcess(char op) {
        List<String> inOps = new ArrayList<>();
        for(int i = 0; i < ops.size(); i++){
            try{
                Long.parseLong(ops.get(i));
                inOps.add(ops.get(i));
            }catch (NumberFormatException e){
                if(ops.get(i).charAt(0) == op){
                    inOps.set(inOps.size()-1, cProcess(inOps.get(inOps.size()-1), ops.get(i+1), op));
                    i++;
                }else{
                    inOps.add(ops.get(i));
                }
            }
        }
        ops = new ArrayList<>(inOps);
    }

    private String cProcess(String num1, String num2, char op) {
        long n1 = Long.parseLong(num1);
        long n2 = Long.parseLong(num2);

        switch (op){
            case '+':
                return String.valueOf(n1+n2);
            case '-':
                return String.valueOf(n1-n2);
            case '*':
                return String.valueOf(n1*n2);
        }
        return null;
    }

}
