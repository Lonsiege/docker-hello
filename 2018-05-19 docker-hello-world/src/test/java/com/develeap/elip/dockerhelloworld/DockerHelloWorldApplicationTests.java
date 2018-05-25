package com.develeap.elip.dockerhelloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.develeap.elip.dockerhelloworld.controller.FileOperations;
import com.develeap.elip.dockerhelloworld.controller.ServerOperations;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DockerHelloWorldApplicationTests {

	@Autowired
	private FileOperations fileManager;
	@Autowired
	private ServerOperations serverManager;

	@Test
	public void contextLoads() {
		System.out.println(serverManager.getServName());
		System.out.println(serverManager.getServFolder());
		System.out.println(serverManager.getFolderPath());
	}

}
