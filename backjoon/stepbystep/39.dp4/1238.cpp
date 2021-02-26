#include<stdio.h>
#include<vector>
#include<queue>
#include<utility>
#include<limits.h>
#define x first
#define y second
using namespace std;
typedef pair<int, int> pii;
class cmp{
public:
	bool operator()(const pii& a, const pii& b){
		return a.y > b.y;
	}
};

const int INF = INT_MAX;
int N, M, X, u, v, d;
int dis[1001][1001];

int dijstra(vector<vector<pii> >& g, int s, int target){
	for(int i = 1; i <= N; i++)
		dis[s][i] = INF;
	priority_queue<pii, vector<pii>, cmp> pq;
	pq.push(pii(s, 0));
	dis[s][s] = 0;

	while(!pq.empty()){
		pii cnod = pq.top();
		pq.pop();
		if(cnod.x == target) return dis[s][target];

		for(int i = 0, size = g[cnod.x].size(); i < size; i++){
			int nnod = g[cnod.x][i].first;
			int ndis = g[cnod.x][i].second+cnod.y;
			if(dis[s][nnod] <= ndis) continue;
			dis[s][nnod] = ndis;
			pq.push(pii(nnod, ndis));
		}
	}
	return -1;
}

int main(){
	scanf("%d %d %d", &N, &M, &X);
	vector<vector<pii> > g(N+1);

	while(M--){
		scanf("%d %d %d", &u, &v, &d);
		g[u].push_back(pii(v, d));
	}

	int ans = 0;
	dijstra(g, X, 0);
	for(int i = 1; i <= N; i++){
		if(i == X) continue;
		int tmp = dijstra(g, i, X)+dis[X][i];
		ans = ans > tmp ? ans : tmp;
	}

	printf("%d\n", ans);
	return 0;
}