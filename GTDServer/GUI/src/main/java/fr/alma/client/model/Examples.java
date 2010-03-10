/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package fr.alma.client.model;

import com.google.gwt.user.client.Window;

public class Examples {

  public static boolean isExplorer() {
    String test = Window.Location.getPath();
    if (test.indexOf("pages") != -1) {
      return false;
    }
    return true;
  }

  public static final String SERVICE = "service";
  public static final String FILE_SERVICE = "fileservice";
  public static final String MODEL = "model";

}