package com.comet.wishgraphback;

import com.comet.wishgraphback.adapter.ItemsAdapter;
import com.comet.wishgraphback.adapter.OwnershipAdapter;
import com.comet.wishgraphback.model.dto.response.ItemDto;
import com.comet.wishgraphback.model.dto.response.ItemsByOwnerDto;
import com.comet.wishgraphback.model.dto.response.OwnersResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdaptersTest {

    @Autowired
    private ItemsAdapter itemsAdapter;
    @Autowired
    private OwnershipAdapter ownershipAdapter;

    @Test
    void itemsByOwner(){
        ItemsByOwnerDto itemsByOwner = itemsAdapter.getItemsByOwner("ETHEREUM:0x60f80121c31a0d46b5279700f9df786054aa5ee5");
        System.out.println(itemsByOwner);
    }

    @Test
    void itemById(){
        ItemDto itemById = itemsAdapter.getItemById("ETHEREUM:0x60f80121c31a0d46b5279700f9df786054aa5ee5:1222391");
        System.out.println(itemById);
    }

    @Test
    void itemsByCreator(){
        ItemsByOwnerDto itemsByCreator = itemsAdapter.getItemsByCreator("ETHEREUM:0x9f4cd5801bad95484b67b16097bbbbb3ad3e664c");
        System.out.println(itemsByCreator);
    }

    @Test
    void ownershipsByItem(){
        OwnersResponseDto ownershipsByItem = ownershipAdapter.getOwnershipsByItem("ETHEREUM:0x60f80121c31a0d46b5279700f9df786054aa5ee5:1222391");
        System.out.println(ownershipsByItem);
    }

}