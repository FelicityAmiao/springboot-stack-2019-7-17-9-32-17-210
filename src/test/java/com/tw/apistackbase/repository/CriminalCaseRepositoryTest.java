package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.CriminalCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.List;

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

    @Test
    public void should_return_case_when_find_by_id(){
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setCaseName("Case2");
        criminalCase.setCaseOccurrenceTime(Calendar.getInstance().getTimeInMillis());

        CriminalCase criminalCase2 = new CriminalCase();
        criminalCase2.setCaseName("Case3");
        criminalCase2.setCaseOccurrenceTime(Calendar.getInstance().getTimeInMillis());

        criminalCaseRepository.save(criminalCase);
        criminalCaseRepository.save(criminalCase2);

        CriminalCase result = criminalCaseRepository.findById(criminalCase.getId()).get();

        assertSame(criminalCase, result);

    }

    @Test
    public void should_desc_cases_when_find_by_order_by_time(){

        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setCaseName("Case2");
        criminalCase.setCaseOccurrenceTime(Calendar.getInstance().getTimeInMillis());

        CriminalCase criminalCase2 = new CriminalCase();
        criminalCase2.setCaseName("Case3");
        criminalCase2.setCaseOccurrenceTime(Calendar.getInstance().getTimeInMillis() + 1);

        criminalCaseRepository.save(criminalCase);
        criminalCaseRepository.save(criminalCase2);

        List<CriminalCase> result = criminalCaseRepository.findByOrderByCaseOccurrenceTimeDesc();

        assertEquals(criminalCase2, result.get(0));
        assertEquals(criminalCase, result.get(1));

    }



}