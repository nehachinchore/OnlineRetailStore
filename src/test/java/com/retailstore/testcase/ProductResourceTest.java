package com.retailstore.testcase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.retailstore.pojo.Category;
import com.retailstore.pojo.Product;
import com.retailstore.resources.ProductResource;
import com.retailstore.services.ProductService;

import io.dropwizard.testing.junit.ResourceTestRule;

public class ProductResourceTest {

	private static final ProductService service = mock(ProductService.class);

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(new ProductResource(service)).build();

	private final Category cat = new Category(1, 0.1f, "categoryA");
	private final Product product = new Product(1, 15f, cat, "maggi");

	@Before
	public void setup() {
		when(service.get(eq(1))).thenReturn(product);
		// we have to reset the mock after each test because of the
		// @ClassRule, or use a @Rule as mentioned below.
		// reset(service);
	}

	@Test
	public void testGetProduct() {
		assertThat(resources.client().target("/product/1").request().get(Product.class).getProductName())
				.isEqualTo(product.getProductName());
		verify(service).get(1);
	}

	@Test
	public void testAddProduct() {
		Product newProduct = new Product(4, 16f, cat, "choclates");
		Response post = resources.client().target("/product/").request().post(Entity.json(newProduct));
		assertThat(post.getStatus()).isEqualTo(200);
	}
	
	@Test
	public void testDeleteProduct() {
		Response post = resources.client().target("/product/3").request().delete();
		assertThat(post.getStatus()).isEqualTo(200);
	}
}