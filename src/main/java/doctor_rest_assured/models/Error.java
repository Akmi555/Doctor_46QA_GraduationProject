package doctor_rest_assured.models;
import lombok.*;
@Getter
@Setter
@ToString
@Builder
public class Error {
    private Object message;
    private int id;

}
