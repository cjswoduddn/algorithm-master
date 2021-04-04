#include <string>
#include <regex>
#include <vector>

using namespace std;

void convertRegex(string& str){
	for(int i = 0; i < str.size(); i++)
		if(str[i] == '?') str[i] = '.';
}

vector<int> solution(vector<string> words, vector<string> qu){
	vector<int> ans;
	vector<vector<string>> strs(10001);
	for(string& str : words){
		strs[str.size()].push_back(str);
	}

	for(string& str : qu){
		int cnt = 0;
		convertRegex(str);
		regex re(str);
		for(string& st : strs[str.size()]){
			if(regex_match(st, re)) cnt++;
		}
		ans.push_back(cnt);
	}

	return ans;

}

int main(){

}