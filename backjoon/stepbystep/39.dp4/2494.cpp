#include<string>
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int N;
string str, target;
vector<vector<int> > dp(10);

int recur(int step, int count){
	if(step == str.size()) return 0;
	int strNum = str[step]-'0';
	int curNum = (strNum+count)%10;

	int& ret = dp[curNum][step];
	if(ret != -1) return ret;

	int tarNum = target[step]-'0';
	if(curNum == tarNum) 
		return ret = recur(step+1, count);
	else if(curNum > tarNum){
		int right = recur(step+1, count)+curNum-tarNum;
		int dif = 10-(curNum-tarNum);
		int left = recur(step+1, count+dif)+dif;
		return ret = right < left ? right : left;
	}
	int left = recur(step+1, count+tarNum-curNum)+tarNum-curNum;
	int dif = 10-(tarNum-curNum);
	int right = recur(step+1, count)+dif;
	return ret = right < left ? right : left;
}

void tracking(int step, int count){
	int strNum = str[step]-'0';
	int curNum = (strNum+count)%10;
	int tarNum = target[step]-'0';
	if(step == str.size()-1){
		if(curNum == tarNum)
			cout << step+1 << " " << 0 << '\n';
		else if(curNum > tarNum){
			int right = curNum-tarNum;
			int left = 10-right;
			if(right < left)
				cout << step+1 << " " << -1*right << '\n';
			else 
				cout << step+1 << " " << left << '\n';
		}else{
			int left = tarNum-curNum;
			int right = 10-left;
			if(right < left)
				cout << step+1 << " " << -1*right << '\n';
			else 
				cout << step+1 << " " << left << '\n';
		}
		return;
	}
	if(curNum == tarNum){
		cout << step+1 << " " << 0 << '\n';
		tracking(step+1, count);
		return;
	}else if(curNum > tarNum){
		int rightMoving = curNum-tarNum;
		int rightNextNum = (str[step+1]-'0'+count)%10;
		int right = dp[rightNextNum][step+1]+rightMoving;

		int leftMoving = 10-rightMoving;
		int leftNextNum = (str[step+1]-'0'+count+leftMoving)%10;
		int left = dp[leftNextNum][step+1]+leftMoving;
		if(right < left){
			cout << step+1 << " " << -1*rightMoving << '\n';
			tracking(step+1, count);
			return;
		}else{
			cout << step+1 << " " << leftMoving << '\n';
			tracking(step+1, count+leftMoving);
			return;
		}
	}
	int rightMoving = 10-(tarNum-curNum);
	int rightNextNum = (str[step+1]-'0'+count)%10;
	int right = dp[rightNextNum][step+1]+rightMoving;

	int leftMoving = tarNum-curNum;
	int leftNextNum = (str[step+1]-'0'+count+leftMoving)%10;
	int left = dp[leftNextNum][step+1]+leftMoving;
	if(right < left){
		cout << step+1 << " " << -1*rightMoving << '\n';
		tracking(step+1, count);
		return;
	}else{
		cout << step+1 << " " << leftMoving << '\n';
		tracking(step+1, count+leftMoving);
		return;
	}
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> str >> target;
	for(vector<int>& v : dp){
		v.resize(N);
		fill(v.begin(), v.end(), -1);
	}

	cout << recur(0, 0) << '\n';
	tracking(0, 0);
	return 0;
}

