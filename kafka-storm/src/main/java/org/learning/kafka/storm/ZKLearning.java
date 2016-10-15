package org.learning.kafka.storm;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class ZKLearning {

	public static void main(String[] ags) throws Exception{
		
<<<<<<< HEAD
		CuratorFramework connPool = CuratorFrameworkFactory.newClient("flo-oms-app1.stage.ch.flipkart.com:2181",new ExponentialBackoffRetry(1000, 3));
=======
		CuratorFramework connPool = CuratorFrameworkFactory.newClient("localhost:2181",new ExponentialBackoffRetry(1000, 3));
>>>>>>> 7d1351d97eae4ba2a033021139ca67d2087f40a6
		connPool.start();
		Stat  stat = connPool.checkExists().forPath("/newtest");
		if(stat != null){
			System.out.println("deleting");
			connPool.delete().forPath("/newtest");
		}
		String msg = connPool.create().forPath("/test","data".getBytes());
		System.out.println(msg);
		connPool.delete().inBackground().forPath("/test");
		msg = connPool.create().withMode(CreateMode.EPHEMERAL).forPath("/newtest","data".getBytes());
		System.out.println(msg);
		byte[] data = connPool.getData().forPath("/newtest");
		System.out.println(new String(data));
		
		connPool.close();
	}
}
