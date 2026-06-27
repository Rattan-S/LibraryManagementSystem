package ca.sheridancollege.rattan.beans;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    private Long userid;
    @NonNull
    private String email;
    @NonNull
    private String encryptedPassword;
    @NonNull
    private Boolean enabled;
}