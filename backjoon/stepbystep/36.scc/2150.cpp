#include<stdio.h>
#include<vector>
#include<algorithm>
#include<stack>
using namespace std;

int N, M, u, v;
int dcnt, scnt, sccid[10001], dfsn[10001];
vector<vector<int> > scc;
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
		vector<int> arr;
		int nod;
		do{
			nod = stk.top();
			sccid[nod] = scnt;
			arr.push_back(nod);
			stk.pop();
		}while(nod != cur);
		sort(arr.begin(), arr.end());
		scc.push_back(arr);
	}
	return ret;
}

int main(){
	scanf("%d %d", &N, &M);
	vector<vector<int> > g(N+1);
	while(M--){
		scanf("%d %d", &u, &v);
		g[u].push_back(v);
	}

	for(int i = 1; i <= N; i++)
		dfsn[i] = sccid[i] = -1;

	for(int i = 1; i <= N; i++){
		if(dfsn[i] == -1)
			dfs(g, i);
	}

	sort(scc.begin(), scc.end());
	printf("%d\n", scc.size());

	for(vector<int>& v : scc){
		for(int ptr : v)
			printf("%d ", ptr);
		printf("-1\n");
	}
	return 0;
}