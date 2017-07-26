module DPCM(DDLS_if.Basic iter);
	logic[7:0] wordNow, wordBefore, preSaturation;

	always_ff @(posedge iter.clk)
		if(iter.rst) begin
			wordNow <= 8'd0;
			iter.Ready <= 1'b1;
			wordBefore <= 8'd0;
			iter.DataOut <= 8'd0;
			preSaturation = 8'd0;
		end
		else begin
			if(iter.Valid && iter.Ready) begin
				iter.Ready <= 1'b0;
				wordBefore <= wordNow;
				wordNow <= iter.DataIn;
			end
			if(~iter.Ready && ~iter.Valid) begin
				if(wordNow>wordBefore)
					preSaturation = wordNow - wordBefore;
				else
					preSaturation = wordBefore - wordNow;
				
				if(preSaturation>8'd200)
					iter.DataOut <= 8'd200;
				else
					iter.DataOut <= preSaturation;
					
				iter.Ready <= 1'b1;
			end
		end
endmodule