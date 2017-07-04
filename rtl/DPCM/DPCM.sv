module DPCM(DDLS_if.Basic iter);
	logic[31:0] wordNow, wordBefore;

	always_ff @(posedge iter.clk)
		if(iter.rst) begin
			wordNow <= 32'd0;
			iter.Ready <= 1'b1;
			wordBefore <= 32'd0;
			iter.DataOut <= 32'd0;
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