package doctor_rest_assured.models;
import lombok.*;


    @Getter
    @Setter
    @ToString
    @Builder
    public class AuthResponse{
        private String identityCookie; // Для хранения значения куки
    }



