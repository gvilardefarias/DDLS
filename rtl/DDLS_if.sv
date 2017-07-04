interface DDLS_if();
	logic[31:0] DataIn, DataOut;
	logic clk, rst, Valid, Ready;
	
	modport Basic(input clk, rst, Valid, DataIn, output Ready, DataOut);
	
endinterface