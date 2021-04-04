package programmers.kakao2020blind.n1;


public class Solution {

    public int solution(String s){
        int ans = s.length();

        for(int i = 1; i <= s.length(); i++){
            ans = Math.min(ans, getTmp(s, i));
        }
        return ans;
    }

    int getTmp(String s, int cnt){
        int ret = 0;
        int count = 1;
        int idx = 0;
        while(idx+cnt*2 <= s.length()){
            if(s.substring(idx, idx+cnt).equals(s.substring(idx+cnt, idx+cnt*2))){
                count++;
            }else{
                ret += cnt;
                if(count != 1){
                    System.out.println(count);
                    ret += String.valueOf(count).length();
                    count = 1;
                }
            }
            idx += cnt;
        }
        ret += cnt;
        if(count != 0){
            ret += String.valueOf(count).length();
            count = 0;
        }
        idx += cnt;
        ret += s.length()-idx;
        return ret;
    }

}

