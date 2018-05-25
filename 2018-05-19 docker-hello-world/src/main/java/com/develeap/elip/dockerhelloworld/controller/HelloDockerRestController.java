package com.develeap.elip.dockerhelloworld.controller;

import static com.develeap.elip.dockerhelloworld.controller.ServerVars.SERVERNAME;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.develeap.elip.dockerhelloworld.model.ServerFileDTO;

@RestController
public class HelloDockerRestController {

	private FileOperations fileManager;
	private ServerOperations serverManager;

	@Autowired
	public HelloDockerRestController(FileOperations fileManager, ServerOperations serverManager) {
		super();
		this.fileManager = fileManager;
		this.serverManager = serverManager;

	}

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		String servFolder = serverManager.getServFolder();
		String servName = serverManager.getServName();
		String servIp = serverManager.getIp();
		String servFolderPath = serverManager.getFolderPath();
		System.out.println(servFolder);
		System.out.println(servFolderPath);
		modelAndView.addObject("name", servName);
		modelAndView.addObject("ip", servIp);
		System.out.println("IP: " + servIp);
		System.out.println(System.getenv(SERVERNAME));
		List<File> filesList = fileManager.getStorageFilesFromDir(servFolderPath);
		List<ServerFileDTO> filesDao = filesList.stream().map(ServerFileDTO::entitleFile).collect(Collectors.toList());
		modelAndView.addObject("listOfFiles", filesDao);
		modelAndView.addObject("ip", servIp);
		// System.out.println(modelAndView.getModel().get("listOfFiles"));
		return modelAndView;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String updateFile(@RequestBody String responseBody) {
		System.out.println(responseBody);
		try {
			fileManager.updateLogOnServer(serverManager, responseBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "OK";
	}

	@GetMapping("/rere")
	public String home() {
		String body = "big data\n" + "is very important\n" + "in modern world";
		try {
			fileManager.fakeLogOnServer(serverManager, body);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "OK";
	}

}
