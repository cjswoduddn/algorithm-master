#include <string>
#include <regex>
#include <iostream>
#include <stack>

using namespace std;

void myReplace(string& str){
	regex re("&(lt|gt|amp);");
	str = regex_replace(str, re, "");
}

bool hexValidation(string str){
	regex re("&x[0-9A-Za-z]{1,};");
	smatch match;
	while(regex_search(str, match, re)){
		if(match.str().size()%2 == 0) return false;
		str = match.suffix();
	}
	return true;
}

void hexReplace(string& str){
	regex re("&x[0-9A-Za-z]{2,};");
	str = regex_replace(str, re, "");
}

void pushStack(string& str, stack<string>& stk){
	regex re("<[0-9a-z]+/?>");
	smatch match;
	string cstr(str);
	while(regex_search(cstr, match, re)){
		string mstr = match.str();
		if(mstr[mstr.size()-2] != '/')
			stk.push(match.str());
		cstr = match.suffix();
	}

	str = regex_replace(str, re, "a");
}

bool stackValid(string& str, stack<string>& stk){
	regex re("</[0-9a-z]+>");
	smatch match;
	string cstr(str);
	while(regex_search(cstr, match, re)){
		string mstr = match.str();
		cstr = match.suffix();
		if(stk.empty() || mstr.substr(2) != stk.top().substr(1)) return false;
		stk.pop();
	}
	if(!stk.empty()) return false;
	str = regex_replace(str, re, "");
	return true;
}

bool lastValid(string& str){
	for(char c : str){
		if(c == '&' || c == '<' || c == '>') return false;
		if(c < 32 || c > 127) return false;
	}
	return true;
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	
	string str;
	while(getline(cin, str)){
		stack<string> stk;
		myReplace(str);
		if(!hexValidation(str)){
			cout << "invalid\n";
			continue;
		}
		hexReplace(str);
		pushStack(str, stk);
		if(!stackValid(str, stk)){
			cout << "invalid\n";
			continue;
		}
		if(!lastValid(str)){
			cout << "invalid\n";
			continue;
		}
		cout << "valid\n";
	}
	return 0;
}
