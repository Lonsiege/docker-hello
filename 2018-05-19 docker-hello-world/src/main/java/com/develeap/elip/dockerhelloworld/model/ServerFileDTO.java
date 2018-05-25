package com.develeap.elip.dockerhelloworld.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ServerFileDTO {
	public String name;
	public String content;

	public ServerFileDTO(String name, String content) {
		super();
		this.name = name;
		this.content = content;
	}

	public static ServerFileDTO entitleFile(File file) {
		String name = file.getName();
		String content = "";

		// try (BufferedReader reader = new BufferedReader(new
		// InputStreamReader(new FileInputStream(file)))) {
		// content =
		// reader.lines().collect(Collectors.joining(System.lineSeparator()));
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		try {
			content = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ServerFileDTO(name, content);
	}

	@Override
	public String toString() {
		return "ServerFileDAO [name=" + name + ", content=" + content + "]";
	}

}
