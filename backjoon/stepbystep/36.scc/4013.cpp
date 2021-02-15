/*
틀린 풀이입니다
도대체 왜 틀린지 몰겟음
*/
#include<stdio.h>
#include<vector>
#include<stack>
#define MAX 500001
using namespace std;

int N, M, u, v, snod, p, q, dcnt, scnt;
int dfsn[MAX], sccid[MAX], cost[MAX], sum[MAX], dp[MAX];
bool isRes[MAX], sccRes[MAX], visited[MAX];
stack<int> stk;

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
		int nod;
		do{
			nod = stk.top();
			stk.pop();
			sccid[nod] = scnt;
			sum[scnt] += cost[nod];
			if(isRes[nod]) sccRes[scnt] = true;
		}while(nod != cur);
	}
	return ret;
}

bool DFS(vector<vector<int> >& g, int cur){
	for(int i = 0, size = g[cur].size(); i < size; i++){
		int nnod = g[cur][i];
		if(visited[nnod]) continue;
		visited[nnod] = true;
		if(DFS(g, nnod))
			sccRes[cur] = true;
	}
	return sccRes[cur];
}

int recur(vector<vector<int> >& g, int cur){
	if(!sccRes[cur]) return 0;
	int& ret = dp[cur];
	if(ret != -1) return ret;
	ret = 0;
	for(int i = 0, size = g[cur].size(); i < size; i++){
		int nnod = g[cur][i];
		int tmp = recur(g, nnod);
		ret = ret > tmp ? ret : tmp;
	}
	return ret+sum[cur];
}
int main(){
	scanf("%d %d", &N, &M);
	vector<vector<int> > g(N+1);

	while(M--){
		scanf("%d %d", &u, &v);
		g[u].push_back(v);
	}

	for(int i = 1; i <= N; i++){
		scanf("%d", &cost[i]);
		dp[i] = dfsn[i] = sccid[i] = -1;
	}

	scanf("%d %d", &snod, &p);
	while(p--){
		scanf("%d", &q);
		isRes[q] = true;
	}

	for(int i = 1; i <= N; i++)
		if(dfsn[i] == -1) dfs(g, i);

	vector<vector<int> > sccg(scnt+1);
	for(int i = 1; i <= N; i++){
		for(int j = 0, size = g[i].size(); j < size; j++){
			int nnod = g[i][j];
			if(sccid[i] == sccid[nnod]) continue;
			sccg[sccid[i]].push_back(sccid[nnod]);
		}
	}

	visited[sccid[snod]] = true; 
	DFS(sccg, sccid[snod]);

	printf("%d\n", recur(sccg, sccid[snod]));
	return 0;
}





