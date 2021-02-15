#include<stdio.h>
#include<vector>
#include<stack>
using namespace std;

int T, N, M, u, v;
int dcnt, scnt, sccid[100001], dfsn[100001];
stack<int> stk;
stack<int> topo;
bool visited[100001];

int dfs(vector<vector<int> >& g, int cur){
	stk.push(cur);
	int ret = dfsn[cur] = ++dcnt;

	for(int i = 0, size = g[cur].size(); i < size; i++){
		int nnod = g[cur][i];
		if(dfsn[nnod] == -1){
			int tmp = dfs(g, nnod);
			ret = ret < tmp ? ret : tmp;
		}else if(sccid[nnod] == -1)
			ret = ret < dfsn[nnod] ? ret : dfsn[nnod];
	}

	if(ret == dfsn[cur]){
		scnt++;
		topo.push(cur);
		do{
			u = stk.top();
			stk.pop();
			sccid[u] = scnt;
		}while(u != cur);
	}
	return ret;
}

void dfs2(vector<vector<int> >& g, int cur){
	for(int i = 0, size = g[cur].size(); i < size; i++){
		int nnod = g[cur][i];
		if(visited[nnod]) continue;
		visited[nnod] = true;
		dfs2(g, nnod);
	}
}

int main(){
	scanf("%d", &T);
	while(T--){
		int ans = 0;
		scanf("%d %d", &N, &M);

		vector<vector<int> > g(N+1);
		while(M--){
			scanf("%d %d", &u, &v);
			g[u].push_back(v);
		}

		for(int i = 1; i <= N; i++){
			dfsn[i] = sccid[i] = -1;
			visited[i] = false;
		}

		for(int i = 1; i <= N; i++){
			if(dfsn[i] == -1){
				dfs(g, i);
			}
		}
		while(!topo.empty()){
			int cur = topo.top();
			topo.pop();
			if(visited[cur]) continue;
			visited[cur] = true;
			ans++;
			dfs2(g, cur);
		}
		printf("%d\n", ans);
	}
	return 0;
}
