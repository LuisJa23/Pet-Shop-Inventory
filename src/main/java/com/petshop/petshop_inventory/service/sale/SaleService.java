package com.petshop.petshop_inventory.service.sale;

import com.petshop.petshop_inventory.dto.sale.SaleRegisterDTO;
import com.petshop.petshop_inventory.dto.sale.SaleResponseDTO;
import com.petshop.petshop_inventory.model.sale.Sale;
import com.petshop.petshop_inventory.repository.person.LoginRepository;
import com.petshop.petshop_inventory.repository.sale.SaleRepository;
import com.petshop.petshop_inventory.service.sale.add_ons.SaleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petshop.petshop_inventory.model.sale.add_ons.SaleDetails;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleDetailsService saleDetailsService;

    @Autowired
    private LoginRepository loginRepository;

    public SaleResponseDTO registerSale(SaleRegisterDTO saleRegisterDTO) {

        //TODO crear validacion dd que el login exista
        var login = loginRepository.findByUsernameData(saleRegisterDTO.username());

        var sale = new Sale(saleRegisterDTO, login);


        //ToDO problema
        var saleDetails = saleRegisterDTO.saleDetails().stream()
                .map(saleDetailsRegisterDTO -> saleDetailsService.registerSaleDetails(saleDetailsRegisterDTO, sale))
                .toList();

        sale.addSaleDetails(saleDetails);

        double total = saleDetails.stream()
                .mapToDouble(SaleDetails::getTotal)
                .sum();

        sale.setTotal(total);

        saleRepository.save(sale);
        return new SaleResponseDTO(sale);
    }
}
