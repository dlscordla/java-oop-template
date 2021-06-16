package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] savedBook = new SchoolBook[count() + 1];
        System.arraycopy(schoolBooks, 0, savedBook, 0, schoolBooks.length);
        savedBook[schoolBooks.length] = book;
        schoolBooks = savedBook;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] findByNameBooks = new SchoolBook[0];
        int i = 0;
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                SchoolBook[] dummyArray = new SchoolBook[i + 1];
                System.arraycopy(findByNameBooks, 0, dummyArray, 0, i);
                dummyArray[i] = book;
                findByNameBooks = dummyArray;
                i++;
            }
        }
        return findByNameBooks;
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name).length == 0) {
            return false;
        } else {
            SchoolBook[] dummyArray = new SchoolBook[count() - findByName(name).length];
            int i = 0;
            for (SchoolBook schoolBookInDatabase : schoolBooks) {
                if (!schoolBookInDatabase.getName().equals(name)) {
                    dummyArray[i] = schoolBookInDatabase;
                    i++;
                }
            }
            schoolBooks = dummyArray;
            return true;
        }
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
