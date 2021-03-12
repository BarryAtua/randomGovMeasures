package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.*;

/**
 * @author TomasHyka
 */

public class Controller implements Initializable {
    public Label text;
    public Button generateButton;
    public Button quotes;
    public Button people;
    public Button dates;
    public Label ig;
    ArrayList peopleArray = new ArrayList();
    ArrayList peopleQuotesArray = new ArrayList();
    ArrayList quotesArray = new ArrayList();
    ArrayList datesArray = new ArrayList();
    Tooltip tooltip, tooltip2, tooltip3, tooltip4;
    Random rand = new Random();
    int year, month, day;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        peopleRead();
        peopleQuotesRead();
        quotesRead();
        datesRead();
        // Volani funkci.
        tooltip = new Tooltip();
        tooltip2 = new Tooltip();
        tooltip3 = new Tooltip();
        tooltip4 = new Tooltip();
        tooltip.setText("Vygeneruje náhodnou větu.");
        tooltip.setFont(Font.font(15));
        generateButton.setTooltip(tooltip);
        tooltip2.setText("Vygeneruje náhodné opatření ve stylu samostatné věty.");
        tooltip2.setFont(Font.font(10));
        quotes.setTooltip(tooltip2);
        tooltip3.setText("Vygeneruje náhodné opatření s náhodným autorem.");
        tooltip3.setFont(Font.font(10));
        people.setTooltip(tooltip3);
        tooltip4.setText("Vygeneruje náhodné opatření v závislosti na náhodné datum.");
        tooltip4.setFont(Font.font(10));
        dates.setTooltip(tooltip4);
         // Inicializace a nastaveni tooltipu k buttonum.

    }

    public void randomClicked(MouseEvent mouseEvent) {
        randomWrite();
        // Funkce pro generaci nahodnych vet ze vsech 3 variant.
    }

    public void quotesClicked(MouseEvent mouseEvent) {
        quotesWrite();
        // Funkce pro generaci nahodnych vet varianty 1.
    }

    public void peopleClicked(MouseEvent mouseEvent) {
        peopleWrite();
        // Funkce pro generaci nahodnych vet varianty 2.
    }

    public void datesClicked(MouseEvent mouseEvent) {
        datesWrite();
        // Funkce pro generaci nahodnych vet varianty 3.
    }


    public void peopleRead() {
        Scanner sc = new Scanner(getClass().getResourceAsStream("/people.txt"), "utf-8");
        while (sc.hasNextLine()) {
            peopleArray.add(sc.nextLine());
        }
        // Funkce pro nacteni a ulozeni hodnot z textoveho souboru do ArrayListu.
    }

    public void peopleQuotesRead() {
        Scanner sc = new Scanner(getClass().getResourceAsStream("/peopleQuotes.txt"), "utf-8");
        while (sc.hasNextLine()) {
            peopleQuotesArray.add(sc.nextLine());
        }
        // Funkce pro nacteni a ulozeni hodnot z textoveho souboru do ArrayListu.
    }

    public void quotesRead() {
        Scanner sc = new Scanner(getClass().getResourceAsStream("/quotes.txt"), "utf-8");
        while (sc.hasNextLine()) {
            quotesArray.add(sc.nextLine());
        }
        // Funkce pro nacteni a ulozeni hodnot z textoveho souboru do ArrayListu.
    }

    public void datesRead() {
        Scanner sc = new Scanner(getClass().getResourceAsStream("/dates.txt"), "utf-8");
        while (sc.hasNextLine()) {
            datesArray.add(sc.nextLine());
        }
        // Funkce pro nacteni a ulozeni hodnot z textoveho souboru do ArrayListu.
    }

    public void randomDate() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(gc.YEAR, 2021);
        int dayTmp = (1 + (int)Math.round(Math.random() * (gc.getActualMaximum(gc.DAY_OF_YEAR)-1)));
        gc.set(gc.DAY_OF_YEAR, dayTmp);
        month = gc.get(gc.MONTH) + 1;
        day = gc.get(gc.DAY_OF_MONTH);
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        if(month <= (calendar.get(Calendar.MONTH))+1)
        {
            year = 2022;
        } else
        {
            year = 2021;
        }
        /**
         * Funkce pro vygenerovani nahodneho data
         * a osetreniho toho, aby datum nebylo
         * mensi, nez je aktualni datum.
         */


    }

    public void quotesWrite() {
        int tmpQuotes = rand.nextInt(quotesArray.size());
        text.setText((quotesArray.get(tmpQuotes)).toString());

    }

    public void peopleWrite() {
        int tmpPeople = rand.nextInt(peopleArray.size());
        int tmpPeopleQuotes = rand.nextInt(peopleQuotesArray.size());
        text.setText((peopleArray.get(tmpPeople) + ": " + peopleQuotesArray.get(tmpPeopleQuotes)));
        // Funkce pro zapis nahodne vety z predpripraveneho ArrayListu do labelu
    }

    public void datesWrite() {
        int tmpDates = rand.nextInt(datesArray.size());
        randomDate();
        text.setText((datesArray.get(tmpDates) + " " + day + ". " + month + ". " + year));
        // Funkce pro zapis nahodne vety z predpripraveneho ArrayListu s nahodnym datem z funkce randomDate do labelu
    }

    public void randomWrite() {
        int tmpRandom = rand.nextInt(3);
        switch (tmpRandom){
            case 0:
                quotesWrite();
                break;
            case 1:
                peopleWrite();
                break;
            case 2:
                datesWrite();
                break;
        }
        // Funkce pro vygenerovani nahodne vety ze 3 hotovych funkci pro generaci vet
    }
}
