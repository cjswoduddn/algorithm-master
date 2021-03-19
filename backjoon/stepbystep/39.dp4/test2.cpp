#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int dfs(vector<vector<int>>&g, int cur, int pre){
	int ret = 0;
	for(int i = 0, size = g[cur].size(); i < size; i++){
		int nnod = g[cur][i];
		if(nnod == pre) continue;
		int tmp = dfs(g, nnod, cur)+1;
		ret = ret > tmp ? ret : tmp;
	}
	return ret;
}

int solution(int n, vector<vector<int>> edges){
	vector<vector<int>> g(n+1);

	for(vector<int>& vec : edges){
		int u = vec[0];
		int v = vec[1];
		g[u].push(v);
		g[v].push(u);
	}

	int root = 0;
	for(int i = 1; i <= n; i++){
		if(g[i].size() >= 2){
			root = i;
			break;
		}
	}

	vector<int> dis;
	for(int u : g[root])
		dis.push_back(dfs(g, u, root));

	sort(dis.begin(), dis.end());
	return dis[dis.size()-1];
}