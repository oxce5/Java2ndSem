public class Expense {
  private double totalCost, finalAmount;
  private String storeName;
  private int receiptID;

  private static final double TAX = 0.12;

  /**
   * @param totalCost
   * @param finalAmount
   * @param storeName
   * @param receiptID
   */
  public Expense(double totalCost, double finalAmount, String storeName, int receiptID) {
    this.totalCost = totalCost;
    this.finalAmount = finalAmount;
    this.storeName = storeName;
    this.receiptID = receiptID;
  }

  private double computeTax(double totalCost) {
    return totalCost * TAX;
  }

  public double finalAmount(double totalCost) {
    return totalCost + computeTax(totalCost);
  }

  /**
   * @return the totalCost
   */
  public double getTotalCost() {
    return totalCost;
  }

  /**
   * @param totalCost the totalCost to set
   */
  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }

  /**
   * @return the storeName
   */
  public String getStoreName() {
    return storeName;
  }

  /**
   * @return the finalAmount
   */
  public double getFinalAmount() {
    return finalAmount;
  }

  /**
   * @param finalAmount the finalAmount to set
   */
  public void setFinalAmount() {
    this.finalAmount = getTotalCost() + (getTotalCost() * getTax());
  }

  /**
   * @param storeName the storeName to set
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  /**
   * @return the receiptID
   */
  public int getReceiptID() {
    return receiptID;
  }

  /**
   * @return the tax
   */
  private double getTax() {
    return TAX;
  }
}
