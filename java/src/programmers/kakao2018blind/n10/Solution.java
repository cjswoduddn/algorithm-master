package programmers.kakao2018blind.n10;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    public int solution(String[] lines){
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();

        for(int i = 0; i < lines.length; i++){
            String[] strs = stringParser(lines[i]);
            int dtime = (int)(Double.parseDouble(strs[2])*1000);
            int etime = endParser(strs[1]);
            int stime = etime-dtime+1;
            start.add(stime);
            end.add(etime);
        }


        int ret = 0;
        for(int i = 0; i < lines.length; i++){
            int tmp = 1;
            int etime = end.get(i);
            int dtime = etime+1000;
            for(int j = i+1; j < lines.length; j++){
                if(dtime > start.get(j)) tmp++;
            }
            ret = Math.max(ret, tmp);
        }
        return ret;
    }

    private int endParser(String str) {
        String[] strs = str.split("[:\\.]");
        int ret = 3600*1000*Integer.parseInt(strs[0]);
        ret += 60*1000*Integer.parseInt(strs[1]);
        ret += 1000*Integer.parseInt(strs[2]);
        ret += Integer.parseInt(strs[3]);
        return ret;
    }

    private String[] stringParser(String line) {
        Pattern pattern = Pattern.compile("(.*)\\s([\\d]{2}:[\\d]{2}:[\\d]{2}\\.[\\d]{3})\\s(.*)s");
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()){
            return new String[]{matcher.group(1), matcher.group(2), matcher.group(3)};
        }
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        String[] strs = new String[]{
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"
        };
        Solution solution = new Solution();
        int ret = solution.solution(strs);
        System.out.println(ret);
    }
}
