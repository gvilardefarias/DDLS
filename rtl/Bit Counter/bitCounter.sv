module bitCounter(input logic clk, input logic rst, input logic Valid, input logic[31:0] DataIn, output logic Ready, output logic[31:0] DataOut);
	logic[31:0] word;
	logic[5:0] counterBits, posCounter;

	always_ff @(posedge clk)
		if(rst) begin
			word <= 32'd0;
			Ready <= 1'b1;
			DataOut <= 32'd0;
			posCounter <= 6'd0;
			counterBits <= 6'd0;			
		end
		else begin
			if(Valid && Ready) begin
				Ready <= 1'b0;
				word <= DataIn;
				posCounter <= 6'd0;
				counterBits <= 6'd0;
			end
			if(~Ready) begin
				posCounter <= posCounter + 6'd1;
				
				if(posCounter<6'd31)
					if(word[31])
						counterBits <= counterBits + word[posCounter];
					else begin
						if(~word[posCounter])
							counterBits <= counterBits + 6'd1;
					end
				else begin
					Ready <= 1'b1;
					posCounter <= 6'd0;
					DataOut <= counterBits;
				end
			end			
		end

endmodule