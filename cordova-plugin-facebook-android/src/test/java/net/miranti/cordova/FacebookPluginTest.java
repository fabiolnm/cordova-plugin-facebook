package net.miranti.cordova;

import static org.junit.Assert.*;

import org.apache.cordova.CallbackContext;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.json.JSONArray;
import org.junit.Test;

public class FacebookPluginTest {
    private Mockery context = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    
	private FacebookService facebook = context.mock(FacebookService.class); 
	private FacebookPlugin plugin = new FacebookPlugin(facebook);
	
	private JSONArray args = new JSONArray();
	private CallbackContext callback = context.mock(CallbackContext.class); 

	@Test 
	public void sussessfullLogin() {
		context.checking(new Expectations(){{
			allowing(facebook).login();
			will(returnValue(true));
			
			oneOf(callback).success();
		}});
		assertTrue(plugin.execute("login", args, callback));
	}
	
	@Test 
	public void failedLogin() {
		context.checking(new Expectations(){{
			allowing(facebook).login();
			will(returnValue(false));
			
			oneOf(callback).error("failed");
		}});
		assertFalse(plugin.execute("login", args, callback));
	}
	
	@Test 
	public void actionNotFound() {
		context.checking(new Expectations(){{
			oneOf(callback).error("NoSuchMethodException");
		}});
		assertFalse(plugin.execute("foo", args, callback));
	}
}
