#include<string>
#include<iostream>
#include<vector>
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

void kmp(string& fline, string& sline,
	vector<int>& pi, vector<int>& ans){
	int j = 0;
	for(int i = 0; i < fline.size(); i++){
		while(j > 0 && fline[i] != sline[j]){
			j = pi[j-1];
		}
		if(fline[i] == sline[j]){
			j++;
			if(j == sline.size()){
				ans.push_back(i-sline.size()+2);
				j = pi[j-1];
			}
		}
	}
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	string fline;
	getline(cin, fline);
	string sline;
	getline(cin, sline);

	vector<int> pi = getPi(sline);
	vector<int> ans;
	kmp(fline, sline, pi, ans);

	cout << ans.size() << '\n';
	for(int ptr : ans)
		cout << ptr << '\n';
	return 0;
}

