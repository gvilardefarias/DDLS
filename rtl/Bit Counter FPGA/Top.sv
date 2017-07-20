module Top(input logic CLOCK_50, input logic HPS_RESET_N, input logic Valid, input logic[9:0] SW, output logic[6:0] HEX0);
	DDLS_if iter(.clk(CLOCK_50), .rst(~HPS_RESET_N));
	
	bitCounter bC(.iter(iter.Basic));
	LED_7seg decoder(.clk(CLOCK_50), .rst(~HPS_RESET_N || ~iter.Ready), .dataIn(iter.DataOut[3:0]), .seg(HEX0));
	
	always_comb begin
		iter.Valid = ~Valid;
		iter.DataIn[31:22] = SW;
		iter.DataIn[21] = ~SW[9];
		iter.DataIn[20] = ~SW[9];
		iter.DataIn[19] = ~SW[9];
		iter.DataIn[18] = ~SW[9];
		iter.DataIn[17] = ~SW[9];
		iter.DataIn[16] = ~SW[9];
		iter.DataIn[15] = ~SW[9];
		iter.DataIn[14] = ~SW[9];
		iter.DataIn[13] = ~SW[9];
		iter.DataIn[12] = ~SW[9];
		iter.DataIn[11] = ~SW[9];
		iter.DataIn[10] = ~SW[9];
		iter.DataIn[09] = ~SW[9];
		iter.DataIn[08] = ~SW[9];
		iter.DataIn[07] = ~SW[9];
		iter.DataIn[06] = ~SW[9];
		iter.DataIn[05] = ~SW[9];
		iter.DataIn[04] = ~SW[9];
		iter.DataIn[03] = ~SW[9];
		iter.DataIn[02] = ~SW[9];
		iter.DataIn[01] = ~SW[9];
		iter.DataIn[00] = ~SW[9];
	end
	
endmodule