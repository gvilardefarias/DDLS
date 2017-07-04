module bitCounter(DDLS_if.Basic iter);
	logic[31:0] word;
	logic[5:0] counterBits, posCounter;

	always_ff @(posedge iter.clk)
		if(iter.rst) begin
			word <= 32'd0;
			iter.Ready <= 1'b1;
			posCounter <= 6'd0;
			counterBits <= 6'd0;
			iter.DataOut <= 32'd0;	
		end
		else begin
			if(iter.Valid && iter.Ready) begin
				posCounter <= 6'd0;
				iter.Ready <= 1'b0;
				counterBits <= 6'd0;
				word <= iter.DataIn;
			end
			if(~iter.Ready) begin
				posCounter <= posCounter + 6'd1;
				
				if(posCounter<6'd31)
					if(word[31])
						counterBits <= counterBits + word[posCounter];
					else begin
						if(~word[posCounter])
							counterBits <= counterBits + 6'd1;
					end
				else begin
					iter.Ready <= 1'b1;
					posCounter <= 6'd0;
					iter.DataOut <= counterBits;
				end
			end			
		end

endmodule