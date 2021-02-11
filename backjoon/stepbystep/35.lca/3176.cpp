#include<stdio.h>
#include<vector>
#include<utility>
#include<limits.h>
using namespace std;
typedef pair<int, int> pii;

const int INF = INT_MAX;
const int MINF = INT_MIN;
int N, u, v, d, M;
int spTable[21][100001];
int maxd[21][100001];
int mind[21][100001];
int depth[100001];

void dfs(vector<vector<pii> >& g, int cur, int pre){
	depth[cur] = depth[pre]+1;

	for(int i = 1; i < 21; i++){
		int tmp = spTable[i-1][cur];
		if(spTable[i-1][tmp] == -1) break;

		spTable[i][cur] = spTable[i-1][tmp];

		maxd[i][cur] = maxd[i-1][cur] > maxd[i-1][tmp] 
		? maxd[i-1][cur] : maxd[i-1][tmp];

		mind[i][cur] = mind[i-1][cur] < mind[i-1][tmp]
		? mind[i-1][cur] : mind[i-1][tmp];
	}

	for(int i = 0, size = g[cur].size(); i < size; i++){
		int nnod = g[cur][i].first;
		int ndis = g[cur][i].second;
		if(nnod == pre) continue;
		spTable[0][nnod] = cur;
		maxd[0][nnod] = mind[0][nnod] = ndis;
		dfs(g, nnod, cur);
	}
}

int LCA(int left, int right){
	for(int i = 20; i >= 0; i--){
		int tmp = spTable[i][right];
		if(tmp == -1) continue;
		if(depth[left] <= depth[tmp])
			right = tmp;
	}

	if(left == right) return left;

	for(int i = 20; i >= 0; i--){
		int ltmp = spTable[i][left];
		int rtmp = spTable[i][right];
		if(ltmp == -1 || rtmp == -1) continue;
		if(ltmp != rtmp){
			left = ltmp;
			right = rtmp;
		}
	}
	return spTable[0][left];
}

int maxvalue(int anc, int child){
	int ret = MINF;
	for(int i = 20; i >= 0; i--){
		int tmp = spTable[i][child];
		if(tmp == -1) continue;
		if(depth[tmp] >= depth[anc]){
			ret = ret > maxd[i][child] ? ret : maxd[i][child];
			child = tmp;
		}
	}
	return ret;
}

int minvalue(int anc, int child){
	int ret = INF;
	for(int i = 20; i >= 0; i--){
		int tmp = spTable[i][child];
		if(tmp == -1) continue;
		if(depth[tmp] >= depth[anc]){
			ret = ret < mind[i][child] ? ret : mind[i][child];
			child = tmp;
		}
	}
	return ret;
}

int main(){
	scanf("%d", &N);
	vector<vector<pii> > g(N+1);
	for(int i = 1; i < N; i++){
		scanf("%d %d %d", &u, &v, &d);
		g[u].push_back(pii(v, d));
		g[v].push_back(pii(u, d));
	}

	for(int i = 0; i < 21; i++){
		for(int j = 0; j <= N; j++){
			spTable[i][j] = -1;
			maxd[i][j] = MINF;
			mind[i][j] = INF;
		}
	}
	dfs(g, 1, 0);

	scanf("%d", &M);
	while(M--){
		scanf("%d %d", &u, &v);
		if(depth[u] > depth[v]){int swap=u;u=v;v=swap;}
		int lca = LCA(u, v);
		int maxleft = maxvalue(lca, u);
		int maxright = maxvalue(lca, v);
		int minleft = minvalue(lca, u);
		int minright = minvalue(lca, v);
		printf("%d ", minleft < minright ? minleft : minright);
		printf("%d\n", maxleft > maxright ? maxleft : maxright);
	}

	return 0;
}