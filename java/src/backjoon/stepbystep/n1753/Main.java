package backjoon.stepbystep.n1753;

import static java.lang.System.in;
import static java.lang.System.out;
import java.io .*;
import java.util.*;

public class Main {

    static class Pair<P, K>{
        P x;
        K y;

        public Pair(P x, K y) {
            this.x = x;
            this.y = y;
        }
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer st;

    public static void main(String[] args) throws Throwable {
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<List<Pair<Integer, Integer>>> graph = new ArrayList<>();
        for(int i = 0; i <= V; i++){
            graph.add(new ArrayList<>());
        }

        int snod = Integer.parseInt(br.readLine());
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Pair<>(v, d));
        }

        int[] dist = getDijstra(graph, snod);
        for(int i = 0; i < dist.length; i++){
            if(dist[i] == Integer.MAX_VALUE) bw.write("INF\n");
            else bw.write(dist[i]+"\n");
        }
        bw.flush();
    }

    static class Nod {
        int u, d;

        public Nod(int u, int d) {
            this.u = u;
            this.d = d;
        }

    }

    private static int[] getDijstra(List<List<Pair<Integer, Integer>>> graph, int snod) {
        PriorityQueue<Nod> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.d));

        int[] dist = new int[graph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[snod] = 0;

        pq.add(new Nod(snod, 0));

        while(!pq.isEmpty()){
            Nod cur = pq.poll();
            int cnod = cur.u;
            int cdis = cur.d;

            List<Pair<Integer, Integer>> list = graph.get(cnod);
            for(int i = 0; i < list.size(); i++){
                int nnod = list.get(i).x;
                int ndis = list.get(i).y;
                if(dist[nnod] < cdis+ndis) continue;
                dist[nnod] = cdis+ndis;
                pq.add(new Nod(nnod, ndis+cdis));
            }
        }
        return dist;
    }

}
