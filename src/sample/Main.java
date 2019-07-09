package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private final PIN[] pin = new PIN[8];
    private static char hexChar[] = {'F','E','D','C','B','A'};

    @Override
    public void start(Stage primaryStage){
        VBox vBox = new VBox();
        HBox root = new HBox();
        vBox.setAlignment(Pos.CENTER);

        final Label l = new Label("0x00");

        for (int i = 0; i < 8; i++) {

            pin[i]=new PIN(i);
            final int finalI = i;
            pin[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (pin[finalI].value == 0) {
                        pin[finalI].activate();
                    } else {
                        pin[finalI].deactivate();
                    }
                    l.setText(getHex() );
                }
            });
            vBox.getChildren().add(pin[i]);
        }
        vBox.setPadding(new Insets(20,20,20,20));
        l.setStyle("-fx-font-size: 40px;" +
                "-fx-padding: 50px");
        l.setAlignment(Pos.CENTER);
        root.setAlignment(Pos.CENTER_LEFT);
        root.getChildren().addAll(vBox,l);
        primaryStage.setTitle("B2X");
        primaryStage.setScene(new Scene(root,310,700));
        primaryStage.setResizable(false);


        primaryStage.show();

    }

    private String getHex() {
        int no=CHECK(),reminder;
        StringBuilder result = new StringBuilder();
        while(no>0){
            reminder = no % 16;
            if(reminder > 9){
                result.insert(0, hexChar[15 - reminder]);
            }else{
                result.insert(0, reminder);
            }
            no = no/16;
        }

        if (result.toString().toCharArray().length==0)
            result = new StringBuilder("0x00");
        else if (result.toString().toCharArray().length==1)
            result.insert(0, "0x0");
        else
            result.insert(0, "0x");

        return result.toString();
    }

    private int CHECK() {
        int l=0;
        for (int i = 0; i < 8; i++) {
                l+=pin[i].value;

        }
        return l;
    }


    public static void main(String[] args) {
        launch(args);
    }
}

class PIN extends Button{
     int value;
    private int PinNumber;

    PIN(int PinNumber){
        this.setText(PinNumber+"");
        this.PinNumber=PinNumber;
        deactivate();
    }

    void deactivate(){
        value = 0;

        this.setStyle("-fx-background-color: red;" +
                "-fx-padding: 30px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: white");
    }

    void activate(){
        int temp=1;
        if (PinNumber!=0) {
            for (int i = 0; i < PinNumber; i++) {
                temp*=2;
            }
        }else{
            value=1;
        }


        value=temp;
        System.out.println(value);
        this.setStyle("-fx-background-color: green;" +
                "-fx-padding: 30px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: white");
    }

}
