#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

string convert(int bit, int k){
	string ret;
	while(k > 0){
		int m = k%bit;
		k /= bit;
		if(m < 10) ret.push_back(m+'0');
		else ret.push_back(m-10+'A');
	}
	reverse(ret.begin(), ret.end());
	return ret;
}

string solution(int n, int t, int m, int p){
	string answer;
	for(int i = 1; answer.size() < 100000; i++){
		answer += convert(n, i);
	}
	cout << answer.substr(0, 10) << '\n';

	p--;
	string ret;
	for(int i = 0; i < t; i++){
		ret.push_back(answer[p+m*i]);
	}
	return ret;
}

int main(){
	solution(2, 4, 2, 1);
	return 0;
}