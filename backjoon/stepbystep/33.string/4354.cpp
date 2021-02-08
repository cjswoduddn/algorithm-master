#include<string>
#include<vector>
#include<iostream>
using namespace std;

vector<int> getPi(string& str){
	vector<int> pi(str.size(), 0);
	int j = 0;
	for(int i = 1; i < str.size(); i++){
		while(j > 0 && str[i] != str[j])
			j = pi[j-1];
		if(str[i] == str[j])
			pi[i] = ++j;
	}
	return pi;
}

int countZero(vector<int>& pi){
	return pi.size()-pi[pi.size()-1];
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	string str;
	while(true){
		cin >> str;
		if(str == ".") break;
		vector<int> pi = getPi(str);
		int s = str.size();
		int k = countZero(pi);
		int t = pi[str.size()-1];
		if(t%k == 0 && k*(s/k) == s)
			cout << s/k << '\n';
		else cout << 1 << '\n';
	}
	return 0;
}