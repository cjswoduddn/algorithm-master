#include <string>
#include <iostream>

using namespace std;

int func(int k, string& str){
	int ret = 0;
	int idx = 0;
	while(idx+k <= str.size()){
		int cnt = 0;
		string sub = str.substr(idx, k);
		for(int i = 1; idx+k*i <= str.size(); i++){
			if(str.substr(idx+k*(i-1), k) == sub) cnt++;
			else break;
		}

		if(cnt == 1){
			ret += k;
		}else{
			ret += k+1;
		}
		idx += cnt*k;
	}
	ret += str.size()-idx;
	cout << k << ' ' << ret << '\n';
	return ret;
}

int solution(string s){
	int ret = s.size();

	for(int i = 1; i <= s.size()/2; i++){
		int tmp = func(i, s);
		ret = ret < tmp ? ret : tmp;
	}

	return ret;
}

int main(){
	cout << solution("aabbaccc");
	return 0;
}