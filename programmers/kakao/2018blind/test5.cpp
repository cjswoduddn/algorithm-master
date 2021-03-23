#include <string>
#include <sstream>
#include <vector>
#include <map>
#include <iostream>

using namespace std;

void getRules(vector<string>& flagRules, map<string, string>&rules,
	map<string, string>& alias){
	for(string& str : flagRules){
		stringstream stream(str);
		vector<string> arr;
		string line;

		while(stream >> line) arr.push_back(line);
		if(arr.size() == 2)
			rules.insert({arr[0], arr[1]});
		else if(arr.size() == 3){
			alias.insert({arr[0], arr[2]});
		}
	}
}

vector<string> parseCommand(string& str){
	vector<string> ret;
	stringstream stream(str);
	string line;
	while(stream >> line) 
		ret.push_back(line);
	return ret;
}

bool validProgram(string& str1, string& str2){
	if(str1 == str2) return true;
	return false;
}

bool checkNumber(string& str){
	if(str[0] == '-') return false;
	for(char c : str){
		if(!(c >= '0' && c <= '9')) return false;
	}
	return true;
}

bool checkString(string& str){
	if(str[0] == '-') return false;
	return true;
}

bool validCommands(vector<string>& commands, 
	map<string, string>& rules, map<string, string>& alias){
	map<string, int> commandCounter;
	string valueSTRING = "STRING";
	string valueNUMBER = "NUMBER";
	string valueNULL = "NULL";
	string valueSTRINGS = "STRINGS";
	string valueNUMBERS = "NUMBERS";
	int idx = 1;
	while(idx < commands.size()){
		auto aptr = alias.find(commands[idx]);
		if(aptr != alias.end())
			commands[idx] = aptr->second;
		auto ptr = rules.find(commands[idx]);

		auto cptr = commandCounter.find(commands[idx]);
		if(cptr != commandCounter.end()) return false;
		commandCounter.insert({commands[idx], 0});
		if(ptr == rules.end()) return false;
		if(ptr->second == valueSTRING){
			if(idx+1 == commands.size() || !checkString(commands[idx+1]))
				return false;
			idx += 2;
		}else if(ptr->second == valueNUMBER){
			if(idx+1 == commands.size() || !checkNumber(commands[idx+1]))
				return false;
			idx += 2;
		}else if(ptr->second == valueNULL){
			if(idx+1 == commands.size()) return true;
			if(commands[idx+1][0] != '-') return false;
			idx++;
		}else if(ptr->second == valueSTRINGS){
			if(idx+1 == commands.size()) return false;
			int sidx = 1;
			while(idx+sidx < commands.size() && commands[idx+sidx][0] != '-'){
				if(!checkString(commands[idx+sidx])) return false;
				sidx++;
			}
			idx += sidx;
		}else if(ptr->second == valueNUMBERS){
			if(idx+1 == commands.size()) return false;
			int sidx = 1;
			while(idx+sidx < commands.size() && commands[idx+sidx][0] != '-'){
				if(!checkNumber(commands[idx+sidx])) return false;
				sidx++;
			}
			idx += sidx;
		}
	}

	return true;
}

bool isValidCommand(vector<string>&parsedCommand, string& program,
	map<string, string>& rules, map<string, string>& alias){
	if(!validProgram(parsedCommand[0], program)){
		return false;
	}else if(!validCommands(parsedCommand, rules, alias)){
		return false;
	}
	return true;
}

vector<bool> solution(string program, vector<string> flag_rules
	, vector<string> commands){
	vector<bool> answer;
	map<string, string> rules;
	map<string, string> alias;

	getRules(flag_rules, rules, alias);

	for(string& str : commands){
		vector<string> parsedCommand = parseCommand(str);
		if(isValidCommand(parsedCommand, program, rules, alias)){
			answer.push_back(true);
		}else{
			answer.push_back(false);
		}
	}
	return answer;
}

int main(){
	string program = "line";
	vector<string> rules = {"-s STRINGS", "-n NUMBERS", "-e NULL"};
	vector<string> commands = {"line -n 100 102 -s hi -e"};
	vector<bool> ans = solution(program, rules, commands);
	cout << ans.size();
	// for(bool ptr : ans){
	// 	if(ptr) cout << "true" << '\n';
	// 	else cout << "false" << '\n';
	// }
	return 0;
}