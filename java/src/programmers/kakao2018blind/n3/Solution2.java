package programmers.kakao2018blind.n3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 00 ~ 23
public class Solution2 {

    String str1;
    String str2;

    public int solution(String str1, String str2){
        init(str1, str2);
        return solve();
    }

    private int solve() {
        List<String> strs1 = getStrs(str1);
        List<String> strs2 = getStrs(str2);
        if(strs1.size()+strs2.size() == 0) return 65536;
        Map<String, Integer> map = new HashMap<>();
        for(String str : strs1){
            map.putIfAbsent(str, 0);
            map.put(str, map.get(str)+1);
        }

        int sameSet = 0;
        for(String str : strs2){
            if(map.containsKey(str)){
                int count = map.get(str);
                if(count > 0){
                    sameSet++;
                    map.put(str, count-1);
                }
            }
        }
        return (int)(sameSet/(double)(strs1.size()+strs2.size()-sameSet)*65536);
    }

    private List<String> getStrs(String str) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < str.length()-1; i++){
            char c = str.charAt(i);
            char nc = str.charAt(i+1);
            if(c < 'a' || c > 'z') continue;
            if(nc < 'a' || nc > 'z') continue;
            list.add(String.valueOf(c)+nc);
        }
        return list;
    }

    private void init(String str1, String str2) {
        this.str1 = str1.toLowerCase();
        this.str2 = str2.toLowerCase();
    }
}
