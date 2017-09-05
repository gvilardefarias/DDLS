class Saturation_test extends uvm_test;
  Saturation_env env_h;
  Saturation_sequence_in seq;

  `uvm_component_utils(Saturation_test)

  function new(string name, uvm_component parent = null);
    super.new(name, parent);
  endfunction

  virtual function void build_phase(uvm_phase phase);
    super.build_phase(phase);
    env_h = Saturation_env::type_id::create("env_h", this);
    seq = Saturation_sequence_in::type_id::create("seq", this);
  endfunction
 
  task run_phase(uvm_phase phase);
    seq.start(env_h.mst.sqr);
  endtask

endclass