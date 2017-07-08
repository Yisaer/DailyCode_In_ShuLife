package com.yisa.cxf.rest.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Yisa on 2017/7/8.
 */
public class ProductServiceImpl implements ProductService {

    private static final List<Product> productList = new ArrayList<Product>();

    static {
        productList.add(new Product(1,"iphone 5s",5000));
        productList.add(new Product(2,"ipad mini",2500));
    }
    public List<Product> retrieveAllProducts() {
        Collections.sort(productList, new Comparator<Product>() {
            public int compare(Product o1, Product o2) {
                return (o2.getId()>o1.getId())?1:-1;
            }
        });
        return productList;
    }

    public Product retrieveProductById(long id) {
        Product targetProduct = null;
        for (Product product : productList) {
            if (product.getId() == id) {
                targetProduct = product;
                break;
            }
        }
        return targetProduct;
    }

    public List<Product> retrieveProductsByName(String name) {
        List<Product> targetProductList = new ArrayList<Product>();
        for (Product product : productList) {
            if (product.getName().contains(name)) {
                targetProductList.add(product);
            }
        }
        return targetProductList;
    }

    public Product createProduct(Product product) {
        productList.add(product);
        System.out.println(product);
        return product;
    }

    public Product updateProductById(long id, Map<String, Object> fieldMap) {
        Product product = retrieveProductById(id);
        if (product != null) {
            try {
                for (Map.Entry<String, Object> fieldEntry : fieldMap.entrySet()) {
                    Field field = Product.class.getDeclaredField(fieldEntry.getKey());
                    field.setAccessible(true);
                    field.set(product, fieldEntry.getValue());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    public Product deleteProductById(long id) {
        Product targetProduct = null;
        Iterator<Product> productIterator = productList.iterator();
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
            if (product.getId() == id) {
                System.out.println(product.toString());
                targetProduct = retrieveProductById(id);
                productIterator.remove();
                break;
            }
        }
        return targetProduct;
    }

}
