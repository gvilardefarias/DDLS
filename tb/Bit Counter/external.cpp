#include <bits/stdc++.h>

using namespace std;

extern "C" int bitCounter(int x){
	int cont = 0;

	string str = bitset<8>(x).to_string();

	for(int i=1;i<str.size();i++){
		if(str[i]=='1' && str[0]=='1')	cont++;
		if(str[i]=='0' && str[0]=='0')	cont++;
	}

	cout << str << "\n";

	return cont;
}
