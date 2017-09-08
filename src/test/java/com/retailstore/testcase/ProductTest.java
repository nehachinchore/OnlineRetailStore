package com.retailstore.testcase;
import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailstore.pojo.Category;
import com.retailstore.pojo.Product;

public class ProductTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    
    @Test
    public void serializesToJSON() throws Exception {
    	final Category cat2 = new Category(3, 0f, "categoryC");
	    final Product prod2 = new Product(2, 20f, cat2, "biscuits");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/Product.json"), Product.class));

        assertThat(MAPPER.writeValueAsString(prod2)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
    	final Category cat2 = new Category(3, 0f, "categoryC");
	    final Product prod2 = new Product(2, 20f, cat2, "biscuits");
        assertThat(MAPPER.readValue(fixture("fixtures/Product.json"), Product.class).getProductId())
                .isEqualTo(prod2.getProductId());
    }
}