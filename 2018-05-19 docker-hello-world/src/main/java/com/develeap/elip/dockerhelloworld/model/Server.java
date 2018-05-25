package com.develeap.elip.dockerhelloworld.model;

import static com.develeap.elip.dockerhelloworld.controller.ServerVars.SERVERFOLDER;
import static com.develeap.elip.dockerhelloworld.controller.ServerVars.SERVERNAME;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.develeap.elip.dockerhelloworld.controller.ServerOperations;

@Component
public class Server implements ServerOperations {
	private WebApplicationContext context;

	@Value("${server.name}")
	String sName;

	@Value("${server.folder}")
	String sFolder;

	@Autowired
	public Server(WebApplicationContext context) {
		super();
		this.context = context;
	}

	@Override
	public String getFolderPath() {
		// return
		// this.context.getServletContext().getRealPath("/").concat(getServFolder());
		return getServFolder();
	}

	@Override
	public String getServFolder() {
		String sysFolder = System.getenv(SERVERFOLDER);
		String servFolder = (sysFolder != null) ? sysFolder : sFolder;
		return servFolder;
	}

	@Override
	public String getServName() {
		String sysName = System.getenv(SERVERNAME);
		String servName = (sysName != null) ? sysName : sName;
		return servName;
	}

	@Override
	public String getIp() {
		String res = "uknown IP";
		try {
			res = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return res;
	}

}
