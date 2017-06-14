module DPCM(input logic clk, input logic rst, input logic Valid, input logic[31:0] DataIn, output logic Ready, output logic[31:0] DataOut);
	logic[31:0] wordNow, wordBefore;

	always_ff @(posedge clk)
		if(rst) begin
			Ready <= 1'b1;
			DataOut <= 32'd0;
			wordNow <= 32'd0;
			wordBefore <= 32'd0;
		end
		else begin
			if(Valid && Ready) begin
				Ready <= 1'b0;
				wordNow <= DataIn;
				wordBefore <= wordNow;
			end
			if(~Ready) begin
				if(wordNow>wordBefore)
					DataOut <= wordNow - wordBefore;
				else
					DataOut <= wordBefore - wordNow;
					
				Ready <= 1'b1;
			end
		end
endmodule