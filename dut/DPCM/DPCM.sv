module DPCM(DDLS_if.Basic iter);
	logic[7:0] wordNow, wordBefore, DataOutD, DataOutS;
	
	Diference d(.Word1(wordNow), .Word2(wordBefore), .DataOut(DataOutD));
	Saturation s(.DataIn(DataOutD), .DataOut(DataOutS));

	always_ff @(posedge iter.clk)
		if(iter.rst) begin
			wordNow <= 8'd0;
			iter.Ready <= 1'b1;
			wordBefore <= 8'd0;
			iter.DataOut <= 8'd0;
		end
		else begin
			if(iter.Valid && iter.Ready) begin
				iter.Ready <= 1'b0;
				wordBefore <= wordNow;
				wordNow <= iter.DataIn;
			end
			if(~iter.Ready && ~iter.Valid) begin
				iter.DataOut <= DataOutS;
					
				iter.Ready <= 1'b1;
			end
		end
endmodule