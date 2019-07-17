package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Procurator;
import com.tw.apistackbase.entity.Procuratorate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProcuratorateRepositoryTest {

    @Autowired
    private ProcuratorateRepository procuratorateRepository;

    @Autowired
    private ProcuratorRepository procuratorRepository;

    @Test
    public void should_return_procuratorate_with_procurator_call_find_all() {
        Procurator procurator = new Procurator();
        procurator.setProcuratorName("PTor Name");
        Procurator procurator1 = new Procurator();
        procurator1.setProcuratorName("PTor2 Name");
        List<Procurator> list = Arrays.asList(procurator, procurator1);
        procuratorRepository.save(procurator);
        procuratorRepository.save(procurator1);

        Procuratorate procuratorate = new Procuratorate();
        procuratorate.setName("PR Name");
        procuratorate.setProcurators(list);
        procuratorateRepository.save(procuratorate);

        assertEquals(1, procuratorateRepository.findAll().size());
        assertEquals("PTor Name", procuratorateRepository.findAll().get(0).getProcurators().get(0).getProcuratorName());

    }

}