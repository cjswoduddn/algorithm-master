#include<string>
#include<iostream>
#include<vector>
#include<map>
using namespace std;
class nod{
public:
	map<string, nod*> child;

	void insert(vector<string>& arr, int idx){
		if(idx == arr.size()) return;
		auto iter = child.find(arr[idx]);
		if(iter == child.end())
			child[arr[idx]] = new nod();
		child[arr[idx]]->insert(arr, idx+1);
	}
	void dfs(int depth){
		for(auto iter = child.begin(); iter != child.end(); iter++){
			for(int i = 0; i < depth; i++)
				cout << "--";
			cout << iter->first << '\n';
			iter->second->dfs(depth+1);
		}
	}
};

int N, n;

int main(){
	cin >> N;
	nod* root = new nod;
	while(N--){
		cin >> n;
		vector<string> arr(n);
		for(int i = 0; i < n; i++)
			cin >> arr[i];
		root -> insert(arr, 0);
	}
	root->dfs(0);
	return 0;
}