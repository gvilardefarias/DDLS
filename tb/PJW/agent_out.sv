class agent_out extends uvm_agent;
    monitor_out mon;

    uvm_analysis_port #(packet) item_collected_port;

    `uvm_component_utils(agent_out)

    function new(string name = "agent_out", uvm_component parent = null);
        super.new(name, parent);
        item_collected_port = new("item_collected_port", this);
    endfunction

    virtual function void build_phase(uvm_phase phase);
        super.build_phase(phase);
        mon = monitor_out::type_id::create("mon_out", this);
    endfunction

    virtual function void connect_phase(uvm_phase phase);
        super.connect_phase(phase);
        mon.item_collected_port.connect(item_collected_port);
    endfunction

endclass