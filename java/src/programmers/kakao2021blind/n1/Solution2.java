package programmers.kakao2021blind.n1;

// 53 ~
public class Solution2 {

    String newId;

    public String solution(String nid){
        init(nid);
        return solve();
    }

    private String solve() {
        String ret = newId.toLowerCase();
        ret = ret.replaceAll("[^a-z\\d\\-_\\.]", "");
        ret = ret.replaceAll("[\\.]{2,}", ".");
        ret = ret.replaceAll("^\\.", "");
        ret = ret.replaceAll("\\.$", "");
        if(ret.length() == 0) ret = "a";
        if(ret.length() > 15){
            ret = ret.substring(0, 15);
            ret = ret.replaceAll("\\.$", "");
        }
        if(ret.length() == 1) ret = ret+ret+ret;
        if(ret.length() == 2) ret = ret+ret.charAt(1);
        return ret;
    }

    private void init(String nid) {
        newId = nid;
    }
}
