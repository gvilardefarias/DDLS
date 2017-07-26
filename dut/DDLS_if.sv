interface DDLS_if(input logic clk, input logic rst);
	logic Valid, Ready;
	logic[7:0] DataIn, DataOut;
	
	modport Basic(input clk, rst, Valid, DataIn, output Ready, DataOut);
	
endinterface