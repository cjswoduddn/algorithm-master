#include <string>
#include <vector>
#include <iostream>
using namespace std;

int crush(int m, int n, vector<string>& bd){
	vector<vector<int> > mop(m);
	for(int i = 0; i < m; i++){
		mop[i].resize(n);
		for(int j = 0; j < n; j++){
			if(bd[i][j] == ' ') mop[i][j] = -1;
			else mop[i][j] = 1;
		}
	}

	for(int i = 0; i < m-1; i++){
		for(int j = 0; j < n-1; j++){
			char c = bd[i][j];
			if(c == ' ') continue;
			if(c != bd[i][j+1]) continue;
			if(c != bd[i+1][j]) continue;
			if(c != bd[i+1][j+1]) continue;
			mop[i][j] = mop[i][j+1] =
			mop[i+1][j] = mop[i+1][j+1] = 0;

		}
	}
	for(int i = 0; i < n; i++){
		for(int j = m-2; j >= 0; j--){
			if(bd[j][i] != ' ' && mop[j][i] == 1){
				int idx = j;
				while(idx+1 < m && mop[idx+1][i] == 0){
					bd[idx+1][i] = bd[idx][i];
					bd[idx][i] = ' ';
					mop[idx+1][i] = 1;
					mop[idx][i] = 0;
					idx++;
				}
			}
		}
	}
	for(int i = 0; i < m; i++){
		for(int j = 0; j < n; j++){
			if(mop[i][j] == 0) bd[i][j] = ' ';
		}
	}
	int ret = 0;
	for(int i = 0; i < m; i++){
		for(int j = 0; j < n; j++){
			if(mop[i][j] == 0) ret++;
		}
	}
	return ret;
}

int solution(int m, int n, vector<string> bd){
	int ans = 0;
	int k = 0;
	while((k = crush(m, n, bd)) > 0){
		ans += k;
	}
	return ans;
}

int main(){
	int m = 4, n = 5;
	vector<string> bd = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
	cout << solution(m, n, bd);
	return 0;
}