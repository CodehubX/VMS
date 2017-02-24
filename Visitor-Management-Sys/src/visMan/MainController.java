package visMan;

import visMan.utils.Utils;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainController implements Initializable {
	@FXML VBox mainRoot;
	@FXML Button goButton;
	@FXML
	void submitGo(){
		System.out.println(Utils.getToggleText(check) + " " + Utils.getToggleText(user));
        mainRoot.setDisable(true);
		try
		{
			// load the FXML resource
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewUser.fxml"));
            Parent newUserRoot = (Parent) loader.load();
            Scene newUser = new Scene(newUserRoot);
            NewUserController controller = (NewUserController) loader.getController();
//            control.initData(selectedSong);
            Stage stager = new Stage();
            stager.setScene(newUser);
            stager.setTitle("Checkin New User");
            stager.showAndWait();
//			stager.setOnCloseRequest((new EventHandler<WindowEvent>() {
//				public void handle(WindowEvent we)
//				{
//					controller.setClosed();
//				}
//			}));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	@FXML ToggleGroup user;
	@FXML ToggleGroup check;

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		/*---------------------Initalizations--------------------------------------------*/
		for(Toggle rb : user.getToggles()){
			RadioButton r = (RadioButton) rb;
			r.setVisible(false);
		}
		check.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
//				System.out.println(getToggleText(newValue));
				boolean visibleValue=true;
				if(Utils.getToggleText(newValue).equals("Checkout")){
					visibleValue=false;
				}
				for(Toggle rb : user.getToggles()){
					RadioButton r = (RadioButton) rb;
					r.setVisible(visibleValue);
				}
				if(Utils.getToggleText(user).equals("null") && visibleValue){
					goButton.setDisable(true);
				}
				if(!visibleValue)
					goButton.setDisable(visibleValue);
			}
		});
		user.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
			if(!Utils.getToggleText(newValue).equals("null")){
				goButton.setDisable(false);
			}
			}
		});
		
//		user.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
//			@Override
//			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
//				// TODO Auto-generated method stub
//			       RadioButton chk = (RadioButton)newValue.getToggleGroup().getSelectedToggle(); // Cast object to radio button
//			        System.out.println("Selected Radio Button - "+chk.getText());	
//			}
//		});
		
	}
	
}