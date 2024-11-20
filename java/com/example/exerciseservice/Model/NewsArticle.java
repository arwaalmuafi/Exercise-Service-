package com.example.exerciseservice.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotEmpty(message = "title connote be null")
    private String id;

    @NotEmpty(message = "title connote be null")
    @Size(min = 4, max = 100 ,message = "Maximum length of 100 characters.")
    private String title;

    @NotEmpty(message = "author connote be empty")
    @Size(min=4,max=20,message = "Must be more than 4 characters.\n" +
            "- Maximum length of 20 characters.")
    private String author;

    @NotEmpty(message = "Cannot be null.")
    @Size(min = 200,message = "must be more then 200")
    private String content;

    @NotEmpty(message = "Cannot be null")
    @Pattern(regexp = "^(politics|sports|technology)$" ,message = "Must be either \"politics\", \" sports\" or \" technology\" only.")
    private String category;

    @NotEmpty(message = "connote be null")
    private String imageUrl;
    private boolean isPublished=false;

    private LocalDate publishDate;



}
