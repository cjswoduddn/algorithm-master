package programmers.kakao2020intern.n1;


public class Solution {

    static int getLeftRight(String hand){
        if(hand.compareTo("left") == 0) return 0;
        return 1;
    }

    public String solution(int[] numbers, String hand){
        int leftRight = getLeftRight(hand);
        StringBuilder ret = new StringBuilder();

        int posLeft = 11;
        int posRight = 12;

        for(int ptr : numbers){
            if(ptr == 1 || ptr == 4 || ptr == 7){
                ret.append('L');
                posLeft = ptr;
            }else if(ptr == 3 || ptr == 6 || ptr == 9){
                ret.append('R');
                posRight = ptr;
            }else{
                int leftTmp = converter(posLeft);
                int rightTmp = converter(posRight);
                int ptrTmp = converter(ptr);

                int leftDis = Math.abs(ptrTmp-leftTmp);
                if(posLeft == 1 || posLeft == 4 || posLeft == 7 || posLeft == 11) leftDis++;
                int rightDis = Math.abs(ptrTmp-rightTmp);
                if(posRight == 1 || posRight == 4 || posRight == 7 || posRight == 11) rightDis++;

                if(leftDis > rightDis){
                    ret.append('R');
                    posRight = ptr;
                }else if(leftDis < rightDis){
                    ret.append('L');
                    posLeft = ptr;
                }else{
                    if(leftRight == 0){
                        ret.append('L');
                        posLeft = ptr;
                    }else{
                        ret.append('R');
                        posRight = ptr;
                    }
                }
            }
        }
        return ret.toString();
    }

    static int converter(int num){
        if(num == 1 || num == 2 || num == 3) return 1;
        if(num == 4 || num == 5 || num == 6) return 2;
        if(num == 7 || num == 8 || num == 9) return 3;
        return 4;
    }
}
