/*package com.retailstore.testcase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.retailstore.pojo.Category;
import com.retailstore.pojo.Product;
import com.retailstore.resources.ProductResource;
import com.retailstore.services.ProductService;

import io.dropwizard.testing.junit.ResourceTestRule;

public class ProductResourceTest {

	private static final ProductService dao = mock(ProductService.class);

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new ProductResource(dao))
			.build();

	Category category = new Category(2, 0.2f, "categoryB");
	private final Product product = new Product(1, 1.5f, category, "paneer");

	@Before
	public void setup() {
		when(dao.get(eq(1))).thenReturn(product);
		// we have to reset the mock after each test because of the
		// @ClassRule, or use a @Rule as mentioned below.
		reset(dao);
	}

	@Test
	public void testGetPerson() {
		assertThat(resources.target("/store/product/1").request().get(Product.class)).isEqualTo(product);
		verify(dao).get(1);
	}
}*/