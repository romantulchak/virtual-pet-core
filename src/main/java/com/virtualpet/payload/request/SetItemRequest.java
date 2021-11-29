package com.virtualpet.payload.request;

import com.virtualpet.model.enums.EItemCategory;
import com.virtualpet.model.enums.EItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SetItemRequest {

    private long subId;
    private EItemCategory bodyPosition;
    private long itemId;
    private EItemType itemType;

    public SetItemRequest() {
    }
}
