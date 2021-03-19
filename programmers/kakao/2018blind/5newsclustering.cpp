#include <string>
#include <vector>
#include <iostream>
using namespace std;

void getPure(string& str){
	for(char& c : str){
		if(c >= 'A' && c <= 'Z'){
			c += 'a'-'A';
		}
	}
}

vector<int> getPi(string& str){
	vector<int> pi(26*26, 0);
	int size = str.size()-1;
	for(int i = 0; i < size; i++){
		if(!(str[i] >= 'a' && str[i] <= 'z')) continue;
		if(!(str[i+1] >= 'a' && str[i+1] <= 'z')) continue;
		int f = 26*(str[i]-'a');
		int s = str[i+1]-'a';
		pi[f+s]++;
	}
	return pi;
}

int solution(string str1, string str2){
	getPure(str1);
	getPure(str2);

	vector<int> pi1 = getPi(str1);
	vector<int> pi2 = getPi(str2);

	int head = 0;
	int tail = 0;

	for(int i = 0, size = pi1.size(); i < size; i++){
		head += pi1[i] < pi2[i] ? pi1[i] : pi2[i];
		tail += pi1[i] > pi2[i] ? pi1[i] : pi2[i];
	}

	double ret = 1;
	if(tail != 0) ret = head/(double)tail;
	return (int)(ret*65536);
}

int main(){
	// string str1 = "handshake";
	// string str2 = "shake hands";
	string str1 = "aa1+aa2";
	string str2 = "AAAA12";
	cout << solution(str1, str2);
	return 0;
}