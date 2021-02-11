#include<stdio.h>
#include<vector>
using namespace std;

int N, M, u, v;
int spTable[21][100001];
int depth[100001];

void dfs(vector<vector<int> >& g, int cur, int pre){
	depth[cur] = depth[pre]+1;
	spTable[0][cur] = pre;
	for(int j = 1; j < 21; j++){
		spTable[j][cur] = 
			spTable[j-1][spTable[j-1][cur]];
	}
	for(int i = 0, size = g[cur].size(); i < size; i++){
		int nnod = g[cur][i];
		if(nnod == pre) continue;
		dfs(g, nnod, cur);
	}
}

int lca(int left, int right){
	for(int i = 20; i >= 0; i--){
		int tmp = spTable[i][right];
		if(depth[tmp] < depth[left]) continue;
		right = tmp;
	}

	if(left == right) return left;
	for(int i = 20; i >= 0; i--){
		int lanc = spTable[i][left];
		int ranc = spTable[i][right];
		if(lanc != ranc){
			left = lanc;
			right = ranc;
		}
	}
	return spTable[0][left];
}

int main(){
	scanf("%d", &N);
	vector<vector<int> > g(N+1);
	for(int i = 1; i < N; i++){
		scanf("%d %d", &u, &v);
		g[u].push_back(v);
		g[v].push_back(u);
	}

	dfs(g, 1, 0);
	scanf("%d", &M);
	while(M--){
		scanf("%d %d", &u, &v);
		int left = depth[u] <= depth[v] ? u : v;
		int right = depth[u] > depth[v] ? u : v;
		printf("%d\n", lca(left, right));
	}
	return 0;
}