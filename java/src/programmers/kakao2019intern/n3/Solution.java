package programmers.kakao2019intern.n3;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    static int[] arr;
    static boolean[] ck;
    static Set<String> set = new HashSet<>();

    static void recur(String[] uid, String[] bid, int cnt){
        if(cnt == bid.length){
            List<String> tmp = new ArrayList<>();
            for(int i = 0; i < bid.length; i++){
                if(!uid[arr[i]].matches(bid[i])) return ;
                else tmp.add(uid[arr[i]]);
            }
            tmp.sort(String::compareTo);
            set.add(convertTmp(tmp));
            return ;
        }

        int ret = 0;
        for(int i = 0; i < uid.length; i++){
            if(ck[i]) continue;
            ck[i] = true;
            arr[cnt] = i;
            recur(uid, bid, cnt+1);
            ck[i] = false;
        }
    }

    private static String convertTmp(List<String> tmp) {
        StringBuilder sb = new StringBuilder();
        for(String str : tmp){
            sb.append(str);
        }
        return sb.toString();
    }


    public int solution(String[] uid, String[] bid){
        for(int i = 0; i < bid.length; i++){
            bid[i] = convertBid(bid[i]);
        }
        arr = new int[bid.length];
        ck = new boolean[uid.length];
        recur(uid, bid, 0);
        return set.size();
    }

    private String convertBid(String s) {
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < sb.length(); i++){
            if(sb.charAt(i) == '*') sb.setCharAt(i, '.');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] uid = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] bid = new String[]{"fr*d*", "abc1**"};
        int ret = solution.solution(uid, bid);
        System.out.println(ret);
    }
}
