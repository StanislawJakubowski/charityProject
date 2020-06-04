package pl.coderslab.charity.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class CategoryConventer implements Converter<String, Category> {

    @Autowired
    CategoryService categoryService;

    @Override
    public Category convert(String s) {
        return categoryService.findById(Long.parseLong(s));
    }
}
