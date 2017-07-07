class sequence_in extends uvm_sequence #(packet);
    `uvm_object_utils(sequence_in)

    function new(string name="sequence_in");
        super.new(name);
    endfunction

    task body;
        packet tx;
        int cont = 0;

        //Casos Base
        tx = packet::type_id::create("tx");
        start_item(tx);
        tx.Data = 32'b10000000000000000000000000000001;
        finish_item(tx);

        tx = packet::type_id::create("tx");
        start_item(tx);
        tx.Data = 32'b01111111111111111111111111111110;
        finish_item(tx);


        //Casos Limites
        tx = packet::type_id::create("tx");
        start_item(tx);
        tx.Data = 32'b10000000000000000000000000000000;
        finish_item(tx);

        tx = packet::type_id::create("tx");
        start_item(tx);
        tx.Data = 32'b11111111111111111111111111111111;
        finish_item(tx);
        

        tx = packet::type_id::create("tx");
        start_item(tx);
        tx.Data = 32'b01111111111111111111111111111111;
        finish_item(tx);

        tx = packet::type_id::create("tx");
        start_item(tx);
        tx.Data = 32'b00000000000000000000000000000000;
        finish_item(tx);

        //Aleatorios
        forever begin
            cont++;
            
            tx = packet::type_id::create("tx");
            start_item(tx);
            assert(tx.randomize());

            if(cont%2)
                while(tx.Data[31])
                    assert(tx.randomize());
            else
                while(~tx.Data[31])
                    assert(tx.randomize());

            finish_item(tx);
        end
    endtask

endclass