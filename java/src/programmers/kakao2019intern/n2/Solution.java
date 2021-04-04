package programmers.kakao2019intern.n2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    public int[] solution(String s){
        Pattern pat = Pattern.compile("[\\d]+");
        Matcher matcher = pat.matcher(s);

        Map<Integer, Integer> map = new HashMap<>();
        while(matcher.find()){
            System.out.println(matcher.group());
            int key = Integer.parseInt(matcher.group());
            if(!map.containsKey(key)){
                map.put(key, 0);
            }
            map.put(key, map.get(key)+1);
        }

        List<Integer> ans = new ArrayList<>(map.keySet());
        Collections.sort(ans, (i1, i2)->{
            return
                map.get(i1).compareTo(map.get(i2));
        });

        int[] ret = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++){
            ret[i] = ans.get(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
    }
}
