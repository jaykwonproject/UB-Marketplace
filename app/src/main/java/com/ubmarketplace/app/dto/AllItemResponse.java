package com.ubmarketplace.app.dto;

import com.ubmarketplace.app.model.ResponseItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllItemResponse {
    private List<ResponseItem> item;
}
