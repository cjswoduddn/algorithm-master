#include<string>
#include<iostream>
using namespace std;

int dp[10][10000], N;
string str, target;

int recur(int step, int num){
	if(step == str.size()) return 0;
	int cnt = (str[step]-'0'+num)%10;
	int tcnt = target[step]-'0';
	int& ret = dp[cnt][step];
	if(ret != -1) return ret;

	if(cnt == tcnt)
		return ret = recur(step+1, num);

	if(cnt < tcnt){
		int left = recur(step+1, num+tcnt-cnt)+tcnt-cnt;
		int right = recur(step+1, num)+10-(tcnt-cnt);
		return ret = left < right ? left : right;
	}
	int left = recur(step+1, num+10-(cnt-tcnt))
					+10-(cnt-tcnt);
	int right = recur(step+1, num)+cnt-tcnt;
	return ret = left < right ? left : right;
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	for(int i = 0; i < 10; i++){
		for(int j = 0; j < 10000; j++){
			dp[i][j] = -1;
		}
	}

	cin >> N >> str >> target;

	cout << recur(0, 0);
	return 0;
}