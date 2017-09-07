
package com.retailstore.main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.retailstore.dao.CategoryDao;
import com.retailstore.dao.ProductDao;
import com.retailstore.dao.TransactionDao;
import com.retailstore.resources.CategoryResource;
import com.retailstore.resources.ProductResource;
import com.retailstore.resources.BillResource;
import com.retailstore.services.CategoryService;
import com.retailstore.services.ProductService;
import com.retailstore.services.BillService;
import com.retailstore.services.TransactionService;

import io.dropwizard.Application;
import io.dropwizard.server.DefaultServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class OnlineRetailStoreApplication extends Application<OnlineRetailStoreConfiguration> {
	private static final String APP_NAME = "retail-store";
	private static final Logger log = Logger.getLogger(OnlineRetailStoreApplication.class.getName());

	public static void main(String[] args) throws Exception {
		new OnlineRetailStoreApplication().run(args);

	}

	@Override
	public String getName() {
		String hostName;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException uhe) {
			hostName = "localhost";
		}
		log.log(Level.INFO, "Application name is " + APP_NAME + "-" + hostName);
		return APP_NAME + "-" + hostName;
	}

	@Override
	public void initialize(Bootstrap<OnlineRetailStoreConfiguration> bootstrap) {
	}

	@Override
	public void run(OnlineRetailStoreConfiguration configuration, Environment environment) throws Exception {

		((DefaultServerFactory) configuration.getServerFactory()).setJerseyRootPath("/store/*");
		// Dao registeration
		ProductDao productDao = new ProductDao();
		CategoryDao cateogoryDao = new CategoryDao();
		TransactionDao transactionDao = new TransactionDao();

		// service registration
		ProductService productService = new ProductService(productDao);
		CategoryService categoryService = new CategoryService(cateogoryDao);
		TransactionService transactionService = new TransactionService(transactionDao);
		BillService retailStoreService = new BillService(productService, categoryService,transactionService);

		// context initialization
		OnlineRetailStoreContext.init(configuration);

		// resource registration
		environment.jersey().register(new BillResource(retailStoreService));
		environment.jersey().register(new ProductResource(productService));
		environment.jersey().register(new CategoryResource(categoryService));

		// cross domain
		final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
		cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
		cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER,
				"X-Requested-With,Content-Type,Accept,Origin,Authorization");
		cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST");
		cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/store/*");

	}

}