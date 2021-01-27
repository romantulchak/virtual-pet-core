package com.virtualpet.payload.request;

import com.virtualpet.model.enums.EItemCategory;
import com.virtualpet.model.enums.EItemType;

public class SetItemRequest {

    private long subId;
    private EItemCategory bodyPosition;
    private long itemId;
    private EItemType itemType;

    public SetItemRequest() {
    }

    public SetItemRequest(long subId, EItemCategory bodyPosition, long itemId, EItemType itemType) {
        this.subId = subId;
        this.bodyPosition = bodyPosition;
        this.itemId = itemId;
        this.itemType = itemType;
    }

    public long getSubId() {
        return subId;
    }

    public void setSubId(long subId) {
        this.subId = subId;
    }

    public EItemCategory getBodyPosition() {
        return bodyPosition;
    }

    public void setBodyPosition(EItemCategory bodyPosition) {
        this.bodyPosition = bodyPosition;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public EItemType getItemType() {
        return itemType;
    }

    public void setItemType(EItemType itemType) {
        this.itemType = itemType;
    }
}
