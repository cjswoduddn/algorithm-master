#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

vector<string> solution(int n, vector<int> m1, vector<int> m2){
	vector<string> ans;
	for(int i = 0; i < n; i++){
		int k = m1[i]|m2[i];
		string str;
		for(int j = 0; j < n; j++){
			int bit = (1<<j);
			if((k&bit) > 0) str.push_back('#');
			else str.push_back(' ');
		}
		reverse(str.begin(), str.end());
		ans.push_back(str);
	}
	return ans;
}

int main(){
	cout << (1<<2);
	return 0;
}