package db;

import java.util.ArrayList;

public class Condition<T extends Comparable<T>>{
  public String title;
  public T lessThan;
  public T moreThan;

  public Condition(String title, T moreThan, T lessThan) {
    this.lessThan = lessThan;
    this.moreThan = moreThan;
    this.title = title;
  }

  public Table<T> filter(Table<T> t) {
    if(!t.table.containsKey(title)) {
      return t;
    }
    else {
//      String[] newTitle = new String[t.title.length - 1];
//      int index = 0;
//      for(int i = 0; i < t.title.length; i++) {
//        if(t.title[i] != title) {
//          newTitle[index++] = t.title[i];
//        }
//      }
      Table<T> tempTable = new Table<>(t.title);
      ArrayList<Integer> targetIndex = new ArrayList();
      for(int i = 0; i < t.nowRow; i++) {
        if ( (lessThan == null || ((Comparable)t.table.get(title).get(i)).compareTo((Comparable)lessThan)<0)
        && (moreThan == null ||((Comparable)t.table.get(title).get(i)).compareTo((Comparable)moreThan)>0)) {
          targetIndex.add(i);
        }
      }
      for(int i = 0; i < targetIndex.size(); i++) {
     //   T[] row =(T[]) new Object[t.title.length];
        T[] row =(T[]) new Comparable[t.title.length];
        for(int j = 0; j < t.title.length; j++) {
          row[j] = (T) t.table.get(t.title[j]).get(targetIndex.get(i));
        }
        tempTable.addRow(row);
      }
      return tempTable;
    }
  }


}