package com.ipiecoles.java.java350.model;




import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;

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


}
