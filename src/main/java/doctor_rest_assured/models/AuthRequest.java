package doctor_rest_assured.models;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Builder
public class AuthRequest {
    private String email;
    private String password;
    private String phoneNumber; // Используется только для регистрации
}