package com.comet.wishgraphback.mapper;

import com.comet.wishgraphback.model.dto.response.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Mapper {

    public NftDto mapRecommendedNftDto(ItemDto itemDto) {
        return NftDto.builder()
                .id(itemDto.getId())
                .title(getName(itemDto))
                .url(getImageUrl(itemDto))
                .build();
    }

    private String getName(ItemDto itemDto) {
        if (itemDto.getMeta() == null) {
            return null;
        }
        return itemDto.getMeta().getName();
    }

    private String getImageUrl(ItemDto itemDto) {
        if (itemDto.getMeta() == null) {
            return null;
        }
        return itemDto.getMeta().getContent().stream()
                .filter(content -> content.getType().equals("IMAGE"))
                .findAny()
                .map(ContentDto::getUrl)
                .orElse(null);
    }

    public RecommendedItemsDto mapRecommendedItemsDto(Map<String, Set<ItemDto>> items) {
        List<RecommendedAuthorDto> authors = new ArrayList<>();
        items.keySet().forEach(key -> {
            authors.add(RecommendedAuthorDto.builder()
                    .nft(items.get(key).stream().map(this::mapRecommendedNftDto).collect(Collectors.toList()))
                    .build());
        });
        return new RecommendedItemsDto(authors);
    }
}
