package evidence.pojistenych.spring.models.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class InsuranceRecordDTO {

    @NotBlank(message = "Vyplňte typ pojištění")
    private String pojisteni;


    @Positive(message = "Částká musí být vyplněna a kladné číslo")
    private double castka;

    @Size(min = 10, message = "Předmět musí mít alespoň 10 znaků")
    @NotBlank(message = "Vyplňte předmět")
    private String predmetPojisteni;

    @NotNull
    @FutureOrPresent(message = "Musí být datum v budoucnosti nebo dnešní")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date platnostOd;

    @NotNull
    @Future(message = "Musí být datum v budoucnosti")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date platnostDo;


    public String getPojisteni() {
        return pojisteni;
    }

    public void setPojisteni(String pojisteni) {
        this.pojisteni = pojisteni;
    }

    public double getCastka() {
        return castka;
    }

    public void setCastka(double castka) {
        this.castka = castka;
    }

    public String getPredmetPojisteni() {
        return predmetPojisteni;
    }

    public void setPredmetPojisteni(String predmetPojisteni) {
        this.predmetPojisteni = predmetPojisteni;
    }

    public Date getPlatnostOd() {
        return platnostOd;
    }

    public void setPlatnostOd(Date platnostOd) {
        this.platnostOd = platnostOd;
    }

    public Date getPlatnostDo() {
        return platnostDo;
    }

    public void setPlatnostDo(Date platnostDo) {
        this.platnostDo = platnostDo;
    }
}
