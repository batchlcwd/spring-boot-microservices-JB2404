package com.core.scopes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("request")
public class Samosa {
    public Samosa() {
        System.out.println("new samosa is created");
    }
}
