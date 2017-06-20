typedef enum logic [2:0] {HashByte1, HashByte2, HashByte3, HashByte4, Finaly} States;

module PJW(input logic clk, input logic rst, input logic Valid, input logic[31:0] DataIn, output logic Ready, output logic[31:0] DataOut);
	States hashState;
	logic[31:0] word, hash, x;

	always_ff @(posedge clk)
		if(rst) begin
			hash <= 32'd0;
			Ready <= 1'b1;
			DataOut <= 32'd0;
			hashState <= HashByte4;
		end
		else begin
			if(Valid && Ready) begin
				hash = 32'd0;
				Ready <= 1'b0;
				hashState <= HashByte4;
				
				word <= DataIn;
			end
			if(~Ready)
				case(hashState)
					HashByte4: begin
						hash = (hash<<4) + (word[31:24]);
						if(x) hash = hash ^ (x >> 24);
						hash = hash & (~x);
						
						hashState <= HashByte3;
					end
					HashByte3: begin
						hash = (hash<<4) + (word[23:16]);
						if(x) hash = hash ^ (x >> 24);
						hash = hash & (~x);
						
						hashState <= HashByte2;
					end
					HashByte2: begin
						hash = (hash<<4) + (word[15:8]);
						if(x) hash = hash ^ (x >> 24);
						hash = hash & (~x);
						
						hashState <= HashByte1;
					end
					HashByte1: begin
						hash = (hash<<4) + (word[7:0]);
						if(x) hash = hash ^ (x >> 24);
						hash = hash & (~x);
						
						hashState <= Finaly;
					end
					Finaly: begin
						Ready <= 1'b1;
						DataOut <= hash;
						
						hashState <= HashByte4;
					end
				endcase
		end
		
		assign x = hash & 32'hF0000000;
		
endmodule