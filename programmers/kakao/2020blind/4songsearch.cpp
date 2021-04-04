#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;
class nod{
public:
	bool be;
	int cnt;
	vector<nod*> nods;

	nod(){
		be = false;
		cnt = 0;
		nods.resize(26);
		fill(nods.begin(), nods.end(), nullptr);
	}

	void insert(string& str, int idx){
		if(str.size() == idx){
			be = true;
			return;
		}
		cnt++;
		int nc = str[idx]-'a';
		if(nods[nc] == nullptr)
			nods[nc] = new nod();
		nods[nc]->insert(str, idx+1);
	}

	int search(string& str, int idx){
		if(idx == str.size()){
			return cnt;
		}

		int nc = str[idx]-'a';
		if(nods[nc] == nullptr) return 0;
		return nods[nc]->search(str, idx+1);
	}
};

string getString(string& str){
	string ret;
	for(char c : str){
		if(c != '?') ret.push_back(c);
	}
	reverse(ret.begin(), ret.end());
	return ret;
}

string getString2(string& str){
	int idx = 0;
	while(str[idx] != '?') idx++;
	return str.substr(0, idx);
}

vector<int> solution(vector<string> words, vector<string> qu){
	vector<int> ans;
	vector<nod*> roots(10001);
	vector<nod*> broots(10001);

	for(int i = 0; i < 10001; i++){
		roots[i] = new nod();
		broots[i] = new nod();
	}
	for(string& str : words){
		roots[str.size()]->insert(str, 0);
		reverse(str.begin(), str.end());
		broots[str.size()]->insert(str, 0);
	}

	for(string& str : qu){
		if(str[0] == '?'){
			string sub = getString(str);
			ans.push_back(broots[str.size()]->search(sub, 0));
		}else{
			string sub = getString2(str);
			ans.push_back(roots[str.size()]->search(sub, 0));
		}
	}

	cout << '\n';

	for(int ptr : ans)
		cout << ptr << '\n';
	return ans;
}




int main(){
	vector<string> arr = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
	vector<string> brr = {"fro??", "????o", "fr???", "fro???", "pro?"};
	solution(arr, brr);
	return 0;
}