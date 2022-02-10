package com.ipiecoles.java.java350.repository;

import com.ipiecoles.java.java350.model.Employe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class EmployeRepositoryTest {

    @Autowired
    EmployeRepository employeRepository;

    @BeforeEach
    @AfterEach
    public void deleteInBDD() {
        employeRepository.deleteAll();
    }

    @Test
    public void testFindLastMatricule3Employes(){
        //Given
        Employe e1 = new Employe("Doe", "John", "M44444", LocalDate.now(), 2000d, 1, 1.0);
        Employe e2 = new Employe("Doe", "Jane", "C66666", LocalDate.now(), 2000d, 1, 1.0);
        Employe e3 = new Employe("Doe", "Paul", "T22222", LocalDate.now(), 2000d, 1, 1.0);
        employeRepository.save(e1);
        employeRepository.save(e2);
        employeRepository.save(e3);
        //When
        String lastMatricule = employeRepository.findLastMatricule();
        //Then
        Assertions.assertThat(lastMatricule).isEqualTo("66666");
    }

    @Test
    public void testFindLastMatricule0Employe(){
        //Given
        //When
        String lastMatricule = employeRepository.findLastMatricule();
        //Then
        Assertions.assertThat(lastMatricule).isNull();
    }

    @Test
    public void testAvgPerformanceWhereMatriculeStartsWith() {

        Employe e1 = new Employe("Doe", "John", "M44444", LocalDate.now(), 2000d, 1, 1.0);
        Employe e2 = new Employe("Doe", "Jane", "C66666", LocalDate.now(), 2000d, 2, 1.0);
        Employe e3 = new Employe("Doe", "Paul", "C22222", LocalDate.now(), 2000d, 3, 1.0);
        employeRepository.save(e1);
        employeRepository.save(e2);
        employeRepository.save(e3);

        Double avgPerf = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");

        Assertions.assertThat(avgPerf).isEqualTo(2.5d);
    }


    @Test
    public void testAvgPerformanceWhereMatriculeStartsWithWrongorNullLetter() {

        Employe e1 = new Employe("Doe", "John", "", LocalDate.now(), 2000d, 1, 1.0);
        Employe e2 = new Employe("Doe", "Jane", "B500000", LocalDate.now(), 2000d, 2, 1.0);
        employeRepository.save(e1);
        employeRepository.save(e2);

        Double avgPerf = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");

        Assertions.assertThat(avgPerf).isNull();
    }
    @Test
    public void testAvgPerformanceWhereMatriculeStartsWithNoEmploye() {


        Double avgPerf = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");

        Assertions.assertThat(avgPerf).isNull();
    }

}
