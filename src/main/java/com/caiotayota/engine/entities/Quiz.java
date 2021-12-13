package com.caiotayota.engine.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Quiz {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotBlank(message = "Title is mandatory")
    private String title;
    
    @NotBlank(message = "Text is mandatory")
    private String text;
    
    @NotNull
    @Size(min = 2, message = "The quiz must have at least 2 answer options.")
    @ElementCollection
    private List<String> options;
    
    @NonNull
    @ElementCollection
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, value = "answer")
    private List<Integer> answers;
    
    @JsonIgnore
    private String email;
    
    public boolean checkAnswer(List<Integer> userAnswer) {
        return new HashSet<>(userAnswer).equals(new HashSet<>(answers));
    }
}
