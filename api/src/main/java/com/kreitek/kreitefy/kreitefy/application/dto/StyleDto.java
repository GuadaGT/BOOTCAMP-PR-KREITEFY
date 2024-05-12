package com.kreitek.kreitefy.kreitefy.application.dto;

import java.io.Serializable;
import java.util.Objects;

public class StyleDto implements Serializable {
    private Long id;
    private String style;

    public StyleDto() {
    }

    public StyleDto(Long id, String style) {
        this.id = id;
        this.style = style;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StyleDto styleDto = (StyleDto) o;
        return Objects.equals(id, styleDto.id) && Objects.equals(style, styleDto.style);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, style);
    }

    @Override
    public String toString() {
        return "StyleDto{" +
                "id=" + id +
                ", style='" + style + '\'' +
                '}';
    }
}
