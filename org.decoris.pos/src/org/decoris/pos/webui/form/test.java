package org.decoris.pos.webui.form;

import java.util.Vector;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;
import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.ListDataEvent;


public class test extends GenericRichlet {
    //Richlet//
private static int numrighe = 56;
private static int numcolonne = 10;
  String[][] model = new String[numrighe][numcolonne];
  Grid gd;
  MyModel mm;
  int conta = 0;
  private class MyModel extends AbstractListModel{ 
      String[][] mymodel = new String[numrighe][numcolonne];
      int nr;
      int nc;
      public MyModel(int nr, int nc) {
         mymodel = new String[nr][nc];
     this.nr = nr;
     this.nc = nc;
      }
      public String getValueAt(int r, int c) {
         return mymodel[r][c];
      }
      public Object getElementAt(int r) {
         return mymodel[r];
      }
      public int getSize() {
         return nr;
      }
      public String getSortDirection(java.util.Comparator cmp) {
         return "natural";
      }
      public void sort(java.util.Comparator cmp, boolean asc) {
      }
      public void fireTableRowsInserted(int start, int end) {
         System.out.println("====>FTROWSINSERTED ["+start+"] ["+end+"]");
         fireEvent(ListDataEvent.INTERVAL_ADDED, start, end);
      }
      public void fireTableRowsDeleted(int start, int end) {
         System.out.println("====>FTROWSDELETED ["+start+"] ["+end+"]");
         fireEvent(ListDataEvent.INTERVAL_REMOVED, start, end);
      }
    }
    public class MyRowRenderer implements RowRenderer { 
    Vector righe = new Vector();
    boolean vf;
    public MyRowRenderer (boolean vf) { 
       this.vf = vf;
       for(int j = 0; j < model.length; ++j)
           righe.add(model[j]);
    }
    public void render(Row row, java.lang.Object data, int k) {
       String []datav = (String[])data;
       for (int i = 0; i < datav.length; i++) {
            Cell hb = null;
            Textbox tb = null;
            hb = new Cell();
            tb = new Textbox();
            tb.setText(new Integer(conta).toString() + "-" +
                       new Integer(k).toString()+ "-" +
                       new Integer(i).toString());
            tb.setParent(hb);
            hb.setParent(row);
            if (vf) {
               hb.setHflex("1");
               tb.setHflex("1");
            } else {
               hb.setWidth("100%");
               tb.setWidth("120px");
            }
        tb.setHeight("30px");
        hb.setHeight("30px");
        row.setHeight("30px");
        }
     }
   }
   public void service(Page p) {
           final Desktop dsk = Executions.getCurrent().getDesktop();
           final Page page = p;
           Window w = null;
           int topwin = 30;
           w = new Window("LoadGrid", "overlapped", true);
           w.setHeight("600px");
           w.setWidth("800px");
           w.setStyle("position:absolute;border:solid 1px;");
           w.setTop(new Integer(topwin)+"px");
           w.setLeft("20px");
           w.setPage(page);
           topwin += 20;
           Columns cls = null;
           Column c = null;
           gd = new Grid();
           gd.setStyle("position:absolute;margin:4px;color:cyan;font-family:Gill Sans MT;font-size:14pt;");
           gd.setLeft("50px");
           gd.setTop("100px");
           gd.setWidth("650px");
           gd.setHeight("450px");
           gd.setParent(w);
           cls = new Columns();
           cls.setSizable(true);
           gd.appendChild(cls);
           for (int i = 0; i < numcolonne; i++) {
              c = new Column();
              c.setLabel(new Integer(i).toString());
              c.setParent(cls);
           }
           mm = new MyModel(numrighe, numcolonne);
           gd.setModel(mm);
           gd.setRowRenderer(new MyRowRenderer(false));
   }
}
