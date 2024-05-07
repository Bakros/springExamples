package com.example.JavaExamples.InterfacesDemo;

import java.util.List;

/**
 * La implementación de una interfaz que a su vez extiende (extend) de otra interfaz debe implementar TODAS los métodos,
 * de las 2 interfaces.
 */
public class ImplHijo implements IntefaceHijo{

    @Override
    public String getBrand(String model) {
        return null;
    }

    @Override
    public List<String> getAllBrands() {
        return null;
    }

    @Override
    public Object getBean(String name) throws Exception {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
