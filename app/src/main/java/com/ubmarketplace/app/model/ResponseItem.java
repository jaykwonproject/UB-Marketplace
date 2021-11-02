package com.ubmarketplace.app.model;

import com.ubmarketplace.app.manager.ImageManager;
import com.ubmarketplace.app.manager.UserManager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseItem {
    public enum imageType {
        THUMB, MEDIUM, LARGE
    }

    public ResponseItem(Item item, imageType imagetype, UserManager userManager, ImageManager imageManager) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.owner = ResponseItemOwner.builder()
                .userId(item.getUserId())
                .displayName(userManager.getDiisplayName(item.getUserId()))
                .build();
        this.category = item.getCategory();
        this.description = item.getDescription();
        this.price = item.getPrice();
        switch(imagetype) {
            case THUMB: {
                this.images = item.getImages().parallelStream()
                        .map(imageManager::getThumbUrl)
                        .collect(Collectors.toList());
                break;
            }
            case MEDIUM: {
                this.images = item.getImages().parallelStream()
                        .map(imageManager::getMediumUrl)
                        .collect(Collectors.toList());
                break;
            }
            case LARGE: {
                this.images = item.getImages().parallelStream()
                        .map(imageManager::getLargeUrl)
                        .collect(Collectors.toList());
                break;
            }
        }
        this.contactPhoneNumber = item.getContactPhoneNumber();
        this.meetingPlace = item.getMeetingPlace();
        this.createdTime = item.getCreatedTime();
    }

    private String itemId;
    private String name;
    private ResponseItemOwner owner;
    private String category;
    private String description;
    private Double price;
    private List<String> images;
    private String meetingPlace;
    private Long createdTime;
    private String contactPhoneNumber;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseItemOwner {
        private String userId;
        private String displayName;
    }
}
