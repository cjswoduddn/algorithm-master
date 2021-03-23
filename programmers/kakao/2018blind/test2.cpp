#include <string>
#include <vector>

using namespace std;

bool checkLength(string& str){
	if(str.size() >= 8 && str.size() <= 15) return true;
	return false;
}

bool checkChar(string& str){
	for(char c : str){
		if(c >= 'A' && c <= 'Z') continue;
		if(c >= 'a' && c <= 'z') continue;
		if(c >= '0' && c <= '9') continue;
		if(c == '~' || c == '!' || c == '@') continue;
		if(c == '#' || c == '$' || c == '%') continue;
		if(c == '^' || c == '&' || c == '*') continue;
		return false;
	}
	return true;
}

bool checkInc(string& str){
	int inc = 0;
	vector<bool> ck(4, false);
	for(char c : str){
		if(c >= 'A' && c <= 'Z' && !ck[0]) {
			ck[0] = true;
			inc++;
		}
		if(c >= 'a' && c <= 'z' && !ck[1]) {
			ck[1] = true;
			inc++;
		}
		if(c >= '0' && c <= '9' && !ck[2]) {
			ck[2] = true;
			inc++;
		}
		if(c == '~' || c == '!' || c == '@' && !ck[3]){
			ck[3] = true;
			inc++;
		}
		if(c == '#' || c == '$' || c == '%' && !ck[3]) {
			ck[3] = true;
			inc++;

		}
		if(c == '^' || c == '&' || c == '*' && !ck[3]) {
			ck[3] = true;
			inc++;
		}
	}
	if(inc >= 3) return true;
	return false;
}

bool checkSeq(string& str){
	int size = str.size()-4;
	for(int i = 0; i <= size; i++){
		char c = str[i];
		if(c != str[i+1]) continue;
		if(c != str[i+2]) continue;
		if(c != str[i+3]) continue;
		return false;
	}
	return true;
}

bool checkFive(string& str){
	vector<int> AZ(26, 0);
	vector<int> az(26, 0);
	vector<int> num(10, 0);
	vector<int> spe(9, 0);
	for(char c : str){
		if(c >= 'A' && c <= 'Z'){
			AZ[c-'A']++;
		}
		else if(c >= 'a' && c <= 'z'){
			az[c-'a']++;
		}
		else if(c >= '0' && c <= '9'){
			num[c-'0']++;
		}else if(c == '~'){
			spe[0]++;
		}else if(c == '!'){
			spe[1]++;
		}else if(c == '@'){
			spe[2]++;
		}else if(c == '#'){
			spe[3]++;
		}else if(c == '$'){
			spe[4]++;
		}else if(c == '%'){
			spe[5]++;
		}else if(c == '^'){
			spe[6]++;
		}else if(c == '&'){
			spe[7]++;
		}else if(c == '*'){
			spe[8]++;
		}
	}
	for(int ptr : AZ) if(ptr >= 5) return false;
	for(int ptr : az) if(ptr >= 5) return false;
	for(int ptr : num) if(ptr >= 5) return false;
	for(int ptr : spe) if(ptr >= 5) return false;
	return true;
}

vector<int> solution(string inp){
	vector<int> ans;
	if(gg)


}

int main(){
	return 0;
}