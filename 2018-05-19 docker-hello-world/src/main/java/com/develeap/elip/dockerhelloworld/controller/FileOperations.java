package com.develeap.elip.dockerhelloworld.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileOperations {
	List<File> getStorageFilesFromDir(String sFolder);

	void updateLogOnServer(ServerOperations server, String body) throws IOException;

	void fakeLogOnServer(ServerOperations server, String body) throws IOException;

}
