class sequence_in extends uvm_sequence #(packet);
    `uvm_object_utils(sequence_in)

    function new(string name="sequence_in");
        super.new(name);
    endfunction

    task body;
        packet tx;
        
        for(int i=0;i<256;i++)
            for(int j=i;j<256;j++)
                for(int k=0;k<2;k++) begin
                    tx = packet::type_id::create("tx");
                    start_item(tx);

                    if(k)
                        tx.Data = j;
                    else
                        tx.Data = i;

                    finish_item(tx);
                end
    endtask

endclass