package server.types;

import static org.junit.Assert.*;

import java.awt.List;

import org.junit.Before;
import org.junit.Test;

import com.alma.server.types.DataManagerResult;

import java.util.ArrayList;

public class DataManagerResultTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testDataManagerResult() {
		ArrayList<String> LI = new ArrayList<String>();
		DataManagerResult<String> DMR = new DataManagerResult<String>();
		
	}

	@Test
	public final void testGetSetToodleDoList() {
		ArrayList<String> LI = new ArrayList<String>();
		LI.add("TD1");
		LI.add("TD2");
		DataManagerResult<String> DMR = new DataManagerResult<String>();
		DMR.setToodleDoList(LI);
		assertTrue(DMR.getToodleDoList().get(0).compareTo("TD1")==0);
		assertTrue(DMR.getToodleDoList().get(1).compareTo("TD2")==0);
	}

	@Test
	public final void testGetSetGTDList() {
		ArrayList<String> LI = new ArrayList<String>();
		LI.add("TD1");
		LI.add("TD2");
		DataManagerResult<String> DMR = new DataManagerResult<String>();
		DMR.setGTDList(LI);
		assertFalse(DMR.getToodleDoList().get(0).equals("TD1"));
		
	}


	@Test
	public final void testSetIsSynchronized() {
		DataManagerResult<String> DMR = new DataManagerResult<String>();
		DMR.setSynchronized(true);
		assertTrue(DMR.isSynchronized());
	}

	

}
