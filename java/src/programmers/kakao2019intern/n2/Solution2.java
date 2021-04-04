package programmers.kakao2019intern.n2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 38 ~ 49
public class Solution2 {

    public int[] solution(String s){
        Pattern pattern = Pattern.compile("[\\d]+");
        Matcher matcher = pattern.matcher(s);

        Map<Integer, Integer> map = new HashMap<>();
        while(matcher.find()){
            String str = matcher.group();
            int key = Integer.parseInt(str);
            if(!map.containsKey(key)) map.put(key, 0);
            map.put(key, map.get(key)+1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        int[] ans = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            ans[ans.length-1-i] = list.get(i).getKey();
        }

        return ans;
    }
}
