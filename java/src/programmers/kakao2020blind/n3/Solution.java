package programmers.kakao2020blind.n3;

import java.util.Arrays;

public class Solution {

    int[][] key1;
    int[][] key2;
    int[][] key3;
    int[][] key4;
    int msize;
    int ksize;
    int lsize;

    public boolean solution(int[][] key, int[][] lock){
        key1 = key;
        key2 = rotateKey(key1);
        key3 = rotateKey(key2);
        key4 = rotateKey(key3);

        ksize = key.length;
        lsize = lock.length;
        msize = ksize*2+lsize;
        int[][] map = new int[msize][msize];
        for(int i = 0; i < msize; i++){
            Arrays.fill(map[i], -1);
        }

        int lockCount = getLock(lock);
        for(int i = 0; i < lsize; i++){
            for(int j = 0; j < lsize; j++){
                map[i+ksize][j+ksize] = lock[i][j];
            }
        }

        for(int i = 0; i < msize-ksize; i++){
            for(int j = 0; j < msize-ksize; j++){
                if(checkKey(map, key1, i, j, lockCount)) return true;
                if(checkKey(map, key2, i, j, lockCount)) return true;
                if(checkKey(map, key3, i, j, lockCount)) return true;
                if(checkKey(map, key4, i, j, lockCount)) return true;
            }
        }
        return false;
    }

    private boolean checkKey(int[][] map, int[][] key, int x, int y, int lockCount) {
        int ret = 0;
        for(int i = 0; i < ksize; i++){
            for(int j = 0; j < ksize; j++){
                if(map[x+i][y+j] == -1) continue;
                if(map[x+i][y+j] == 0 && key[i][j] == 1) ret++;
                if(map[x+i][y+j] == 1 && key[i][j] == 1) return false;
            }
        }
        if(ret == lockCount) return true;
        return false;
    }

    private int getLock(int[][] lock) {
        int ret = 0;
        for(int i = 0; i < lock.length; i++){
            for(int j = 0; j < lock.length; j++){
                if(lock[i][j] == 0) ret++;
            }
        }
        return ret;
    }

    private int[][] rotateKey(int[][] key) {
        int[][] ret = new int[key.length][key.length];
        for(int i = 0; i < key.length; i++){
            for(int j = 0; j < key.length; j++){
                ret[key.length-1-j][i] = key[i][j];
            }
        }
        return ret;
    }
}
