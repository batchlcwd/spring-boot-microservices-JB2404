package com.core.qualifier;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CocaCola implements ColdDrink {
    @Override
    public void drink() {
        System.out.println("Drinking CocaCola");
    }
}
