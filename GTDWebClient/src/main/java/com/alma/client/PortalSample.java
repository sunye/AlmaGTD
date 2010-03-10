/* 
 * GWT-Ext Widget Library 
 * Copyright 2007 - 2008, GWT-Ext LLC., and individual contributors as indicated 
 * by the @authors tag. See the copyright.txt in the distribution for a 
 * full listing of individual contributors. 
 * 
 * This is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation; either version 3 of 
 * the License, or (at your option) any later version. 
 * 
 * This software is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * Lesser General Public License for more details. 
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this software; if not, write to the Free 
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org. 
 */  
   
  
 package com.alma.client; 
  
import com.google.gwt.core.client.EntryPoint;  
import com.gwtext.client.core.Function;  
import com.gwtext.client.data.*;  
import com.gwtext.client.widgets.MessageBox;  
import com.gwtext.client.widgets.Panel;  
import com.gwtext.client.widgets.Tool;  
import com.gwtext.client.widgets.Viewport;  
import com.gwtext.client.widgets.grid.ColumnConfig;  
import com.gwtext.client.widgets.grid.ColumnModel;  
import com.gwtext.client.widgets.grid.GridPanel;  
import com.gwtext.client.widgets.layout.ColumnLayoutData;  
import com.gwtext.client.widgets.layout.FitLayout;  
import com.gwtext.client.widgets.portal.Portal;  
import com.gwtext.client.widgets.portal.PortalColumn;  
import com.gwtext.client.widgets.portal.Portlet;  
  
public class PortalSample {  
  
    public static Panel getPanel() {  
        Panel panel = new Panel();  
        panel.setBorder(false);  
        panel.setPaddings(15);  
        panel.setLayout(new FitLayout());  
  
        //create some shared tools  
        Tool gear = new Tool(Tool.GEAR, new Function() {  
            public void execute() {  
                MessageBox.alert("Message", "The Settings Tool was clicked");  
            }  
        });  
  
        Tool close = new Tool(Tool.CLOSE, new Function() {  
            public void execute() {  
                MessageBox.alert("Message", "The Settings Tool was clicked");  
            }  
        });  
  
        Tool[] tools = new Tool[]{gear, close};  
  
        //create a portal  
        Portal portal = new Portal();  
  
        //create portal columns  
        PortalColumn firstCol = new PortalColumn();  
        firstCol.setPaddings(10, 10, 0, 10);  
  
        //add portlets to portal columns  
        Portlet gridPortlet = new Portlet();  
        gridPortlet.setTitle("Grid in a Portlet");  
        gridPortlet.setLayout(new FitLayout());  
        gridPortlet.setTools(tools);  
        GridPanel grid = getSampleGrid();  
        grid.setFrame(false);  
        gridPortlet.add(grid);  
        firstCol.add(gridPortlet);  
  
        Portlet portlet = new Portlet("Another Panel 1", getShortBogusMarkup(), tools);  
        firstCol.add(portlet);  
  
        //add portal column to portal  
        portal.add(firstCol, new ColumnLayoutData(.33));  
  
        //another column  
        PortalColumn secondCol = new PortalColumn();  
        secondCol.setPaddings(10, 10, 0, 10);  
        secondCol.add(new Portlet("Panel 2", getShortBogusMarkup(), tools));  
        secondCol.add(new Portlet("Another Panel 2", getShortBogusMarkup(), tools));  
        portal.add(secondCol, new ColumnLayoutData(.33));  
  
        //third column  
        PortalColumn thirdCol = new PortalColumn();  
        thirdCol.setPaddings(10, 10, 0, 10);  
        thirdCol.add(new Portlet("Panel 3", getShortBogusMarkup(), tools));  
        thirdCol.add(new Portlet("Another Panel 3", getShortBogusMarkup(), tools));  
        portal.add(thirdCol, new ColumnLayoutData(.33));  
  
        panel.add(portal);  
  
        Viewport viewport = new Viewport(panel);  
        return panel;
    }  
  
    private static String getShortBogusMarkup() {  
        return "<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed metus nibh, sodales a, porta at, vulputate eget, dui.  In pellentesque nisl non sem. Suspendisse nunc sem, pretium eget, cursus a, fringilla vel, urna.";  
    }  
  
    private static GridPanel getSampleGrid() {  
        RecordDef recordDef = new RecordDef(  
                new FieldDef[]{  
                        new StringFieldDef("company"),  
                        new FloatFieldDef("price"),  
                        new FloatFieldDef("change"),  
                        new FloatFieldDef("pctChange"),  
                        new DateFieldDef("lastChanged", "n/j h:ia"),  
                        new StringFieldDef("symbol"),  
                        new StringFieldDef("industry")  
                }  
        );  
  
        GridPanel grid = new GridPanel();  
  
        Object[][] data = getCompanyData();  
        MemoryProxy proxy = new MemoryProxy(data);  
  
        ArrayReader reader = new ArrayReader(recordDef);  
        Store store = new Store(proxy, reader);  
        store.load();  
        grid.setStore(store);  
  
  
        ColumnConfig[] columns = new ColumnConfig[]{  
                //column ID is company which is later used in setAutoExpandColumn  
                new ColumnConfig("Company", "company", 160, true, null, "company"),  
                new ColumnConfig("Change", "change", 45),  
                new ColumnConfig("% Change", "pctChange", 65)  
        };  
  
        ColumnModel columnModel = new ColumnModel(columns);  
        grid.setColumnModel(columnModel);  
  
        grid.setFrame(true);  
        grid.setStripeRows(true);  
        grid.setAutoExpandColumn("company");  
  
        grid.setWidth(600);  
        grid.setTitle("Company Grid");  
        return grid;  
    }  
  
    private static Object[][] getCompanyData() {  
        return new Object[][]{  
                new Object[]{"3m Co", new Double(71.72), new Double(0.02),  
                        new Double(0.03), "9/1 12:00am", "MMM", "Manufacturing"},  
                new Object[]{"Alcoa Inc", new Double(29.01), new Double(0.42),  
                        new Double(1.47), "9/1 12:00am", "AA", "Manufacturing"},  
                new Object[]{"Altria Group Inc", new Double(83.81), new Double(0.28),  
                        new Double(0.34), "9/1 12:00am", "MO", "Manufacturing"},  
                new Object[]{"American Express Company", new Double(52.55), new Double(0.01),  
                        new Double(0.02), "9/1 12:00am", "AXP", "Finance"},  
                new Object[]{"American International Group, Inc.", new Double(64.13), new Double(0.31),  
                        new Double(0.49), "9/1 12:00am", "AIG", "Services"},  
                new Object[]{"AT&T Inc.", new Double(31.61), new Double(-0.48),  
                        new Double(-1.54), "9/1 12:00am", "T", "Services"},  
                new Object[]{"Boeing Co.", new Double(75.43), new Double(0.53),  
                        new Double(0.71), "9/1 12:00am", "BA", "Manufacturing"},  
                new Object[]{"Caterpillar Inc.", new Double(67.27), new Double(0.92),  
                        new Double(1.39), "9/1 12:00am", "CAT", "Services"},  
                new Object[]{"Citigroup, Inc.", new Double(49.37), new Double(0.02),  
                        new Double(0.04), "9/1 12:00am", "C", "Finance"},  
                new Object[]{"E.I. du Pont de Nemours and Company", new Double(40.48), new Double(0.51),  
                        new Double(1.28), "9/1 12:00am", "DD", "Manufacturing"}  
        };  
    }  
}  