module Diference(input logic[7:0] Word1, input logic[7:0] Word2, output logic [7:0] DataOut);
	
	always_comb
		if(Word1>Word2)
			DataOut = Word1 - Word2;
		else
			DataOut = Word2 - Word1;
endmodule