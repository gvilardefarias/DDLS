class Saturation_sequence_in extends uvm_sequence #(packet);
    `uvm_object_utils(Saturation_sequence_in)

    function new(string name="sequence_in");
        super.new(name);
    endfunction

    task body;
        packet tx;
        
        for(int i=0;i<256;i++) begin
            tx = packet::type_id::create("tx");
            start_item(tx);

            tx.Data = i;

            finish_item(tx);
        end
    endtask

endclass