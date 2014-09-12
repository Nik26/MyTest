package swd;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditFirm extends Stage{
    GridPane gpane;
    HBox hbox;
    
    Label lbl_name;
    Label lbl_contract;
    Label lbl_rule;
    Label lbl_address;
    Label lbl_IIK;
    Label lbl_BIK;
    Label lbl_bank;
    
    TextField tf_name;
    TextField tf_contract;
    TextField tf_rule;
    TextField tf_address;
    TextField tf_IIN_BIN;
    TextField tf_IIK;
    TextField tf_BIK;
    TextField tf_bank;
    
    ToggleGroup group;
    RadioButton rb_IIN;
    RadioButton rb_BIN;
    
    Button btn_add;
    Button btn_cancel;
    
    public EditFirm(Stage stage){
        super();
        initModality(Modality.APPLICATION_MODAL);
        
        initControl();
        setControl();
        setStyleControl();
        
        Scene scene = new Scene(gpane);
        
        setTitle("Редактирование");
        setScene(scene);
    }
    
    public void initControl(){
        gpane = new GridPane();
        hbox = new HBox();
        
        lbl_name = new Label("Наименование ораганизации: ");
        tf_name = new TextField();
        
        lbl_contract = new Label("Договор №: ");        
        tf_contract = new TextField();
        
        lbl_rule = new Label("Условия оплаты по договору: ");
        tf_rule = new TextField();
        
        lbl_address = new Label("Адрес: ");
        tf_address = new TextField();
        
        group = new ToggleGroup();
        
        rb_IIN = new RadioButton("ИИН");
        rb_BIN = new RadioButton("БИН");
        tf_IIN_BIN = new TextField();
        
        lbl_IIK = new Label("ИИК: ");
        tf_IIK = new TextField();
        
        lbl_BIK = new Label("БИК: ");
        tf_BIK = new TextField();
        
        lbl_bank = new Label("Банк: ");
        tf_bank = new TextField();
        
        btn_add = new Button("Сохранить изменения");
        btn_cancel = new Button("Отмена");
    }
    
    public void setControl(){
        gpane.add(lbl_name, 0, 0);
        gpane.add(tf_name, 1, 0);
        
        gpane.add(lbl_contract, 0, 1);
        gpane.add(tf_contract, 1, 1);
        
        gpane.add(lbl_rule, 0, 2);
        gpane.add(tf_rule, 1, 2);
        
        
        gpane.add(lbl_address, 0, 3);
        gpane.add(tf_address, 1, 3);
        
        rb_IIN.setToggleGroup(group);
        rb_IIN.setSelected(true);
        rb_BIN.setToggleGroup(group);
        
        hbox.getChildren().addAll(rb_IIN,rb_BIN);
        gpane.add(hbox, 0, 4);
        gpane.add(tf_IIN_BIN, 1, 4);
        
        gpane.add(lbl_IIK, 0, 5);
        gpane.add(tf_IIK,1,5);
        
        gpane.add(lbl_BIK,0,6);
        gpane.add(tf_BIK,1,6);
        
        gpane.add(lbl_bank, 0, 7);
        gpane.add(tf_bank, 1, 7);
        
        gpane.add(btn_add, 0, 8);
        gpane.add(btn_cancel,1,8);
    }
    
    public void setStyleControl(){
        gpane.setPadding(new Insets(10));
        gpane.setVgap(5);
        
        tf_name.setMinWidth(300);
        tf_contract.setMinWidth(300);
        tf_rule.setMinWidth(300);
        tf_address.setMinWidth(300);
        tf_IIN_BIN.setMinWidth(300);
        tf_IIK.setMinWidth(300);
        tf_BIK.setMinWidth(300);
        tf_bank.setMinWidth(300);
    }
}