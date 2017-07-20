class sequence_in extends uvm_sequence #(packet);
    `uvm_object_utils(sequence_in)

    function new(string name="sequence_in");
        super.new(name);
    endfunction

    task body;
        packet tx;
        logic[8:0] cont = 9'd0;

        while(cont <= 9'd255) begin
            tx = packet::type_id::create("tx");
            start_item(tx);
            tx.Data = cont;
            finish_item(tx);

            cont++;
        end
    endtask

endclass