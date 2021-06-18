package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            authors = Arrays.copyOf(authors, authors.length + 1);
            authors[authors.length - 1] = author;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author authorInDatabase : authors) {
            if (name.equals(authorInDatabase.getName()) && lastname.equals(authorInDatabase.getLastName())) {
                return authorInDatabase;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (authors == null) {
            return false;
        } else {
            for (Author authorInDatabase : authors) {
                if (findByFullName(authorInDatabase.getName(), authorInDatabase.getLastName()) != null) {
                    authors = Arrays.copyOf(authors, count() - 1);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}