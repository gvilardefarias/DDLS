module bitCounter(DDLS_if.Basic iter);
	logic[7:0] word;
	logic[3:0] counterBits, posCounter;

	always_ff @(posedge iter.clk)
		if(iter.rst) begin
			word <= 8'd0;
			iter.Ready <= 1'b1;
			posCounter <= 4'd0;
			counterBits <= 4'd0;
			iter.DataOut <= 8'd0;	
		end
		else begin
			if(iter.Valid && iter.Ready) begin
				posCounter <= 4'd0;
				iter.Ready <= 1'b0;
				counterBits <= 4'd0;
				word <= iter.DataIn;
			end
			if(~iter.Ready) begin
				posCounter <= posCounter + 4'd1;
				
				if(posCounter<4'd7)
					if(word[7])
						counterBits <= counterBits + word[posCounter];
					else begin
						if(~word[posCounter])
							counterBits <= counterBits + 4'd1;
					end
				else begin
					iter.Ready <= 1'b1;
					posCounter <= 4'd0;
					iter.DataOut <= counterBits;
				end
			end			
		end

endmodule