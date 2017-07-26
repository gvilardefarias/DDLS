module Top(input logic CLOCK_50, input logic HPS_RESET_N, input logic Valid, input logic[9:0] SW, output logic[6:0] HEX0);
	DDLS_if iter(.clk(CLOCK_50), .rst(~HPS_RESET_N));
	
	bitCounter bC(.iter(iter.Basic));
	LED_7seg decoder(.clk(CLOCK_50), .rst(~HPS_RESET_N || ~iter.Ready), .dataIn(iter.DataOut[3:0]), .seg(HEX0));
	
	always_comb begin
		iter.Valid = ~Valid;
		
		for(int i=0;i<8;i++)
			iter.DataIn[i] = SW[i+2];
	end
	
endmodule