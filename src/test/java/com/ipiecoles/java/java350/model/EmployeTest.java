package com.ipiecoles.java.java350.model;




import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EmployeTest {

    //Date d'embauche dans le futur (2023) => 0
    @Test
    public void CheckDateEmbaucheFuture() {

        LocalDate d = LocalDate.now().plusYears(1);

        Employe e = new Employe("", "", "", d, 1000d, 1, 1.0);

        Integer dateNegatif = e.getNombreAnneeAnciennete();

        Assertions.assertThat(dateNegatif).isEqualTo(0);

    }

    //Date d'embauche dans le passé (2020) => 2
    @Test
    public void CheckDateEmbauchePasse() {

        LocalDate d2 = LocalDate.now().minusYears(2);

        Employe e = new Employe("", "", "", d2, 1000d, 1, 1.0);

        Integer dateEmbauche = e.getNombreAnneeAnciennete();

        Assertions.assertThat(dateEmbauche).isEqualTo(2);

    }

    //Date d'embauche en année en cours (2022) => 0
    @Test
    public void CheckDateEmbaucheEnCours() {

        LocalDate d3 = LocalDate.now();

        Employe e = new Employe("", "", "", d3, 1000d, 1, 1.0);

        Integer dateEmbaucheEnCours = e.getNombreAnneeAnciennete();

        Assertions.assertThat(dateEmbaucheEnCours).isEqualTo(0);
    }

    //Date d'embauche null => 0
    @Test
    public void CheckDateEmbaucheNull() {
        Employe e = new Employe("", "", "", null, 1000d, 1, 1.0);

        Integer dateEmbauchenull = e.getNombreAnneeAnciennete();

        Assertions.assertThat(dateEmbauchenull).isEqualTo(0);

    }

    @ParameterizedTest(name = "matricule {0}, ancienneté {1}, taux activité {2}, performance {3} => prime {4}")
    @CsvSource({
            "'M12345', 0, 1.0, 1, 1700.0", //1000 * 1.7 + 0 * 1 = 1700
            "'M12345', 2, 1.0, 1, 1900.0",
            "'T12345', 0, 1.0, 1, 1000.0",
            "'T12345', 0, 1.0, 3, 3300.0",//1000 * 1.7 + 0 * 1 = 1700
            ", 0, 1.0, 1, 1000.0", //(1000 + 0 * 100) * 1 = 1000
            "'T12345', 0, 1.0,, 1000.0"
    })
//Paramètres : matricule, ancienneté, taux d'activité, performance, prime
    public void testGetPrimeAnnuelle(
            String matricule,
            Integer nbAnneesAnciennete,
            Double tauxActivite,
            Integer performance,
            Double primeCalculee){
        //Given
        //Manager : matricule M12345 embauché cette année à plein temps
        Employe employe = new Employe("Doe", "John",
                matricule, LocalDate.now().minusYears(nbAnneesAnciennete), 2000d, performance, tauxActivite);
        //When
        Double prime = employe.getPrimeAnnuelle();
        //Then
        Assertions.assertThat(prime).isEqualTo(primeCalculee);
    }


}
