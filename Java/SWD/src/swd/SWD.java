/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swd;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author inoob
 */
public class SWD extends Application {
    
    Stage edit_firm;
    Stage del_firm;
    
    VBox vbox;
    HBox hbox;
    
    MenuBar mbar;
    Menu m_file;
    MenuItem mi_close;
    
    Menu m_edit;
    MenuItem mi_add_firm;
    MenuItem mi_edit_firm;
    MenuItem mi_del_firm;
    
    MenuItem mi_add_service;
    MenuItem mi_edit_service;
    MenuItem mi_del_service;
    
    Menu m_create;
    MenuItem mi_create_invoice;
    MenuItem mi_create_report;
    
    ToolBar tbar_menu;
        
    Button btn_add_firm;
    Button btn_edit_firm;
    
    Image img_add_firm;
    Image img_edit_firm;
    
    CreatePDF pdf;
    
    public void initControls(){
        //Инициализация макета компонентов
        vbox = new VBox();
        hbox = new HBox();
        
        //Инициализация компонентов
        mbar = new MenuBar();
        m_file = new Menu("Файл");
        mi_close = new MenuItem("Завершить Работу");
        
        m_edit = new Menu("Редактирование");
        mi_add_firm = new MenuItem("Добавить новую организацию");
        mi_edit_firm = new MenuItem("Редактирование организации");
        mi_del_firm = new MenuItem("Удаление организации");
        mi_add_service = new MenuItem("Добавить Услугу");
        mi_edit_service = new MenuItem("Редактировать услуги");
        mi_del_service = new MenuItem("Удаление услуги");
        
        m_create = new Menu("Документ");
        mi_create_invoice = new MenuItem("Счет-Фактура"); 
        mi_create_report = new MenuItem("Акт выполненых работ");
        
        ToolBar tbar_menu;
        
        btn_add_firm = new Button();
        btn_edit_firm = new Button();
        
        img_add_firm = new Image(getClass().getResourceAsStream("add.png"));
        img_edit_firm = new Image(getClass().getResourceAsStream("edit.png"));
        
        pdf = new CreatePDF();
    }
    
    public void showAddFirm(){
        //событие на добавление фирмы
        AddFirm add_firm = new AddFirm();
        Stage stage_add_firm = new Stage();
        
        add_firm.start(stage_add_firm);
    }
    
    public void showEditFirm(Stage stage){
        //событие на редактирование фирмы
        edit_firm = new EditFirm(stage);
        //Stage stage_edit_firm = new Stage();
        edit_firm.sizeToScene();
        edit_firm.show();
    }
    
    public void showDelFirm(Stage stage){
        //событие на удаление фирмы
        del_firm = new DelFirm(stage);
        del_firm.sizeToScene();
        del_firm.show();
    }
    
    public void setControl(){
        
        mi_close = MenuItemBuilder.create()
                .text("Завершение работы")
                .accelerator(new KeyCodeCombination(KeyCode.Q,KeyCombination.CONTROL_DOWN))
                .onAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        try{
                            
                        }catch(Exception e){
                            
                        }
                    }
                })
                .build()
        ;
        m_file.getItems().add(mi_close);
        
        m_edit.getItems().addAll(mi_add_firm,mi_edit_firm,mi_del_firm,mi_add_service,mi_edit_service,mi_del_service);
        
        m_create.getItems().addAll(mi_create_invoice,mi_create_report);
        
        mbar.getMenus().addAll(m_file,m_edit,m_create);
        
        hbox.getChildren().addAll(btn_add_firm,btn_edit_firm);
        
        tbar_menu = new ToolBar(hbox);
        vbox.getChildren().addAll(mbar,tbar_menu);
        
        btn_add_firm.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try{
                    showAddFirm();
                }catch(Exception e){
                    e.printStackTrace();
                }      
            }
        });
        
        mi_add_firm.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try{
                    showAddFirm();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        
        mi_create_invoice.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try{
                    pdf.make_invoice();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        
        mi_create_report.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try{
                    pdf.make_report();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
         
    }
    
    public void setStyleControl(){
        hbox.setSpacing(5);
        
        btn_add_firm.setGraphic(new ImageView(img_add_firm));
        btn_edit_firm.setGraphic(new ImageView(img_edit_firm));
        
        
    }
    
    @Override
    public void start(Stage stage) {
        initControls();
        setControl();
        setStyleControl();
        
        Scene scene = new Scene(vbox,500,500);
        stage.setTitle("Автоматизированая Система Выписки Докуметов");
        stage.setScene(scene);
        
        
        btn_edit_firm.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try{
                    showEditFirm(stage);
                }catch(Exception e){
                }      
            }
        });
        
        mi_edit_firm.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try{
                    showEditFirm(stage);
                }catch(Exception e){
                }      
            }
        });
        
        mi_del_firm.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try{
                    showDelFirm(stage);
                }catch(Exception e){
                }      
            }
        });
        
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }   
    
}
