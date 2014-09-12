package swd;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DelFirm extends Stage{
    VBox vbox;
    HBox hbox;
    
    Label lbl_name_firm;
    
    Button btn_del_firm;
    Button btn_cancel;
    
    public void initControl(){
        vbox = new VBox();
        hbox = new HBox();
        
        lbl_name_firm = new Label("Вы уверены что хотите удалить Организацию?");
        
        btn_del_firm = new Button("Удалить организацию");
        btn_cancel = new Button("Отмена");
    }
    
    public void setControl(){
        vbox.getChildren().addAll(lbl_name_firm,hbox);
        hbox.getChildren().addAll(btn_del_firm,btn_cancel);
    }
    
    public void setStyleControl(){
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(5);
        hbox.setSpacing(5);
    }
    
    public DelFirm(Stage stage){
        super();
        initModality(Modality.APPLICATION_MODAL);
        initControl();
        setControl();
        setStyleControl();
        Scene scene = new Scene(vbox);
        setScene(scene);
    }
}