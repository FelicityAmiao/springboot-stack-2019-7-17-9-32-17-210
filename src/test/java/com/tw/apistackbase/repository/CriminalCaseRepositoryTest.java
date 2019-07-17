package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.CriminalCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CriminalCaseRepositoryTest {

    @Autowired
    private CriminalCaseRepository criminalCaseRepository;

    @Test
    public void should_throw_exception_when_call_save_given_null_time() {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setCaseName("Case1");
        assertThrows(Exception.class, () -> criminalCaseRepository.saveAndFlush(criminalCase));
    }

}