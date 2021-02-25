#include<iostream>
#include<string>
using namespace std;

int dp[2500][2500];
int ndp[2500];

int palindrom(string& str, int s, int e){
	int& ret = dp[s][e];
	if(ret != -1) return ret;
	if(s >= e) return ret = 1;

	if(str[s] == str[e] && palindrom(str, s+1, e-1) == 1)
		return ret = 1;
	return ret = 0;
}

int recur(string& str, int s){
	if(s == str.size()) return 0;
	int& ret = ndp[s];
	if(ret != -1) return ret;

	ret = 2500;
	for(int i = s; i < str.size(); i++){
		if(palindrom(str, s, i)){
			int tmp = 1+recur(str, i+1);
			ret = ret < tmp ? ret : tmp;
		}
	}
	return ret;
}
int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	for(int i = 0; i < 2500; i++){
		ndp[i] = -1;
		for(int j = 0; j < 2500; j++){
			dp[i][j] = -1;
		}
	}

	string str;
	cin >> str;

	cout << recur(str, 0);
	return 0;
}