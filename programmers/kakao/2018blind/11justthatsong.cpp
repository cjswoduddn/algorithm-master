#include <string>
#include <vector>
#include <sstream>
using namespace std;

int getTime(string& str){
	int hour = stoi(str.substr(0, 2))*60;
	int minute = stoi(str.substr(3, 2));
	return hour+minute;
}

string convertShop(string& str){
	string ret;
	int size = str.size();
	int idx = 0;
	while(idx < size){
		if(idx+1 < size && str[idx+1] == '#'){
			ret.push_back(str[idx]-'A'+'a');
			idx++;
		}else{
			ret.push_back(str[idx]);
		}
		idx++;
	}
	return ret;
}

vector<int> getPi(string& str){
	int j = 0;
	int size = str.size();
	vector<int> pi(size, 0);
	for(int i = 1; i < size; i++){
		while(j > 0 && str[i] != str[j])
			j = pi[j];
		if(str[i] == str[j])
			pi[i] = ++j;
	}
	return pi;
}

bool isInclude(string& str1, string& str2, vector<int>& pi){
	int j = 0, size = str1.size();
	for(int i = 0; i < size; i++){
		while(j > 0 && str1[i] != str2[j])
			j = pi[j];
		if(str1[i] == str2[j]){
			j++;
			if(j == pi.size()) return true;
		}
	}
	return false;
}

string solution(string m, vector<string> mus){

	string ret = "(None)";
	int cnt = 0;

	for(string& str : mus){
		stringstream stream(str);
		vector<string> arr(4);
		for(int i = 0; getline(stream, arr[i], ','); i++);
		int duration = getTime(arr[1])-getTime(arr[0]);	
		string conv = convertShop(arr[3]);
		string conv2 = convertShop(m);

		int idx = 0;
		while(duration > conv.size())
			conv.push_back(conv[idx++]);
		conv = conv.substr(0, duration);

		vector<int> pi = getPi(conv2);
		if(isInclude(conv, conv2, pi)){
			if(duration > cnt) ret = arr[2];
		}
	}

	return ret;
}

int main(){
	return 0;
}