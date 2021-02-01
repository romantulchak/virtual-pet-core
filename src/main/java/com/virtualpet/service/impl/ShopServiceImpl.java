package com.virtualpet.service.impl;

import com.virtualpet.model.Shop;
import com.virtualpet.repository.ShopRepository;
import com.virtualpet.service.ShopService;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    private ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository){
        this.shopRepository = shopRepository;
    }

    @Override
    public Shop getShop() {
        return shopRepository.findAll().stream().findFirst().orElse(createShop());
    }

    private Shop createShop(){
        Shop shop = new Shop();
        shopRepository.save(shop);
        return shop;
    }

}
