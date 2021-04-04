package programmers.kakao2018blind.n6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    static class Nod{
        int during;
        int idx;
        String name;

        public Nod(int during, int idx, String name) {
            this.during = during;
            this.idx = idx;
            this.name = name;
        }
    }

    public String solution(String m, String[] music){

        List<Nod> list = new ArrayList<>();
        m = convertStr(m);

        for(int i = 0; i < music.length; i++){
            String[] strs = music[i].split(",");
            int during = getDuring(strs[0], strs[1]);
            String str = getStr(during, convertStr(strs[3]));
            if(isMatch(str, m)) list.add(new Nod(during, i, strs[2]));
        }

        Collections.sort(list, (i1, i2)->{
            if(i1.during == i2.during) {
                if(i1.idx > i2.idx) return 1;
                return -1;
            }
            if(i1.during > i2.during) return 1;
            return -1;
        });
        if(list.size() == 0) return "(None)";
        return list.get(0).name;
    }

    private boolean isMatch(String tar, String reg) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(tar);
        return matcher.find();
    }

    private String convertStr(String str) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("[A-Z]#?");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            if(matcher.group().length() > 1){
                sb.append(matcher.group().charAt(0)-'A'+'a');
            }else{
                sb.append(matcher.group());
            }
        }
        return sb.toString();
    }

    private String getStr(int during, String str) {
        StringBuilder sb = new StringBuilder();
        while(during > 0){
            if(during/str.length() > 0){
                during -= str.length();
                sb.append(str);
            }else{
                sb.append(str.substring(0, during));
                break;
            }
        }
        return sb.toString();
    }

    private int getDuring(String str, String str1) {
        String[] s1 = str.split(":");
        String[] s2 = str1.split(":");
        int n1 = Integer.parseInt(s1[0])*60+Integer.parseInt(s1[1]);
        int n2 = Integer.parseInt(s2[0])*60+Integer.parseInt(s2[1]);
        return n2-1;
    }
}
