#include <bits/stdc++.h>

using namespace std;

int before = 0;

extern "C" int Dife(int now){
	int aux = before-now;

	before = now;

	if(aux<0)
		aux = -aux;;

	//printf("%d %d\n", aux, now);

	return aux;
}

extern "C" int Saturation(int now){
	if(now>200)
		return 200;
	else
		return now;
}
	
extern "C" int DPCM(int now){
	int aux = Dife(now);

	return Saturation(aux);
}
