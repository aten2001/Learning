package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Helper {

    private static Logger       logger                = LoggerFactory.getLogger(Helper.class);

    private static final String INTERCEPTOR_CLASS     = "hibernate.util.interceptor_class";

    private Configuration       configuration;
    private SessionFactory      sessionFactory;

    private static final String HIBERNATE_CONFIG_BASE = "hibernate.cfg.xml";
    
    private static Helper selfRef = null;

    public static Helper getInstance(){
    	if(selfRef == null){
    		selfRef = new Helper();
    	}
    	return selfRef;
    }
    
    private Helper(){
    	this.configuration = new AnnotationConfiguration();
    	configuration.configure(HIBERNATE_CONFIG_BASE)
    }

    private void init(String hibernateConfigXml, String db) {
        try {
            configuration = new AnnotationConfiguration();
            // Read not only hibernate.properties, but also hibernate.cfg.xml
            configuration.configure(hibernateConfigXml);
            setInterceptor(null);
            setConfigurableDBParams(db);
            ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            //configuration.setProperty(Environment.)
            if (configuration.getProperty(Environment.SESSION_FACTORY_NAME) != null)
                configuration.buildSessionFactory();
            else
                sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            logger.error("Building SessionFactory failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public SessionFactory getSessionFactory() {
        SessionFactory sf = null;
        String sfName = configuration.getProperty(Environment.SESSION_FACTORY_NAME);
        if (sfName != null) {
            logger.debug("Looking up SessionFactory in JNDI.");
            try {
                sf = (SessionFactory) new InitialContext().lookup(sfName);
            } catch (NamingException ex) {
                throw new RuntimeException(ex);
            }
        } else
            sf = sessionFactory;
        if (sf == null)
            throw new IllegalStateException("SessionFactory not available.");
        return sf;
    }

    public void shutdown() {
        logger.debug("Shutting down Hibernate.");
        getSessionFactory().close();
        configuration = null;
        sessionFactory = null;
    }

    public void rebuildSessionFactory() {
        logger.debug("Using current Configuration for rebuild.");
        rebuildSessionFactory(configuration);
    }

    public void rebuildSessionFactory(Configuration cfg) {
        logger.debug("Rebuilding the SessionFactory from given Configuration.");
        synchronized (sessionFactory) {
            if (sessionFactory != null && !sessionFactory.isClosed())
                sessionFactory.close();
            if (cfg.getProperty(Environment.SESSION_FACTORY_NAME) != null)
                cfg.buildSessionFactory();
            else
                sessionFactory = cfg.buildSessionFactory();
            configuration = cfg;
        }
    }

    public SessionFactory registerInterceptorAndRebuild(Interceptor interceptor) {
        logger.debug("Setting new global Hibernate interceptor and restarting.");
        setInterceptor(interceptor);
        rebuildSessionFactory();
        return getSessionFactory();
    }

    public Interceptor getInterceptor() {
        return configuration.getInterceptor();
    }

    public void resetInterceptor() {
        logger.debug("Resetting global interceptor to configuration setting");
        setInterceptor(null);
    }

    private void setInterceptor(Interceptor interceptor) {
        String interceptorName = configuration.getProperty(INTERCEPTOR_CLASS);
        if (interceptor == null && interceptorName != null) {
            try {
                Class<?> interceptorClass = HibernateHelper.class.getClassLoader().loadClass(
                        interceptorName);
                interceptor = (Interceptor) interceptorClass.newInstance();
            } catch (Exception ex) {
                throw new RuntimeException("Could not configure interceptor: " + interceptorName,
                        ex);
            }
        }
        configuration.setInterceptor((interceptor != null) ? interceptor
                : EmptyInterceptor.INSTANCE);
    }

    private void setConfigurableDBParams(String db) {
        if (db == null || db == ADX_DB) {
            configuration.setProperty("hibernate.connection.driver_class",
                    PropertiesReader.getAdxDriverClass());
            configuration.setProperty("hibernate.connection.url",
                    PropertiesReader.getAdxConnectionUrl());
            configuration.setProperty("hibernate.connection.username",
                    PropertiesReader.getAdxConnectionUser());
            configuration.setProperty("hibernate.connection.password",
                    PropertiesReader.getAdxConnectionPassword());
            configuration.setProperty("bonecp.partitionCount",
                    PropertiesReader.getAdxPartitionCount());
            configuration.setProperty("bonecp.maxConnectionsPerPartition",
                    PropertiesReader.getAdxMaxConnectionsPerPartition());
            configuration.setProperty("bonecp.minConnectionsPerPartition",
                    PropertiesReader.getAdxMinConnectionsPerPartition());
            configuration.setProperty("bonecp.idleConnectionTestPeriod",
                    PropertiesReader.getAdxIdleConnectionTestPeriodInMin());
            configuration.setProperty("bonecp.maxConnectionAge",
                    PropertiesReader.getAdxMaxConnectionAgeInSec());
            configuration.setProperty("hibernate.cache.provider_configuration_file_resource_path",
                    URIConstants.PROPS_BASE_CONF_PATH + "ehcache.xml");
            configuration.setProperty("hibernate.cache.region.factory_class",
                    "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
            configuration.setProperty("hibernate.cache.use_structured_entries", "true");
            configuration.setProperty("hibernate.cache.provider_class",
                    PropertiesReader.getAdxCacheProviderClass());
        } else if (db == RTB_DB) {
            configuration.setProperty("hibernate.connection.driver_class",
                    PropertiesReader.getRtbDriverClass());
            configuration.setProperty("hibernate.connection.url",
                    PropertiesReader.getRtbConnectionUrl());
            configuration.setProperty("hibernate.connection.username",
                    PropertiesReader.getRtbConnectionUser());
            configuration.setProperty("hibernate.connection.password",
                    PropertiesReader.getRtbConnectionPassword());
            configuration.setProperty("bonecp.partitionCount",
                    PropertiesReader.getRtbPartitionCount());
            configuration.setProperty("bonecp.maxConnectionsPerPartition",
                    PropertiesReader.getRtbMaxConnectionsPerPartition());
            configuration.setProperty("bonecp.minConnectionsPerPartition",
                    PropertiesReader.getRtbMinConnectionsPerPartition());
            configuration.setProperty("bonecp.idleConnectionTestPeriod",
                    PropertiesReader.getRtbIdleConnectionTestPeriodInMin());
            configuration.setProperty("bonecp.maxConnectionAge",
                    PropertiesReader.getRtbMaxConnectionAgeInSec());
            configuration.setProperty("hibernate.cache.provider_class",
                    PropertiesReader.getRtbCacheProviderClass());
        } else if (db == DCO_DB) {
        	configuration.setProperty("hibernate.connection.driver_class",
                    PropertiesReader.getDcoDriverClass());
            configuration.setProperty("hibernate.connection.url",
                    PropertiesReader.getDcoConnectionUrl());
            configuration.setProperty("hibernate.connection.username",
                    PropertiesReader.getDcoConnectionUser());
            configuration.setProperty("hibernate.connection.password",
                    PropertiesReader.getDcoConnectionPassword());
            configuration.setProperty("bonecp.partitionCount",
                    PropertiesReader.getDcoPartitionCount());
            configuration.setProperty("bonecp.maxConnectionsPerPartition",
                    PropertiesReader.getDcoMaxConnectionsPerPartition());
            configuration.setProperty("bonecp.minConnectionsPerPartition",
                    PropertiesReader.getDcoMinConnectionsPerPartition());
            configuration.setProperty("bonecp.idleConnectionTestPeriod",
                    PropertiesReader.getDcoIdleConnectionTestPeriodInMin());
            configuration.setProperty("bonecp.maxConnectionAge",
                    PropertiesReader.getDcoMaxConnectionAgeInSec());
        } else if (db == SOCIAL_DB) { //ADDED FOR SOCIAL
          configuration.setProperty("hibernate.connection.driver_class",
                    PropertiesReader.getSocialDriverClass());
            configuration.setProperty("hibernate.connection.url",
                    PropertiesReader.getSocialConnectionUrl());
            configuration.setProperty("hibernate.connection.username",
                    PropertiesReader.getSocialConnectionUser());
            configuration.setProperty("hibernate.connection.password",
                    PropertiesReader.getSocialConnectionPassword());
            configuration.setProperty("bonecp.partitionCount",
                    PropertiesReader.getSocialPartitionCount());
            configuration.setProperty("bonecp.maxConnectionsPerPartition",
                    PropertiesReader.getSocialMaxConnectionsPerPartition());
            configuration.setProperty("bonecp.minConnectionsPerPartition",
                    PropertiesReader.getSocialMinConnectionsPerPartition());
            configuration.setProperty("bonecp.idleConnectionTestPeriod",
                    PropertiesReader.getSocialIdleConnectionTestPeriodInMin());
            configuration.setProperty("bonecp.maxConnectionAge",
                    PropertiesReader.getSocialMaxConnectionAgeInSec());
        }else {
            //throw Exception
        }  
    }
}
