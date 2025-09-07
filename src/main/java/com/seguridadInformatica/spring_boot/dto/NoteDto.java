package com.seguridadInformatica.spring_boot.dto;

import jakarta.validation.constraints.NotBlank;

public class NoteDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    // Getters y setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}