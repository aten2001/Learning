package org.learning.kafka.storm;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class ZKLearning {

	public static void main(String[] ags) throws Exception{
		
		CuratorFramework connPool = CuratorFrameworkFactory.newClient("flo-oms-app1.stage.ch.flipkart.com:2181",new ExponentialBackoffRetry(1000, 3));
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
