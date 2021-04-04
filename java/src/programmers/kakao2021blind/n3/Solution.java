package programmers.kakao2021blind.n3;

import java.util.*;

// 26~
public class Solution {

    Map<String, List<Integer>> maps = new HashMap<>();
    boolean[] check = new boolean[4];

    public int[] solution(String[] info, String[] query){

        Arrays.fill(check, false);
        for(int i = 0; i < info.length; i++){
            String[] strs = info[i].split("\\s");
            for(int j = 0; j <= 4; j++){
                permutation(strs, 0, 0, j);
            }
        }

        for(List<Integer> list : maps.values()){
            list.sort(Integer::compareTo);
        }

        List<Integer> answers = new ArrayList<>();
        for(int i = 0; i < query.length; i++){
            String[] strs = query[i].split("\\s(and\\s)?");
            String key = getKey(strs);
            if(!maps.containsKey(key)) answers.add(0);
            List<Integer> list = maps.get(key);
            answers.add(list.size()-lowerBound(list, 0, list.size() - 1, Integer.parseInt(strs[4])));
        }

        int[] ans = new int[answers.size()];
        for(int i = 0; i < answers.size(); i++){
            ans[i] = answers.get(i);
        }
        return ans;
    }

    private int lowerBound(List<Integer> list, int s, int e, int value) {
        if(s > e) return list.size();
        int mid = (s+e)/2;
        if(list.get(mid) >= value){
            int tmp = lowerBound(list, s, mid-1, value);
            return tmp < mid ? tmp : mid;
        }
        return lowerBound(list, mid+1, e, value);
    }

    private String getKey(String[] strs) {
        String ret = "";
        for(int i = 0; i < 4; i++) ret += strs[i];
        return ret;
    }

    private void permutation(String[] strs, int s, int cnt, int tar) {
        if(cnt == tar){
            String key = "";
            for(int i = 0; i < check.length; i++){
                if(check[i] == false) key += "-";
                else key += strs[i];
            }

            if(!maps.containsKey(key)) maps.put(key, new ArrayList<>());
            maps.get(key).add(Integer.parseInt(strs[4]));
            return;
        }

        for(int i = s; i < check.length; i++){
            check[i] = true;
            permutation(strs, i + 1, cnt + 1, tar);
            check[i] = false;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] info = new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        solution.solution(info, query);
    }
}