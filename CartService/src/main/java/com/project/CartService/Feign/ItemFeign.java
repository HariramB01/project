package com.project.CartService.Feign;


import com.project.CartService.Client.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ITEM-SERVICE")
public interface ItemFeign {

    @GetMapping("/{id}")
    Item getItemById(@PathVariable("id") Long id);

    @GetMapping("/{id}/quantity")
    boolean getItemQuantity(@PathVariable("id") Long id);
}
