module PJW(input logic clk, input logic rst, input logic Valid, input logic[31:0] DataIn, output logic Ready, output logic[31:0] DataOut);
	logic getNum, go;
	logic[31:0] hash, x;
	logic[31:0] words [6:0];
	logic[2:0] count1, count2, hashAux;

	always_ff @(posedge clk)
		if(rst) begin
			go <= 1'b0;
			hash <= 32'd0;
			Ready <= 1'b1;
			hashAux <= 2'd0;
			getNum <= 1'b1;
			DataOut <= 32'd0;
			
			for(int i=0;i<7;i++)
				words[i] <= 32'd0;
		end
		else begin
			if(Valid==1'b1) begin
				Ready <= 1'b0;
				getNum <= 1'b0;
				count1 <= count1 - 3'd1;
				
				if(count1==3'b1)
					go <= 1'b1;
					
				if(getNum) begin
					hash <= 32'd0;
					hashAux <= 2'd0;
					
					count1 <= DataIn;
					count2 <= DataIn;
				end
				else
					words[count1] <= DataIn;
			end
			else begin
				if(Ready==1'b0 && go) begin
					hashAux <= hashAux + 3'd1;
					
					case(hashAux)
						3'd0: begin
							hash = (hash<<4) + (words[count2][31:24]);
							if(x) hash = hash ^ (x >> 24);
							hash = hash & (~x);
						end
						3'd1: begin
							hash = (hash<<4) + (words[count2][23:16]);
							if(x) hash = hash ^ (x >> 24);
							hash = hash & (~x);
						end
						3'd2: begin
							hash = (hash<<4) + (words[count2][15:8]);
							if(x) hash = hash ^ (x >> 24);
							hash = hash & (~x);
						end
						3'd3: begin
							hash = (hash<<4) + (words[count2][7:0]);
							if(x) hash = hash ^ (x >> 24);
							hash = hash & (~x);
						end
					endcase
					
					if(hashAux==3'd4) begin
						count2 <= count2 - 3'd1;
						
						if(count2==3'd1)begin
							Ready <= 1'b1;
							DataOut <= hash;
						end
					end
				end
			end
		end
		
		assign x = hash & 32'hF0000000;
		
endmodule