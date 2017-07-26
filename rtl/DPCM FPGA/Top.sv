module Top(input logic CLOCK_50, input logic HPS_RESET_N, input logic Valid, input logic[9:2] SW, output logic[6:0] HEX0, output logic[6:0] HEX1, output logic[6:0] HEX2);
	logic[3:0] seg1, seg2, seg3;
	logic[6:0] dezSegs;
	
	DDLS_if iter(.clk(CLOCK_50), .rst(~HPS_RESET_N));
	
	DPCM dpcm(.iter(iter.Basic));
	LED_7seg decoder1(.clk(CLOCK_50), .rst(~HPS_RESET_N || ~iter.Ready), .dataIn(seg1), .seg(HEX0));
	LED_7seg decoder2(.clk(CLOCK_50), .rst(~HPS_RESET_N || ~iter.Ready), .dataIn(seg2), .seg(HEX1));
	LED_7seg decoder3(.clk(CLOCK_50), .rst(~HPS_RESET_N || ~iter.Ready), .dataIn(seg3), .seg(HEX2));
	
	always_comb begin
		iter.Valid = ~Valid;
		
		for(int i=0;i<8;i++)
			iter.DataIn[i] = SW[i+2];
			
		if(iter.DataOut<8'd100) begin
			seg3 = 4'd0;
			dezSegs = iter.DataOut;
		end
		else
			if(iter.DataOut<8'd200) begin
				seg3 = 4'd1;
				dezSegs = iter.DataOut - 8'd100;
			end
			else begin
				seg3 = 4'd2;
				dezSegs = iter.DataOut - 8'd200;
			end
		
		if(dezSegs<7'd10) begin
			seg2 = 4'd0;
			seg1 = dezSegs;
		end
		else
			if(dezSegs<7'd20) begin
				seg2 = 4'd1;
				seg1 = dezSegs - 7'd10;
			end
			else
				if(dezSegs<7'd30) begin
					seg2 = 4'd2;
					seg1 = dezSegs - 7'd20;
				end
				else
					if(dezSegs<7'd40) begin
						seg2 = 4'd3;
						seg1 = dezSegs - 7'd30;
					end
					else
						if(dezSegs<7'd50) begin
							seg2 = 4'd4;
							seg1 = dezSegs - 7'd40;
						end
						else
							if(dezSegs<7'd60) begin
								seg2 = 4'd5;
								seg1 = dezSegs - 7'd50;
							end
							else
								if(dezSegs<7'd70) begin
									seg2 = 4'd6;
									seg1 = dezSegs - 7'd60;
								end
								else
									if(dezSegs<7'd80) begin
										seg2 = 4'd7;
										seg1 = dezSegs - 7'd70;
									end
									else
										if(dezSegs<7'd90) begin
											seg2 = 4'd8;
											seg1 = dezSegs - 7'd80;
										end
										else begin
											seg2 = 4'd9;
											seg1 = dezSegs - 7'd90;
										end	
	end
	
endmodule