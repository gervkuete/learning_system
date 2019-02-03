package com.gervkuete.contextListener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

// Listener used to initialize log4j when the app is being started

public class ContextListener implements ServletContextListener {

	private static Logger logger = Logger.getLogger(ContextListener.class);
	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {

		// Log4j initialization.
		ServletContext context = sce.getServletContext();
		String log4jConfigFile = context.getInitParameter("log4j-config-location");
		String filePath = context.getRealPath("") + File.separator +log4jConfigFile;

		PropertyConfigurator.configure(filePath);
		logger.info("Writting in file");
	}

}
