#include<stdio.h>
#include<queue>
#include<vector>
using namespace std;
class cmp{
public:
	bool operator()(int a, int b){
		return a > b;
	}
};

int N, M, u, v;

void bfs(vector<vector<int> >& g, vector<int>& topo){
	priority_queue<int, vector<int>, cmp> pq;
	for(int i = 1; i <= N; i++)
		if(topo[i] == 0) pq.push(i);

	while(!pq.empty()){
		int cur = pq.top();
		pq.pop();
		printf("%d ", cur);

		for(int i = 0, size = g[cur].size(); i < size; i++){
			int nnod = g[cur][i];
			topo[nnod]--;
			if(topo[nnod] == 0) pq.push(nnod);
		}
	}
}
int main(){
	scanf("%d %d", &N, &M);
	vector<vector<int> > g(N+1);
	vector<int> topo(N+1, 0);

	while(M--){
		scanf("%d %d", &u, &v);
		g[u].push_back(v);
		topo[v]++;
	}
	bfs(g, topo);
	return 0;
}