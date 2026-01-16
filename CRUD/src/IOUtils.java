public class Item {
  private String name;
  private int value;

  public Item(String name, int value) {
    this.name = name;
    this.value = value;
  }

  private void SetName(String name) { this.name = name; }
  private void SetValue(int value) { this.value = value; }
  
  private String GetName() { return this.name; }
  private int GetValue() { return this.value; }
}
