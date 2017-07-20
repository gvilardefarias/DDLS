module DPCM(DDLS_if.Basic iter);
	logic[7:0] wordNow, wordBefore;

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
			if(~iter.Ready) begin
				if(wordNow>wordBefore)
					iter.DataOut <= wordNow - wordBefore;
				else
					iter.DataOut <= wordBefore - wordNow;
					
				iter.Ready <= 1'b1;
			end
		end
endmodule