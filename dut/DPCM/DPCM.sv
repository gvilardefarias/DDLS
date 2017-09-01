typedef enum logic [1:0] {Diference, Saturation, Finish} State;
typedef enum logic [1:0] {Begin, Calcul, End} StateS;

module DPCM(DDLS_if.Basic iter);
	State state;
	StateS stateS;
	
	logic[31:0] Resp;

	DDLS_if diferenceIt(.clk(iter.clk), .rst(iter.rst));
	DDLS_if saturationIt(.clk(iter.clk), .rst(iter.rst));
	
	Diference d(.iter(diferenceIt.Basic));
	Saturation s(.iter(saturationIt.Basic));

	always_ff @(posedge iter.clk)
		if(iter.rst) begin
			Resp <= 32'd0;
			state <= Finish;
		end
		else begin
			case(state)
				Diference: begin
					iter.Ready <= 1'b0;
					diferenceIt.Valid <= iter.Valid;
					diferenceIt.DataIn <= iter.DataIn;
					
					if(diferenceIt.Ready) begin
						Resp <= diferenceIt.DataOut;
						state <= Saturation;
						stateS <= Begin;
					end
				end
				Saturation: begin
					diferenceIt.Valid <= iter.Valid;
					diferenceIt.DataIn <= iter.DataIn;
					
					case(stateS)
						Begin: begin
							if(saturationIt.Ready) begin
								saturationIt.Valid <= 1'b1;
								saturationIt.DataIn <= Resp;
								
								stateS <= Calcul;
							end
						end
						Calcul: begin
							if(~saturationIt.Ready) begin
								saturationIt.Valid <= 1'b0;
								
								stateS <= End;
							end
						end
						End: begin
							if(saturationIt.Ready) begin
								Resp <= saturationIt.DataOut;
								
								state <= Finish;
							end
						end
					endcase;
				end
				Finish: begin
					diferenceIt.Valid <= iter.Valid;
					diferenceIt.DataIn <= iter.DataIn;
					iter.Ready <= diferenceIt.Ready;
					iter.DataOut <= Resp;
					
					if(~diferenceIt.Ready)
						state <= Diference;
				end
			endcase
		end

		
endmodule