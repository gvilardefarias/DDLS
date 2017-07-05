class sequence_in extends uvm_sequence #(packet);
    `uvm_object_utils(sequence_in)

    function new(string name="sequence_in");
        super.new(name);
    endfunction

    task body;
        packet tx;

        forever begin
            tx = packet::type_id::create("tx");
            start_item(tx);
            assert(tx.randomize());
            finish_item(tx);
        end
    endtask

endclass