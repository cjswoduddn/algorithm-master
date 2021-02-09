#include<string>
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
class trie{
public:
	int cnt;
	vector<trie*> vec;
	trie(){
		cnt = 0;
		vec.resize(26);
		fill(vec.begin(), vec.end(), (trie*)NULL);
	}

	void insert(string& str, int cur){
		if(str.size() == cur){
			this->cnt++;
			return;
		}

		int charAt = str[cur]-'a';
		if(vec[charAt] == NULL){
			this->cnt++;
			vec[charAt] = new trie();
		}

		vec[charAt]->insert(str, cur+1);
	}

	int find(string& str, int cur){
		if(str.size() == cur)
			return 0;

		int charAt = str[cur]-'a';
		int ret = 1;
		if(cnt == 1) ret = 0;
		return ret + vec[charAt]->find(str, cur+1);
	}

	void dfsDelete(){
		for(trie* ptr : vec){
			if(ptr != NULL) ptr->dfsDelete();
			delete ptr;
		}
	}
};

int N;

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cout << fixed;
	cout.precision(2);
	while(!cin.eof()){
		cin >> N;
		vector<string> arr(N);
		trie* root = new trie();
		for(string& str : arr){
			cin >> str;
			root->insert(str, 0);
		}
		int ans = 0;
		for(string& str : arr){
			ans += root->find(str, 0);
			if(root->cnt == 1) ans++;
		}
		static int a = 1;
		cout << ans/(double)N << '\n';
		root->dfsDelete();
	}
	return 0;
}