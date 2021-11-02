package com.ubmarketplace.app.dto;

import com.ubmarketplace.app.model.ResponseItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetItemResponse {
    private ResponseItem item;
}
