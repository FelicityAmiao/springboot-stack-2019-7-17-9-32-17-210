package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.CaseInfo;
import com.tw.apistackbase.entity.CriminalCase;
import com.tw.apistackbase.entity.Procuratorate;
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

    @Autowired
    private ProcuratorateRepository procuratorateRepository;

    @Test
    public void should_throw_exception_when_call_save_given_null_time() {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setCaseName("Case1");
        criminalCase.setProcuratorate(new Procuratorate());
        assertThrows(Exception.class, () -> criminalCaseRepository.saveAndFlush(criminalCase));
    }

    @Test
    public void should_return_case_when_find_by_id(){
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setCaseName("Case2");
        criminalCase.setProcuratorate(new Procuratorate());
        criminalCase.setCaseOccurrenceTime(Calendar.getInstance().getTimeInMillis());

        CriminalCase criminalCase2 = new CriminalCase();
        criminalCase2.setCaseName("Case3");
        criminalCase2.setProcuratorate(new Procuratorate());
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
        Procuratorate procuratorate = new Procuratorate();
        procuratorate.setName("P1 name");
        criminalCase.setProcuratorate(procuratorate);
        criminalCase.setCaseOccurrenceTime(Calendar.getInstance().getTimeInMillis());

        CriminalCase criminalCase2 = new CriminalCase();
        criminalCase2.setCaseName("Case3");
        criminalCase2.setProcuratorate(procuratorate);
        criminalCase2.setCaseOccurrenceTime(Calendar.getInstance().getTimeInMillis() + 1);

        criminalCaseRepository.save(criminalCase);
        criminalCaseRepository.save(criminalCase2);

        List<CriminalCase> result = criminalCaseRepository.findByOrderByCaseOccurrenceTimeDesc();

        assertEquals(criminalCase2, result.get(0));
        assertEquals(criminalCase, result.get(1));

    }

    @Test
    public void should_cases_when_find_by_case_name(){

        Procuratorate procuratorate = new Procuratorate();
        procuratorate.setName("P2 name");
        procuratorateRepository.save(procuratorate);

        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setCaseName("Case");
        criminalCase.setProcuratorate(procuratorate);
        criminalCase.setCaseOccurrenceTime(Calendar.getInstance().getTimeInMillis());
        criminalCaseRepository.save(criminalCase);

        CriminalCase criminalCase2 = new CriminalCase();
        criminalCase2.setCaseName("Case");
        criminalCase2.setProcuratorate(procuratorate);
        criminalCase2.setCaseOccurrenceTime(Calendar.getInstance().getTimeInMillis() + 1);

        criminalCaseRepository.save(criminalCase2);

        List<CriminalCase> result = criminalCaseRepository.findByCaseName("Case");

        assertEquals(2, result.size());

    }

    @Test
    public void should_deleteId_when_call_delete_by_id() {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setCaseName("Case");
        Procuratorate procuratorate = new Procuratorate();
        procuratorate.setName("Pxxxx");
        criminalCase.setProcuratorate(procuratorate);
        criminalCase.setCaseOccurrenceTime(Calendar.getInstance().getTimeInMillis());

        CriminalCase criminalCase2 = new CriminalCase();
        criminalCase2.setCaseName("Case");
        criminalCase2.setProcuratorate(procuratorate);
        criminalCase2.setCaseOccurrenceTime(Calendar.getInstance().getTimeInMillis() + 1);

        criminalCaseRepository.save(criminalCase);
        criminalCaseRepository.save(criminalCase2);

        criminalCaseRepository.deleteById(criminalCase.getId());

        assertEquals(1, criminalCaseRepository.findAll().size());
    }

    @Test
    public void should_cases_with_caseInfo_when_call_findByCaseId_given_id() {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setCaseName("Case");
        criminalCase.setCaseOccurrenceTime(Calendar.getInstance().getTimeInMillis());
        CaseInfo caseInfo = new CaseInfo();
        caseInfo.setObjectiveCondition("This is objective msg");
        caseInfo.setSubjectiveCondition("This is subjective msg");
        criminalCase.setCaseInfo(caseInfo);

        criminalCaseRepository.save(criminalCase);
        CriminalCase criminalCase1 = criminalCaseRepository.findById(criminalCase.getId()).get();

        assertEquals(caseInfo, criminalCase1.getCaseInfo());
    }


}