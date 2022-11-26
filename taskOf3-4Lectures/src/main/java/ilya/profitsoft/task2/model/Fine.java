package ilya.profitsoft.task2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.Objects;


/**
 * Class represents fine model from JSON file
 *
 * @author Ilya Panteleychuk
 * */
public class Fine {
    
    private LocalDateTime localDateTime;
    private String firstName;
    private String lastName;
    private String type;
    private double fineAmount;
    
    public Fine() {
    }
    
    public Fine(LocalDateTime localDateTime, String firstName,
                String lastName, String type, double fineAmount) {
        this.localDateTime = localDateTime;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.fineAmount = fineAmount;
    }
    
    @JsonProperty("date_time")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
    
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @JsonProperty("type")
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @JsonProperty("fine_amount")
    public double getFineAmount() {
        return fineAmount;
    }
    
    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }
    
    @Override
    public String toString() {
        return "Fine{" +
                "localDateTime=" + localDateTime +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", type='" + type + '\'' +
                ", fineAmount=" + fineAmount +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fine that = (Fine) o;
        return Double.compare(that.fineAmount, fineAmount) == 0
                && Objects.equals(localDateTime, that.localDateTime)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(type, that.type);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(localDateTime, firstName, lastName, type, fineAmount);
    }
}
