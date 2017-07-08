package demo.cxf.rest_cxf;

import com.yisa.cxf.rest.demo.JSONUtil;
import com.yisa.cxf.rest.demo.Product;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.Test;

import javax.print.attribute.standard.Media;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yisa on 2017/7/8.
 */
public class WebClientTest {
    private static final String baseAddress = "http://localhost:9090/ws/rest";

    private static final List<Object> providerList = new ArrayList<Object>();

    static {
        providerList.add(new JacksonJsonProvider());
    }


    @Test
    public void productServiceRetrieveAllProductsTest() {
        List productList = WebClient.create(baseAddress, providerList)
                .path("/products")
                .accept(MediaType.APPLICATION_JSON)
                .get(List.class);
        for (Object product : productList) {
            System.out.println(JSONUtil.toJSON(product));
        }
    }

    @Test
    public void productPostJson(){
        Product product = new Product(3,"Test",1000);
        Object res = WebClient.create(baseAddress)
                .path("/products")
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(product,Product.class);
        System.out.println(JSONUtil.toJSON(res));

    }

    @Test
    public void productDelete(){
         WebClient.create(baseAddress)
                .path("product/1")
                .accept(MediaType.APPLICATION_JSON)
                .delete();
    }
}

