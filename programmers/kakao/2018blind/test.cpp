#include <string>
#include <sstream>
#include <vector>

using namespace std;

string solution(vector<string> tb, vector<string> lag,
	vector<int> pref){

	string voca;
	vector<string> tName(5);
	vector<vector<string> > arr(5);

	for(int i = 0; i < 5; i++){
		stringstream stream(tb[i]);
		stream >> tName[i];
		while(stream >> voca)
			arr[i].push_back(voca);
	}

	vector<int> score = {5, 4, 3, 2, 1};
	vector<int> board(5, 0);

	for(int i = 0; i < 5; i++){
		for(int j = 0; j < lag.size(); j++){
			for(int k = 0; k < 5; k++){
				if(lag[j] == arr[i][k])
					board[i] += pref[j]*score[k];
			}
		}
	}

	int value = -1;
	int ret = 0;
	for(int i = 0; i < 5; i++){
		if(board[i] > value){
			value = board[i];
			ret = i;
		}else if(board[i] == value && tName[ret] > tName[i]){
			value = board[i];
			ret = i;
		}
	}
	return tName[ret];
}

int main(){
	return 0;
}
