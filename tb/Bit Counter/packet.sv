class packet extends uvm_sequence_item;
    rand integer Data;

    `uvm_object_utils_begin(packet_in)
        `uvm_field_int(Data, UVM_ALL_ON|UVM_HEX)
    `uvm_object_utils_end

    function new(string name="packet");
        super.new(name);
    endfunction
    
endclass