package com.develeap.elip.dockerhelloworld.controller;

import static com.develeap.elip.dockerhelloworld.controller.ServerVars.FILE_SUFFIX;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FileOperationsImpl implements FileOperations {

	@Override
	public List<File> getStorageFilesFromDir(String sFolder) {
		List<File> res = new LinkedList<>();

		File[] filesArr = new File(sFolder).listFiles((dir, name) -> name.endsWith(FILE_SUFFIX));
		if (filesArr == null) {
			return res;
		}

		if (filesArr.length > 0) {
			List<File> folderFiles = Arrays.asList(filesArr);
			res.addAll(folderFiles);
		}

		return res;
	}

	@Override
	public void updateLogOnServer(ServerOperations server, String body) throws IOException {
		Path folderPath = Paths.get(server.getFolderPath());
		Path finalFile = folderPath.resolve(server.getServName() + FILE_SUFFIX);
		Path newFilePath;

		if (Files.notExists(folderPath)) {
			Files.createDirectories(folderPath);
		}
		if (Files.notExists(finalFile)) {
			newFilePath = Files.createFile(finalFile);
			System.out.println("New file created: " + newFilePath);
			System.out.println("New File exits: " + Files.exists(newFilePath));
		} else {
			newFilePath = finalFile.toAbsolutePath();
			System.out.println("Old File exits: " + Files.exists(newFilePath));
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFilePath.toString(), true))) {
			bw.write(body);
			bw.newLine();
		}
	}

	@Override
	public void fakeLogOnServer(ServerOperations server, String body) throws IOException {
		Path folderPath = Paths.get(server.getFolderPath());
		Path finalFile = folderPath.resolve("fake-data-storage.txt");
		Path newFilePath;

		if (Files.notExists(folderPath)) {
			Files.createDirectories(folderPath);
		}
		if (Files.notExists(finalFile)) {
			newFilePath = Files.createFile(finalFile);
			System.out.println("New file created: " + newFilePath);
			System.out.println("New File exits: " + Files.exists(newFilePath));
		} else {
			newFilePath = finalFile.toAbsolutePath();
			System.out.println("Old File exits: " + Files.exists(newFilePath));
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFilePath.toString(), true))) {
			bw.write(body);
			bw.newLine();
		}

	}

}
