package backjoon.stepbystep.util;

class Pair implements Comparable<Pair>{
    private int x, y;


    @Override
    public int compareTo(Pair o) {
        if(this.x > o.x) return 1;
        else if(this.x < o.x) return -1;

        if(this.y > o.y) return 1;
        else if(this.y > o.y) return -1;
        return 0;
    }
}
