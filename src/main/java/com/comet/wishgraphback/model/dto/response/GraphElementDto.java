package com.comet.wishgraphback.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GraphElementDto {
    private Integer type;
    private String source;
    private String target;
}
