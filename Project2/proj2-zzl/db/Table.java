package db;

import java.util.ArrayList;
import java.util.HashMap;

public class Table<T> {
  public HashMap<String, Column> table;
  public String[] title;
  public Integer nowRow;//show the number of rows of the table
//  public int nowCol;

  /**
   * Constructor
   * @param title String[]
   */
  public Table(String[] title) {
    table = new HashMap<>();
    this.title = title;
    for (int i = 0; i < title.length; i++) {
      table.put(this.title[i], new Column<T>());
    }
    nowRow = 0;
  }

  /**
   * Add a new row of the table
   * @param data
   */
  public void addRow(T[] data) {
    for (int i = 0; i < data.length; i++) {
      table.get(title[i]).put(nowRow, data[i]);
    }
    nowRow++;
  }

  /**
   * Print the table
   */
  public void printTable() {
    for (int i = 0; i < title.length; i++) {
      System.out.print(title[i] + " ");
    }
    System.out.println();
    for (int i = 0; i < nowRow; i++) {
      for (int j = 0; j < title.length; j++) {
        System.out.print(table.get(title[j]).get(i) + " ");
      }
      System.out.println();
    }
  }

  /**
   * Return true if the table t has same title with this table, otherwise return false
   * @param t Table<T></>
   * @return
   */
  public boolean hasCommon(Table<T> t) {
    for (int i = 0; i < title.length; i++) {
      if (t.table.containsKey(title[i])) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return an ArrayList which contains the common title of two tables
   * @param t
   * @return
   */
  public ArrayList<String> commonTitle(Table<T> t) {
    ArrayList<String> coTitle = new ArrayList<>();
    for(int i = 0; i < title.length; i++) {
      if(t.table.containsKey(title[i])) {
        coTitle.add(title[i]);
      }
    }
    return coTitle;
  }

  /**
   * Return a String array which is the title of the join table
   * @param t Table<T></>
   * @return
   */
  public String[] mergeTitle(Table<T> t) {
    ArrayList<String> coTitle = new ArrayList<>();
    ArrayList<String> uniqT1 = new ArrayList<>();
    ArrayList<String> uniqT2 = new ArrayList<>();
    for(int i = 0; i < title.length; i++) {
      if(t.table.containsKey(title[i])) {
        coTitle.add(title[i]);
      }
      else {
        uniqT1.add(title[i]);
      }
    }
    for(int j = 0; j < t.title.length; j++) {
      if(!table.containsKey(t.title[j])) {
        uniqT2.add(t.title[j]);
      }
    }
    int size = coTitle.size() + uniqT1.size() + uniqT2.size();
    String[] mergeStr = new String[size];
    for(int i = 0; i < coTitle.size(); i++) {
      mergeStr[i] = coTitle.get(i);
    }
    for(int i = coTitle.size(); i < coTitle.size() + uniqT1.size(); i++) {
      mergeStr[i] = uniqT1.get(i - coTitle.size());
    }
    for(int i = coTitle.size() + uniqT1.size(); i < size; i++) {
      mergeStr[i] = uniqT2.get(i - coTitle.size() - uniqT1.size());
    }
    return mergeStr;
  }

  /**
   * Return a array of T which is the row of the join table
   * @param t Table<T></>
   * @param index1 int, the join row number of this table
   * @param index2 int, the join row number of table t
   * @return a array of T
   */
  public T[] getJoinRow(Table<T> t, int index1, int index2) {
    String[] mergeTitle = this.mergeTitle(t);
    T[] joinRow = (T[]) new Object[mergeTitle.length];
    for (int i = 0; i < joinRow.length; i++) {
      if (this.table.containsKey(mergeTitle[i])) {
        joinRow[i] = (T)this.table.get(mergeTitle[i]).get(index1);
      } else {
        joinRow[i] = (T)t.table.get(mergeTitle[i]).get(index2);
      }
    }
    return joinRow;
  }
  public Table<T> cartesianJoin(Table<T> t) {
    int size = title.length + t.title.length;
    String[] joinTitle = new String[size];
    for(int i = 0; i < title.length; i++) {
      joinTitle[i] = title[i];
    }
    for(int i = title.length; i < joinTitle.length; i++) {
      joinTitle[i] = t.title[i - title.length];
    }
    Table<T> joinTable = new Table<>(joinTitle);
    for(int i = 0; i < t.nowRow; i++) {
      for(int j = 0; j < this.nowRow; j++) {
        T[] row = (T[]) new Object[joinTitle.length];
        for(int k = 0; k < title.length; k++) {
          row[k] = (T)this.table.get(joinTitle[k]).get(j);
        }
        for(int k = title.length; k < joinTitle.length; k++) {
          row[k] = (T)t.table.get(joinTitle[k]).get(i);
        }
        joinTable.addRow(row);
      }
    }
    return joinTable;
  }

  /**
   * Join two tables and return the join table
   * @param t Table<T></>
   * @return a new Table<T></>
   */
  public Table join(Table<T> t) {
    if (this.hasCommon(t)) {
      ArrayList<String> coTitle = this.commonTitle(t);
      ArrayList<Integer> firstValueIndex = new ArrayList<>();
      ArrayList<Integer> secondValueIndex = new ArrayList<>();
      for (int i = 0; i < nowRow; i++) {
        if (t.table.get(coTitle.get(0)).containsValue(table.get(coTitle.get(0)).get(i))) {
          int count = 1;
          int secondIndex = -1;
          for (int j = 0; j < t.nowRow; j++) {
            if (t.table.get(coTitle.get(0)).get(j) == table.get(coTitle.get(0)).get(i)) {
              secondIndex = j;
              for (int k = 1; k < coTitle.size(); k++) {
                if (t.table.get(coTitle.get(k)).get(secondIndex) == table.get(coTitle.get(k)).get(i)) {
                  count++;
                }
              }
              if (count == coTitle.size()) {
                firstValueIndex.add(i);
                secondValueIndex.add(secondIndex);
              }
            }
          }
//          for (int k = 1; k < coTitle.size(); k++) {
//            if (t.table.get(coTitle.get(k)).get(secondIndex) == table.get(coTitle.get(k)).get(i)) {
//              count++;
//            }
//          }
//          if (count == coTitle.size()) {
//            firstValueIndex.add(i);
//            secondValueIndex.add(secondIndex);
//          }
        }
      }
      String[] joinTitle = this.mergeTitle(t);
      Table joinTable = new Table(joinTitle);
      for(int i = 0; i < firstValueIndex.size(); i++) {
        joinTable.addRow(this.getJoinRow(t, firstValueIndex.get(i), secondValueIndex.get(i)));
      }
      return joinTable;
    } else {
      return this.cartesianJoin(t);
    }
  }




  public static void main(String[] args) {
    String[] title = {"x", "y","z"};
    String[] title1 = {"m", "t","x", "l"};
    Table<Integer> table = new Table<>(title);
    Integer[] row1 = {1, 2, 3};
    Integer[] row2 = {2, 2, 4};
    Integer[] row3 = {0, 3, 5};
    Integer[] row11 = {4, 3, 5};
    Integer[] row22 = {5, 3, 5};
    String[] row4 = {"x", "s", "dm", "q"};
    String[] row5 = {"y", "k", "bn", "q"};
    String[] row6 = {"m", "i", "ba", "t"};
    table.addRow(row22);
    table.addRow(row1);
    table.addRow(row2);
    table.addRow(row3);
    table.addRow(row11);
    Table<String> table1 = new Table<>(title1);
    table1.addRow(row4);
    table1.addRow(row5);
    table1.addRow(row6);
//    Table<Integer> t3 = table.join(table1);
    //t3.printTable();

    Condition<Integer> c1 = new Condition<>("x", 1, 4);
    table.printTable();
    System.out.println();
   c1.filter(table).printTable();
    System.out.println("");
   table1.printTable();
    System.out.println();
    Condition<String> c2 = new Condition<>("x", "a", "bm");
    c2.filter(table1).printTable();
  }
}







