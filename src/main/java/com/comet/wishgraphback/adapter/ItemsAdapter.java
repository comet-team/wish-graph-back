package com.comet.wishgraphback.adapter;

import com.comet.wishgraphback.model.dto.response.ItemDto;
import com.comet.wishgraphback.model.dto.response.ItemsByOwnerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "items-adapter",
        url = "https://api.rarible.org/v0.1/items")
public interface ItemsAdapter {

    @GetMapping("/{itemId}")
    ItemDto getItemById(@PathVariable(name = "itemId") String itemId);

    @GetMapping("/byOwner")
    ItemsByOwnerDto getItemsByOwner(@RequestParam(name = "owner") String owner);

    @GetMapping("/byCreator")
    ItemsByOwnerDto getItemsByCreator(@RequestParam(name = "creator") String creator);
}
