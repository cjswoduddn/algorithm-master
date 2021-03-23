#include <sstream>
#include <string>
#include <vector>
#include <map>

using namespace std;

vector<string> solution(vector<string> record){
	map<string, string> m;
	vector<pair<string, string> > lines;

	for(string& str : record){
		stringstream stream(str);
		vector<string> arr(3);
		for(int i = 0; stream >> arr[i]; i++);

		if(m.find(arr[1]) == m.end())
			m.insert({arr[1], arr[2]});

		if(arr[0] == "Enter"){
			lines.push_back({arr[1], "님이 들어왔습니다."});
			m[arr[1]] = arr[2];
		}
		else if(arr[0] == "Leave")
			lines.push_back({arr[1], "님이 나갔습니다."});
		else if(arr[0] == "Change"){
			m[arr[1]] = arr[2];
		}
	}

	vector<string> ret;
	for(pair<string, string>& ptr : lines){
		ret.push_back(m[ptr.first]+ptr.second);
	}
	return ret;
}

int main(){
	return 0;
}