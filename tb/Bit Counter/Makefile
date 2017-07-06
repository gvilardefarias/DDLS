run:
	g++ -c -fpic external.cpp -o external.o
	g++ -shared -o external.so external.o
	irun -uvm -access +r top.sv -SV_LIB external.so
