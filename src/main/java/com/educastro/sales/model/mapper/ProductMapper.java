package com.educastro.sales.model.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.educastro.sales.model.dto.ProductDTO;
import com.educastro.sales.model.entities.Product;
import com.educastro.sales.model.entities.Category;
import com.educastro.sales.service.CategoryService;

import jakarta.annotation.PostConstruct;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;

    @PostConstruct
    public void init() {
        Converter<Integer, Category> categoryConverter = new Converter<Integer, Category>() {
            @Override
            public Category convert(MappingContext<Integer, Category> context) {
                return categoryService.findById(context.getSource());
            }
        };
        this.modelMapper.addMappings(new PropertyMap<ProductDTO, Product>() {
            @Override
            protected void configure() {
                using(categoryConverter).map(source.getCategory()).setCategory(null);
            }
        });
    }

    public Product map(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}
