public class Employee {
  private String name;
  private double hourlyRate, hoursWorked;

  Employee(String name, double hourlyRate, double hoursWorked) {
    this.name = name;
    this.hourlyRate = hourlyRate;
    this.hoursWorked = hoursWorked;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the hourlyRate
   */
  public double getHourlyRate() {
    return hourlyRate;
  }

  /**
   * @param hourlyRate the hourlyRate to set
   */
  public void setHourlyRate(double hourlyRate) {
    this.hourlyRate = hourlyRate;
  }

  /**
   * @return the hoursWorked
   */
  public double getHoursWorked() {
    return hoursWorked;
  }

  /**
   * @param hoursWorked the hoursWorked to set
   */
  public void setHoursWorked(double hoursWorked) {
    this.hoursWorked = hoursWorked;
  }
}
