#include <string>
#include <vector>
#include <set>
#include <map>
#include <cmath>
#include <iostream>

using namespace std;
class cmp{
public:
	bool operator()(int a, int b){
		int aidx = 0, bidx = 0;
		for(int i = 0; (1<<i) < a; i++){
			if(((1<<i)&a) > 0) aidx++;
		}
		for(int i = 0; (1<<i) < b; i++){
			if(((1<<i)&b) > 0) bidx++;
		}
		if(aidx == bidx)
			return a < b;
		return aidx < bidx; 
	}
};

string getString(vector<string>&arr, int ptr){
	string ret;
	for(int i = 0; i < arr.size(); i++){
		if((ptr&(1<<i)) > 0) ret += arr[i];
	}
	return ret;
}

bool checkMap(map<string, int>& m){
	for(auto iter = m.begin(); iter != m.end(); iter++)
		if(iter->second > 1) return false;
	return true;
}

int solution(vector<vector<string> > relation){
	int ret = 0;
	set<int, cmp> mySet;
	for(int i = 1; i < pow(2, relation[0].size()); i++)
		mySet.insert(i);

	while(!mySet.empty()){
		int ptr = *mySet.begin();
		map<string, int> m;
		for(vector<string>& arr : relation){
			string str = getString(arr, ptr);
			if(m.find(str) == m.end())
				m[str] = 0;
			m[str]++;
		}
		if(checkMap(m)){
			ret++;
			for(auto iter = mySet.begin(); iter != mySet.end();){
				if((*iter&ptr)==ptr)
					mySet.erase(iter++);
				else iter++;
			}
		}else{
			mySet.erase(ptr);
		}
	}
	return ret;
}

int main(){
	vector<vector<string> > relation = {{"100","ryan","music","2"},
	{"200","apeach","math","2"},{"300","tube","computer","3"},
	{"400","con","computer","4"},{"500","muzi","music","3"},
	{"600","apeach","music","2"}};
	int ans = solution(relation);
	cout << ans << '\n';
	return 0;
}