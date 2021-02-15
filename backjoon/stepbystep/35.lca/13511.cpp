#include<stdio.h>
#include<vector>
#include<utility>
using namespace std;
typedef pair<int, int> pii;
typedef long long ll;

int N, M, q, u, v, d;
int anc[21][100001];
int depth[100001];
ll cost[21][100001];

void dfs(vector<vector<pii> >& g, int cur, int pre){
	depth[cur] = depth[pre]+1;

	for(int i = 1; i < 21; i++){
		int tmp = anc[i-1][cur];
		if(tmp == -1) break;
		anc[i][cur] = anc[i-1][tmp];
		cost[i][cur] = cost[i-1][tmp]+cost[i-1][cur];
	}

	for(int i = 0, size = g[cur].size(); i < size; i++){
		int nnod = g[cur][i].first;
		int ndis = g[cur][i].second;
		if(nnod == pre) continue;
		anc[0][nnod] = cur;
		cost[0][nnod] = ndis;
		dfs(g, nnod, cur);
	}
}

int LCA(int u, int v){
	if(depth[u] > depth[v]){int swap=u;u=v;v=swap;}
	for(int i = 20; i >= 0; i--){
		int tmp = anc[i][v];
		if(tmp == -1) continue;
		if(depth[tmp] >= depth[u])
			v = tmp;
	}

	if(u == v) return u;

	for(int i = 20; i >= 0; i--){
		int ltmp = anc[i][u];
		int rtmp = anc[i][v];
		if(ltmp != rtmp){
			u = ltmp;
			v = rtmp;
		}
	}
	return anc[0][u];
}

ll dist(int lca, int u){
	ll ret = 0;
	for(int i = 20; i >= 0; i--){
		int tmp = anc[i][u];
		if(tmp == -1) continue;
		if(depth[tmp] >= depth[lca]){
			ret += cost[i][u];
			u = tmp;
		}
	}
	return ret;
}

int query(int u, int d){
	for(int i = 20; i >= 0; i--){
		int tmp = anc[i][u];
		if(tmp == -1) continue;
		int dd = depth[u]-depth[tmp];
		if(dd <= d){
			u = tmp;
			d -= dd;
		}
	}
	return u;
}

int main(){
	scanf("%d", &N);
	for(int i = 0; i < 21; i++){
		for(int j = 0; j <= N; j++){
			anc[i][j] = cost[i][j] = -1;
		}
	}
	vector<vector<pii> > g(N+1);
	for(int i = 1; i < N; i++){
		scanf("%d %d %d", &u, &v, &d);
		g[u].push_back(pii(v, d));
		g[v].push_back(pii(u, d));
	}

	dfs(g, 1, 0);
	scanf("%d", &M);

	while(M--){
		scanf("%d", &q);
		if(q == 1){
			scanf("%d %d", &u, &v);
			int lca = LCA(u, v);
			printf("%lld\n", dist(lca, u)+dist(lca, v));
		}else if(q == 2){
			scanf("%d %d %d", &u, &v, &d);
			int lca = LCA(u, v);
			int du = depth[u]-depth[lca]+1;
			if(du >= d){
				printf("%d\n", query(u, d-1));
			}else{
				int dv = depth[v]-depth[lca];
				d = dv-(d-du);
				printf("%d\n", query(v, d));
			}
		}
	}
	return 0;
}
