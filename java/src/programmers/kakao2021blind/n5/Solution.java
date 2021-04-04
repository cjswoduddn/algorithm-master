package programmers.kakao2021blind.n5;

import java.util.Arrays;

// 35 ~
public class Solution {

    int[] values;

    public String solution(String ptime, String adtime, String[] logs){
        int pTime = convertStringToInteger(ptime);
        values = new int[pTime+1];
        Arrays.fill(values, 0);

        int adTime = convertStringToInteger(adtime);

        for(int i = 0; i < logs.length; i++){
            String[] se = logs[i].split("-");
            int s = convertStringToInteger(se[0]);
            int e = convertStringToInteger(se[1]);
            for(int j = s; j <= e; j++){
                values[j]++;
            }
        }

        // 126001
        int ans = 0;
        int ret = 0;
        for(int i = 0; i < adTime+1; i++) ret += values[i];
        int tmp  = ret;
        for(int i = 0; i+adTime+1 < values.length; i++){
            tmp = tmp-values[i]+values[i+adTime+1];
//            System.out.println(convertIntegerToString(i)+"-"+convertIntegerToString(i+adTime+1)+" "+tmp);
            if(tmp > ret){
                ret = tmp;
                ans = i+1;
            }
        }
        return convertIntegerToString(ans);
    }

    public int convertStringToInteger(String str){
        String[] strs = str.split(":");
        int ret = 3600*Integer.parseInt(strs[0]);
        ret += 60 * Integer.parseInt(strs[1]);
        ret += Integer.parseInt(strs[2]);
        return ret;
    }

    public String convertIntegerToString(int value){
        String hour = String.valueOf(value/3600);
        value %= 3600;
        String min = String.valueOf(value/60);
        String sec = String.valueOf(value%60);
        if(hour.length() == 1) hour = "0"+hour;
        if(min.length() == 1) min = "0"+min;
        if(sec.length() == 1) sec = "0"+sec;
        return hour+":"+min+":"+sec;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String ret = solution.solution("02:03:55", "00:14:15", new String[]{"01:20:15-01:45:14", "00:25:50-00:48:29", "00:40:31-01:00:00", "01:37:44-02:02:30", "01:30:59-01:53:29"});
        System.out.println(ret);
    }
}
