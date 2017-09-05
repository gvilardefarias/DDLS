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
`include "./Diference_refmod.sv"
`include "./refmod.sv"
`include "./comparator.sv"
`include "./Diference_env.sv"
`include "./env.sv"
`include "./Diference_test.sv"
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
  
  DDLS_if DPCM_iter(.clk(clk), .rst(rst));
  DDLS_if Diference_iter(.clk(clk), .rst(rst));
  
  DPCM DPCM(DPCM_iter.Basic);
  Diference Diference(Diference_iter.Basic);

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
    
    uvm_config_db#(Vif)::set(uvm_root::get(), "*.env_h.mst.*", "vif", Diference_iter);
    uvm_config_db#(Vif)::set(uvm_root::get(), "*.env_h.slv.*",  "vif", Diference_iter);
    
    run_test("Diference_test");
    //run_test("Saturation_test");

    uvm_config_db#(Vif)::set(uvm_root::get(), "*.env_h.mst.*", "vif", DPCM_iter);
    uvm_config_db#(Vif)::set(uvm_root::get(), "*.env_h.slv.*",  "vif", DPCM_iter);

    run_test("DPCM_test");
  end
  
endmodule