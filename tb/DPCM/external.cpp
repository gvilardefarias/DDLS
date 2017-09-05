#include <bits/stdc++.h>

using namespace std;

int before = 0;

extern "C" int Dife(int now){
	int aux = before-now;

	before = now;

	if(aux<0)
		aux = -aux;;

	printf("Dif: %d\n", aux);

	return aux;
}

extern "C" int Satu(int now){
	int aux = -1;

	if(now>200)
		aux = 200;
	else
		aux = now;

	printf("Sat: %d\n", aux);

	return aux;
}
	
extern "C" int DPCM(int now){
	printf("DPCM: %d %d\n", before, now);
	
	int aux = Dife(now);

	return Satu(aux);
}
