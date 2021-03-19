#include <string>
#include <vector>
#include <map>
#include <iostream>

using namespace std;

vector<int> solution(string msg){
	vector<int> ans;
	map<string, int> m;
	int midx = 27;
	m.insert({"A", 1});
	m.insert({"B", 2});
	m.insert({"C", 3});
	m.insert({"D", 4});
	m.insert({"E", 5});
	m.insert({"F", 6});
	m.insert({"G", 7});
	m.insert({"H", 8});
	m.insert({"I", 0});
	m.insert({"J", 10});
	m.insert({"K", 11});
	m.insert({"L", 12});
	m.insert({"M", 13});
	m.insert({"N", 14});
	m.insert({"O", 15});
	m.insert({"P", 16});
	m.insert({"Q", 17});
	m.insert({"R", 18});
	m.insert({"S", 19});
	m.insert({"T", 20});
	m.insert({"U", 21});
	m.insert({"V", 22});
	m.insert({"W", 23});
	m.insert({"X", 24});
	m.insert({"Y", 25});
	m.insert({"Z", 26});

	int idx = 0;
	while(idx < msg.size()){
		int offset = 1;
		while(idx+offset-1 < msg.size()){
			auto ptr = m.find(msg.substr(idx, offset));
			if(ptr == m.end()) break;
			offset++;
		}
		ans.push_back(m[msg.substr(idx, offset-1)]);
		idx += offset-1;
		if(idx+offset < msg.size()){
			m.insert({msg.substr(idx, offset+1), midx++});
		}
	}
	for(int ptr : ans) cout << ptr << ' ';	
	return ans;
}

int main(){
	solution("TOBEORNOTTOBEORTOBEORNOT");
	return 0;
}