package programmers.kakao2019intern.n3;

import java.util.*;

// 50 ~
public class Solution2 {

    int usize;
    int bsize;
    List<Integer> arr = new ArrayList<>();
    boolean[] check;
    Set<String> set = new HashSet<>();

    public int solution(String[] uid, String[] bid){
        usize = uid.length;
        bsize = bid.length;
        check = new boolean[usize];
        Arrays.fill(check, false);

        convertBidToRegex(bid);
        permutation(uid, bid, 0);
        return set.size();
    }

    private void permutation(String[] uid, String[] bid, int cnt) {
        if(cnt == bsize){
            if(validation(uid, bid)){
                char[] key = new char[arr.size()];
                for(int i = 0; i < arr.size(); i++){
                    key[i] = (char)(arr.get(i)+'0');
                }
                Arrays.sort(key);
                set.add(new String(key));
            }
            return;
        }

        for(int i = 0; i < usize; i++){
            if(check[i]) continue;
            check[i] = true;
            arr.add(i);
            permutation(uid, bid,cnt+1);
            check[i] = false;
            arr.remove(arr.size()-1);
        }
    }

    private boolean validation(String[] uid, String[] bid) {
        for(int i = 0; i < bsize; i++){
            System.out.println(uid[arr.get(i)]);
        }
        System.out.println(" ");
        int count = 0;
        for(int i = 0; i < arr.size(); i++){
            if(uid[arr.get(i)].matches(bid[i])) count++;
        }
        if(count == bsize) return true;
        return false;
    }

    private void convertBidToRegex(String[] bid) {
        for(int i = 0; i < bsize; i++){
            bid[i] = bid[i].replace('*', '.');
        }
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.solution(
                new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"fr*d*", "*rodo", "******", "******"}
        );
    }
}
