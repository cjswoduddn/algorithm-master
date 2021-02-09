#include<string>
#include<vector>
#include<iostream>
#include<algorithm>
using namespace std;
class trie{
public:
	bool isLast;
	vector<trie*> vec;

	trie(){
		isLast = false;
		vec.resize('z'-'a'+1);
		fill(vec.begin(), vec.end(), (trie*)NULL);
	}

	void insert(string& str, int cnt){
		if(cnt == str.size()) {
			isLast = true;
			return;
		}

		int charAt = str[cnt]-'a';
		if(vec[charAt] == NULL)
			vec[charAt] = new trie();
		vec[charAt]->insert(str, cnt+1);
	}

	bool find(string& str, int cnt){
		if(cnt == str.size())
			return isLast;

		int charAt = str[cnt]-'a';
		if(vec[charAt] == NULL) return false;
		return vec[charAt]->find(str, cnt+1);
	}
};

int N, M;
string str;

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> M;
	trie* root = new trie();
	while(N--){
		cin >> str;
		root->insert(str, 0);
	}
	int ans = 0;
	while(M--){
		cin >> str;
		if(root->find(str, 0)) ans++;
	}
	cout << ans << '\n';
	return 0;
}