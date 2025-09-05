package br.com.gustavo.sustentacao.ecommerce.controller.mock;

import br.com.gustavo.sustentacao.ecommerce.DTO.ProdutoHybrisDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/mock/sap-hybris/products")
public class SapHybrisMockController {

    @GetMapping("/{sku}")
    public ProdutoHybrisDTO getProductBySku(@PathVariable String sku) {
        ProdutoHybrisDTO produto = new ProdutoHybrisDTO();
        produto.setSku(sku);
        produto.setNome("Produto Simulado " + sku);
        produto.setPreco(new BigDecimal("199.99"));
        produto.setEstoque(50);
        return produto;
    }
}
