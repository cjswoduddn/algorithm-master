#include<iostream>
#include<string>
#include<vector>
using namespace std;
typedef long long ll;

ll dp[100][1<<15];
vector<string> g;
vector<int> tenMOD(51);
vector<int> gMOD;

int N, MOD;

int mod(string& str){
	int ret = 0;
	int j = 0;
	for(int i = str.size()-1; i >= 0; i--){
		ret += ((str[j++]-'0')*tenMOD[i])%MOD;
		ret %= MOD;
	}
	return ret;
}

ll recur(int m, int state){
	if(state == (1<<N)-1){
		if(m == 0) return 1;
		return 0;
	}
	ll& ret = dp[m][state];
	if(ret != -1) return ret;

	ret = 0;
	for(int i = 0; i < N; i++){
		int bit = 1<<i;
		if((state&bit) != 0) continue;
		int nm = ((m*tenMOD[g[i].size()])%MOD+gMOD[i])%MOD;
		ret += recur(nm, state|bit);
	}
	return ret;
}

ll GCD(ll a, ll b){
	if(b == 0) return a;
	return GCD(b, a%b);
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N;
	g.resize(N);
	gMOD.resize(N);
	for(int i = 0; i < N; i++){
		cin >> g[i];
	}
	cin >> MOD;
	tenMOD[0] = 1;
	for(int i = 0; i < 100; i++){
		for(int j = 0; j < (1<<N); j++)
			dp[i][j] = -1;
	}
	for(int i = 1; i < 51; i++)
		tenMOD[i] = (tenMOD[i-1]*10)%MOD;

	for(int i = 0; i < N; i++)
		gMOD[i] = mod(g[i]);

	ll child = recur(0, 0);
	ll parent = 1;
	for(int i = 1; i <= N; i++)
		parent *= i;

	ll gcd = GCD(child, parent);
	child /= gcd;
	parent /= gcd;
	printf("%lld/%lld\n", child, parent);
	return 0;
}

