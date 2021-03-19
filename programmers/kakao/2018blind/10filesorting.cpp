#include <vector>
#include <string>
#include <algorithm>

using namespace std;
class nod{
public:
	string head;
	int num;
	int index;
};
class cmp{
public:
	bool operator()(const nod& a, const nod& b){
		if(a.head == b.head){
			if(a.num == b.num){
				return a.index < b.index;
			}
			return a.num < b.num;
		}
		return a.head < b.head;
	}
};

string convertHead(string str){
	string ret;
	for(int i = 0; i < str.size(); i++){
		if(str[i] >= 'A' && str[i] <= 'Z')
			ret.push_back(str[i]-'A'+'a');
		else ret.push_back(str[i]);
	}
	return ret;
}

int convertNum(string str){
	int size = 0;
	int idx = 0;
	int ret = 0;
	while(true){
		ret *= 10;
		ret += str[idx++]-'0';
		size++;
		if(idx == str.size()) break;
		if(size == 5) break;
		if(!(str[idx] >= '0' && str[idx] <= '9')) break;
	}
	return ret;
}
vector<string> solution(vector<string> files){

	vector<nod> nods;
	for(int i = 0; i < files.size(); i++){
		int fnum = 0;
		while(!(files[i][fnum] >= '0' && files[i][fnum] <= '9'))
			fnum++;
		string head = convertHead(files[i].substr(0, fnum));
		int num = convertNum(files[i].substr(fnum));
		nods.push_back({head, num, i});
	}
	sort(nods.begin(), nods.end(), cmp());
	vector<string> ans;
	for(nod n : nods){
		ans.push_back(files[n.index]);
	}
	return ans;
}

int main(){
	return 0;
}