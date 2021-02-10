#include<stdio.h>
#include<queue>
#include<vector>
using namespace std;

int N, M;
int u, v;
int arr[32001];

void bfs(vector<vector<int> >& g){
	queue<int> q;
	for(int i = 1; i <= N; i++)
		if(arr[i] == 0) q.push(i);

	while(!q.empty()){
		int cur = q.front();
		q.pop();
		printf("%d ", cur);
		for(int i = 0, size = g[cur].size(); i < size; i++){
			int nnod = g[cur][i];
			arr[nnod]--;
			if(arr[nnod] == 0) q.push(nnod);
		}
	}
}

int main(){
	scanf("%d %d", &N, &M);
	vector<vector<int> > g(N+1);
	while(M--){
		scanf("%d %d", &u, &v);
		g[u].push_back(v);
		arr[v]++;
	}

	bfs(g);
	return 0;
}
