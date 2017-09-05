import "DPI-C" context function int Dife(int now);

class Diference_refmod extends uvm_component;
    `uvm_component_utils(Diference_refmod)
    
    packet tr_in;
    packet tr_out;
    integer x;
    uvm_get_port #(packet) in;
    uvm_put_port #(packet) out;
    
    function new(string name = "Diference_refmod", uvm_component parent);
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
            $display("%d\n", tr_in.Data);
            tr_out.Data = Dife(tr_in.Data);

            out.put(tr_out);
        end
    endtask

endclass