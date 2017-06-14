module PJW(input logic clk, input logic rst, input logic Valid, input logic[31:0] DataIn, output logic Ready, output logic[31:0] DataOut);
	logic[2:0] hashCount;
	logic[31:0] word, hash, x;

	always_ff @(posedge clk)
		if(rst) begin
			hash <= 32'd0;
			Ready <= 1'b1;
			DataOut <= 32'd0;
			hashCount <= 2'd0;
		end
		else begin
			if(Valid && Ready) begin
				hash = 32'd0;
				Ready <= 1'b0;
				hashCount <= 3'd0;
				
				word <= DataIn;
			end
			
			if(~Ready) begin
				hashCount <= hashCount + 3'd1;
				
				case(hashCount)
					3'd0: begin
						hash = (hash<<4) + (word[31:24]);
						if(x) hash = hash ^ (x >> 24);
						hash = hash & (~x);
					end
					3'd1: begin
						hash = (hash<<4) + (word[23:16]);
						if(x) hash = hash ^ (x >> 24);
						hash = hash & (~x);
					end
					3'd2: begin
						hash = (hash<<4) + (word[15:8]);
						if(x) hash = hash ^ (x >> 24);
						hash = hash & (~x);
					end
					3'd3: begin
						hash = (hash<<4) + (word[7:0]);
						if(x) hash = hash ^ (x >> 24);
						hash = hash & (~x);
					end
					3'd4: begin
						Ready <= 1'b1;
						DataOut <= hash;
					end
				endcase
			end
		end
		
		assign x = hash & 32'hF0000000;
		
endmodule