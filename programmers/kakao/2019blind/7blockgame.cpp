#include <vector>
#include <queue>

using namespace std;
class nod{
public:
	int x, y, value;
};

bool isA(vector<vector<int> >& bd, int size, int x, int y){
	if(x+1 >= size || y+2 >= size) return false;
	int k = bd[x][y];
	if(bd[x+1][y] != k || bd[x+1][y+1] != k || bd[x+1][y+2] != k)
		return false;
	return true;
}

bool crushA(vector<vector<int> >& bd, int x, int y, int& ret){
	for(int i = 0; x-i >= 0; i++){
		if(bd[x-i][y+1] != 0 || bd[x-i][y+2] != 0)
			return false;
	}
	bd[x+1][y] = bd[x+1][y+1] = bd[x+1][y+2] = bd[x][y] = 0;
	ret++;
	return true;
}

bool isB(vector<vector<int> >& bd, int size, int x, int y){
	if(x+1 >= size || y-1 < 0 || y+1 >= size) return false;
	int k = bd[x][y];
	if(bd[x+1][y] != k || bd[x+1][y-1] != k || bd[x+1][y+1] != k)
		return false;
	return true;
}

bool crushB(vector<vector<int> >& bd, int x, int y, int& ret){
	for(int i = 0; x-i >= 0; i++){
		if(bd[x-i][y+1] != 0 || bd[x-i][y-1] != 0) 
			return false;
	}
	bd[x+1][y-1] = bd[x+1][y+1] = bd[x+1][y] = bd[x][y] = 0;
	ret++;
	return true;
}
bool isC(vector<vector<int> >& bd, int size, int x, int y){
	if(x+1 >= size || y-2 < 0) return false;
	int k = bd[x][y];
	if(bd[x+1][y-1] != k || bd[x+1][y] != k || bd[x+1][y-2] != k)
		return false;
	return true;
}

bool crushC(vector<vector<int> >& bd, int x, int y, int& ret){
	for(int i = 0; x-i >= 0; i++){
		if(bd[x-i][y-1] != 0 || bd[x-i][y-2] != 0) 
			return false;
	}
	bd[x+1][y] = bd[x+1][y-1] = bd[x+1][y-2] = bd[x][y] = 0;
	ret++;
	return true;
}

bool isD(vector<vector<int> >& bd, int size, int x, int y){
	if(x+2 >= size || y-1 < 0) return false;
	int k = bd[x][y];
	if(bd[x+1][y] != k || bd[x+2][y] != k || bd[x+2][y-1] != k)
		return false;
	return true;
}

bool crushD(vector<vector<int> >& bd, int x, int y, int& ret){
	for(int i = 0; x-i+1 >= 0; i++){
		if(bd[x+1-i][y-1] != 0) return false;
	}
	bd[x][y] = bd[x+1][y] = bd[x+2][y] = bd[x+2][y-1] = 0;
	ret++;
	return true;
}
bool isE(vector<vector<int> >& bd, int size, int x, int y){
	if(x+2 >= size || y+1 >= size) return false;
	int k = bd[x][y];
	if(bd[x+1][y] != k || bd[x+2][y] != k || bd[x+2][y+1] != k)
		return false;
	return true;
}

bool crushE(vector<vector<int> >& bd, int x, int y, int& ret){
	for(int i = 0; x-i+1 >= 0; i++){
		if(bd[x+1-i][y+1] != 0) return false;
	}
	bd[x][y] = bd[x+1][y] = bd[x+2][y] = bd[x+2][y+1] = 0;
	ret++;
	return true;
}


int solution(vector<vector<int> > bd){
	int size = bd.size();
	int ret = 0;
	queue<nod> q;

	for(int i = 0; i < size; i++){
		for(int j = 0; j < size; j++){
			if(bd[i][j] == 0) continue;
			if(isA(bd, size, i, j))
				q.push({i, j, 0});
			else if(isB(bd, size, i, j))
				q.push({i, j, 1});
			else if(isC(bd, size, i ,j))
				q.push({i, j, 2});
			else if(isD(bd, size, i, j))
				q.push({i, j, 3});
			else if(isE(bd, size, i, j))
				q.push({i, j, 4});
		}
	}


	while(true){
		int pre = ret;
		int size = q.size();
		for(int i = 0; i < size; i++){
			nod cur = q.front();
			q.pop();
			if(cur.value == 0){
				if(!crushA(bd, cur.x, cur.y, ret))
					q.push(cur);
			}
			else if(cur.value == 1){
				if(!crushB(bd, cur.x, cur.y, ret))
					q.push(cur);
			}
			else if(cur.value == 2){
				if(!crushC(bd, cur.x, cur.y, ret))
					q.push(cur);
			}
			else if(cur.value == 3){
				if(!crushD(bd, cur.x, cur.y, ret))
					q.push(cur);
			}
			else if(cur.value == 4){
				if(!crushE(bd, cur.x, cur.y, ret))
					q.push(cur);
			}
		}
		if(ret == pre) break;
	}


	return ret;
}

int main(){
	return 0;
}