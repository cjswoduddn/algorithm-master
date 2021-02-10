#include<stdio.h>
#include<queue>
#include<vector>
using namespace std;

int T, N, M, u, v, target;

int bfs(vector<vector<int> >& g, vector<int>&topo, 
	vector<int>&cost, int target){
	queue<int> q;
	vector<int> tracking(N+1, -1);
	for(int i = 1; i <= N; i++)
		if(topo[i] == 0){
			q.push(i);
			tracking[i] = cost[i];
		}


	while(!q.empty()){
		int cur = q.front();
		q.pop();
		if(cur == target) break;

		for(int i = 0, size = g[cur].size(); i < size; i++){
			int nnod = g[cur][i];
			topo[nnod]--;
			int tmp = tracking[cur]+cost[nnod];
			tracking[nnod] = tmp > tracking[nnod] 
							? tmp : tracking[nnod];
			if(topo[nnod] == 0){
				q.push(nnod);
			}
		}
	}
	return tracking[target];
}
int main(){
	scanf("%d", &T);
	while(T--){
		scanf("%d %d", &N, &M);
		vector<int> cost(N+1);
		for(int i = 1; i <= N; i++)
			scanf("%d", &cost[i]);

		vector<vector<int> > g(N+1);
		vector<int> topo(N+1);
		while(M--){
			scanf("%d %d", &u, &v);
			g[u].push_back(v);
			topo[v]++;
		}
		scanf("%d", &target);
		printf("%d\n", bfs(g, topo, cost, target));
	}
	return 0;
}