#include <string>
#include <sstream>
#include <iostream>
#include <vector>

using namespace std;

int main(){
	string str = "1,2,3,4,5";

	stringstream stream(str);

	string line;
	while(getline(stream, line, ','))
		cout << line << '\n';

	string str2 = "1 2 3 4 5";
	stringstream stream2(str2);
	while(stream2 >> line){
		cout << line << '\n';
	}
	return 0;
}
