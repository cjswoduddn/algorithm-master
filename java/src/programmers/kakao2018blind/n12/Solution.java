package programmers.kakao2018blind.n12;

import java.util.Arrays;

public class Solution {

    static class Trie{
        int count;
        Trie[] tries = new Trie[26];

        public Trie() {
            count = 0;
            Arrays.fill(tries, null);
        }

        public int search(String str, int idx){
            if(this.count == 1 || str.length() == idx) return 0;
            int index = str.charAt(idx)-'a';
            return this.tries[index].search(str, idx+1)+1;
        }

        public void insert(String str, int idx){
            count++;
            if(idx == str.length()){
                return;
            }

            int index = str.charAt(idx)-'a';
            if(this.tries[index] == null) this.tries[index] = new Trie();
            this.tries[index].insert(str, idx+1);
        }
    }

    Trie root = new Trie();

    public int solution(String[] words){

        int ret = 0;
        for(int i = 0; i < words.length; i++){
            root.insert(words[i], 0);
        }
        for(int i = 0; i < words.length; i++){
            ret += root.search(words[i], 0);
            System.out.println(ret);
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int solution1 = solution.solution(new String[]{"go", "gone", "guild"});
        System.out.println("solution1 = " + solution1);
    }
}
