import "DPI-C" context function int ELFHash(logic[31:0] x);

class refmod extends uvm_component;
    `uvm_component_utils(refmod)
    
    packet tr_in;
    packet tr_out;
    logic[31:0] x;
    uvm_get_port #(packet) in;
    uvm_put_port #(packet) out;
    
    function new(string name = "refmod", uvm_component parent);
        super.new(name, parent);
        in = new("in", this);
        out = new("out", this);
    endfunction
    
    virtual function void build_phase(uvm_phase phase);
        super.build_phase(phase);
        tr_out = packet::type_id::create("tr_out", this);
    endfunction
    
    virtual task run_phase(uvm_phase phase);
        super.run_phase(phase);
        
        forever begin
            in.get(tr_in);
            
            tr_out.Data = ELFHash({tr_in.Data[7:0], tr_in.Data[15:8], tr_in.Data[23:16], tr_in.Data[31:24]});

            out.put(tr_out);
        end
    endtask

endclass