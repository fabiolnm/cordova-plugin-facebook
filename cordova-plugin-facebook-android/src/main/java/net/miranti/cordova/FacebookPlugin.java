package net.miranti.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;

public class FacebookPlugin extends CordovaPlugin {
	public interface Service {
		boolean login();
	}
	Service service;
	
	public FacebookPlugin() {
		service = new FacebookService();
	}
	
	public FacebookPlugin(Service service) {
		this.service = service;
	}
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callback) {
		try {
			return (Boolean) getClass().getDeclaredMethod(action, 
			JSONArray.class, CallbackContext.class)
			.invoke(this, args, callback);
		} catch (Exception e) {
			callback.error(e.getClass().getSimpleName());
			return false;
		}
	}

	boolean login(JSONArray args, CallbackContext callback) {
		boolean res = service.login();
		if (res) {
			res = true;
			callback.success();
		} else {
			callback.error("failed");
		}
		return res;
	}
}