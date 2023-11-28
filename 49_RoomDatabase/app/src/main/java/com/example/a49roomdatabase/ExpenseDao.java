package com.example.a49roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Query("SELECT * FROM expense")
    List<Expense> getAllTransaction();

    @Insert
    void addTransaction(Expense expense);

    @Update
    void updateTransaction(Expense expense);

    @Delete
    void deleteTransaction(Expense expense);
}
