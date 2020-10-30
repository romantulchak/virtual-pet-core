package com.virtualpet.services;

import com.virtualpet.models.Items.Sword;
import com.virtualpet.models.Sub;

import java.util.List;

public interface InventoryService {

    List<Sword> getSwords(Sub sub);

}
