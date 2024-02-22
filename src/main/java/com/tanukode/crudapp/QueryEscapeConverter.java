package com.tanukode.crudapp;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;


public class QueryEscapeConverter implements Converter<String, String>{
    @Override
    public String convert(String input){
        String[] parts = input.split("\\s+");

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equalsIgnoreCase("FROM") && i + 1 < parts.length) {
                // Escape the table name with quotation marks
                parts[i + 1] = "\"" + parts[i + 1] + "\"";
            }
        }

        return String.join(" ", parts);
    }
}
