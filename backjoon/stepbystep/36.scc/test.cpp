#include<stdio.h>
#include<vector>
#include<utility>
#include<queue>
#include<stack>
#define MAX 500001
using namespace std;
typedef pair<int, int> pii;

int N, M, u, v, start, dcnt, scnt;
int cost[MAX], dfsn[MAX], scc[MAX], scost[MAX];
int dp[MAX];
bool isres[MAX], sres[MAX];
vector<int> g[MAX];
vector<int> tree[MAX];
stack<int> stk;
queue<int> q;

int dfs(int cnod){
	int ret = dfsn[cnod] = dcnt++;
	stk.push(cnod);

	for(int i = 0, size = g[cnod].size(); i < size; i++){
		int nnod = g[cnod][i];
		if(dfsn[nnod] == -1){
			int tmp = dfs(nnod);
			ret = ret < tmp ? ret : tmp;
		}else if(scc[nnod] == -1){
			ret = ret < dfsn[nnod] ? ret : dfsn[nnod];
		}
	}

	if(ret == dfsn[cnod]){
		do{
			u = stk.top();
			stk.pop();
			scc[u] = scnt;
			scost[scnt] += cost[u];
		}while(u != cnod);
		scnt++;
	}

	return ret;
}

int recur(int cnod){
	int& ret = dp[cnod];
	if(ret != -1) return ret;

	ret = 0;
	int value = 0;
	for(int nod : tree[cnod]){
		int tmp = recur(nod);
		value = value > tmp ? value : tmp;
	}

	if(value == 0){
		if(sres[cnod]) return ret = scost[cnod];
		return ret = 0;
	}
	return ret = value+scost[cnod];
}

int main(){
	scanf("%d %d", &N, &M);
	for(int i = 0; i < MAX; i++)
		dp[i] = dfsn[i] = scc[i] = -1;

	while(M--){
		scanf("%d %d", &u, &v);
		g[u].push_back(v);
	}

	for(int i = 1; i <= N; i++)
		scanf("%d", &cost[i]);

	scanf("%d %d", &start, &u);
	while(u--){
		scanf("%d", &v);
		isres[v] = true;
	}

	for(int i = 1; i <= N; i++)
		if(dfsn[i] == -1) dfs(i);

	for(int i = 1; i <= N; i++){
		u = scc[i];
		for(int j : g[i]){
			v = scc[j];
			if(isres[j])
				sres[v] = true;
			if(u == v) continue;
			tree[u].push_back(v);
		}
	}

	printf("%d\n", recur(scc[start]));
	return 0;
}