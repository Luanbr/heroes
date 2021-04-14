package br.com.lbr.heroes.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.lang.String;

@Document
public record Hero(@MongoId String name, String biography, String gender, String universe, List<String> abilities) {}
