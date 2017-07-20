#include <bits/stdc++.h>

using namespace std;

int before = 0;

extern "C" int DPCM(int now){
	cout << now << " " << before << "\n";

	int aux = before-now;

	before = now;

	if(aux>0)
		return aux;

	return -aux;
}
