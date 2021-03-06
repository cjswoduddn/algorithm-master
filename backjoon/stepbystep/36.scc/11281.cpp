#include<stdio.h>
#include<vector>
#include<stack>
#define MAX 20001
using namespace std;

int N, M, u, v, dcnt, scnt;
int dfsn[MAX], sccid[MAX];
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
		int k;
		do{
			k = stk.top();
			stk.pop();
			sccid[k] = scnt;
		}while(k != cur);
	}
	return ret;
}
int main(){
	scanf("%d %d", &N, &M);
	vector<vector<int> > g(2*N+1);

	while(M--){
		scanf("%d %d", &u, &v);
		u = u < 0 ? u*-1+N : u;
		v = v < 0 ? v*-1+N : v;
		int ru = u > N ? u-N : u+N;
		int rv = v > N ? v-N : v+N;
		g[ru].push_back(v);
		g[rv].push_back(u);
	}

	for(int i = 1; i <= 2*N; i++){
		dfsn[i] = sccid[i] = -1;
	}

	for(int i = 1; i <= 2*N; i++)
		if(dfsn[i] == -1) dfs(g, i);

	bool flag = true;
	for(int i = 1; i <= N; i++){
		if(sccid[i] == sccid[i+N])
			flag = false;
	}
	if(!flag) printf("0\n");
	else{
		printf("1\n");
		for(int i = 1; i <= N; i++){
			if(sccid[i] < sccid[i+N]) printf("1 ");
			else printf("0 ");
		}
	}
	for(int i = 1; i <= 2*N; i++)
		printf("%d : %d\n", i, sccid[i]);

	printf("\n");
	return 0;
}