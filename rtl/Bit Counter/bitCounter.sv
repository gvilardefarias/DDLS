module bitCounter(input logic clk, input logic rst, input logic Valid, input logic[31:0] DataIn, output logic Ready, output logic[31:0] DataOut);
	logic[31:0] word;
	logic[5:0] counter;

	always_ff @(posedge clk)
		if(rst) begin
			word <= 32'd0;
			Ready <= 1'b1;
			counter <= 6'd0;
			DataOut <= 32'd0;
		end
		else begin
			if(Valid==1'b1) begin
				Ready <= 1'b0;
				word <= DataIn;
				counter <= 6'd0;
			end
			else begin
				if(Ready==1'b0)
					for(int i=0;i<31;i++)
						if(word[31])
							counter += word[i];
						else
							if(~word[i])
								counter += 6'd1;
				
				Ready <= 1'b1;
				DataOut <= counter;
			end
		end

endmodule