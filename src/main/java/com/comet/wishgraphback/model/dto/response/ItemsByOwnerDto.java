package com.comet.wishgraphback.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ItemsByOwnerDto {
    private Integer total;
    private String continuation;
    List<ItemDto> items;
}
