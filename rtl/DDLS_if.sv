interface DDLS_if(logic clk, logic rst);
	logic Valid, Ready;
	logic[31:0] DataIn, DataOut;
	
	modport Basic(input clk, rst, Valid, DataIn, output Ready, DataOut);
	
endinterface