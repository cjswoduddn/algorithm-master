#include<stdio.h>
#include<vector>
#include<stack>
using namespace std;

int T, N, M, u, v;
int dcnt, scnt, dfsn[100000], sccid[100000];
bool visited[100000];
stack<int> stk, topo;

int dfs(vector<vector<int> >&g, int cur){
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

	if(dfsn[cur] == ret){
		scnt++;
		do{
			u = stk.top();
			stk.pop();
			sccid[u] = scnt;
		}while(u != cur);
		topo.push(cur);
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
		scanf("%d %d", &N, &M);
		dcnt = scnt = 0;
		vector<vector<int> > g(N);
		while(M--){
			scanf("%d %d", &u, &v);
			g[u].push_back(v);
		}

		for(int i = 0; i < N; i++){
			dfsn[i] = -1;
			sccid[i] = -1;
			visited[i] = false;
		}

		for(int i = 0; i < N; i++){
			if(dfsn[i] == -1)
				dfs(g, i);
		}

		int ans = 0;
		int nod = topo.top();
		while(!topo.empty()){
			u = topo.top();
			topo.pop();
			if(visited[u]) continue;
			visited[u] = true;
			ans++;
			dfs2(g, u);
		}
		if(ans > 1) printf("Confused\n");
		else{
			for(int i = 0; i < N; i++){
				if(sccid[nod] == sccid[i])
					printf("%d\n", i);
			}
		}
		printf("\n");
	}
	return 0;
}