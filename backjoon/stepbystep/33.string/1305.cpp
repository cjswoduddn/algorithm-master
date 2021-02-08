#include<string>
#include<iostream>
#include<vector>
using namespace std;

int N;
string str;

vector<int> getPi(){
	vector<int> pi(N, 0);
	int j = 0;
	for(int i = 1; i < N; i++){
		while(j > 0 && str[i] != str[j])
			j = pi[j-1];
		if(str[i] == str[j])
			pi[i] = ++j;
	}
	return pi;
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> str;
	vector<int> pi = getPi();
	cout << N-pi[N-1];
	return 0;
}
