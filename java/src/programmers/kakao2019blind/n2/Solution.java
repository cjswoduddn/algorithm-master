package programmers.kakao2019blind.n2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 55 ~ 06
public class Solution {

    static class Nod{
        String uid;
        String message;

        public Nod(String uid, String message) {
            this.uid = uid;
            this.message = message;
        }
    }

    public String[] solution(String[] record){

        List<Nod> nods = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        for(int i = 0; i < record.length; i++){
            String[] strs = record[i].split("\\s");
            if(strs[0].equals("Enter")){
                map.put(strs[1], strs[2]);
                nods.add(new Nod(strs[1], "님이 들어왔습니다."));
            }else if(strs[0].equals("Leave")){
                nods.add(new Nod(strs[1], "님이 나갔습니다."));
            }else if(strs[0].equals("Change")){
                map.put(strs[1], strs[2]);
            }
            System.out.println(map.get(strs[1]));
        }

        String[] ans = new String[nods.size()];
        for(int i = 0; i < nods.size(); i++){
            ans[i] = map.get(nods.get(i).uid)+nods.get(i).message;
            System.out.println(ans[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"});
    }
}
