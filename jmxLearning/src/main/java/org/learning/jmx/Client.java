package org.learning.jmx;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class Client {

	public static final String HOST = "localhost";
	public static final String PORT = "8888";

	//note need to start App.java with jvm args
	//-Dcom.sun.management.jmxremote.port=8888 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false

	public static void main(String[] args) throws IOException, MalformedObjectNameException {
		JMXServiceURL url =
				new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + HOST + ":" + PORT + "/jmxrmi");

		JMXConnector jmxConnector = JMXConnectorFactory.connect(url);
		MBeanServerConnection mbeanServerConnection = jmxConnector.getMBeanServerConnection();
		//ObjectName should be same as your MBean name
		ObjectName mbeanName = new ObjectName("org.learning.jmx:type=SystemInfo");

		//Get MBean proxy instance that will be used to make calls to registered MBean
		SystemInfoMBean mbeanProxy =
				(SystemInfoMBean) MBeanServerInvocationHandler.newProxyInstance(
						mbeanServerConnection, mbeanName, SystemInfoMBean.class, true);

		//let's make some calls to mbean through proxy and see the results.
		System.out.println("Current SystemConfig::" + mbeanProxy.doConfig());
		
		Proxy.newP
		mbeanProxy.setSchemaName("NewSchema");
		mbeanProxy.setThreadCount(5);

		System.out.println("New SystemConfig::" + mbeanProxy.doConfig());

		//let's terminate the mbean by making thread count as 0
		mbeanProxy.setThreadCount(0);

		//close the connection
		jmxConnector.close();
	}

}
