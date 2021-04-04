package programmers.kakao2021blind.n2;

import java.util.*;

// 12 ~
public class Solution2 {

    List<String> orders = new ArrayList<>();
    List<Integer> course = new ArrayList<>();
    List<Map<String, Integer>> maps = new ArrayList<>();

    List<Integer> pems = new ArrayList<>();

    public String[] solution(String[] orders, int[] course){
        init(orders, course);
        return solve();
    }

    private String[] solve() {
        for(int i = 0; i < course.size(); i++){
            for(int j = 0; j < orders.size(); j++){
                permutation(0, 0, i, orders.get(j));
            }
        }

        List<String> ans = new ArrayList<>();
        for(int i = 0; i < maps.size(); i++){
            Map<String, Integer> map = maps.get(i);
            int max = 0;
            for(int value : map.values()){
                max = Math.max(max, value);
            }

            if(max == 1) continue;
            for(Map.Entry<String, Integer> entry : map.entrySet()){
                if(entry.getValue() == max) ans.add(entry.getKey());
            }
        }

        ans.sort(String::compareTo);
        String[] ret = new String[ans.size()];
        for(int i = 0; i < ans.size(); i++){
            ret[i] = ans.get(i);
        }
        return ret;
    }

    private void permutation(int s, int cnt, int tar, String str) {
        if(cnt == course.get(tar)){
            String ret = "";
            for(int i = 0; i < course.get(tar); i++){
                ret += str.charAt(pems.get(i));
            }
            maps.get(tar).putIfAbsent(ret, 0);
            maps.get(tar).put(ret, maps.get(tar).get(ret)+1);
            return;
        }

        for(int i = s; i < str.length(); i++){
            pems.add(i);
            permutation(i+1, cnt+1, tar, str);
            pems.remove(pems.size()-1);
        }
    }

    private void init(String[] orders, int[] course) {
        for(int i = 0; i < orders.length; i++){
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            this.orders.add(new String(chars));
        }

        for(int i = 0; i < course.length; i++){
            this.course.add(course[i]);
            maps.add(new HashMap<>());
        }
    }
}
