public class Student {
  private String name, email;
  private double finalGrade;
  
  Student(String name, String email, double finalGrade) {
    this.name = name;
    this.email = email;
    this.finalGrade = finalGrade;
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
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the finalGrade
   */
  public double getFinalGrade() {
    return finalGrade;
  }

  /**
   * @param finalGrade the finalGrade to set
   */
  public void setFinalGrade(double finalGrade) {
    this.finalGrade = finalGrade;
  }
}
