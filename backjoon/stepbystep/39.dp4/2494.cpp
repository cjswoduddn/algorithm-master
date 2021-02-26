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

void tracking(int step, int num){
	if(step == str.size()-1){
		cout << step << " " << num << '\n';
		return;
	}

	int cnt = (str[step]-'0'+num)%10;
	int tcnt = target[step]-'0';

	if(cnt == tcnt){
		cout << step << " " << 0 << '\n';
		tracking(step+1, num);
	}else if(cnt < tcnt){
		int left = dp[(str[step+1]-'0'+num+tcnt-cnt)%10][step+1]
		+tcnt-cnt;
		int right = dp[(str[step+1]-'0'+num)%10][step+1]
		+10-tcnt+cnt;
		if(left < right){
			cout << step << " " << num+tcnt-cnt << '\n';
			tracking(step+1, num, tcnt-cnt);
		}else{
			cout << step << " " << num+tcnt-cnt << '\n';
			tracking(step+1, num, tcnt-cnt);
		}
	}
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
	tracking(0, 0);
	return 0;
}