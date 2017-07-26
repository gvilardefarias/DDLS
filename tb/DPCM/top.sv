import uvm_pkg::*;
`include "uvm_macros.svh"
`include "../../dut/DDLS_if.sv"
`include "../../dut/DPCM/Diference.sv"
`include "../../dut/DPCM/Saturation.sv"
`include "../../dut/DPCM/DPCM.sv"
`include "./packet.sv"
`include "./sequence.sv"
`include "./sequencer.sv"
`include "./driver.sv"
`include "./monitor.sv"
`include "./monitor_out.sv"
`include "./agent.sv"
`include "./agent_out.sv"
`include "./refmod.sv"
`include "./comparator.sv"
`include "./env.sv"
`include "./DPCM_test.sv"

module top;
  logic clk;
  logic rst;
  
  initial begin
    clk = 0;
    rst = 1;
    #22 rst = 0;
  end
  
  always #10 clk = !clk;
  
  logic [1:0] state;
  
  DDLS_if iter(.clk(clk), .rst(rst));
  
  DPCM DPCM(iter.Basic);

  initial begin
    `ifdef INCA
       $recordvars();
    `endif
    `ifdef VCS
       $vcdpluson;
    `endif
    `ifdef QUESTA
       $wlfdumpvars();
       set_config_int("*", "recording_detail", 1);
    `endif
    
    uvm_config_db#(Vif)::set(uvm_root::get(), "*.env_h.mst.*", "vif", iter);
    uvm_config_db#(Vif)::set(uvm_root::get(), "*.env_h.slv.*",  "vif", iter);
    
    run_test("DPCM_test");
  end
  
endmodule