package pl.eclinic.rest.recepcionist.visits;

import lombok.Data;

@Data
public class AddVisitsData {
    private Integer duration;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer firstHour;
    private Integer firstMinutes;
    private Integer quantityVisit;
    private Integer price;
}
