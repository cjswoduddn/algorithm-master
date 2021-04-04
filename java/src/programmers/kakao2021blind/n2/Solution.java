package programmers.kakao2021blind.n2;

import java.util.*;

// 40 ~
public class Solution {

    List<Map<String, Integer>> maps = new ArrayList<>();
    List<Integer> arr = new ArrayList<>();

    public String[] solution(String[] orders, int[] course){
        for(int i = 0; i < orders.length; i++){
            orders[i] = convertSortString(orders[i]);
        }
        for(int i = 0; i < course.length; i++){
            maps.add(new HashMap<>());
            for(int j = 0; j < orders.length; j++){
                permutation(orders[j], course[i], maps.get(i), 0, 0);
            }
        }

        List<String> answers = new ArrayList<>();
        for(int i = 0; i < course.length; i++){
            int best = 0;
            for(Integer value : maps.get(i).values()){
                best = Math.max(best, value);
            }
            if(best < 2) continue;
            for(Map.Entry<String, Integer> entry : maps.get(i).entrySet()){
                if(entry.getValue() == best) answers.add(entry.getKey());
            }
        }

        answers.sort(String::compareTo);
        String[] ans = new String[answers.size()];
        for(int i = 0; i < ans.length; i++){
            ans[i] = answers.get(i);
        }
        return ans;
    }

    private String convertSortString(String order) {
        char[] chars = order.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private void permutation(String order, int tar, Map<String, Integer> map, int s, int cnt) {
        if(cnt == tar){
            String ret = "";
            for(int i = 0; i < tar; i++){
                ret += order.charAt(arr.get(i));
            }
            if(!map.containsKey(ret)) map.put(ret, 0);
            map.put(ret, map.get(ret)+1);
            return;
        }

        for(int i = s; i < order.length(); i++){
            arr.add(i);
            permutation(order, tar, map, i + 1, cnt + 1);
            arr.remove(arr.size()-1);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4});
    }
}
