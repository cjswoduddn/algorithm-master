#include<string>
#include<iostream>
#define MOD 1000000007
using namespace std;
typedef long long ll;

ll two[61] = {1};
ll dp[61][2][2] = {4, -1, 1, 0};
ll ans[2][2];

void mat(ll a[2][2], ll b[2][2], ll c[2][2]){
	for(int i = 0; i < 2 ; i++)
		for(int j = 0; j < 2; j++)
			c[i][j] = MOD;
	c[0][0] += ((a[0][0]*b[0][0])%MOD+(a[0][1]*b[1][0])%MOD)%MOD;
	c[0][1] += ((a[0][0]*b[0][1])%MOD+(a[0][1]*b[1][1])%MOD)%MOD;
	c[1][0] += ((a[1][0]*b[0][0])%MOD+(a[1][1]*b[1][0])%MOD)%MOD;
	c[1][1] += ((a[1][0]*b[0][1])%MOD+(a[1][1]*b[1][1])%MOD)%MOD;
	c[0][0] %= MOD;
	c[0][1] %= MOD;
	c[1][0] %= MOD;
	c[1][1] %= MOD;
}

void recur(ll k){
	if(k == 0){
		ans[0][0] = 1;
		ans[0][1] = 0;
		ans[1][0] = 0;
		ans[1][1] = 1;
		return;
	}

	int idx = 0;
	while(two[idx] <= k) idx++;
	idx--; // k보다 작은 2의 제곱수중 최대인 값
	recur(k-two[idx]);
	ll tmp[2][2];
	mat(dp[idx], ans, tmp);
	ans[0][0] = tmp[0][0];
	ans[0][1] = tmp[0][1];
	ans[1][0] = tmp[1][0];
	ans[1][1] = tmp[1][1];
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	ll k;

	cin >> k;

	if(k%2 == 1){
		cout << 0 << '\n';
		return 0;
	}
	k /= 2;

	for(int i = 0; i < 60; i++){
		two[i+1] = two[i]*2;
		mat(dp[i], dp[i], dp[i+1]);
	}

	recur(k);
	cout << (ans[0][0]+ans[0][1])%MOD;
	return 0;
}