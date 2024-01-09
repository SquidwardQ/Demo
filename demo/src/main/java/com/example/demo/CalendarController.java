package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

public class CalendarController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    ZonedDateTime auxDate;

    ZonedDateTime auxDate2 = dateFocus;

    int currentDate;

    int numberOfWeek;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        numberOfWeek = today.getDayOfMonth() / 5;
        drawCalendar();
    }

    @FXML
    void backOneWeek(ActionEvent event) {
        auxDate2 = dateFocus;
        dateFocus = dateFocus.minusWeeks(1);
        /*if(numberOfWeek == 0)
            numberOfWeek = 4;
        else
            numberOfWeek -= 1;*/


        if(dateFocus.getMonth() != auxDate2.getMonth() && auxDate2.getDayOfMonth() == 1){

            int monthMaxDate = dateFocus.getMonth().maxLength();
            //Check for leap year
            if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
                monthMaxDate = 28;
            }
            dateFocus = ZonedDateTime.of(dateFocus.getYear(),dateFocus.getMonthValue(),monthMaxDate,0,0,0,0,dateFocus.getZone());
        }
        else if(dateFocus.getMonth() != auxDate2.getMonth()){
            dateFocus = ZonedDateTime.of(auxDate2.getYear(),auxDate2.getMonthValue(),1,0,0,0,0,dateFocus.getZone());
        }
        calendar.getChildren().clear();
        drawCalendar();

    }

    @FXML
    void forwardOneWeek(ActionEvent event) {
        auxDate2 = dateFocus;
        dateFocus = dateFocus.plusWeeks(1);
        /*if(numberOfWeek == 4)
            numberOfWeek = 0;
        else
            numberOfWeek += 1;*/
        if(dateFocus.getMonth() != auxDate2.getMonth()){

            int monthMaxDate = dateFocus.getMonth().maxLength();
            //Check for leap year
            if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
                monthMaxDate = 28;
            }
            dateFocus = ZonedDateTime.of(dateFocus.getYear(),dateFocus.getMonthValue(),1,0,0,0,0,dateFocus.getZone());
        }

        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar(){
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        //List of activities for a given month
        //Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }

        boolean checkDate = false;


        while(!checkDate){
            if(dateFocus.getDayOfMonth() == 1)
                checkDate = true;
            else if(dateFocus.getDayOfWeek().getValue() == 1)
                checkDate = true;

            else
                dateFocus = dateFocus.minusDays(1);
        }
        int dayWrite = 1;
        auxDate = dateFocus;
        if(auxDate.getDayOfMonth() == 1) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                //int calculatedDate = (j+1)+(7*numberOfWeek);

                //System.out.println(dateFocus);




                if (auxDate.getDayOfWeek().getValue() == (j + 1)) {
                    System.out.println("AuxDate = "+auxDate.toString());
                    System.out.println("dateFocus = "+dateFocus.toString());

                    Text date = new Text(String.valueOf(dayWrite));
                    dayWrite += 1;
                    double textTranslationY = -(rectangleHeight / 2) * 0.75;
                    date.setTranslateY(textTranslationY);
                    stackPane.getChildren().add(date);
                    auxDate = auxDate.plusDays(1);
                }


                if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                    rectangle.setStroke(Color.BLUE);
                }


                /*if(dateFocus){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        /*List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        if(calendarActivities != null){
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }*/
                calendar.getChildren().add(stackPane);

            }
        }else {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                //int calculatedDate = (j+1)+(7*numberOfWeek);

                //System.out.println(dateFocus);


                if (auxDate.getDayOfWeek().getValue() == (j + 1) && auxDate.getMonth() == dateFocus.getMonth()) {
                    Text date = new Text(String.valueOf(auxDate.getDayOfMonth()));
                    double textTranslationY = -(rectangleHeight / 2) * 0.75;
                    date.setTranslateY(textTranslationY);
                    stackPane.getChildren().add(date);
                }


                if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                }

                /*if(dateFocus){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        /*List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        if(calendarActivities != null){
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }*/
                calendar.getChildren().add(stackPane);


                auxDate = auxDate.plusDays(1);
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();

        }
    }


    private void createCalendarActivity(List<CalendarActivity> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        for (int k = 0; k < calendarActivities.size(); k++) {
            if(k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    //On ... click print all activities for given date
                    System.out.println(calendarActivities);
                });
                break;
            }
            Text text = new Text(calendarActivities.get(k).getClientName() + ", " + calendarActivities.get(k).getDate().toLocalTime());
            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                //On Text clicked
                System.out.println(text.getText());
            });
        }
        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:GRAY");
        stackPane.getChildren().add(calendarActivityBox);
    }

    private Map<Integer, List<CalendarActivity>> createCalendarMap(List<CalendarActivity> calendarActivities) {
        Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

        for (CalendarActivity activity: calendarActivities) {
            int activityDate = activity.getDate().getDayOfMonth();
            if(!calendarActivityMap.containsKey(activityDate)){
                calendarActivityMap.put(activityDate, List.of(activity));
            } else {
                List<CalendarActivity> OldListByDate = calendarActivityMap.get(activityDate);

                List<CalendarActivity> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return  calendarActivityMap;
    }

    private Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<CalendarActivity> calendarActivities = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();

        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            ZonedDateTime time = ZonedDateTime.of(year, month, random.nextInt(27)+1, 16,0,0,0,dateFocus.getZone());
            calendarActivities.add(new CalendarActivity(time, "Hans", 111111));
        }

        return createCalendarMap(calendarActivities);
    }
}