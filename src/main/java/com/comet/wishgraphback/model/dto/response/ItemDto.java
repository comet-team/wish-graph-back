package com.comet.wishgraphback.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private List<CreatorDto> creators;
    private String id;
    private MetaDto meta;
    private String contract;
}

