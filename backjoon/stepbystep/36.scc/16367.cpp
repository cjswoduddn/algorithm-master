#include<string>
#include<iostream>
#include<vector>
#include<stack>
#define MAX 10001
using namespace std;

int N, M, p, q, r, rp, rq, rr;
int dcnt, scnt;
string cp, cq, cr;
vector<int> dfsn(MAX, -1);
vector<int> sccid(MAX, -1);
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
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> M;
	vector<vector<int> > g(2*N+1);

	while(M--){
		cin >> p >> cp >> q >> cq >> r >> cr;
		if(cp == "R") rp = p+N;
		else {
			rp = p;
			p += N;
		}
		if(cq == "R") rq = q+N;
		else {
			rq = q;
			q += N;
		}
		if(cr == "R") rr = r+N;
		else {
			rr = r;
			r += N;
		}

		g[rp].push_back(q);
		g[rp].push_back(r);

		g[rq].push_back(p);
		g[rq].push_back(r);

		g[rr].push_back(q);
		g[rr].push_back(p);
	}

	for(int i = 1; i <= 2*N; i++){
		if(dfsn[i] == -1) dfs(g, i);
	}

	bool flag = true;
	for(int i = 1; i <= N; i++)
		if(sccid[i] == sccid[i+N]) flag = false;

	if(!flag) printf("-1\n");
	else{
		for(int i = 1; i <= N; i++){
			if(sccid[i] > sccid[i+N]) printf("R");
			else printf("B");
		}
	}
	return 0;
}