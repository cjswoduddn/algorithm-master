#include <string>
#include <vector>
#include <iostream>
#define MOD 1000000007
typedef long long ll;

using namespace std;

int solution(int n){
	vector<ll> dp(5001);
	dp[0] = 1;
	dp[2] = 3;

	for(int i = 4; i <= 5000; i+=2){
		dp[i] = (dp[i-2]*3)%MOD;
		for(int j = 0; j < i-2; j+=2){
			dp[i] += dp[j]*2;
			dp[i] %= MOD;
		}
	}

	if(n%2 == 1) return 0;
	return dp[n];
}

int main(){
	int N;
	cin >> N;
	cout << solution(N);
	return 0; 
}