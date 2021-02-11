#include<stdio.h>
#include<vector>
using namespace std;

int T, N, u, v;

int dfs(vector<vector<int> >& g, int cur, int pre){
	int ret = 0;
	if(cur == u || cur == v) ret = 1;
	for(int i = 0, size = g[cur].size(); i < size; i++){
		int nnod = g[cur][i];
		if(nnod == pre) continue;
		ret += dfs(g, nnod, cur);
	}
	if(ret == 2){
		printf("%d\n", cur);
		return 0;
	}
	return ret;
}


int main(){
	scanf("%d", &T);
	while(T--){
		scanf("%d", &N);
		vector<vector<int> > g(N+1);
		vector<bool> findRoot(N+1, false);
		for(int i = 1; i < N; i++){
			scanf("%d %d", &u, &v);
			g[u].push_back(v);
			findRoot[v] = true;
		}
		int root = 0;
		for(int i = 1; i <= N; i++){
			if(findRoot[i]) continue;
			root = i;
		}

		scanf("%d %d", &u, &v);
		dfs(g, root, 0);
	}

	return 0;
}