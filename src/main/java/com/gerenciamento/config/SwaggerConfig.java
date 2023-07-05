package com.gerenciamento.config;

import com.gerenciamento.controller.FretesController;
import com.gerenciamento.controller.ProdutoController;
import com.gerenciamento.controller.UsuarioController;
import com.gerenciamento.controller.VeiculoController;
import io.swagger.v3.oas.models.OpenAPI;
import jakarta.annotation.PostConstruct;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI();
    }

    @PostConstruct
    public void addRestControllers() {
        SpringDocUtils.getConfig().addRestControllers(FretesController.class);
        SpringDocUtils.getConfig().addRestControllers(ProdutoController.class);
        SpringDocUtils.getConfig().addRestControllers(UsuarioController.class);
        SpringDocUtils.getConfig().addRestControllers(VeiculoController.class);
    }
}
