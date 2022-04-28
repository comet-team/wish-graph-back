package com.comet.wishgraphback.adapter;

import com.comet.wishgraphback.model.dto.response.OwnersResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ownership-adapter",
        url = "https://api.rarible.org/v0.1/ownerships")
public interface OwnershipAdapter {

    @GetMapping("/byItem")
    OwnersResponseDto getOwnershipsByItem(@RequestParam(name = "itemId") String itemId);

}
