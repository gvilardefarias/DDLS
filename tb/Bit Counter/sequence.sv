class sequence_in extends uvm_sequence #(packet);
    `uvm_object_utils(sequence_in)

    function new(string name="sequence_in");
        super.new(name);
    endfunction

    task body;
        packet tx;
        logic[32:0] cont = 32'd0;

        while(cont <= 32'd4294967295) begin
            tx = packet::type_id::create("tx");
            start_item(tx);
            tx.Data = cont;
            finish_item(tx);

            cont++;
        end
    endtask

endclass