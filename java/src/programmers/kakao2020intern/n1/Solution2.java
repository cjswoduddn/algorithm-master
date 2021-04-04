package programmers.kakao2020intern.n1;

public class Solution2 {

    public String solution(int[] nums, String hand){
        String ret = "";
        int curLeft = 9;
        int curRight = 11;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0) nums[i] = 10;
            else nums[i]--;
        }

        for(int i = 0; i < nums.length; i++){
            int k = nums[i];
            if(k%3 == 0){
                ret += "L";
                curLeft = k;
            }else if(k%3 == 2){
                ret += "R";
                curRight = k;
            }else{
                int dl = Math.abs(curLeft/3-k/3);
                if(curLeft%3 == 0) dl++;
                int dr = Math.abs(curRight/3-k/3);
                if(curRight%3 == 2) dr++;
                if(dl == dr){
                    if(hand.equals("right")){
                        ret += "R";
                        curRight = k;
                    }else{
                        ret += "L";
                        curLeft = k;
                    }
                }else if(dl < dr){
                    ret += "L";
                    curLeft = k;
                }else if(dl > dr){
                    ret += "R";
                    curRight = k;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        String ret = solution.solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right");
        System.out.println(ret);
    }
}
