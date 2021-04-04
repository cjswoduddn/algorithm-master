#include <string>
#include <vector>
#include <stack>
#include <iostream>

using namespace std;

int balancePoint(string& str){
	int left = 0, right = 0;
	int idx = 0;
	do{
		if(str[idx] == '(') left++;
		else right++;
		idx++;
	}while(idx < str.size() && left != right);
	return idx;
}

bool isValid(string& str){
	stack<char> stk;
	for(int i = 0; i < str.size(); i++){
		if(str[i] == '(') stk.push(str[i]);
		else {
			if(stk.empty()) return false;
			stk.pop();
		}
	}
	if(!stk.empty()) return false;
	return true;
}

string recur(string str){
	if(str.size() == 0) return "";
	int idx = balancePoint(str);

	string sub = str.substr(0, idx);
	if(isValid(sub)){
		return sub+recur(str.substr(idx));
	}

	string ret = "("+recur(str.substr(idx))+")";
	for(int i = 1; i < sub.size()-1; i++){
		char tmp = sub[i] == '(' ? ')' : '(';
		ret.push_back(tmp);
	}
	return ret;
}

string solution(string p){
	return recur(p);
}

int main(){
	cout << solution("()))((()");
	return 0;
}
