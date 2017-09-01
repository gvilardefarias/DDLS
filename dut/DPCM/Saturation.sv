module Saturation(DDLS_if.Basic iter);
	logic[7:0] word;
	
	always_ff @(posedge iter.clk)
		if(iter.rst) begin
			word <= 8'd0;
			iter.Ready <= 1'b1;
			iter.DataOut <= 8'd0;
		end
		else begin
			if(iter.Valid && iter.Ready) begin
				iter.Ready <= 1'b0;
				word <= iter.DataIn;
			end
			if(~iter.Ready && ~iter.Valid) begin
				if(word>8'd200)
					iter.DataOut <= 8'd200;
				else
					iter.DataOut <= word;
					
				iter.Ready <= 1'b1;
			end
		end
		
endmodule