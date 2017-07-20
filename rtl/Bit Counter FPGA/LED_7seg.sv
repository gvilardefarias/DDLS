module LED_7seg(input logic clk, input logic rst, input logic[3:0] dataIn, output logic[6:0] seg);

	always_ff @(posedge clk)
		if(rst)
			seg = ~7'b1000000;
		else
			case(dataIn)
				 4'h0: seg = ~7'b0111111;
				 4'h1: seg = ~7'b0000110;
				 4'h2: seg = ~7'b1011011;
				 4'h3: seg = ~7'b1001111;
				 4'h4: seg = ~7'b1100110;
				 4'h5: seg = ~7'b1101101;				 
				 4'h6: seg = ~7'b1111101;				 
				 4'h7: seg = ~7'b0000111;				 
				 4'h8: seg = ~7'b1111111;
				 4'h9: seg = ~7'b1101111;
				 default: seg = ~7'b1000000;
			endcase
	
endmodule