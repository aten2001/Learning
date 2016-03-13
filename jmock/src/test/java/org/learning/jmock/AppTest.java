package org.learning.jmock;

import mockit.Expectations;
import mockit.Mocked;

import org.testng.annotations.Test;

import bsh.classpath.BshClassPath.AmbiguousName;

/**
 * Unit test for simple App.
 */
@Test
public class AppTest  {
	
	
	@Test
	public void testCase1() {
		new Expectations() {{
			UserDAO.loadByUserNameAndPassword(anyString,anyString);
			result = null;
		}};
	}
}
