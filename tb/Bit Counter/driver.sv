typedef virtual DDLS_if Vif;

class driver extends uvm_driver #(packet);
    `uvm_component_utils(driver)

    Vif vif;
    event begin_record, end_record;

    function new(string name = "driver", uvm_component parent = null);
        super.new(name, parent);
    endfunction

    virtual function void build_phase(uvm_phase phase);
        super.build_phase(phase);
        assert(uvm_config_db#(Vif)::get(this, "", "vif", vif));
    endfunction

    virtual task run_phase(uvm_phase phase);
        super.run_phase(phase);
        fork
            reset_signals();
            get_and_drive(phase);
            record_tr();
        join
    endtask

    virtual protected task reset_signals();
        wait (vif.rst === 1);

        forever begin
            vif.Valid <= '0;
            vif.DataIn <= 'x;
            @(negedge vif.rst);
        end
    endtask

    virtual protected task get_and_drive(uvm_phase phase);
        wait(vif.rst === 1);
        @(negedge vif.rst);
        @(posedge vif.clk);
        
        forever begin
            seq_item_port.get(req);
            -> begin_record;
            drive_transfer(req);
        end
    endtask

    virtual protected task drive_transfer(packet tr);
        while(!vif.Ready)
            @(negedge vif.clk);

        vif.DataIn = tr.Data;
        vif.Valid = 1;

        @(negedge vif.Ready);
        vif.Valid = 0;
        
        -> end_record;
    endtask

    virtual task record_tr();
        forever begin
            @(begin_record);
            begin_tr(req, "driver");
            @(end_record);
            end_tr(req);
        end
    endtask

endclass