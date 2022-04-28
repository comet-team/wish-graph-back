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
public class OwnersResponseDto {
    private Integer total;
    private String continuation;
    private List<OwnershipDto> ownerships;
}
