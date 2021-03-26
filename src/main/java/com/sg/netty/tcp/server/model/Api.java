package com.sg.netty.tcp.server.model;

import java.util.Map;

public class Api {

	private Map<String, Info> api;

	public Map<String, Info> getApi() {
		return api;
	}

	public void setApi(Map<String, Info> api) {
		this.api = api;
	}

	public static class Info {
		private String url;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}
}
